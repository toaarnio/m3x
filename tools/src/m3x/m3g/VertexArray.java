/**
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

package m3x.m3g;

import java.util.List;
import m3x.m3g.primitives.SectionSerializable;
import m3x.m3g.primitives.ObjectTypes;
import java.io.IOException;
import java.lang.reflect.Array;
import m3x.m3g.primitives.Serializable;


/**
 * See http://java2me.org/m3g/file-format.html#VertexArray<br>
  Byte          componentSize;<br>
  Byte          componentCount;<br>
  Byte          encoding;<br>
  UInt16        vertexCount;<br>
  FOR each vertex...<br>
    IF componentSize==1, THEN<br>
        IF encoding==0, THEN<br>
              Byte[componentCount] components;<br>
        ELSE IF encoding==1, THEN<br>
              Byte[componentCount] componentDeltas;<br>
        END<br>
    ELSE<br>
        IF encoding==0, THEN<br>
              Int16[componentCount] components;<br>
        ELSE IF encoding==1, THEN<br>
              Int16[componentCount] componentDeltas;<br>
        END<br>
    END<br>
  END<br>
  <br>
 * @author jsaarinen
 * @author jgasseli
 */
public class VertexArray extends Object3D implements SectionSerializable
{
    public static final int BYTE = 1;
    public static final int SHORT = 2;

    public static final int ENCODING_RAW = 0;
    public static final int ENCODING_DELTA = 1;

    private static final void requireValidFirstVertex(final int firstVertex)
    {
        if (firstVertex < 0)
        {
            throw new IllegalArgumentException("firstVertex < 0");
        }
        if (firstVertex > 65535)
        {
            throw new IllegalArgumentException("firstVertex > 65535");
        }
    }

    private static final void requireValidVertexCount(final int vertexCount)
    {
        if (vertexCount <= 0)
        {
            throw new IllegalArgumentException("vertexCount <= 0");
        }
        if (vertexCount > 65535)
        {
            throw new IllegalArgumentException("vertexCount > 65535");
        }
    }

    private static final void requireValidComponentType(final int componentType)
    {
        if (componentType < BYTE)
        {
            throw new IllegalArgumentException("componentType < BYTE");
        }
        if (componentType > SHORT)
        {
            throw new IllegalArgumentException("componentType > SHORT");
        }
    }

    private static final void requireValidComponentCount(final int componentCount)
    {
        if (componentCount < 1)
        {
            throw new IllegalArgumentException("componentCount < 1");
        }
        if (componentCount > 4)
        {
            throw new IllegalArgumentException("componentCount > 4");
        }
    }

    private static abstract class Values implements Serializable
    {
        private int vertexCount;
        private int componentCount;
        private int encoding;

        protected Values(int vertexCount, int componentCount)
        {
            this.vertexCount = vertexCount;
            this.componentCount = componentCount;
        }

        protected static final <T> void requireArrayNotNull(T array)
        {
            if (array == null)
            {
                throw new NullPointerException("array is null");
            }
        }

        private final void requireSize(int numVertices, int size)
        {
            requireValidVertexCount(numVertices);
            final int neededSize = numVertices * getComponentCount();
            if (size < neededSize)
            {
                throw new IllegalArgumentException("size too small: "
                    + size + " where " + neededSize + " is needed");
            }
        }

        protected final <T> void requireArrayLength(int numVertices,
            T array)
        {
            requireArrayNotNull(array);
            requireSize(numVertices, Array.getLength(array));
        }

        protected final <T> void requireListSize(int numVertices, List<T> values)
        {
            requireArrayNotNull(values);
            requireSize(numVertices, values.size());
        }

        protected final void requireSourceLength(int firstVertex, int numVertices)
        {
            requireValidFirstVertex(firstVertex);
            requireValidVertexCount(numVertices);
            if (firstVertex + numVertices > getVertexCount())
            {
                throw new IllegalArgumentException(
                    "firstVertex + numVertices > getVertexCount()");
            }
        }

        public final int getComponentCount()
        {
            return componentCount;
        }

