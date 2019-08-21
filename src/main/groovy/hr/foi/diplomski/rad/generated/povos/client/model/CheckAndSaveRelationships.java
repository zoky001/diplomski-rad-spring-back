//
// Generated By:JAX-WS RI IBM 2.2.1-11/28/2011 08:28 AM(foreman)- (JAXB RI IBM 2.2.3-11/28/2011 06:21 AM(foreman)-)
//


package hr.foi.diplomski.rad.generated.povos.client.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for checkAndSaveRelationships complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="checkAndSaveRelationships">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="newRelationships" type="{http://service.ze.zaba.hr/}zokRelationship" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="changedRelationships" type="{http://service.ze.zaba.hr/}zokRelationship" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="closedRelationships" type="{http://service.ze.zaba.hr/}zokRelationship" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="identifiedClient" type="{http://service.ze.zaba.hr/}zokMember" minOccurs="0"/>
 *         &lt;element name="sourceApp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkAndSaveRelationships", propOrder = {
    "newRelationships",
    "changedRelationships",
    "closedRelationships",
    "identifiedClient",
    "sourceApp",
    "userId"
})
public class CheckAndSaveRelationships {

    protected List<ZokRelationship> newRelationships;
    protected List<ZokRelationship> changedRelationships;
    protected List<ZokRelationship> closedRelationships;
    protected ZokMember identifiedClient;
    protected String sourceApp;
    protected String userId;

    /**
     * Gets the value of the newRelationships property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the newRelationships property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNewRelationships().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZokRelationship }
     * 
     * 
     */
    public List<ZokRelationship> getNewRelationships() {
        if (newRelationships == null) {
            newRelationships = new ArrayList<ZokRelationship>();
        }
        return this.newRelationships;
    }

    /**
     * Gets the value of the changedRelationships property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the changedRelationships property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChangedRelationships().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZokRelationship }
     * 
     * 
     */
    public List<ZokRelationship> getChangedRelationships() {
        if (changedRelationships == null) {
            changedRelationships = new ArrayList<ZokRelationship>();
        }
        return this.changedRelationships;
    }

    /**
     * Gets the value of the closedRelationships property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the closedRelationships property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClosedRelationships().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZokRelationship }
     * 
     * 
     */
    public List<ZokRelationship> getClosedRelationships() {
        if (closedRelationships == null) {
            closedRelationships = new ArrayList<ZokRelationship>();
        }
        return this.closedRelationships;
    }

    /**
     * Gets the value of the identifiedClient property.
     * 
     * @return
     *     possible object is
     *     {@link ZokMember }
     *     
     */
    public ZokMember getIdentifiedClient() {
        return identifiedClient;
    }

    /**
     * Sets the value of the identifiedClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZokMember }
     *     
     */
    public void setIdentifiedClient(ZokMember value) {
        this.identifiedClient = value;
    }

    /**
     * Gets the value of the sourceApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceApp() {
        return sourceApp;
    }

    /**
     * Sets the value of the sourceApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceApp(String value) {
        this.sourceApp = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

}
