package hr.foi.diplomski.rad.model.exposure

import groovy.transform.ToString

@ToString
class Collateral {
	Long id
	String name
	BigDecimal valueHrk
	BigDecimal valueEur
	Long exposureId

  void add(Collateral c) {
		if (valueHrk != null && c.valueHrk != null) {
			valueHrk = valueHrk.add(c.valueHrk)
		}
		if (valueEur != null && c.valueEur != null) {
			valueEur = valueEur.add(c.valueEur)
		}
	}
}
