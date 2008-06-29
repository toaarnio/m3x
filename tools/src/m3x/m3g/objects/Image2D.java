package m3x.m3g.objects;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import m3x.m3g.FileFormatException;
import m3x.m3g.M3GSupport;
import m3x.m3g.M3GTypedObject;
import m3x.m3g.ObjectTypes;
import m3x.m3g.primitives.ObjectIndex;

public class Image2D extends Object3D implements M3GTypedObject
{
  public static final int FORMAT_ALPHA = 96;
  public static final int FORMAT_LUMINANCE = 97;
  public static final int FORMAT_LUMINANCE_ALPHA = 98;
  public static final int FORMAT_RGB = 99;
  public static final int FORMAT_RGBA = 100;

  private final int format;
  private final boolean isMutable;
  private final int width;
  private final int height;
  private final byte[] palette;
  private final byte[] pixels;

  public Image2D(ObjectIndex[] animationTracks, UserParameter[] userParameters,
      int format, int width, int height, byte[] palette, byte[] pixels)
  {
    super(animationTracks, userParameters);
    this.format = format;
    this.isMutable = false;
    this.width = width;
    this.height = height;
    this.palette = palette;
    this.pixels = pixels;
  }

  public Image2D(ObjectIndex[] animationTracks, UserParameter[] userParameters,
      byte format, int width, int height)
  {
    super(animationTracks, userParameters);
    this.format = format;
    this.isMutable = true;
    this.width = width;
    this.height = height;
    this.palette = null;
    this.pixels = null;
  }

  public void deserialize(DataInputStream dataInputStream, String m3gVersion)
      throws IOException, FileFormatException
  {    
  }

  public void serialize(DataOutputStream dataOutputStream, String m3gVersion)
      throws IOException
  {
    super.serialize(dataOutputStream, m3gVersion);
    dataOutputStream.write(this.format);
    dataOutputStream.writeBoolean(this.isMutable);
    M3GSupport.writeInt(dataOutputStream, this.width);
    M3GSupport.writeInt(dataOutputStream, this.height);
    if (this.isMutable == false)
    {
      M3GSupport.writeInt(dataOutputStream, this.palette.length);
      dataOutputStream.write(this.palette);
      M3GSupport.writeInt(dataOutputStream, this.pixels.length);
      dataOutputStream.write(this.pixels);
    }
  }

  public byte getObjectType()
  {
    return ObjectTypes.IMAGE_2D;
  }
}
