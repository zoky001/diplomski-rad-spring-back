//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.irws.client.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for izracunajUclcRejting complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="izracunajUclcRejting">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brRegistra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aops" type="{http://service.irws.zaba.hr/}aop" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "izracunajUclcRejting", propOrder = {
    "brRegistra",
    "aops"
})
public class IzracunajUclcRejting {

    protected String brRegistra;
    protected List<Aop> aops;

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

}
