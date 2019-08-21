//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.10.10 at 12:17:04 PM CEST 
//


package hr.foi.diplomski.rad.generated.rispo.ws.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rispoIzlozenostSuma complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rispoIzlozenostSuma">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authorityLevelBankAustria" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="authorityLevelIUniCreditGroupTotal" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="authorityLevelUniCreditGroupTotal" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="cashGreater1Year" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="cashLess1Year" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="creditLines" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="creditRisk" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="equityAndBondTradingLimits" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="errorMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fiduciaryLoansForLargeExposures" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="guarantees" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="issuerRisk" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="issuerRiskAndDerivates" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="permanentHoldings" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="preSettlement" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="thereofCommited" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="thereofHedgedByCDS" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="thereofHedgedByCDS_II" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="thereofUnsecuredPortion" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="thereofUnsecuredPortionBankAustria" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *         &lt;element name="thereofUnsecuredPortion_II" type="{http://main/}rispoIzlozenostSumaIznos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rispoIzlozenostSuma", propOrder = {
    "authorityLevelBankAustria",
    "authorityLevelIUniCreditGroupTotal",
    "authorityLevelUniCreditGroupTotal",
    "cashGreater1Year",
    "cashLess1Year",
    "creditLines",
    "creditRisk",
    "equityAndBondTradingLimits",
    "error",
    "errorMsg",
    "fiduciaryLoansForLargeExposures",
    "guarantees",
    "issuerRisk",
    "issuerRiskAndDerivates",
    "permanentHoldings",
    "preSettlement",
    "thereofCommited",
    "thereofHedgedByCDS",
    "thereofHedgedByCDSII",
    "thereofUnsecuredPortion",
    "thereofUnsecuredPortionBankAustria",
    "thereofUnsecuredPortionII"
})
public class RispoIzlozenostSuma {

    protected RispoIzlozenostSumaIznos authorityLevelBankAustria;
    protected RispoIzlozenostSumaIznos authorityLevelIUniCreditGroupTotal;
    protected RispoIzlozenostSumaIznos authorityLevelUniCreditGroupTotal;
    protected RispoIzlozenostSumaIznos cashGreater1Year;
    protected RispoIzlozenostSumaIznos cashLess1Year;
    protected RispoIzlozenostSumaIznos creditLines;
    protected RispoIzlozenostSumaIznos creditRisk;
    protected RispoIzlozenostSumaIznos equityAndBondTradingLimits;
    protected boolean error;
    protected String errorMsg;
    protected RispoIzlozenostSumaIznos fiduciaryLoansForLargeExposures;
    protected RispoIzlozenostSumaIznos guarantees;
    protected RispoIzlozenostSumaIznos issuerRisk;
    protected RispoIzlozenostSumaIznos issuerRiskAndDerivates;
    protected RispoIzlozenostSumaIznos permanentHoldings;
    protected RispoIzlozenostSumaIznos preSettlement;
    protected RispoIzlozenostSumaIznos thereofCommited;
    protected RispoIzlozenostSumaIznos thereofHedgedByCDS;
    @XmlElement(name = "thereofHedgedByCDS_II")
    protected RispoIzlozenostSumaIznos thereofHedgedByCDSII;
    protected RispoIzlozenostSumaIznos thereofUnsecuredPortion;
    protected RispoIzlozenostSumaIznos thereofUnsecuredPortionBankAustria;
    @XmlElement(name = "thereofUnsecuredPortion_II")
    protected RispoIzlozenostSumaIznos thereofUnsecuredPortionII;

    /**
     * Gets the value of the authorityLevelBankAustria property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getAuthorityLevelBankAustria() {
        return authorityLevelBankAustria;
    }

    /**
     * Sets the value of the authorityLevelBankAustria property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setAuthorityLevelBankAustria(RispoIzlozenostSumaIznos value) {
        this.authorityLevelBankAustria = value;
    }

    /**
     * Gets the value of the authorityLevelIUniCreditGroupTotal property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getAuthorityLevelIUniCreditGroupTotal() {
        return authorityLevelIUniCreditGroupTotal;
    }

    /**
     * Sets the value of the authorityLevelIUniCreditGroupTotal property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setAuthorityLevelIUniCreditGroupTotal(RispoIzlozenostSumaIznos value) {
        this.authorityLevelIUniCreditGroupTotal = value;
    }

    /**
     * Gets the value of the authorityLevelUniCreditGroupTotal property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getAuthorityLevelUniCreditGroupTotal() {
        return authorityLevelUniCreditGroupTotal;
    }

    /**
     * Sets the value of the authorityLevelUniCreditGroupTotal property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setAuthorityLevelUniCreditGroupTotal(RispoIzlozenostSumaIznos value) {
        this.authorityLevelUniCreditGroupTotal = value;
    }

    /**
     * Gets the value of the cashGreater1Year property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getCashGreater1Year() {
        return cashGreater1Year;
    }

    /**
     * Sets the value of the cashGreater1Year property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setCashGreater1Year(RispoIzlozenostSumaIznos value) {
        this.cashGreater1Year = value;
    }

    /**
     * Gets the value of the cashLess1Year property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getCashLess1Year() {
        return cashLess1Year;
    }

    /**
     * Sets the value of the cashLess1Year property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setCashLess1Year(RispoIzlozenostSumaIznos value) {
        this.cashLess1Year = value;
    }

    /**
     * Gets the value of the creditLines property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getCreditLines() {
        return creditLines;
    }

    /**
     * Sets the value of the creditLines property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setCreditLines(RispoIzlozenostSumaIznos value) {
        this.creditLines = value;
    }

    /**
     * Gets the value of the creditRisk property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getCreditRisk() {
        return creditRisk;
    }

    /**
     * Sets the value of the creditRisk property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setCreditRisk(RispoIzlozenostSumaIznos value) {
        this.creditRisk = value;
    }

    /**
     * Gets the value of the equityAndBondTradingLimits property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getEquityAndBondTradingLimits() {
        return equityAndBondTradingLimits;
    }

    /**
     * Sets the value of the equityAndBondTradingLimits property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setEquityAndBondTradingLimits(RispoIzlozenostSumaIznos value) {
        this.equityAndBondTradingLimits = value;
    }

    /**
     * Gets the value of the error property.
     *
     */
    public boolean isError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     *
     */
    public void setError(boolean value) {
        this.error = value;
    }

