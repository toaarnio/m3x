/*
 * Copyright (c) 2008-2010, Jacques Gasselin de Richebourg
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package m3x.microedition.m3g.awt;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.microedition.m3g.Camera;
import javax.microedition.m3g.Transform;
import m3x.Vmath;
import m3x.microedition.m3g.TransformUtils;

/**
 * A turntable camera controller operating like the one in Blender 2.4+
 *
 * <p>The middle button, usually the wheel, is the trigger for reading input.
 * When it is depressed the controller will be affected as follows:
 * <ul>
 * <li>Default ->  Orbit, yaw driven by x-axis movement, pitch driven by y-axis movement.</li>
 * <li>Shift held -> Pan in the screen plane.</li>
 * <li>Control held -> Dolly in the screen z-axis, driven by y-axis movement.</li>
 * </ul>
 * Furthermore, scrolling the mouse wheel also affects the dolly. If there is no
 * middle button; it can be emulated by holding down Alt and the left mouse
 * button.</p>
 *
 * <p>See the <a href="http://www.blender.org/">blender homepage</a> for more
 * information</p>
 * 
 * @author jgasseli
 */
public class BlenderTurntableCameraController
        extends MouseAndKeyInputTransformController
{
    private static final float ROTATION_SPEED = 0.35f;
    private final Camera camera;
    
    private float yaw;
    private float pitch;
    private float dolly;

    private float x, y, z;
    private int mx, my;

    private boolean leftDown;
    private boolean wheelDown;

    /**
     * Indicates if the previous rotation ended with the positive
     * up axis pointing down, in which case the yaw direction is reversed.
     */
    private boolean isUpAxisDown;
    

    public BlenderTurntableCameraController(Camera camera, Component component)
    {
        this(camera, component, 0, 0, 20);
    }

    public BlenderTurntableCameraController(Camera camera, Component component,
                                            float yaw, float pitch, float dolly)
    {
        super(component);
        
        if (camera == null)
        {
            throw new NullPointerException("camera is null");
        }

        setTransform(new Transform());

        this.camera = camera;
        this.yaw = yaw;
        this.pitch = pitch;
        this.dolly = dolly;
    }

    public void getLookAtPosition(final float[] result)
    {
        if (result.length < 3)
        {
            throw new IllegalArgumentException();
        }

        result[0] = x;
        result[1] = y;
        result[2] = z;
    }
    
    @Override
    public synchronized void mouseDragged(MouseEvent e)
    {
        final int eventX = e.getX();
        final int eventY = e.getY();
        final int dx = eventX - mx;
        final int dy = eventY - my;
        mx = eventX;
        my = eventY;

        final boolean emulateWheelDown = (leftDown && e.isAltDown());
        if (emulateWheelDown || wheelDown)
        {
            //pan if shift is down
            if (e.isShiftDown())
            {
                //get the 3d space offset corresponding to the screen offset
                final float[] t = new float[4];
                Vmath.vload4(t, x, y, z, 1);
                final Transform localToCamera = new Transform(getTransform());
                localToCamera.invert();
                TransformUtils.screenToLocalOffset(camera,
                                                   localToCamera,
                                                   t,
                                                   -dx, -dy, 
                                                   getComponent().getWidth(),
                                                   getComponent().getHeight());

                //move the camera
                this.x = t[0];
                this.y = t[1];
                this.z = t[2];
            }
            else if (e.isControlDown())
            {
                //dolly
                dolly += dy * 0.02f;
            }
            else
            {
                yaw += isUpAxisDown ? dx * ROTATION_SPEED : -dx * ROTATION_SPEED;
                pitch += -dy * ROTATION_SPEED;
            }
        }
    }

    @Override
    public synchronized void mousePressed(MouseEvent e)
    {
        //reset the delta movement origin
        mx = e.getX();
        my = e.getY();

        switch (e.getButton())
        {
            case MouseEvent.BUTTON1:
            {
                leftDown = true;
                break;
            }
            case MouseEvent.BUTTON2:
            {
                wheelDown = true;
                break;
            }
        }
    }

    @Override
    public synchronized void mouseReleased(MouseEvent e)
    {
        switch (e.getButton())
        {
            case MouseEvent.BUTTON1:
            {
                leftDown = false;
                break;
            }
            case MouseEvent.BUTTON2:
            {
                wheelDown = false;
                break;
            }
        }

        //check if the up axis is pointing down. this information
        //is used during the next rotation to make sure points between
        //the lookat and the camera move in the same horizontal direction
        //as the cursor
        float wrappedPitch = pitch;
        if (pitch > 180.0f)
        {
            wrappedPitch = pitch - 360.0f * (int)((pitch + 180.0f) / 360.0f);
        }
        else if (pitch < -180.0f)
        {
            wrappedPitch = pitch - 360.0f * (int)((pitch - 180.0f) / 360.0f);
        }
        isUpAxisDown = wrappedPitch < -90.0f || wrappedPitch > 90.0f;
    }

    @Override
    public synchronized void mouseWheelMoved(MouseWheelEvent e)
    {
        dolly += e.getWheelRotation() * 0.25f;
    }

    @Override
    public synchronized void update(double seconds)
    {
        Transform t = getTransform();
        if (t != null)
        {
            t.setIdentity();
            t.postTranslate(x, y, z);
            t.postRotate(yaw, 0, 1, 0);
            t.postRotate(pitch, 1, 0, 0);
            t.postTranslate(0, 0, dolly);
        }
    }

    
}
