//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.povos.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getGroupData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getGroupData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kpo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="br_registra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getGroupData", propOrder = {
    "kpo",
    "brRegistra"
})
public class GetGroupData {

    protected String kpo;
    @XmlElement(name = "br_registra")
    protected String brRegistra;

    /**
     * Gets the value of the kpo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKpo() {
        return kpo;
    }

    /**
     * Sets the value of the kpo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKpo(String value) {
        this.kpo = value;
    }

    /**
     * Gets the value of the brRegistra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrRegistra() {
        return brRegistra;
    }

    /**
     * Sets the value of the brRegistra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrRegistra(String value) {
        this.brRegistra = value;
    }

}
