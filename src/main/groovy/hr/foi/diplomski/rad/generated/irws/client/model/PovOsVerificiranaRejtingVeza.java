//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.irws.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for povOsVerificiranaRejtingVeza complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="povOsVerificiranaRejtingVeza">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brRegistra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brojRadnika" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "povOsVerificiranaRejtingVeza", propOrder = {
    "brRegistra",
    "brojRadnika"
})
public class PovOsVerificiranaRejtingVeza {

    protected String brRegistra;
    protected String brojRadnika;

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

}
