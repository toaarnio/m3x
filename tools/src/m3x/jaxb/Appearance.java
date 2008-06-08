//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-463 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.06.08 at 06:07:01 PM EST 
//


package m3x.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}Object3DType">
 *       &lt;sequence>
 *         &lt;element ref="{}CompositingModeInstance" minOccurs="0"/>
 *         &lt;element ref="{}FogInstance" minOccurs="0"/>
 *         &lt;element ref="{}PolygonModeInstance" minOccurs="0"/>
 *         &lt;element ref="{}MaterialInstance" minOccurs="0"/>
 *         &lt;element ref="{}Texture2DInstance" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="layer" type="{}Byte" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "compositingModeInstance",
    "fogInstance",
    "polygonModeInstance",
    "materialInstance",
    "texture2DInstance"
})
@XmlRootElement(name = "Appearance")
public class Appearance
    extends Object3DType
{

    @XmlElement(name = "CompositingModeInstance")
    protected CompositingModeInstance compositingModeInstance;
    @XmlElement(name = "FogInstance")
    protected FogInstance fogInstance;
    @XmlElement(name = "PolygonModeInstance")
    protected PolygonModeInstance polygonModeInstance;
    @XmlElement(name = "MaterialInstance")
    protected MaterialInstance materialInstance;
    @XmlElement(name = "Texture2DInstance")
    protected List<Texture2DInstance> texture2DInstance;
    @XmlAttribute
    protected Short layer;

    /**
     * Gets the value of the compositingModeInstance property.
     * 
     * @return
     *     possible object is
     *     {@link CompositingModeInstance }
     *     
     */
    public CompositingModeInstance getCompositingModeInstance() {
        return compositingModeInstance;
    }

    /**
     * Sets the value of the compositingModeInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompositingModeInstance }
     *     
     */
    public void setCompositingModeInstance(CompositingModeInstance value) {
        this.compositingModeInstance = value;
    }

    /**
     * Gets the value of the fogInstance property.
     * 
     * @return
     *     possible object is
     *     {@link FogInstance }
     *     
     */
    public FogInstance getFogInstance() {
        return fogInstance;
    }

    /**
     * Sets the value of the fogInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link FogInstance }
     *     
     */
    public void setFogInstance(FogInstance value) {
        this.fogInstance = value;
    }

    /**
     * Gets the value of the polygonModeInstance property.
     * 
     * @return
     *     possible object is
     *     {@link PolygonModeInstance }
     *     
     */
    public PolygonModeInstance getPolygonModeInstance() {
        return polygonModeInstance;
    }

    /**
     * Sets the value of the polygonModeInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolygonModeInstance }
     *     
     */
    public void setPolygonModeInstance(PolygonModeInstance value) {
        this.polygonModeInstance = value;
    }

    /**
     * Gets the value of the materialInstance property.
     * 
     * @return
     *     possible object is
     *     {@link MaterialInstance }
     *     
     */
    public MaterialInstance getMaterialInstance() {
        return materialInstance;
    }

    /**
     * Sets the value of the materialInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaterialInstance }
     *     
     */
    public void setMaterialInstance(MaterialInstance value) {
        this.materialInstance = value;
    }

    /**
     * Gets the value of the texture2DInstance property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the texture2DInstance property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTexture2DInstance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Texture2DInstance }
     * 
     * 
     */
    public List<Texture2DInstance> getTexture2DInstance() {
        if (texture2DInstance == null) {
            texture2DInstance = new ArrayList<Texture2DInstance>();
        }
        return this.texture2DInstance;
    }

    /**
     * Gets the value of the layer property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getLayer() {
        return layer;
    }

    /**
     * Sets the value of the layer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setLayer(Short value) {
        this.layer = value;
    }

}
