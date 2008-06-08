//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-463 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.06.08 at 06:17:17 PM EST 
//


package m3x.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransformableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransformableType">
 *   &lt;complexContent>
 *     &lt;extension base="{}Object3DType">
 *       &lt;sequence>
 *         &lt;element ref="{}translation" minOccurs="0"/>
 *         &lt;element ref="{}scale" minOccurs="0"/>
 *         &lt;element ref="{}orientation" minOccurs="0"/>
 *         &lt;element ref="{}Transform" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransformableType", propOrder = {
    "translation",
    "scale",
    "orientation",
    "transform"
})
@XmlSeeAlso({
    Texture2D.class,
    NodeType.class
})
public class TransformableType
    extends Object3DType
{

    @XmlList
    @XmlElement(type = Float.class)
    protected List<Float> translation;
    @XmlList
    @XmlElement(type = Float.class)
    protected List<Float> scale;
    protected Orientation orientation;
    @XmlList
    @XmlElement(name = "Transform", type = Float.class)
    protected List<Float> transform;

    /**
     * Gets the value of the translation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the translation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTranslation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Float }
     * 
     * 
     */
    public List<Float> getTranslation() {
        if (translation == null) {
            translation = new ArrayList<Float>();
        }
        return this.translation;
    }

    /**
     * Gets the value of the scale property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the scale property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Float }
     * 
     * 
     */
    public List<Float> getScale() {
        if (scale == null) {
            scale = new ArrayList<Float>();
        }
        return this.scale;
    }

    /**
     * Gets the value of the orientation property.
     * 
     * @return
     *     possible object is
     *     {@link Orientation }
     *     
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Sets the value of the orientation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Orientation }
     *     
     */
    public void setOrientation(Orientation value) {
        this.orientation = value;
    }

    /**
     * Gets the value of the transform property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transform property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransform().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Float }
     * 
     * 
     */
    public List<Float> getTransform() {
        if (transform == null) {
            transform = new ArrayList<Float>();
        }
        return this.transform;
    }

}
