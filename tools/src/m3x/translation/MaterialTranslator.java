package m3x.translation;


import java.util.ArrayList;

import javax.xml.bind.JAXBContext;

import m3x.m3g.FileFormatException;
import m3x.m3g.objects.Object3D;
import m3x.m3g.primitives.ColorRGB;
import m3x.m3g.primitives.ColorRGBA;
import m3x.m3g.primitives.Matrix;
import m3x.m3g.primitives.ObjectIndex;
import m3x.xml.NodeType;
import m3x.xml.Object3DType;

public class MaterialTranslator extends AbstractTranslator
{

  public Object3D toM3G()
  {
    if (this.m3gObject != null)
    {
      return this.m3gObject;
    }

    // do translation
    m3x.xml.Material material = (m3x.xml.Material)this.m3xObject;
    ObjectIndex[] animationTracks = this.getM3GAnimationTracks();
    Object3D.UserParameter[] userParameters = new Object3D.UserParameter[0];
   
    ColorRGB ambient = this.translateColorRGB(material.getAmbientColor());
    ColorRGBA diffuse = this.translateColorRGBA(material.getDiffuseColor());
    ColorRGB emissive = this.translateColorRGB(material.getEmissiveColor());
    ColorRGB specular = this.translateColorRGB(material.getSpecularColor());

    this.m3gObject = new m3x.m3g.objects.Material(animationTracks, 
          userParameters, 
          ambient,
          diffuse,
          emissive,
          specular,
          material.getShininess().floatValue(),
          material.isVertexColorTrackingEnabled().booleanValue());

    return this.m3gObject;
  }

  public Object3DType toXML()
  {
    return null;
  }
}