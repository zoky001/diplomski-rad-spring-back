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
 * <p>Java class for podrucjeKanal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="podrucjeKanal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idKlijent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kanal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nadleznost" type="{http://service.zok.zaba.hr/}kanalNadleznost" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="podrucje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipPromjene" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vaziDo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vaziOd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "podrucjeKanal", propOrder = {
    "idKlijent",
    "kanal",
    "nadleznost",
    "podrucje",
    "tipPromjene",
    "vaziDo",
    "vaziOd"
})
public class PodrucjeKanal {

    protected String idKlijent;
    protected String kanal;
    @XmlElement(nillable = true)
    protected List<KanalNadleznost> nadleznost;
    protected String podrucje;
    protected String tipPromjene;
    protected String vaziDo;
    protected String vaziOd;

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
     * Gets the value of the kanal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKanal() {
        return kanal;
    }

    /**
     * Sets the value of the kanal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKanal(String value) {
        this.kanal = value;
    }

    /**
     * Gets the value of the nadleznost property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nadleznost property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNadleznost().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KanalNadleznost }
     * 
     * 
     */
    public List<KanalNadleznost> getNadleznost() {
        if (nadleznost == null) {
            nadleznost = new ArrayList<KanalNadleznost>();
        }
        return this.nadleznost;
    }

    /**
     * Gets the value of the podrucje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodrucje() {
        return podrucje;
    }

    /**
     * Sets the value of the podrucje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodrucje(String value) {
        this.podrucje = value;
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
     * Gets the value of the vaziDo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVaziDo() {
        return vaziDo;
    }

    /**
     * Sets the value of the vaziDo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVaziDo(String value) {
        this.vaziDo = value;
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

}
