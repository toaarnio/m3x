//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-463 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.06.08 at 06:07:01 PM EST 
//


package m3x.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 The Instance type declares a base for M3G instance elements.
 *             
 * 
 * <p>Java class for InstanceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InstanceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ref" type="{}ObjectReference" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstanceType")
@XmlSeeAlso({
    m3x.jaxb.SkinnedMesh.Bone.class,
    VertexArrayInstance.class,
    TriangleStripArrayInstance.class,
    Texture2DInstance.class,
    FogInstance.class,
    CompositingModeInstance.class,
    MaterialInstance.class,
    Image2DInstance.class,
    ImageBaseInstance.class,
    PolygonModeInstance.class,
    AppearanceInstance.class,
    VertexBufferInstance.class,
    AnimationTrackInstance.class,
    AnimationControllerInstance.class,
    IndexBufferInstance.class,
    KeyframeSequenceInstance.class
})
public class InstanceType {

    @XmlAttribute
    protected String ref;

    /**
     * Gets the value of the ref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRef() {
        return ref;
    }

    /**
     * Sets the value of the ref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRef(String value) {
        this.ref = value;
    }

}
