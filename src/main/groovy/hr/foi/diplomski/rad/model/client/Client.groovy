package hr.foi.diplomski.rad.model.client

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.core.model.CodebookEntry
import hr.foi.diplomski.rad.model.exposure.Exposure

@Slf4j
@ToString
class Client implements Serializable {
    public static final String VRSTA_OSOBE_ZEMLJA = "Z"
    public static final String VRSTA_OSOBE_FIRMA = "F"
    public static final String VRSTA_OSOBE_OBRT = "O"
    public static final String VRSTA_OSOBE_GRADANIN = "G"

    static final long serialVersionUID = 1L
    Long id
    String mb
    String smb
    String oib
    String jmbg
    String registerNumber
    String borrower
    String country
    CodebookEntry intRating
    CodebookEntry ratingModel
    BigDecimal pd
    boolean financialsEnclosed
    String industry
    String ownerName
    Double ownerShare
    int index
    int indexWithExposures
    String sndg
    List<Exposure> exposures
    Exposure total
    boolean selected
    CodebookEntry ratingRelation
    Long groupId
    String owner
    boolean grouped
    Long groupedClientId
    boolean manualInput
    boolean includedInReport
    boolean primaryMember
    String orgJed
    BigDecimal intRateHRK
    BigDecimal intRateEUR
    BigDecimal feesHRK
    BigDecimal feesEUR
    //DB response: GRESKA_DOHVATA_IZLOZENOSTI
    boolean error
    String vrstaOsobe //NT

    void addExposure(Exposure e) {
	e.setIsNew(false)
	exposures.add(0, e)
    }

    Client() {
	exposures = new ArrayList<Exposure>()
	total = new Exposure()
	manualInput = true
	includedInReport = true
	intRateHRK = new BigDecimal(0)
	intRateEUR = new BigDecimal(0)
	feesHRK = new BigDecimal(0)
	feesEUR = new BigDecimal(0)
    }

    Exposure getTotal() {
	if (total == null) {
	    total = new Exposure()
	}
	return total
    }

    boolean render() {
	return exposures.isEmpty() || manualInput || error
    }

    boolean provjeriVrstuOsobe(String expectedValue) throws RispoException {
	return expectedValue.equals(this.vrstaOsobe)
    }

    //Set of rules that define if the client should have defined exposure
    //NT
    boolean shouldHaveExposure()
    {
	try{
	    if (provjeriVrstuOsobe(Client.VRSTA_OSOBE_ZEMLJA))  {
		return false
	    }
	}catch (RispoException e) {
	    log.info "$e.message"
	}

	return true
    }
}
