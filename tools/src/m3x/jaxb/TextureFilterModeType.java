//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-463 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.06.02 at 11:04:04 PM EST 
//


package m3x.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TextureFilterModeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TextureFilterModeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ANISOTROPIC"/>
 *     &lt;enumeration value="LINEAR"/>
 *     &lt;enumeration value="NEAREST"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TextureFilterModeType")
@XmlEnum
public enum TextureFilterModeType {

    ANISOTROPIC,
    LINEAR,
    NEAREST;

    public String value() {
        return name();
    }

    public static TextureFilterModeType fromValue(String v) {
        return valueOf(v);
    }

}
