package m3x.m3g.objects;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import m3x.m3g.FileFormatException;
import m3x.m3g.M3GTypedObject;
import m3x.m3g.M3GSupport;
import m3x.m3g.ObjectTypes;
import m3x.m3g.primitives.ObjectIndex;

public class AnimationController extends Object3D implements M3GTypedObject
{
  private float speed;
  private float weight;
  private int activeIntervalStart;
  private int activeIntervalEnd;
  private float referenceSequenceTime;
  private int referenceWorldTime;
  
  public AnimationController(ObjectIndex[] animationTracks,
      UserParameter[] userParameters, float speed, float weight,
      int activeIntervalStart, int activeIntervalEnd,
      float referenceSequenceTime, int referenceWorldTime)
  {
    super(animationTracks, userParameters);
    this.speed = speed;
    this.weight = weight;
    this.activeIntervalStart = activeIntervalStart;
    this.activeIntervalEnd = activeIntervalEnd;
    this.referenceSequenceTime = referenceSequenceTime;
    this.referenceWorldTime = referenceWorldTime;
  }
  
  public AnimationController()
  {
    super();
  }

  public void deserialize(DataInputStream dataInputStream, String m3gVersion)
      throws IOException, FileFormatException
  {
    super.deserialize(dataInputStream, m3gVersion);
    this.speed = M3GSupport.readFloat(dataInputStream);
    this.weight = M3GSupport.readFloat(dataInputStream);
    this.activeIntervalStart = M3GSupport.readInt(dataInputStream);
    this.activeIntervalEnd = M3GSupport.readInt(dataInputStream);
    this.referenceSequenceTime = M3GSupport.readFloat(dataInputStream);
    this.referenceWorldTime = M3GSupport.readInt(dataInputStream);
  }

  public void serialize(DataOutputStream dataOutputStream, String m3gVersion) throws IOException
  {
    super.serialize(dataOutputStream, m3gVersion);
    M3GSupport.writeFloat(dataOutputStream, this.speed);
    M3GSupport.writeFloat(dataOutputStream, this.weight);
    M3GSupport.writeInt(dataOutputStream, this.activeIntervalStart);
    M3GSupport.writeInt(dataOutputStream, this.activeIntervalEnd);
    M3GSupport.writeFloat(dataOutputStream, this.referenceSequenceTime);
    M3GSupport.writeInt(dataOutputStream, this.referenceWorldTime);
  }
  
  public byte getObjectType()
  {
    return ObjectTypes.ANIMATION_CONTROLLER;
  }  
}
