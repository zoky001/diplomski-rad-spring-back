package hr.foi.diplomski.rad.model.group

import hr.foi.diplomski.rad.model.client.Client
import hr.foi.diplomski.rad.model.exposure.Exposure
import org.joda.time.DateTime

import groovy.transform.ToString
import hr.foi.diplomski.rad.enums.ReportStatus

@ToString
class Group {

	Long id
	String name
	String kpo
	String mb
	String jmbg
	String oib
	String application
	ReportStatus status
	float progress
	DateTime reportDate
	String owner
	List<Client> members
	Exposure total
	DateTime creationDate
	String currency
	boolean djelomicanDohvat
	boolean dohvatPoPostojecimClanicama
	String orgJed
	BigDecimal intRateHRK = new BigDecimal(0)
	BigDecimal intRateEUR = new BigDecimal(0)

	BigDecimal feesHRK = new BigDecimal(0)
	BigDecimal feesEUR = new BigDecimal(0)


	void refreshIndexes() {
		int index = 1
		int indexWithExposures = 1

		for (Client c : members) {
			c.setIndex(index++)
			if (!c.getExposures().isEmpty() || c.isManualInput()) {
				c.setIndexWithExposures(indexWithExposures++)
			}
		}
	}

	String getCreationDateAsString() {
		if (creationDate == null)  {
			return ""
		}
		return creationDate.toString("dd.MM.yyyy. HH:mm")
	}

	String getReportDateAsString() {
		if (reportDate == null) {
			return ""
		}
		return reportDate.toString("dd.MM.yyyy.")
	}

	//NT
	String getExposureView(){
		if (reportDate == null)  { return "" }

		if (reportDate.withTimeAtStartOfDay().isEqual(creationDate.withTimeAtStartOfDay())) { return "-" }

		if (dohvatPoPostojecimClanicama == true ) {
			return "za trenutne članice grupe"
		}
		return "za povijesne članice grupe"
	}

	//NT
	String getExposureViewForEditView(){
		if (reportDate == null)  { return "" }

		if (reportDate.withTimeAtStartOfDay().isEqual(new DateTime().withTimeAtStartOfDay())) { return "" }

		if (dohvatPoPostojecimClanicama == true )  {
			return "Dohvat po trenutnim �lanicama grupe"
		}
		return "Dohvat po povijesnim �lanicama grupe"
	}

	boolean isLocked() {
		return status == ReportStatus.LOCKED
	}

	Exposure getTotal() {
		if (total == null) {
			total = new Exposure()
		}
		return total
	}

	void updateIntRate(BigDecimal intRateHRK, BigDecimal intRateEUR) {
		if (intRateHRK != null) {
			this.intRateHRK = this.intRateHRK.add(intRateHRK)
		}
		if (intRateEUR != null) {
			this.intRateEUR = this.intRateEUR.add(intRateEUR)
		}
	}

	void updateFees(BigDecimal feesHRK, BigDecimal feesEUR) {
		if (feesHRK != null) {
			this.feesHRK = this.feesHRK.add(feesHRK)
		}
		if (feesEUR != null) {
			this.feesEUR = this.feesEUR.add(feesEUR)
		}
	}
}
