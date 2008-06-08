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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GroupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GroupType">
 *   &lt;complexContent>
 *     &lt;extension base="{}NodeType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;group ref="{}NodeElementGroup"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GroupType", propOrder = {
    "cameraOrGroupOrLight"
})
@XmlSeeAlso({
    World.class,
    Group.class
})
public class GroupType
    extends NodeType
{

    @XmlElements({
        @XmlElement(name = "SkinnedMesh", type = SkinnedMesh.class),
        @XmlElement(name = "Camera", type = Camera.class),
        @XmlElement(name = "Group", type = Group.class),
        @XmlElement(name = "Mesh", type = Mesh.class),
        @XmlElement(name = "Light", type = Light.class)
    })
    protected List<NodeType> cameraOrGroupOrLight;

    /**
     * Gets the value of the cameraOrGroupOrLight property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cameraOrGroupOrLight property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCameraOrGroupOrLight().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SkinnedMesh }
     * {@link Camera }
     * {@link Group }
     * {@link Mesh }
     * {@link Light }
     * 
     * 
     */
    public List<NodeType> getCameraOrGroupOrLight() {
        if (cameraOrGroupOrLight == null) {
            cameraOrGroupOrLight = new ArrayList<NodeType>();
        }
        return this.cameraOrGroupOrLight;
    }

}
