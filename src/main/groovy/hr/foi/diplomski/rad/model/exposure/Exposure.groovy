package hr.foi.diplomski.rad.model.exposure

import java.math.MathContext

import org.joda.time.DateTime

import groovy.json.StringEscapeUtils
import groovy.transform.ToString
import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.core.model.CodebookEntry
import wiremock.com.google.common.base.Strings


@TypeChecked
@ToString
class Exposure {

    final static MathContext mt = new MathContext(16, java.math.RoundingMode.HALF_UP)

    Long id
    String brojPartije
    String brojUgovora
    String brojLimita
    String brojOkvira

    BigDecimal balanceHrk
    BigDecimal balanceEur
    BigDecimal changeHrk
    BigDecimal changeEur

    List<Collateral> collaterals
    boolean commited
    InterestRateReference interestRate
    String intRate
    String fees
    boolean lessThanYear
    CodebookEntry plasmanType
    BigDecimal previousHrk
    BigDecimal previousEur
    BigDecimal proposedHrk
    BigDecimal proposedEur
    CodebookEntry riskClass
    BigDecimal securedBalanceHrk
    BigDecimal securedBalanceEur
    BigDecimal securedPreviousHrk
    BigDecimal securedPreviousEur
    BigDecimal securedProposedHrk
    BigDecimal securedProposedEur
    String source
    BigDecimal spread
    CodebookEntry taker
    String tenor
    String typeOfCredit
    boolean selected
    boolean isNew
    Long clientId
    String owner
    boolean grouped
    Long groupedExposureId
    Long groupedClientId
    int tip

    BigDecimal newChange
    BigDecimal newBalance
    BigDecimal newPrevious
    BigDecimal newProposed
    BigDecimal newSecuredBalance
    BigDecimal newSecuredPrevious
    BigDecimal newSecuredProposed
    
    boolean tenorLaterThanToday 

    Exposure() {
	balanceHrk = new BigDecimal(0)
	changeHrk = new BigDecimal(0)
	previousHrk = new BigDecimal(0)
	proposedHrk = new BigDecimal(0)
	securedBalanceHrk = new BigDecimal(0)
	securedPreviousHrk = new BigDecimal(0)
	securedProposedHrk = new BigDecimal(0)

	balanceEur = new BigDecimal(0)
	changeEur = new BigDecimal(0)
	previousEur = new BigDecimal(0)
	proposedEur = new BigDecimal(0)
	securedBalanceEur = new BigDecimal(0)
	securedPreviousEur = new BigDecimal(0)
	securedProposedEur = new BigDecimal(0)

	newChange = new BigDecimal(0)
	newBalance = new BigDecimal(0)
	newPrevious = new BigDecimal(0)
	newProposed = new BigDecimal(0)
	newSecuredBalance = new BigDecimal(0)
	newSecuredPrevious = new BigDecimal(0)
	newSecuredProposed = new BigDecimal(0)

	spread = new BigDecimal(0)
	collaterals = new ArrayList<Collateral>()
	tip = 99//polje po kojem se sortira - ru�ni unos na kraj
	source = "MI"//manual input
    }

