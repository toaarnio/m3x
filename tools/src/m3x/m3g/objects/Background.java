package m3x.m3g.objects;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import m3x.m3g.FileFormatException;
import m3x.m3g.M3GSupport;
import m3x.m3g.M3GTypedObject;
import m3x.m3g.ObjectTypes;
import m3x.m3g.primitives.ColorRGBA;
import m3x.m3g.primitives.ObjectIndex;

public class Background extends Object3D implements M3GTypedObject
{
  public final static int MODE_BORDER = 32;
  public final static int MODE_REPEAT = 33;

  private final ColorRGBA backgroundColor;
  private final ObjectIndex backgroundImage;
  private final int backgroundImageModeX;
  private final int backgroundImageModeY;
  private final int cropX;
  private final int cropY;
  private final int cropWidth;
  private final int cropHeight;
  private final boolean depthClearEnabled;
  private final boolean colorClearEnabled;

  public Background(ObjectIndex[] animationTracks,
      UserParameter[] userParameters, ColorRGBA backgroundColor,
      ObjectIndex backgroundImage, int backgroundImageModeX,
      int backgroundImageModeY, int cropX, int cropY, int cropWidth,
      int cropHeight, boolean depthClearEnabled, boolean colorClearEnabled)
  {
    super(animationTracks, userParameters);
    assert (backgroundImageModeX == MODE_BORDER || backgroundImageModeX == MODE_REPEAT);
    assert (backgroundImageModeY == MODE_BORDER || backgroundImageModeY == MODE_REPEAT);
    this.backgroundColor = backgroundColor;
    this.backgroundImage = backgroundImage;
    this.backgroundImageModeX = backgroundImageModeX;
    this.backgroundImageModeY = backgroundImageModeY;
    this.cropX = cropX;
    this.cropY = cropY;
    this.cropWidth = cropWidth;
    this.cropHeight = cropHeight;
    this.depthClearEnabled = depthClearEnabled;
    this.colorClearEnabled = colorClearEnabled;
  }

  public void deserialize(DataInputStream dataInputStream, String m3gVersion)
      throws IOException, FileFormatException
  {    
  }

  public void serialize(DataOutputStream dataOutputStream, String m3gVersion)
      throws IOException
  {
    super.serialize(dataOutputStream, m3gVersion);
    this.backgroundColor.serialize(dataOutputStream, m3gVersion);
    this.backgroundImage.serialize(dataOutputStream, m3gVersion);
    dataOutputStream.writeInt(this.backgroundImageModeX);
    dataOutputStream.writeInt(this.backgroundImageModeY);
    dataOutputStream.writeInt(M3GSupport.swapBytes(this.cropX));
    dataOutputStream.writeInt(M3GSupport.swapBytes(this.cropY));
    dataOutputStream.writeInt(M3GSupport.swapBytes(this.cropWidth));
    dataOutputStream.writeInt(M3GSupport.swapBytes(this.cropHeight));
    dataOutputStream.writeBoolean(this.depthClearEnabled);
    dataOutputStream.writeBoolean(this.colorClearEnabled);
  }

  
  public byte getObjectType()
  {
    return ObjectTypes.BACKGROUND;
  }
}