        public final int getEncoding()
        {
            return encoding;
        }

        public abstract int getComponentType();

        public final int getVertexCount()
        {
            return vertexCount;
        }

        public final void setEncoding(int encoding)
        {
            this.encoding = encoding;
        }

        public abstract void set(int firstVertex, int numVertices, List<? extends Number> values);
        public abstract void set(int firstVertex, int numVertices, byte[] values);
        public abstract void set(int firstVertex, int numVertices, short[] values);
    }

    private static class ByteValues extends Values
    {
        private byte[] values;

        public ByteValues(int vertexCount, int componentCount)
        {
            super(vertexCount, componentCount);
            values = new byte[vertexCount * componentCount];
        }

        @Override
        public int getComponentType()
        {
            return VertexArray.BYTE;
        }

        public void get(int firstVertex, int numVertices, byte[] dst)
        {
            requireSourceLength(firstVertex, numVertices);
            requireArrayLength(numVertices, dst);

            final int offset = firstVertex * getComponentCount();
            final int length = numVertices * getComponentCount();
            System.arraycopy(values, offset,
                dst, 0, length);
        }

        @Override
        public void set(int firstVertex, int numVertices, List<? extends Number> values)
        {
            requireListSize(numVertices, values);
            
            final int size = values.size();
            byte[] src = new byte[size];
            for (int i = 0; i < size; ++i)
            {
                src[i] = values.get(i).byteValue();
            }

            set(firstVertex, numVertices, src);
        }

        public void set(int firstVertex, int numVertices, byte[] values)
        {
            requireSourceLength(firstVertex, numVertices);
            requireArrayLength(numVertices, values);

            final int offset = firstVertex * getComponentCount();
            final int length = numVertices * getComponentCount();
            System.arraycopy(values, 0,
                this.values, offset, length);
        }

        public void set(int firstVertex, int numVertices, short[] values)
        {
            throw new IllegalStateException("BYTE array only supports byte[] values");
        }

        public void deserialise(Deserializer deserialiser)
            throws IOException
        {
            final int length = getVertexCount() * getComponentCount();
            if (getEncoding() == ENCODING_DELTA)
            {
                byte delta = 0;
                for (int i = 0; i < length; ++i)
                {
                    delta += deserialiser.readByte();
                    values[i] = delta;
                }
            }
            else
            {
                for (int i = 0; i < length; ++i)
                {
                    values[i] = deserialiser.readByte();
                }
            }
        }

        public void serialise(Serializer serialiser)
            throws IOException
        {
            final int length = getVertexCount() * getComponentCount();
            if (getEncoding() == ENCODING_DELTA)
            {
                byte prev = 0;
                for (int i = 0; i < length; ++i)
                {
                    byte delta = (byte) (values[i] - prev);
                    prev = values[i];
                    serialiser.writeByte(delta);
                }
            }
            else
            {
                for (int i = 0; i < length; ++i)
                {
                    serialiser.writeByte(values[i]);
                }
            }
        }

    }

    private static class ShortValues extends Values
    {
        private short[] values;

        public ShortValues(int vertexCount, int componentCount)
        {
            super(vertexCount, componentCount);
            values = new short[vertexCount * componentCount];
        }

        @Override
        public int getComponentType()
        {
            return VertexArray.SHORT;
        }

        public void get(int firstVertex, int numVertices, short[] dst)
        {
            requireSourceLength(firstVertex, numVertices);
            requireArrayLength(numVertices, dst);

            final int offset = firstVertex * getComponentCount();
            final int length = numVertices * getComponentCount();
            System.arraycopy(values, offset,
                dst, 0, length);
        }

        @Override
        public void set(int firstVertex, int numVertices, List<? extends Number> values)
        {
            requireListSize(numVertices, values);

            final int size = values.size();
            short[] src = new short[size];
            for (int i = 0; i < size; ++i)
            {
                src[i] = values.get(i).shortValue();
            }

            set(firstVertex, numVertices, src);
        }

        public void set(int firstVertex, int numVertices, byte[] values)
        {
            throw new IllegalStateException("SHORT array only supports short[] values");
        }