    void add(Exposure e) {

	if (e.balanceHrk != null) {
	    this.balanceHrk = this.balanceHrk.add(e.balanceHrk)
	}

	if (e.changeHrk != null) {
	    this.changeHrk = this.changeHrk.add(e.changeHrk)
	}

	if (e.getPreviousHrk() != null) {
	    this.setPreviousHrk(this.getPreviousHrk().add(e.getPreviousHrk()))
	}

	if (e.getProposedHrk() != null) {
	    this.setProposedHrk(this.getProposedHrk().add(e.getProposedHrk()))
	}

	if (e.getSecuredBalanceHrk() != null) {
	    this.setSecuredBalanceHrk(this.getSecuredBalanceHrk().add(e.getSecuredBalanceHrk()))
	}

	if (e.getSecuredPreviousHrk() != null) {
	    this.setSecuredPreviousHrk(this.getSecuredPreviousHrk().add(e.getSecuredPreviousHrk()))
	}

	if (e.getSecuredProposedHrk() != null) {
	    this.setSecuredProposedHrk(this.getSecuredProposedHrk().add(e.getSecuredProposedHrk()))
	}

	if (e.getBalanceEur() != null) {
	    this.setBalanceEur(this.getBalanceEur().add(e.getBalanceEur()))
	}

	if (e.getChangeEur() != null) {
	    this.setChangeEur(this.getChangeEur().add(e.getChangeEur()))
	}

	if (e.getPreviousEur() != null) {
	    this.setPreviousEur(this.getPreviousEur().add(e.getPreviousEur()))
	}

	if (e.getProposedEur() != null) {
	    this.setProposedEur(this.getProposedEur().add(e.getProposedEur()))
	}

	if (e.getSecuredBalanceEur() != null) {
	    this.setSecuredBalanceEur(this.getSecuredBalanceEur().add(e.getSecuredBalanceEur()))
	}

	if (e.getSecuredPreviousEur() != null) {
	    this.setSecuredPreviousEur(this.getSecuredPreviousEur().add(e.getSecuredPreviousEur()))
	}

	if (e.getSecuredProposedEur() != null) {
	    this.setSecuredProposedEur(this.getSecuredProposedEur().add(e.getSecuredProposedEur()))
	}
    }

    String getConditions() {
	StringBuilder builder = new StringBuilder()
	if (getIntRate() != null) {
	    builder.append(getIntRate())
	}
	if (!Strings.isNullOrEmpty(getFees())) {
	    if (builder.length() != 0) {
		builder.append("; " + getFees())
	    } else {
		builder.append(getFees())
	    }
	}
	if (getSpread() != null) {
	    if (builder.length() != 0) {
		builder.append("; " + getSpread())
	    } else {
		builder.append(getSpread())
	    }
	}
	return StringEscapeUtils.unescapeJavaScript(builder.toString())
    }

    void addCollateral(Collateral c) {
	if (collaterals == null) {
	    collaterals = new ArrayList<Collateral>()
	}
	collaterals.add(c)
    }

    void setCollaterals(List<Collateral> c) {
	this.collaterals = c
    }

    void removeCollateral(Collateral c) {
	collaterals.remove(c)
    }

    String getLessThanYearAsString() {
	if (tenor == null)
	    return "-"
	if (lessThanYear) {
	    return "Y"
	} else {
	    return "N"
	}
    }

    void refreshSecuredValues(){
	updateSecurePrevious()
	updateSecuredBalance()
    }

    void updateSecurePrevious() {
	BigDecimal sPrevious = calculateSecuredPreviousHrk()
	if (previousHrk != null && sPrevious.compareTo(previousHrk) == 1) {
	    securedPreviousHrk = new BigDecimal(previousHrk.doubleValue())
	} else {
	    securedPreviousHrk = sPrevious
	}

	sPrevious = calculateSecuredPreviousEur()
	if (previousEur != null && sPrevious.compareTo(previousEur) == 1) {
	    securedPreviousEur = new BigDecimal(previousEur.doubleValue())
	} else {
	    securedPreviousEur = sPrevious
	}
    }

    void updateSecuredBalance() {
	BigDecimal sBalance = calculateSecuredPreviousHrk()
	if (balanceHrk != null && sBalance.compareTo(balanceHrk) == 1) {
	    securedBalanceHrk = new BigDecimal(balanceHrk.doubleValue())
	} else {
	    securedBalanceHrk = sBalance
	}

	sBalance = calculateSecuredPreviousEur()
	if (balanceEur != null && sBalance.compareTo(balanceEur) == 1) {
	    securedBalanceEur = new BigDecimal(balanceEur.doubleValue())
	} else {
	    securedBalanceEur = sBalance
	}
    }