    /**
     * Gets the value of the errorMsg property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Sets the value of the errorMsg property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setErrorMsg(String value) {
        this.errorMsg = value;
    }

    /**
     * Gets the value of the fiduciaryLoansForLargeExposures property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getFiduciaryLoansForLargeExposures() {
        return fiduciaryLoansForLargeExposures;
    }

    /**
     * Sets the value of the fiduciaryLoansForLargeExposures property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setFiduciaryLoansForLargeExposures(RispoIzlozenostSumaIznos value) {
        this.fiduciaryLoansForLargeExposures = value;
    }

    /**
     * Gets the value of the guarantees property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getGuarantees() {
        return guarantees;
    }

    /**
     * Sets the value of the guarantees property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setGuarantees(RispoIzlozenostSumaIznos value) {
        this.guarantees = value;
    }

    /**
     * Gets the value of the issuerRisk property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getIssuerRisk() {
        return issuerRisk;
    }

    /**
     * Sets the value of the issuerRisk property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setIssuerRisk(RispoIzlozenostSumaIznos value) {
        this.issuerRisk = value;
    }

    /**
     * Gets the value of the issuerRiskAndDerivates property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getIssuerRiskAndDerivates() {
        return issuerRiskAndDerivates;
    }

    /**
     * Sets the value of the issuerRiskAndDerivates property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setIssuerRiskAndDerivates(RispoIzlozenostSumaIznos value) {
        this.issuerRiskAndDerivates = value;
    }

    /**
     * Gets the value of the permanentHoldings property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getPermanentHoldings() {
        return permanentHoldings;
    }

    /**
     * Sets the value of the permanentHoldings property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setPermanentHoldings(RispoIzlozenostSumaIznos value) {
        this.permanentHoldings = value;
    }

    /**
     * Gets the value of the preSettlement property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getPreSettlement() {
        return preSettlement;
    }

    /**
     * Sets the value of the preSettlement property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setPreSettlement(RispoIzlozenostSumaIznos value) {
        this.preSettlement = value;
    }

    /**
     * Gets the value of the thereofCommited property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getThereofCommited() {
        return thereofCommited;
    }

    /**
     * Sets the value of the thereofCommited property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setThereofCommited(RispoIzlozenostSumaIznos value) {
        this.thereofCommited = value;
    }

    /**
     * Gets the value of the thereofHedgedByCDS property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getThereofHedgedByCDS() {
        return thereofHedgedByCDS;
    }

    /**
     * Sets the value of the thereofHedgedByCDS property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setThereofHedgedByCDS(RispoIzlozenostSumaIznos value) {
        this.thereofHedgedByCDS = value;
    }

    /**
     * Gets the value of the thereofHedgedByCDSII property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getThereofHedgedByCDSII() {
        return thereofHedgedByCDSII;
    }

    /**
     * Sets the value of the thereofHedgedByCDSII property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setThereofHedgedByCDSII(RispoIzlozenostSumaIznos value) {
        this.thereofHedgedByCDSII = value;
    }

    /**
     * Gets the value of the thereofUnsecuredPortion property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getThereofUnsecuredPortion() {
        return thereofUnsecuredPortion;
    }

    /**
     * Sets the value of the thereofUnsecuredPortion property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setThereofUnsecuredPortion(RispoIzlozenostSumaIznos value) {
        this.thereofUnsecuredPortion = value;
    }

    /**
     * Gets the value of the thereofUnsecuredPortionBankAustria property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getThereofUnsecuredPortionBankAustria() {
        return thereofUnsecuredPortionBankAustria;
    }

    /**
     * Sets the value of the thereofUnsecuredPortionBankAustria property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public void setThereofUnsecuredPortionBankAustria(RispoIzlozenostSumaIznos value) {
        this.thereofUnsecuredPortionBankAustria = value;
    }

    /**
     * Gets the value of the thereofUnsecuredPortionII property.
     *
     * @return
     *     possible object is
     *     {@link RispoIzlozenostSumaIznos }
     *
     */
    public RispoIzlozenostSumaIznos getThereofUnsecuredPortionII() {
        return thereofUnsecuredPortionII;
    }

    /**
     * Sets the value of the thereofUnsecuredPortionII property.
     *
     * @param value
     *     allowed object is
     *     {@link RispoIzlozenostSumaIznos }
     *     
     */
    public void setThereofUnsecuredPortionII(RispoIzlozenostSumaIznos value) {
        this.thereofUnsecuredPortionII = value;
    }

}