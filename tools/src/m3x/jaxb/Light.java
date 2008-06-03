//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-463 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.06.02 at 11:04:04 PM EST 
//


package m3x.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *     &lt;extension base="{}NodeType">
 *       &lt;attribute name="attenuationConstant" type="{}float" />
 *       &lt;attribute name="attenuationLinear" type="{}float" />
 *       &lt;attribute name="attenuationQuadratic" type="{}float" />
 *       &lt;attribute name="color" type="{}int" />
 *       &lt;attribute name="mode" type="{}LightType" />
 *       &lt;attribute name="intensity" type="{}float" />
 *       &lt;attribute name="spotAngle" type="{}float" />
 *       &lt;attribute name="spotExponent" type="{}float" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Light")
public class Light
    extends NodeType
{

    @XmlAttribute
    protected Double attenuationConstant;
    @XmlAttribute
    protected Double attenuationLinear;
    @XmlAttribute
    protected Double attenuationQuadratic;
    @XmlAttribute
    protected Long color;
    @XmlAttribute
    protected LightType mode;
    @XmlAttribute
    protected Double intensity;
    @XmlAttribute
    protected Double spotAngle;
    @XmlAttribute
    protected Double spotExponent;

    /**
     * Gets the value of the attenuationConstant property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAttenuationConstant() {
        return attenuationConstant;
    }

    /**
     * Sets the value of the attenuationConstant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAttenuationConstant(Double value) {
        this.attenuationConstant = value;
    }

    /**
     * Gets the value of the attenuationLinear property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAttenuationLinear() {
        return attenuationLinear;
    }

    /**
     * Sets the value of the attenuationLinear property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAttenuationLinear(Double value) {
        this.attenuationLinear = value;
    }

    /**
     * Gets the value of the attenuationQuadratic property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAttenuationQuadratic() {
        return attenuationQuadratic;
    }

    /**
     * Sets the value of the attenuationQuadratic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAttenuationQuadratic(Double value) {
        this.attenuationQuadratic = value;
    }

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setColor(Long value) {
        this.color = value;
    }

    /**
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link LightType }
     *     
     */
    public LightType getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link LightType }
     *     
     */
    public void setMode(LightType value) {
        this.mode = value;
    }

    /**
     * Gets the value of the intensity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIntensity() {
        return intensity;
    }

    /**
     * Sets the value of the intensity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIntensity(Double value) {
        this.intensity = value;
    }

    /**
     * Gets the value of the spotAngle property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSpotAngle() {
        return spotAngle;
    }

    /**
     * Sets the value of the spotAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSpotAngle(Double value) {
        this.spotAngle = value;
    }

    /**
     * Gets the value of the spotExponent property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSpotExponent() {
        return spotExponent;
    }

    /**
     * Sets the value of the spotExponent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSpotExponent(Double value) {
        this.spotExponent = value;
    }

}