    BigDecimal calculateSecuredPreviousHrk() {
	BigDecimal sPrevious = new BigDecimal(0)
	for (Collateral c : collaterals) {
	    sPrevious = sPrevious.add(c.getValueHrk())
	}
	return sPrevious
    }

    BigDecimal calculateSecuredPreviousEur() {
	BigDecimal sPrevious = new BigDecimal(0)
	for (Collateral c : collaterals) {
	    sPrevious = sPrevious.add(c.getValueHrk())
	}
	return sPrevious
    }

    boolean isDisplayed() {
	return (atLeastOneAmountGreaterThanZero() || isTenorLaterThanToday())
    }

    boolean atLeastOneAmountGreaterThanZero() {
	return (changeHrk.compareTo(BigDecimal.ZERO) > 0) || (proposedHrk.compareTo(BigDecimal.ZERO) > 0) || (previousHrk.compareTo(BigDecimal.ZERO) > 0) || (balanceHrk.compareTo(BigDecimal.ZERO) > 0)
    }

    boolean isTenorLaterThanToday(){
	if (tenor != null) {

	    GregorianCalendar tenorDate = stringToDate2(tenor)
	    GregorianCalendar todayDate = new GregorianCalendar()

	    return todayDate.before(tenorDate)
	}
	else {
	    return true
	}
    }

    GregorianCalendar stringToDate2(String strDate) {
	// primjer ulaza: 13.04.2016
	GregorianCalendar date

	if (strDate != null && strDate.size() > 0 && strDate.size() == 10) {
	    int day, month, year

	    year = Integer.parseInt(strDate.substring(6, 10))
	    month = Integer.parseInt(strDate.substring(3, 5))
	    day = Integer.parseInt(strDate.substring(0, 2))

	    date = new GregorianCalendar(year, month - 1, day)
	}

	return date
    }

    void setNewPlacementsInHrk() {
	this.newBalance = balanceHrk
	this.newChange = changeHrk
	this.newPrevious = previousHrk
	this.newProposed =  proposedHrk
	this.newSecuredBalance = securedBalanceHrk
	this.newSecuredPrevious = securedPreviousHrk
	this.newSecuredProposed = securedProposedHrk
    }

    void setNewPlacementsInEur() {
	this.newBalance = balanceEur
	this.newChange = changeEur
	this.newPrevious = previousEur
	this.newProposed =  proposedEur
	this.newSecuredBalance = securedBalanceEur
	this.newSecuredPrevious = securedPreviousEur
	this.newSecuredProposed = securedProposedEur  //NT
    }

    boolean isAnyPlacementChanged(String currency) {
	if("HRK".equals(currency)) {
	    return isAnyPlacementChangedHrk()
	} else if("EUR".equals(currency)) {
	    return isAnyPlacementChangedEur()
	} else {
	    return false
	}
    }

    boolean isAnyPlacementChangedHrk() {
	return newBalance.compareTo(balanceHrk) != 0 ||
		newChange.compareTo(changeHrk) != 0 ||
		newPrevious.compareTo(previousHrk) != 0 ||
		newProposed.compareTo(proposedHrk) != 0 ||
		newSecuredBalance.compareTo(securedBalanceHrk) != 0 ||
		newSecuredProposed.compareTo(securedProposedHrk) != 0 ||
		newSecuredPrevious.compareTo(securedPreviousHrk) != 0
    }

    boolean isAnyPlacementChangedEur() {
	return newBalance.compareTo(balanceEur) != 0 ||
		newChange.compareTo(changeEur) != 0 ||
		newPrevious.compareTo(previousEur) != 0 ||
		newProposed.compareTo(proposedEur) != 0 ||
		newSecuredBalance.compareTo(securedBalanceEur) != 0 ||
		newSecuredProposed.compareTo(securedProposedEur) != 0 ||
		newSecuredPrevious.compareTo(securedPreviousEur) != 0
    }

