//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.povos.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for uPosOdnosuSBankomResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="uPosOdnosuSBankomResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PosOdnosSBankom" type="{http://service.ze.zaba.hr/}posOdnosSBankom" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uPosOdnosuSBankomResponse", propOrder = {
    "posOdnosSBankom"
})
public class UPosOdnosuSBankomResponse {

    @XmlElement(name = "PosOdnosSBankom")
    protected PosOdnosSBankom posOdnosSBankom;

    /**
     * Gets the value of the posOdnosSBankom property.
     * 
     * @return
     *     possible object is
     *     {@link PosOdnosSBankom }
     *     
     */
    public PosOdnosSBankom getPosOdnosSBankom() {
        return posOdnosSBankom;
    }

    /**
     * Sets the value of the posOdnosSBankom property.
     * 
     * @param value
     *     allowed object is
     *     {@link PosOdnosSBankom }
     *     
     */
    public void setPosOdnosSBankom(PosOdnosSBankom value) {
        this.posOdnosSBankom = value;
    }

}
