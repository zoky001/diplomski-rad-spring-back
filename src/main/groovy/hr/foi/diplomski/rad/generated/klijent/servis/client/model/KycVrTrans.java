//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.klijent.servis.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for kycVrTrans complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="kycVrTrans">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idKlijent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipPromjene" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vaziOd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vrTrans" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "kycVrTrans", propOrder = {
    "idKlijent",
    "tipPromjene",
    "vaziOd",
    "vrTrans"
})
public class KycVrTrans {

    protected String idKlijent;
    protected String tipPromjene;
    protected String vaziOd;
    protected Integer vrTrans;

    /**
     * Gets the value of the idKlijent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdKlijent() {
        return idKlijent;
    }

    /**
     * Sets the value of the idKlijent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdKlijent(String value) {
        this.idKlijent = value;
    }

    /**
     * Gets the value of the tipPromjene property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipPromjene() {
        return tipPromjene;
    }

    /**
     * Sets the value of the tipPromjene property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipPromjene(String value) {
        this.tipPromjene = value;
    }

    /**
     * Gets the value of the vaziOd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVaziOd() {
        return vaziOd;
    }

    /**
     * Sets the value of the vaziOd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVaziOd(String value) {
        this.vaziOd = value;
    }

    /**
     * Gets the value of the vrTrans property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVrTrans() {
        return vrTrans;
    }

    /**
     * Sets the value of the vrTrans property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVrTrans(Integer value) {
        this.vrTrans = value;
    }

}