        public void set(int firstVertex, int numVertices, short[] values)
        {
            requireSourceLength(firstVertex, numVertices);
            requireArrayLength(numVertices, values);

            final int offset = firstVertex * getComponentCount();
            final int length = numVertices * getComponentCount();
            System.arraycopy(values, 0,
                this.values, offset, length);
        }

        public void deserialise(Deserializer deserialiser)
            throws IOException
        {
            final int length = getVertexCount() * getComponentCount();
            if (getEncoding() == ENCODING_DELTA)
            {
                short delta = 0;
                for (int i = 0; i < length; ++i)
                {
                    delta += deserialiser.readShort();
                    values[i] = delta;
                }
            }
            else
            {
                for (int i = 0; i < length; ++i)
                {
                    values[i] = deserialiser.readShort();
                }
            }
        }

        public void serialise(Serializer serialiser)
            throws IOException
        {
            final int length = getVertexCount() * getComponentCount();
            if (getEncoding() == ENCODING_DELTA)
            {
                short prev = 0;
                for (int i = 0; i < length; ++i)
                {
                    short delta = (short) (values[i] - prev);
                    prev = values[i];
                    serialiser.writeShort(delta);
                }
            }
            else
            {
                for (int i = 0; i < length; ++i)
                {
                    serialiser.writeShort(values[i]);
                }
            }
        }
    }
    
    private Values values;

    public VertexArray()
    {
        super();
    }

    public VertexArray(int numVertices, int numComponents, int componentType)
    {
        super();
        setSizeAndType(numVertices, numComponents, componentType);
    }

    @Override
    public void deserialise(Deserializer deserialiser)
        throws IOException
    {
        super.deserialise(deserialiser);
        final int componentType = deserialiser.readUnsignedByte();
        final int componentCount = deserialiser.readUnsignedByte();
        final int encoding = deserialiser.readUnsignedByte();
        final int vertexCount = deserialiser.readUnsignedShort();
        setSizeAndType(vertexCount, componentCount, componentType);
        setEncoding(encoding);
        this.values.deserialise(deserialiser);
    }

    @Override
    public void serialise(Serializer serialiser)
        throws IOException
    {
        super.serialise(serialiser);

        serialiser.writeUnsignedByte(getComponentType());
        serialiser.writeUnsignedByte(getComponentCount());
        serialiser.writeUnsignedByte(getEncoding());
        serialiser.writeUnsignedShort(getVertexCount());
        this.values.serialise(serialiser);
    }

    public int getSectionObjectType()
    {
        return ObjectTypes.VERTEX_ARRAY;
    }

    public int getComponentType()
    {
        return this.values.getComponentType();
    }

    public int getComponentCount()
    {
        return this.values.getComponentCount();
    }

    public void setEncoding(int encoding)
    {
        this.values.setEncoding(encoding);
    }
    
    public int getEncoding()
    {
        return this.values.getEncoding();
    }

    public int getVertexCount()
    {
        return this.values.getVertexCount();
    }

    public void set(int firstVertex, int numVertices, List<? extends Number> values)
    {
        this.values.set(firstVertex, numVertices, values);
    }

    public void set(int firstVertex, int numVertices, byte[] values)
    {
        this.values.set(firstVertex, numVertices, values);
    }

    public void set(int firstVertex, int numVertices, short[] values)
    {
        this.values.set(firstVertex, numVertices, values);
    }

    public void setSizeAndType(int vertexCount, int componentCount, int componentType)
    {
        requireValidVertexCount(vertexCount);
        requireValidComponentCount(componentCount);
        requireValidComponentType(componentType);
        
        switch (componentType)
        {
            case BYTE:
                this.values = new ByteValues(vertexCount, componentCount);
                break;

            case SHORT:
                this.values = new ShortValues(vertexCount, componentCount);
                break;

            default:
                throw new IllegalArgumentException(
                    "Invalid component type: " + componentType);
        }

        assert(getComponentType() == componentType);
    }

    public final int getComponentType(String type)
    {
        return getFieldValue(type, "type");
    }




}
