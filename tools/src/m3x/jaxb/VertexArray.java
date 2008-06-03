//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-463 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.06.02 at 11:04:04 PM EST 
//


package m3x.jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
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
 *         &lt;choice>
 *           &lt;element ref="{}float_array"/>
 *           &lt;element ref="{}int_array"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="componentCount" type="{}uint" />
 *       &lt;attribute name="componentType" type="{}VertexArrayDataType" default="FLOAT" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "floatArray",
    "intArray"
})
@XmlRootElement(name = "VertexArray")
public class VertexArray
    extends Object3DType
{

    @XmlList
    @XmlElement(name = "float_array", type = Double.class)
    protected List<Double> floatArray;
    @XmlList
    @XmlElement(name = "int_array", type = Long.class)
    protected List<Long> intArray;
    @XmlAttribute
    protected BigInteger componentCount;
    @XmlAttribute
    protected VertexArrayDataType componentType;

    /**
     * Gets the value of the floatArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the floatArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFloatArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Double }
     * 
     * 
     */
    public List<Double> getFloatArray() {
        if (floatArray == null) {
            floatArray = new ArrayList<Double>();
        }
        return this.floatArray;
    }

    /**
     * Gets the value of the intArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the intArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIntArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getIntArray() {
        if (intArray == null) {
            intArray = new ArrayList<Long>();
        }
        return this.intArray;
    }

    /**
     * Gets the value of the componentCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getComponentCount() {
        return componentCount;
    }

    /**
     * Sets the value of the componentCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setComponentCount(BigInteger value) {
        this.componentCount = value;
    }

    /**
     * Gets the value of the componentType property.
     * 
     * @return
     *     possible object is
     *     {@link VertexArrayDataType }
     *     
     */
    public VertexArrayDataType getComponentType() {
        if (componentType == null) {
            return VertexArrayDataType.FLOAT;
        } else {
            return componentType;
        }
    }

    /**
     * Sets the value of the componentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link VertexArrayDataType }
     *     
     */
    public void setComponentType(VertexArrayDataType value) {
        this.componentType = value;
    }

}
