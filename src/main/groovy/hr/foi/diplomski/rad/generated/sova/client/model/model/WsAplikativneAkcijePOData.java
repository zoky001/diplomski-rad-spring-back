//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.sova.client.model.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsAplikativneAkcijePOData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsAplikativneAkcijePOData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sifraAkcije" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sifraPO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipAkcije" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipPO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsAplikativneAkcijePOData", propOrder = {
    "sifraAkcije",
    "sifraPO",
    "tipAkcije",
    "tipPO"
})
public class WsAplikativneAkcijePOData {

    protected String sifraAkcije;
    protected String sifraPO;
    protected String tipAkcije;
    protected String tipPO;

    /**
     * Gets the value of the sifraAkcije property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraAkcije() {
        return sifraAkcije;
    }

    /**
     * Sets the value of the sifraAkcije property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraAkcije(String value) {
        this.sifraAkcije = value;
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
     * Gets the value of the tipAkcije property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipAkcije() {
        return tipAkcije;
    }

    /**
     * Sets the value of the tipAkcije property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipAkcije(String value) {
        this.tipAkcije = value;
    }

    /**
     * Gets the value of the tipPO property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipPO() {
        return tipPO;
    }

    /**
     * Sets the value of the tipPO property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipPO(String value) {
        this.tipPO = value;
    }

}
