//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.klijent.servis.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for podaciOKlijentuResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="podaciOKlijentuResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.zok.zaba.hr/}klijentPodatak" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "podaciOKlijentuResponse", propOrder = {
    "_return"
})
public class PodaciOKlijentuResponse {

    @XmlElement(name = "return")
    protected KlijentPodatak _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link KlijentPodatak }
     *     
     */
    public KlijentPodatak getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link KlijentPodatak }
     *     
     */
    public void setReturn(KlijentPodatak value) {
        this._return = value;
    }

}
