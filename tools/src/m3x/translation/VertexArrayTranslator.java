package m3x.translation;

import java.util.List;

import m3x.m3g.objects.Object3D;
import m3x.m3g.primitives.ObjectIndex;
import m3x.xml.Deserialiser;
import m3x.xml.M3G;
import m3x.xml.Object3DType;

public class VertexArrayTranslator extends AbstractTranslator
{
  public void set(Object3DType object, M3G root, Deserialiser deserialiser)
  {
    super.set((m3x.xml.VertexArray) object, root, deserialiser);
  }

  public void set(Object3D object)
  {
    super.set((m3x.m3g.objects.VertexArray) object);
  }

  public Object3D toM3G()
  {
    if (this.m3gObject == null)
    {
      m3x.xml.VertexArray va = (m3x.xml.VertexArray)m3xObject;
      ObjectIndex[] animationTracks = this.getM3GAnimationTracks();
      Object3D.UserParameter[] userParameters = new Object3D.UserParameter[0];
      List<Integer> ints = va.getIntArray();
      switch (va.getComponentType())
      {
        case BYTE:
          System.out.println(va.getComponentCount());
          byte[] byteComponents = new byte[ints.size()];
          for (int i = 0; i < ints.size(); i++)
          {
            byteComponents[i] = ints.get(i).byteValue();
          }
          m3gObject = new m3x.m3g.objects.VertexArray(animationTracks,
              userParameters, byteComponents, false);
        case SHORT:
          short[] shortComponents = new short[ints.size()];
          for (int i = 0; i < ints.size(); i++)
          {
            shortComponents[i] = ints.get(i).shortValue();
          }
          m3gObject = new m3x.m3g.objects.VertexArray(animationTracks,
              userParameters, shortComponents, false);
      }
    }
    // else translation is done already
    return m3gObject;
  }

  public Object3DType toXML()
  {
    if (this.m3xObject == null)
    {

    }
    return m3xObject;
  }

}
