package m3x.m3g.objects;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import m3x.m3g.FileFormatException;
import m3x.m3g.M3GSupport;
import m3x.m3g.M3GTypedObject;
import m3x.m3g.ObjectTypes;
import m3x.m3g.primitives.ObjectIndex;

public class Appearance extends Object3D implements M3GTypedObject
{
  private byte layer;
  private ObjectIndex compositingMode;
  private ObjectIndex fog;
  private ObjectIndex polygonMode;
  private ObjectIndex material;
  private ObjectIndex[] textures;

  public Appearance(ObjectIndex[] animationTracks,
      UserParameter[] userParameters, byte layer, ObjectIndex compositingMode,
      ObjectIndex fog, ObjectIndex polygonMode, ObjectIndex material,
      ObjectIndex[] textures)
  {
    super(animationTracks, userParameters);
    this.layer = layer;
    this.compositingMode = compositingMode;
    this.fog = fog;
    this.polygonMode = polygonMode;
    this.material = material;
    this.textures = textures;
  }
  
  public void deserialize(DataInputStream dataInputStream, String m3gVersion)
      throws IOException, FileFormatException
  {    
    this.layer = dataInputStream.readByte();
    this.compositingMode.deserialize(dataInputStream, m3gVersion);
    this.fog.deserialize(dataInputStream, m3gVersion);
    this.polygonMode.deserialize(dataInputStream, m3gVersion);
    this.material.deserialize(dataInputStream, m3gVersion);
    int texturesLength = M3GSupport.readInt(dataInputStream);
    this.textures = new ObjectIndex[texturesLength];
    for (int i = 0; i < this.textures.length; i++)
    {
      this.textures[i] = new ObjectIndex();
      this.textures[i].deserialize(dataInputStream, m3gVersion);
    }
  }

  public void serialize(DataOutputStream dataOutputStream, String m3gVersion)
      throws IOException
  {
    super.serialize(dataOutputStream, m3gVersion);
    dataOutputStream.write(this.layer);
    this.compositingMode.serialize(dataOutputStream, null);
    this.fog.serialize(dataOutputStream, null);
    this.polygonMode.serialize(dataOutputStream, null);
    this.material.serialize(dataOutputStream, null);
    M3GSupport.writeInt(dataOutputStream, this.textures.length);
    for (int i = 0; i < this.textures.length; i++)
    {
      this.textures[i].serialize(dataOutputStream, null);
    }
  }

  public byte getObjectType()
  {
    return ObjectTypes.APPEARANCE;
  }
}