    void updateAllPlacement(BigDecimal tecaj, String currency) {
	if("HRK".equals(currency)) {
	    convertPlacementsInEur(tecaj)
	    updatePlacementsInHrk()
	} else if("EUR".equals(currency)) {
	    convertPlacementsInHrk(tecaj)
	    updatePlacementsInEur()
	} else {
	    return
	}
    }

    void updatePlacementsInHrk() {
	// NT dodana provjera za sva secured polja
	changeHrk = newChange
	balanceHrk = newBalance
	previousHrk = newPrevious
	proposedHrk = newPrevious.add(newChange)// NT

	securedBalanceHrk = newSecuredBalance
	securedPreviousHrk = newSecuredPrevious
	securedProposedHrk = newSecuredProposed

	if(newSecuredBalance.compareTo(balanceHrk) <= 0)  { securedBalanceHrk = newSecuredBalance }

	else { securedBalanceHrk = balanceHrk }

	if(newSecuredPrevious.compareTo(previousHrk) <= 0)  { securedPreviousHrk = newSecuredPrevious }
	else { securedPreviousHrk = previousHrk }

	if(newSecuredProposed.compareTo(proposedHrk) <= 0)  { securedProposedHrk = newSecuredProposed }
	else { securedProposedHrk = proposedHrk }

    }

    void updatePlacementsInEur() {
	// NT dodana provjera za sva secured polja
	changeEur = newChange

	balanceEur = newBalance
	previousEur = newPrevious
	proposedEur = newPrevious.add(newChange) // NT

	if(newSecuredBalance.compareTo(balanceEur) <= 0)  { securedBalanceEur = newSecuredBalance }
	else  {securedBalanceEur = balanceEur }

	if(newSecuredPrevious.compareTo(previousEur) <= 0)  { securedPreviousEur = newSecuredPrevious }
	else  {securedPreviousEur = previousEur }

	if(newSecuredProposed.compareTo(proposedEur) <= 0)  { securedProposedEur = newSecuredProposed }
	else  {securedProposedEur = proposedEur }
    }

    void convertPlacementsInHrk(BigDecimal tecaj) {
	// NT dodana provjera za sva secured polja
	changeHrk = convertIntoHrk(newChange, tecaj)
	balanceHrk = convertIntoHrk(newBalance, tecaj)
	previousHrk = convertIntoHrk(newPrevious, tecaj)
	proposedHrk = this.previousHrk.add(this.changeHrk) // NT

	securedBalanceHrk = convertIntoHrk(newSecuredBalance, tecaj)
	if (securedBalanceHrk.compareTo(balanceHrk) > 0)  {
	    securedBalanceHrk = balanceHrk
	}

	securedPreviousHrk = convertIntoHrk(newSecuredPrevious, tecaj)
	if (securedPreviousHrk.compareTo(previousHrk) > 0)  {
	    securedPreviousHrk = previousHrk
	}

	securedProposedHrk = convertIntoHrk(newSecuredProposed, tecaj)
	if (securedProposedHrk.compareTo(proposedHrk) > 0)  {
	    securedProposedHrk = proposedHrk
	}

    }

    void convertPlacementsInEur(BigDecimal tecaj) {
	// NT dodana provjera za sva secured polja
	changeEur = convertIntoEur(newChange, tecaj)
	balanceEur = convertIntoEur(newBalance, tecaj)
	previousEur = convertIntoEur(newPrevious, tecaj)
	proposedEur = this.previousEur.add(this.changeEur) //NT proposed = previous + change

	securedBalanceEur = convertIntoEur(newSecuredBalance, tecaj)
	if (securedBalanceEur.compareTo(balanceEur) > 0)  {
	    securedBalanceEur = balanceEur
	}

	securedPreviousEur = convertIntoEur(newSecuredPrevious, tecaj)
	if (securedPreviousEur.compareTo(previousEur) > 0)  {
	    securedPreviousEur = previousEur
	}

	securedProposedEur = convertIntoEur(newSecuredProposed, tecaj)
	if (securedProposedEur.compareTo(proposedEur) > 0) {
	    securedProposedEur = proposedEur
	}

    }

