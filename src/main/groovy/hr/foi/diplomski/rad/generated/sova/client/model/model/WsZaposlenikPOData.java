//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.sova.client.model.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsZaposlenikPOData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsZaposlenikPOData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brojRadnika" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iznosPO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kombinacijaPO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prezime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sifraPO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iDPO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsZaposlenikPOData", propOrder = {
    "brojRadnika",
    "ime",
    "iznosPO",
    "kombinacijaPO",
    "prezime",
    "sifraPO",
    "idpo"
})
public class WsZaposlenikPOData {

    protected String brojRadnika;
    protected String ime;
    protected String iznosPO;
    protected String kombinacijaPO;
    protected String prezime;
    protected String sifraPO;
    @XmlElement(name = "iDPO")
    protected String idpo;

    /**
     * Gets the value of the brojRadnika property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrojRadnika() {
        return brojRadnika;
    }

    /**
     * Sets the value of the brojRadnika property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojRadnika(String value) {
        this.brojRadnika = value;
    }

    /**
     * Gets the value of the ime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the iznosPO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIznosPO() {
        return iznosPO;
    }

    /**
     * Sets the value of the iznosPO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIznosPO(String value) {
        this.iznosPO = value;
    }

    /**
     * Gets the value of the kombinacijaPO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKombinacijaPO() {
        return kombinacijaPO;
    }

    /**
     * Sets the value of the kombinacijaPO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKombinacijaPO(String value) {
        this.kombinacijaPO = value;
    }

    /**
     * Gets the value of the prezime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Sets the value of the prezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrezime(String value) {
        this.prezime = value;
    }

    /**
     * Gets the value of the sifraPO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraPO() {
        return sifraPO;
    }

    /**
     * Sets the value of the sifraPO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraPO(String value) {
        this.sifraPO = value;
    }

    /**
     * Gets the value of the idpo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDPO() {
        return idpo;
    }

    /**
     * Sets the value of the idpo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPO(String value) {
        this.idpo = value;
    }

}
