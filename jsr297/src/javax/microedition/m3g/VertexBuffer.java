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

package javax.microedition.m3g;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author jgasseli
 */
public class VertexBuffer extends Object3D
{
    private static final class ScaleBiasedVertexArray
    {

        private VertexArray array;
        private float scale;
        private final float[] bias = new float[3];

        public VertexArray getArray()
        {
            return this.array;
        }

        public float[] getBias()
        {
            return this.bias;
        }

        public float getScale()
        {
            return this.scale;
        }

        private static final void requireArrayNotNull(VertexArray array)
        {
            if (array == null)
            {
                throw new NullPointerException("array is null");
            }
        }

        private static final void requireValidBias(float[] bias, VertexArray array)
        {
            if (array == null || bias == null)
            {
                return;
            }

            if (bias.length < Math.min(3, array.getComponentCount()))
            {
                throw new IllegalArgumentException("bias.length < min(3, array.getComponentCount())");
            }
        }

        public void set(VertexArray array, float scale, float[] bias)
        {
            //apply the tests early to assert atomicity
            requireArrayNotNull(array);
            requireValidBias(bias, array);

            setArray(array);
            setScale(scale);
            setBias(bias);
        }

        public void setArray(VertexArray array)
        {
            requireArrayNotNull(array);

            this.array = array;
        }

        public void setBias(float[] bias)
        {
            requireValidBias(bias, this.array);
            
            Arrays.fill(this.bias, 0.0f);
            if (bias != null)
            {
                System.arraycopy(bias, 0,
                        this.bias, 0,
                        Math.min(3, array.getComponentCount()));
            }
        }

        public void setScale(float scale)
        {
            this.scale = scale;
        }
    }

    private boolean mutable = true;
    private int vertexCount;
    private final ScaleBiasedVertexArray positions = new ScaleBiasedVertexArray();
    private VertexArray normals;
    private VertexArray colors;
    private final ArrayList<ScaleBiasedVertexArray> textureCoordinates =
            new ArrayList<ScaleBiasedVertexArray>();
    
    public VertexBuffer()
    {

    }

    public void commit()
    {
        throw new UnsupportedOperationException();
    }

    public VertexArray getAttribute(String name, boolean signedNormalized[],
            float[] defaultValue)
    {
        throw new UnsupportedOperationException();
    }

    public int getAttributeCount()
    {
        throw new UnsupportedOperationException();
    }

    public String[] getAttributeNames()
    {
        throw new UnsupportedOperationException();
    }

    public VertexArray getBoneIndices()
    {
        throw new UnsupportedOperationException();
    }

    public VertexArray getBoneWeights()
    {
        throw new UnsupportedOperationException();
    }

    public VertexArray getColors()
    {
        throw new UnsupportedOperationException();
    }

    public int getDefaultColor()
    {
        throw new UnsupportedOperationException();
    }

    public float getDefaultPointSize()
    {
        throw new UnsupportedOperationException();
    }

    public VertexArray getNormals()
    {
        throw new UnsupportedOperationException();
    }

    public VertexArray getPositions(float[] scaleBias)
    {
        throw new UnsupportedOperationException();
    }

    public VertexArray getTexCoords(int index, float[] scaleBias)
    {
        throw new UnsupportedOperationException();
    }

    public int getVertexCount()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isMutable()
    {
        throw new UnsupportedOperationException();
    }

    public void setAttribute(String name, float v0, float v1, float v2, float v3)
    {
        throw new UnsupportedOperationException();
    }

    public void setAttribute(String name, VertexArray attributes, boolean signed, boolean normalized)
    {
        throw new UnsupportedOperationException();
    }

    public void setBoneInfluences(VertexArray boneIndices, VertexArray boneWeights)
    {
        throw new UnsupportedOperationException();
    }

    public void setColors(VertexArray colors)
    {
        throw new UnsupportedOperationException();
    }

    public void setDefaultColor(int argb)
    {
        throw new UnsupportedOperationException();
    }

    public void setDefaultPointSize(float pointSize)
    {
        throw new UnsupportedOperationException();
    }

    public void setNormals(VertexArray normals)
    {
        throw new UnsupportedOperationException();
    }

    public void setPointSizes(VertexArray pointSizes)
    {
        throw new UnsupportedOperationException();
    }

    public void setPositions(VertexArray positions, float scale, float[] bias)
    {
        this.positions.set(positions, scale, bias);
    }

    public void setTexCoords(int index, VertexArray texCoords, float scale, float[] bias)
    {
        throw new UnsupportedOperationException();
    }
}
