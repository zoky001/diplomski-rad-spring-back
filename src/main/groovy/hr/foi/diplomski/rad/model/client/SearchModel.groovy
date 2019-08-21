package hr.foi.diplomski.rad.model.client

import groovy.transform.ToString
import hr.foi.diplomski.rad.enums.SearchType

@ToString
class SearchModel {

    SearchType type
    String data
    Date exposureDate
    String currency

    SearchModel() {
	type = SearchType.KPO
	Calendar c = new GregorianCalendar()
	c.setTime(new Date())
	exposureDate = c.getTime()
	currency = "HRK"
    }

    @Override
    String toString() {
	return String.format("{tip: %s, data: %s, datum: %s, valuta: %s}", type.toString(), data, exposureDate.toString(), currency)
    }
}
