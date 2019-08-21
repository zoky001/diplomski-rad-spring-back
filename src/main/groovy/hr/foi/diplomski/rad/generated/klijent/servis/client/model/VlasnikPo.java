//
// Generated By:JAX-WS RI IBM 2.2.1-07/09/2014 01:53 PM(foreman)- (JAXB RI IBM 2.2.3-07/07/2014 12:56 PM(foreman)-)
//


package hr.foi.diplomski.rad.generated.klijent.servis.client.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for vlasnikPo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vlasnikPo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idKlijent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idVlasnik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nazivBurze" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nazivBurzeOstalo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nazivTijela" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nominalniIznDionice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="oib" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oznakaDionice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redniBroj" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipPromjene" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipVlasnika" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ukBrojDionica" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="vaziDo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vaziOd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vlasnikDrzava" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vlasnikNaziv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vlasnikPo", propOrder = {
    "idKlijent",
    "idVlasnik",
    "nazivBurze",
    "nazivBurzeOstalo",
    "nazivTijela",
    "nominalniIznDionice",
    "oib",
    "oznakaDionice",
    "redniBroj",
    "tipPromjene",
    "tipVlasnika",
    "ukBrojDionica",
    "vaziDo",
    "vaziOd",
    "vlasnikDrzava",
    "vlasnikNaziv"
})
public class VlasnikPo {

    protected String idKlijent;
    protected String idVlasnik;
    protected Integer nazivBurze;
    protected String nazivBurzeOstalo;
    protected String nazivTijela;
    protected BigDecimal nominalniIznDionice;
    protected String oib;
    protected String oznakaDionice;
    protected Integer redniBroj;
    protected String tipPromjene;
    protected String tipVlasnika;
    protected Integer ukBrojDionica;
    protected String vaziDo;
    protected String vaziOd;
    protected String vlasnikDrzava;
    protected String vlasnikNaziv;

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
     * Gets the value of the idVlasnik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdVlasnik() {
        return idVlasnik;
    }

    /**
     * Sets the value of the idVlasnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdVlasnik(String value) {
        this.idVlasnik = value;
    }

    /**
     * Gets the value of the nazivBurze property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNazivBurze() {
        return nazivBurze;
    }

    /**
     * Sets the value of the nazivBurze property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNazivBurze(Integer value) {
        this.nazivBurze = value;
    }

    /**
     * Gets the value of the nazivBurzeOstalo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivBurzeOstalo() {
        return nazivBurzeOstalo;
    }

    /**
     * Sets the value of the nazivBurzeOstalo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivBurzeOstalo(String value) {
        this.nazivBurzeOstalo = value;
    }

    /**
     * Gets the value of the nazivTijela property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivTijela() {
        return nazivTijela;
    }

    /**
     * Sets the value of the nazivTijela property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivTijela(String value) {
        this.nazivTijela = value;
    }

    /**
     * Gets the value of the nominalniIznDionice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNominalniIznDionice() {
        return nominalniIznDionice;
    }

    /**
     * Sets the value of the nominalniIznDionice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNominalniIznDionice(BigDecimal value) {
        this.nominalniIznDionice = value;
    }

    /**
     * Gets the value of the oib property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOib() {
        return oib;
    }

    /**
     * Sets the value of the oib property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOib(String value) {
        this.oib = value;
    }

    /**
     * Gets the value of the oznakaDionice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOznakaDionice() {
        return oznakaDionice;
    }

    /**
     * Sets the value of the oznakaDionice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOznakaDionice(String value) {
        this.oznakaDionice = value;
    }

    /**
     * Gets the value of the redniBroj property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRedniBroj() {
        return redniBroj;
    }

    /**
     * Sets the value of the redniBroj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRedniBroj(Integer value) {
        this.redniBroj = value;
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
     * Gets the value of the tipVlasnika property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipVlasnika() {
        return tipVlasnika;
    }

    /**
     * Sets the value of the tipVlasnika property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipVlasnika(String value) {
        this.tipVlasnika = value;
    }

    /**
     * Gets the value of the ukBrojDionica property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUkBrojDionica() {
        return ukBrojDionica;
    }

    /**
     * Sets the value of the ukBrojDionica property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUkBrojDionica(Integer value) {
        this.ukBrojDionica = value;
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

    /**
     * Gets the value of the vlasnikDrzava property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVlasnikDrzava() {
        return vlasnikDrzava;
    }

    /**
     * Sets the value of the vlasnikDrzava property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVlasnikDrzava(String value) {
        this.vlasnikDrzava = value;
    }

    /**
     * Gets the value of the vlasnikNaziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVlasnikNaziv() {
        return vlasnikNaziv;
    }

    /**
     * Sets the value of the vlasnikNaziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVlasnikNaziv(String value) {
        this.vlasnikNaziv = value;
    }

}