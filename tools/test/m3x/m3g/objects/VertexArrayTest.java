package m3x.m3g.objects;

import m3x.m3g.M3GSupport;
import m3x.m3g.objects.Object3D.UserParameter;
import m3x.m3g.primitives.ColorRGB;
import m3x.m3g.primitives.Matrix;
import m3x.m3g.primitives.ObjectIndex;

public class VertexArrayTest extends AbstractTestCase
{
  public void testSerializationAndDeseriliazation1()
  {
    ObjectIndex[] animationTracks = getAnimationTracks();
    UserParameter[] userParameters = getUserParameters();
    byte[] components1 = new byte[] {1, 2, 3, 4, 5, 6};
    VertexArray array = new VertexArray(animationTracks,
                                        userParameters,
                                        components1,
                                        false);                            
    try
    {   
      byte[] serialized = M3GSupport.objectToBytes(array);
      VertexArray deserialized = (VertexArray)M3GSupport.bytesToObject(serialized, VertexArray.class);
      this.doTestAccessors(array, deserialized);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      fail(e.getMessage());
    }    
    short[] components2 = new short[] {1, 2, 3, 4, 5, 6};
    array = new VertexArray(animationTracks,
                            userParameters,
                            components2,
                            false);                            
    try
    {   
      byte[] serialized = M3GSupport.objectToBytes(array);
      VertexArray deserialized = (VertexArray)M3GSupport.bytesToObject(serialized, VertexArray.class);
      this.doTestAccessors(array, deserialized);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      fail(e.getMessage());
    } 
  }

  public void testSerializationAndDeseriliazation2()
  {
    ObjectIndex[] animationTracks = getAnimationTracks();
    UserParameter[] userParameters = getUserParameters();
    ColorRGB color = new ColorRGB(0.1f, 0.2f, 0.3f);
    Fog fog = new Fog(animationTracks,
                      userParameters,
                      color,
                      0.2f,
                      0.3f);
                            
    try
    {   
      byte[] serialized = M3GSupport.objectToBytes(fog);
      Fog deserialized = (Fog)M3GSupport.bytesToObject(serialized, Fog.class);
      this.doTestAccessors(fog, deserialized);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      fail(e.getMessage());
    }    
  }
}