    /* //NT refactored and commented out
     void updateChangeEur(BigDecimal tecaj) {  changeEur = convertIntoEur(newChange, tecaj);  }
     void updateBalanceEur(BigDecimal tecaj) {
     balanceEur = convertIntoEur(newBalance, tecaj);
     }
     void updatePreviousEur(BigDecimal tecaj) {
     previousEur = convertIntoEur(newPrevious, tecaj);
     }
     void updateSecuredBalanceEur(BigDecimal tecaj) { securedBalanceEur = convertIntoEur(newSecuredBalance, tecaj);  }
     void updateSecuredPreviousEur(BigDecimal tecaj) { securedPreviousEur = convertIntoEur(newSecuredPrevious, tecaj);  }
     void updateSecuredProposedEur(BigDecimal tecaj) { securedProposedEur = convertIntoEur(newSecuredProposed, tecaj);  }
     void updateChangeHrk(BigDecimal tecaj) {
     changeHrk = convertIntoHrk(newChange, tecaj);
     }
     void updateBalanceHrk(BigDecimal tecaj) {
     balanceHrk = convertIntoHrk(newBalance, tecaj);
     }
     void updatePreviousHrk(BigDecimal tecaj) {
     previousHrk = convertIntoHrk(newPrevious, tecaj);
     }
     void updateSecuredBalanceHrk(BigDecimal tecaj) {  securedBalanceHrk = convertIntoHrk(newSecuredBalance, tecaj);   }
     void updateSecuredPreviousHrk(BigDecimal tecaj) {  securedPreviousHrk = convertIntoHrk(newSecuredPrevious, tecaj);  }
     void updateSecuredProposedHrk(BigDecimal tecaj) { 	securedProposedHrk = convertIntoHrk(newSecuredProposed, tecaj);  }
     */
    BigDecimal convertIntoEur(BigDecimal placementHrk, BigDecimal tecaj) {
	BigDecimal placementEur = new BigDecimal(0)

	if (placementHrk != null && placementHrk.compareTo(BigDecimal.ZERO) > 0 && tecaj != null && tecaj.compareTo(BigDecimal.ZERO) > 0) {
	    placementEur = placementHrk.divide(tecaj, mt)
	    return placementEur
	}
	return placementEur
    }

    BigDecimal convertIntoHrk(BigDecimal placementEur, BigDecimal tecaj) {
	BigDecimal placementHrk = new BigDecimal(0)

	if (placementEur != null && placementEur.compareTo(BigDecimal.ZERO) > 0 && tecaj != null && tecaj.compareTo(BigDecimal.ZERO) > 0) {
	    placementHrk = placementEur.multiply(tecaj)
	    return placementHrk
	}
	return placementHrk
    }

    //NT
    //Function will return only the date part of the tenor.
    //Problem was that some tenors have word overdue in them and I switched that check to the exposureView.xhtml
    /*
     public String getTenorForPrint(){
     if (this.tenor.length() > 11)
     return this.tenor.substring(0, 11);
     return this.tenor;
     }
     */

    //NT
    DateTime getTenorDate(){
	return parseTenorString(this.tenor)
    }


    //NT
    //Parses db string to joda.DateTime
    static DateTime parseTenorString(String tenorString){

	org.joda.time.format.DateTimeFormatter formatter = org.joda.time.format.DateTimeFormat.forPattern("dd.MM.yyyy")
	if (tenorString != null && tenorString.length() >= 10) {
	    return formatter.parseDateTime(tenorString.substring(0, 10))
	}
	return new DateTime()
    }
}
