//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.10 at 12:17:04 PM CEST 
//


package hr.foi.diplomski.rad.generated.rispo.ws.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="criteria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="criteriaType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="oznDevize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brRadnika" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dohvatPoPostojecimClanicama" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getData", propOrder = {
    "criteria",
    "criteriaType",
    "oznDevize",
    "datum",
    "brRadnika",
    "dohvatPoPostojecimClanicama"
})
public class GetData {

    protected String criteria;
    protected int criteriaType;
    protected String oznDevize;
    protected String datum;
    protected String brRadnika;
    protected Boolean dohvatPoPostojecimClanicama;

    /**
     * Gets the value of the criteria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * Sets the value of the criteria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCriteria(String value) {
        this.criteria = value;
    }

    /**
     * Gets the value of the criteriaType property.
     * 
     */
    public int getCriteriaType() {
        return criteriaType;
    }

    /**
     * Sets the value of the criteriaType property.
     * 
     */
    public void setCriteriaType(int value) {
        this.criteriaType = value;
    }

    /**
     * Gets the value of the oznDevize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOznDevize() {
        return oznDevize;
    }

    /**
     * Sets the value of the oznDevize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOznDevize(String value) {
        this.oznDevize = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatum(String value) {
        this.datum = value;
    }

    /**
     * Gets the value of the brRadnika property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrRadnika() {
        return brRadnika;
    }

    /**
     * Sets the value of the brRadnika property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrRadnika(String value) {
        this.brRadnika = value;
    }

    /**
     * Gets the value of the dohvatPoPostojecimClanicama property.
     * 
     */
    public boolean isDohvatPoPostojecimClanicama() {
        return dohvatPoPostojecimClanicama;
    }

    /**
     * Sets the value of the dohvatPoPostojecimClanicama property.
     * 
     */
    public void setDohvatPoPostojecimClanicama(boolean value) {
        this.dohvatPoPostojecimClanicama = value;
    }

}
