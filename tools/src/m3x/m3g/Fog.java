package m3x.m3g;

import java.io.DataOutputStream;
import java.io.IOException;

import m3x.m3g.primitives.ColorRGB;

/**
 * See See http://java2me.org/m3g/file-format.html#Fog<br>
  ColorRGB      color;<br>
  Byte          mode;<br>
  IF mode==EXPONENTIAL, THEN<br>
    Float32       density;<br>
  ELSE IF mode==LINEAR, THEN<br>
    Float32       near;<br>
    Float32       far;<br>
  END<br>

 * @author jsaarinen
 */
public class Fog extends Object3D implements M3GTypedObject
{
    public final static int MODE_EXPONENTIAL = 80;
    public final static int MODE_LINEAR = 81;
    private ColorRGB color;
    private int mode;
    private float density;
    private float near;
    private float far;

    public Fog(AnimationTrack[] animationTracks, UserParameter[] userParameters,
        ColorRGB color, float density)
    {
        super(animationTracks, userParameters);
        this.color = color;
        this.mode = MODE_EXPONENTIAL;
        this.density = density;
        this.near = 0.0f;
        this.far = 0.0f;
    }

    public Fog(AnimationTrack[] animationTracks, UserParameter[] userParameters,
        ColorRGB color, float near, float far)
    {
        super(animationTracks, userParameters);
        this.color = color;
        this.mode = MODE_LINEAR;
        this.density = 0.0f;
        this.near = near;
        this.far = far;
    }

    public Fog()
    {
        super();
    }

    @Override
    public void deserialize(M3GDeserialiser deserialiser)
        throws IOException, FileFormatException
    {
        super.deserialize(deserialiser);
        this.color = new ColorRGB();
        this.color.deserialize(deserialiser);
        this.mode = deserialiser.readUnsignedByte();
        if (this.mode == MODE_EXPONENTIAL)
        {
            this.density = deserialiser.readFloat();
        }
        else if (this.mode == MODE_LINEAR)
        {
            this.near = deserialiser.readFloat();
            this.far = deserialiser.readFloat();
        }
        else
        {
            throw new FileFormatException("Invalid fog mode: " + this.mode);
        }
    }

    @Override
    public void serialize(DataOutputStream dataOutputStream, String m3gVersion)
        throws IOException
    {
        super.serialize(dataOutputStream, m3gVersion);
        this.color.serialize(dataOutputStream, m3gVersion);
        dataOutputStream.write(this.mode);
        if (this.mode == MODE_EXPONENTIAL)
        {
            M3GSupport.writeFloat(dataOutputStream, this.density);
        }
        else if (this.mode == MODE_LINEAR)
        {
            M3GSupport.writeFloat(dataOutputStream, this.near);
            M3GSupport.writeFloat(dataOutputStream, this.far);
        }
        else
        {
            assert (false);
        }
    }

    public int getObjectType()
    {
        return ObjectTypes.FOG;
    }

    public ColorRGB getColor()
    {
        return this.color;
    }

    public int getMode()
    {
        return this.mode;
    }

    public float getDensity()
    {
        return this.density;
    }

    public float getNear()
    {
        return this.near;
    }

    public float getFar()
    {
        return this.far;
    }
}
