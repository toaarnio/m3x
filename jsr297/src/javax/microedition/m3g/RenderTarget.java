/*
 * Copyright (c) 2009-2010, Jacques Gasselin de Richebourg
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

package javax.microedition.m3g;

/**
 * This is a modified version of RenderTarget. Used here to illustrate a more
 * type safe interface for Graphics3D.bind
 * @author jgasseli
 */
public class RenderTarget extends AbstractRenderTarget
{
    public RenderTarget(Image2D target, int miplevel)
    {

    }

    public RenderTarget(ImageCube target, int face, int miplevel)
    {

    }
    
    @Override
    public int getWidth()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getHeight()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getContentScale()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isDepthBuffered()
    {
        return false;
    }

    public boolean isStencilBuffered()
    {
        return false;
    }

    public Renderer bindRenderer()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void releaseRenderer()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
