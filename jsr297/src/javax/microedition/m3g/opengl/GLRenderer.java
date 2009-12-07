/*
 * Copyright (c) 2008, Jacques Gasselin de Richebourg
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

package javax.microedition.m3g.opengl;

import javax.media.opengl.GL;
import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.Background;
import javax.microedition.m3g.IndexBuffer;
import javax.microedition.m3g.Renderer;
import javax.microedition.m3g.Transform;
import javax.microedition.m3g.VertexBuffer;

/**
 * @author jgasseli
 */
public class GLRenderer extends Renderer
{
    private GL instanceGL;
    private int width;
    private int height;

    private final Transform modelTransform = new Transform();
    
    public GLRenderer()
    {
    }

    public GL getGL()
    {
        return instanceGL;
    }

    public void bind(GL gl, int width, int height)
    {
        this.instanceGL = gl;
        this.width = width;
        this.height = height;
        
        clearCachedObjects();
    }

    public void release()
    {
        this.instanceGL = null;
    }

    /**
     * Clears any cached state objects
     */
    private void clearCachedObjects()
    {
        //TODO clear cached objects.
        //Used to lessen state changes in the renderer.
    }

    @Override
    public void clear(Background background)
    {
        int clearColorARGB = 0xff000000; //default to full alpha black
        int colorMask = 0xffffffff; //clear all color channels
        int clearStencil = 0;
        int stencilMask = 0xffffffff; //clear all stencil bits
        float clearDepth = 1.0f;
        int clearFlags = 0;

        if (background != null)
        {
            clearColorARGB = background.getColor();
            colorMask = background.getColorClearMask();

            clearStencil = background.getStencil();
            stencilMask = background.getStencilClearMask();
            
            clearDepth = background.getDepth();
            clearFlags |= (background.isDepthClearEnabled()
                    ? GL.GL_DEPTH_BUFFER_BIT : 0);
        }
        else
        {
            //clear all buffers if no background is given
            clearFlags |= GL.GL_DEPTH_BUFFER_BIT;
        }

        clearFlags |= (colorMask != 0 ? GL.GL_COLOR_BUFFER_BIT : 0);
        clearFlags |= (stencilMask != 0 ? GL.GL_STENCIL_BUFFER_BIT : 0);

        final boolean redMask = ((colorMask >> 16) & 0xff) != 0;
        final boolean greenMask = ((colorMask >> 8) & 0xff) != 0;
        final boolean blueMask = ((colorMask >> 0) & 0xff) != 0;
        final boolean alphaMask = ((colorMask >> 24) & 0xff) != 0;

        final float ubyteToFloat = 1.0f / 255;
        final float redColor = ubyteToFloat * ((clearColorARGB >> 16) & 0xff);
        final float greenColor = ubyteToFloat * ((clearColorARGB >> 8) & 0xff);
        final float blueColor = ubyteToFloat * ((clearColorARGB >> 0) & 0xff);
        final float alphaColor = ubyteToFloat * ((clearColorARGB >> 24) & 0xff);

        final GL gl = getGL();
        gl.glColorMask(redMask, greenMask, blueMask, alphaMask);
        gl.glClearColor(redColor, greenColor, blueColor, alphaColor);
        gl.glStencilMask(stencilMask);
        gl.glClearStencil(clearStencil);
        gl.glClearDepth(clearDepth);
        gl.glClear(clearFlags);
    }

    @Override
    public void setViewport(int x, int y, int width, int height)
    {
        final GL gl = getGL();
        //OpenGL uses lower-left as origin
        gl.glViewport(x, this.height - (y + height), width, height);
    }

    @Override
    public void render(VertexBuffer vertices, IndexBuffer primitives, Appearance appearance, Transform transform, int scope)
    {
        if (vertices == null)
        {
            throw new NullPointerException("vertices is null");
        }
        if (primitives == null)
        {
            throw new NullPointerException("primitives is null");
        }
        if (appearance == null)
        {
            throw new NullPointerException("appearance is null");
        }

        final GL gl = getGL();
        setModelTransform(transform);
        setAppearance(gl, appearance);
        setVertexBuffer(gl, vertices);
        render(gl, primitives);
    }

    private void setModelTransform(Transform transform)
    {
        if (transform != null)
        {
            this.modelTransform.set(transform);
        }
        else
        {
            this.modelTransform.setIdentity();
        }
    }

    private void setAppearance(GL gl, Appearance appearance)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void setVertexBuffer(GL gl, VertexBuffer vertices)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void render(GL gl, IndexBuffer primitives)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    
}
