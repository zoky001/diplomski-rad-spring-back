//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.irws.client.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gfiPodaci complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gfiPodaci">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aops" type="{http://service.irws.zaba.hr/}aop" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="godina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gfiPodaci", propOrder = {
    "aops",
    "godina",
    "id",
    "shema"
})
public class GfiPodaci {

    @XmlElement(nillable = true)
    protected List<Aop> aops;
    protected String godina;
    protected String id;
    protected String shema;

    /**
     * Gets the value of the aops property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aops property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAops().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Aop }
     * 
     * 
     */
    public List<Aop> getAops() {
        if (aops == null) {
            aops = new ArrayList<Aop>();
        }
        return this.aops;
    }

    /**
     * Gets the value of the godina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGodina() {
        return godina;
    }

    /**
     * Sets the value of the godina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGodina(String value) {
        this.godina = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the shema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShema() {
        return shema;
    }

    /**
     * Sets the value of the shema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShema(String value) {
        this.shema = value;
    }

}
