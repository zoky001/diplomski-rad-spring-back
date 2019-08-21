//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.klijent.servis.client.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for djelatnost complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="djelatnost">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="djelat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idKlijent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="naziv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proizvUsl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redBr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="registrirana" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tip" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipPromjene" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="udio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="vrstaPoslovanja" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="vrstaPoslovanjaTip" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "djelatnost", propOrder = {
    "djelat",
    "idKlijent",
    "naziv",
    "opis",
    "proizvUsl",
    "redBr",
    "registrirana",
    "tip",
    "tipPromjene",
    "udio",
    "vrstaPoslovanja",
    "vrstaPoslovanjaTip"
})
public class Djelatnost {

    protected String djelat;
    protected String idKlijent;
    protected String naziv;
    protected String opis;
    protected String proizvUsl;
    protected Integer redBr;
    protected Boolean registrirana;
    protected Integer tip;
    protected String tipPromjene;
    protected BigDecimal udio;
    protected Integer vrstaPoslovanja;
    protected Integer vrstaPoslovanjaTip;

    /**
     * Gets the value of the djelat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDjelat() {
        return djelat;
    }

    /**
     * Sets the value of the djelat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDjelat(String value) {
        this.djelat = value;
    }

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
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaziv(String value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the opis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the value of the opis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpis(String value) {
        this.opis = value;
    }

    /**
     * Gets the value of the proizvUsl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProizvUsl() {
        return proizvUsl;
    }

    /**
     * Sets the value of the proizvUsl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProizvUsl(String value) {
        this.proizvUsl = value;
    }

    /**
     * Gets the value of the redBr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRedBr() {
        return redBr;
    }

    /**
     * Sets the value of the redBr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRedBr(Integer value) {
        this.redBr = value;
    }

    /**
     * Gets the value of the registrirana property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRegistrirana() {
        return registrirana;
    }

    /**
     * Sets the value of the registrirana property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRegistrirana(Boolean value) {
        this.registrirana = value;
    }

    /**
     * Gets the value of the tip property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTip() {
        return tip;
    }

    /**
     * Sets the value of the tip property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTip(Integer value) {
        this.tip = value;
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
     * Gets the value of the udio property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUdio() {
        return udio;
    }

    /**
     * Sets the value of the udio property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUdio(BigDecimal value) {
        this.udio = value;
    }

    /**
     * Gets the value of the vrstaPoslovanja property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVrstaPoslovanja() {
        return vrstaPoslovanja;
    }

    /**
     * Sets the value of the vrstaPoslovanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVrstaPoslovanja(Integer value) {
        this.vrstaPoslovanja = value;
    }

    /**
     * Gets the value of the vrstaPoslovanjaTip property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVrstaPoslovanjaTip() {
        return vrstaPoslovanjaTip;
    }

    /**
     * Sets the value of the vrstaPoslovanjaTip property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVrstaPoslovanjaTip(Integer value) {
        this.vrstaPoslovanjaTip = value;
    }

}