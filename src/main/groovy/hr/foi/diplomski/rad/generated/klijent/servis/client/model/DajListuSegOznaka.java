//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.klijent.servis.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dajListuSegOznaka complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dajListuSegOznaka">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="oznTabele" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dajListuSegOznaka", propOrder = {
    "oznTabele",
    "oj",
    "segment"
})
public class DajListuSegOznaka {

    protected String oznTabele;
    protected String oj;
    protected String segment;

    /**
     * Gets the value of the oznTabele property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOznTabele() {
        return oznTabele;
    }

    /**
     * Sets the value of the oznTabele property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOznTabele(String value) {
        this.oznTabele = value;
    }

    /**
     * Gets the value of the oj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOj() {
        return oj;
    }

    /**
     * Sets the value of the oj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOj(String value) {
        this.oj = value;
    }

    /**
     * Gets the value of the segment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegment() {
        return segment;
    }

    /**
     * Sets the value of the segment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegment(String value) {
        this.segment = value;
    }

}