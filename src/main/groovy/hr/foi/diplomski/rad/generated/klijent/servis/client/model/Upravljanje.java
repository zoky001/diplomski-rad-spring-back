//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.klijent.servis.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for upravljanje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="upravljanje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datumOdjave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datumPrijave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fo" type="{http://service.zok.zaba.hr/}fizOsoPodaci" minOccurs="0"/>
 *         &lt;element name="funkcija" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idKlijent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idNoUprava" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nacinIdentZakonZastup" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nacinZastupanja" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "upravljanje", propOrder = {
    "datumOdjave",
    "datumPrijave",
    "fo",
    "funkcija",
    "idKlijent",
    "idNoUprava",
    "nacinIdentZakonZastup",
    "nacinZastupanja",
    "tipPromjene",
    "vaziDo",
    "vaziOd"
})
public class Upravljanje {

    protected String datumOdjave;
    protected String datumPrijave;
    protected FizOsoPodaci fo;
    protected Integer funkcija;
    protected String idKlijent;
    protected String idNoUprava;
    protected Integer nacinIdentZakonZastup;
    protected Integer nacinZastupanja;
    protected String tipPromjene;
    protected String vaziDo;
    protected String vaziOd;

    /**
     * Gets the value of the datumOdjave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatumOdjave() {
        return datumOdjave;
    }

    /**
     * Sets the value of the datumOdjave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatumOdjave(String value) {
        this.datumOdjave = value;
    }

    /**
     * Gets the value of the datumPrijave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatumPrijave() {
        return datumPrijave;
    }

    /**
     * Sets the value of the datumPrijave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatumPrijave(String value) {
        this.datumPrijave = value;
    }

    /**
     * Gets the value of the fo property.
     * 
     * @return
     *     possible object is
     *     {@link FizOsoPodaci }
     *     
     */
    public FizOsoPodaci getFo() {
        return fo;
    }

    /**
     * Sets the value of the fo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FizOsoPodaci }
     *     
     */
    public void setFo(FizOsoPodaci value) {
        this.fo = value;
    }

    /**
     * Gets the value of the funkcija property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFunkcija() {
        return funkcija;
    }

    /**
     * Sets the value of the funkcija property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFunkcija(Integer value) {
        this.funkcija = value;
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
     * Gets the value of the idNoUprava property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNoUprava() {
        return idNoUprava;
    }

    /**
     * Sets the value of the idNoUprava property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNoUprava(String value) {
        this.idNoUprava = value;
    }

    /**
     * Gets the value of the nacinIdentZakonZastup property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNacinIdentZakonZastup() {
        return nacinIdentZakonZastup;
    }

    /**
     * Sets the value of the nacinIdentZakonZastup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNacinIdentZakonZastup(Integer value) {
        this.nacinIdentZakonZastup = value;
    }

    /**
     * Gets the value of the nacinZastupanja property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNacinZastupanja() {
        return nacinZastupanja;
    }

    /**
     * Sets the value of the nacinZastupanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNacinZastupanja(Integer value) {
        this.nacinZastupanja = value;
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
