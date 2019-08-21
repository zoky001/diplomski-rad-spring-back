//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.klijent.servis.client.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dokaz complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dokaz">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dokument" type="{http://service.zok.zaba.hr/}dokument" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="labelPolja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="polja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipPolja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vrijednostPolja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dokaz", propOrder = {
    "dokument",
    "labelPolja",
    "polja",
    "tipPolja",
    "vrijednostPolja"
})
public class Dokaz {

    @XmlElement(nillable = true)
    protected List<Dokument> dokument;
    protected String labelPolja;
    protected String polja;
    protected String tipPolja;
    protected String vrijednostPolja;

    /**
     * Gets the value of the dokument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dokument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDokument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dokument }
     * 
     * 
     */
    public List<Dokument> getDokument() {
        if (dokument == null) {
            dokument = new ArrayList<Dokument>();
        }
        return this.dokument;
    }

    /**
     * Gets the value of the labelPolja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelPolja() {
        return labelPolja;
    }

    /**
     * Sets the value of the labelPolja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelPolja(String value) {
        this.labelPolja = value;
    }

    /**
     * Gets the value of the polja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolja() {
        return polja;
    }

    /**
     * Sets the value of the polja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolja(String value) {
        this.polja = value;
    }

    /**
     * Gets the value of the tipPolja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipPolja() {
        return tipPolja;
    }

    /**
     * Sets the value of the tipPolja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipPolja(String value) {
        this.tipPolja = value;
    }

    /**
     * Gets the value of the vrijednostPolja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrijednostPolja() {
        return vrijednostPolja;
    }

    /**
     * Sets the value of the vrijednostPolja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrijednostPolja(String value) {
        this.vrijednostPolja = value;
    }

}