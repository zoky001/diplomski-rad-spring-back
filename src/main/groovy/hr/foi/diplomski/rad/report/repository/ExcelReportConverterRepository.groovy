package hr.foi.diplomski.rad.report.repository

import java.math.RoundingMode

import org.springframework.stereotype.Component

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j

@TypeChecked
@Slf4j
@Component
class ExcelReportConverterRepository extends ExcelReportColumnWidths {

    static final BigDecimal THOUSANDS_DIVIDER = new BigDecimal(1000)
    static final String EUROPEANCURRENCYCODE = "EUR"
    static final String CROATIANCURRENCYCODE = "HRK"
    
    /**
     * convertValuesCurrency
     * @param valueHrk
     * @param valueEur
     * @param convertToThousands
     * @param currency
     * @param numberOfDecimalPlaces
     * @return
     */
	BigDecimal convertValuesCurrency(
			BigDecimal valueHrk,
			BigDecimal valueEur,
			boolean convertToThousands,
			String currency,
			int numberOfDecimalPlaces
	) {

		BigDecimal value
		if (this.EUROPEANCURRENCYCODE.equals(currency)) {
			value = valueEur
		} else {
			value = valueHrk
		}

		if (value != null && convertToThousands) {
			value = value.divide(THOUSANDS_DIVIDER)
		}

		if (value != null){
			value = value.setScale(numberOfDecimalPlaces, RoundingMode.HALF_UP)
		}

		value
	}
}
