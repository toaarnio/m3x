package m3x.m3g.objects;

import java.io.DataOutputStream;
import java.io.IOException;

import m3x.m3g.M3GSerializable;
import m3x.m3g.M3GSupport;
import m3x.m3g.objects.Object3D.UserParameter;
import m3x.m3g.primitives.ObjectIndex;

public class TriangleStripArray extends IndexBuffer implements M3GSerializable
{
  private final int encoding;
  private final int intStartIndex;
  private final byte byteStartIndex;
  private final short shortStartIndex;
  private final int[] intIndices;
  private final byte[] byteIndices;
  private final short[] shortIndices;
  
  public TriangleStripArray(ObjectIndex[] animationTracks,
      UserParameter[] userParameters, int intStartIndex)
  {
    super(animationTracks, userParameters);
    this.encoding = 0;
    this.byteStartIndex = 0;
    this.shortStartIndex = 0;
    this.intStartIndex = intStartIndex;
    this.intIndices = null;
    this.byteIndices = null;
    this.shortIndices = null;
  }
  
  public TriangleStripArray(ObjectIndex[] animationTracks,
      UserParameter[] userParameters, byte byteStartIndex)
  {
    super(animationTracks, userParameters);
    this.encoding = 1;
    this.byteStartIndex = byteStartIndex;
    this.shortStartIndex = 0;
    this.intStartIndex = 0;
    this.intIndices = null;
    this.byteIndices = null;
    this.shortIndices = null;
  }

  public TriangleStripArray(ObjectIndex[] animationTracks,
      UserParameter[] userParameters, short shortStartIndex)
  {
    super(animationTracks, userParameters);
    this.encoding = 2;
    this.byteStartIndex = 0;
    this.shortStartIndex = shortStartIndex;
    this.intStartIndex = 0;
    this.intIndices = null;
    this.byteIndices = null;
    this.shortIndices = null;
  }
  
  public TriangleStripArray(ObjectIndex[] animationTracks,
      UserParameter[] userParameters, int[] intIndices)
  {
    super(animationTracks, userParameters);
    this.encoding = 0;
    this.byteStartIndex = 0;
    this.shortStartIndex = 0;
    this.intStartIndex = 0;
    this.intIndices = intIndices;
    this.byteIndices = null;
    this.shortIndices = null;
  }
  
  public TriangleStripArray(ObjectIndex[] animationTracks,
      UserParameter[] userParameters, byte[] byteIndices)
  {
    super(animationTracks, userParameters);
    this.encoding = 128;
    this.byteStartIndex = 0;
    this.shortStartIndex = 0;
    this.intStartIndex = 0;
    this.intIndices = null;
    this.byteIndices = byteIndices;
    this.shortIndices = null;
  }

  public TriangleStripArray(ObjectIndex[] animationTracks,
      UserParameter[] userParameters, short[] shortIndices)
  {
    super(animationTracks, userParameters);
    this.encoding = 130;
    this.byteStartIndex = 0;
    this.shortStartIndex = 0;
    this.intStartIndex = 0;
    this.intIndices = null;
    this.byteIndices = null;
    this.shortIndices = shortIndices;
  }

  @Override
  public void serialize(DataOutputStream dataOutputStream, String m3gVersion)
      throws IOException
  {
    super.serialize(dataOutputStream, m3gVersion);
    dataOutputStream.write(this.encoding);
    switch (this.encoding)
    {
      case 0:
        dataOutputStream.writeInt(M3GSupport.swapBytes(this.intStartIndex));
        break;
        
      case 1:
        dataOutputStream.writeShort(M3GSupport.swapBytes(this.shortStartIndex));
        break;
     
      case 2:
        dataOutputStream.write(this.byteStartIndex);
        break;
      
      case 128:
        for (int index : this.intIndices)
        {
          dataOutputStream.writeInt(M3GSupport.swapBytes(index));          
        }
        break;
        
      case 129:
        for (byte index : this.byteIndices)
        {
          dataOutputStream.write(index);          
        }
        break;

      case 130:
        for (short index : this.shortIndices)
        {
          dataOutputStream.writeShort(M3GSupport.swapBytes(index));          
        }
        break;
    
      default:
        assert(false);
        break;
    }
  }  
}