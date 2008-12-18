package m3x.m3g;

import java.io.DataOutputStream;
import java.io.IOException;

import m3x.m3g.primitives.ColorRGB;
import m3x.m3g.primitives.Matrix;

/**
 * See http://java2me.org/m3g/file-format.html#Light<br>
  Float32       attenuationConstant;<br>
  Float32       attenuationLinear;<br>
  Float32       attenuationQuadratic;<br>
  ColorRGB      color;<br>
  Byte          mode;<br>
  Float32       intensity;<br>
  Float32       spotAngle;<br>
  Float32       spotExponent;<br>
  <br>
 * @author jsaarinen
 */
public class Light extends Node implements M3GTypedObject
{
    public final static int MODE_AMBIENT = 128;
    public final static int MODE_DIRECTIONAL = 129;
    public final static int MODE_OMNI = 130;
    public final static int MODE_SPOT = 131;
    private float attenuationConstant;
    private float attenuationLinear;
    private float attenuationQuadratic;
    private ColorRGB color;
    private int mode;
    private float intensity;
    private float spotAngle;
    private float spotExponent;

    public Light(AnimationTrack[] animationTracks, UserParameter[] userParameters,
        Matrix transform, boolean enableRendering, boolean enablePicking,
        byte alphaFactor, int scope, float attenuationConstant,
        float attenuationLinear, float attenuationQuadratic, ColorRGB color,
        int mode, float intensity, float spotAngle, float spotExponent) throws FileFormatException
    {
        super(animationTracks, userParameters, transform, enableRendering,
            enablePicking, alphaFactor, scope);
        this.attenuationConstant = attenuationConstant;
        this.attenuationLinear = attenuationLinear;
        this.attenuationQuadratic = attenuationQuadratic;
        this.color = color;
        validateMode(mode);
        this.mode = mode;
        this.intensity = intensity;
        this.spotAngle = spotAngle;
        this.spotExponent = spotExponent;
    }

    private static void validateMode(int mode) throws FileFormatException
    {
        if (mode < MODE_AMBIENT || mode > MODE_SPOT)
        {
            throw new FileFormatException("Invalid light mode: " + mode);
        }
    }

    public Light()
    {
        super();
    }

    @Override
    public void deserialize(M3GDeserialiser deserialiser)
        throws IOException, FileFormatException
    {
        super.deserialize(deserialiser);
        this.attenuationConstant = deserialiser.readFloat();
        this.attenuationLinear = deserialiser.readFloat();
        this.attenuationQuadratic = deserialiser.readFloat();
        this.color = new ColorRGB();
        this.color.deserialize(deserialiser);
        this.mode = deserialiser.readUnsignedByte();
        validateMode(this.mode);
        this.intensity = deserialiser.readFloat();
        this.spotAngle = deserialiser.readFloat();
        this.spotExponent = deserialiser.readFloat();
    }

    @Override
    public void serialize(DataOutputStream dataOutputStream, String m3gVersion)
        throws IOException
    {
        super.serialize(dataOutputStream, m3gVersion);
        M3GSupport.writeFloat(dataOutputStream, this.attenuationConstant);
        M3GSupport.writeFloat(dataOutputStream, this.attenuationLinear);
        M3GSupport.writeFloat(dataOutputStream, this.attenuationQuadratic);
        this.color.serialize(dataOutputStream, m3gVersion);
        dataOutputStream.write(this.mode);
        M3GSupport.writeFloat(dataOutputStream, this.intensity);
        M3GSupport.writeFloat(dataOutputStream, this.spotAngle);
        M3GSupport.writeFloat(dataOutputStream, this.spotExponent);
    }

    public int getObjectType()
    {
        return ObjectTypes.LIGHT;
    }

    public float getAttenuationConstant()
    {
        return this.attenuationConstant;
    }

    public float getAttenuationLinear()
    {
        return this.attenuationLinear;
    }

    public float getAttenuationQuadratic()
    {
        return this.attenuationQuadratic;
    }

    public ColorRGB getColor()
    {
        return this.color;
    }

    public int getMode()
    {
        return this.mode;
    }

    public float getIntensity()
    {
        return this.intensity;
    }

    public float getSpotAngle()
    {
        return this.spotAngle;
    }

    public float getSpotExponent()
    {
        return this.spotExponent;
    }
}
