package hr.foi.diplomski.rad.report.repository

import org.springframework.stereotype.Component

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j

@TypeChecked
@Slf4j
@Component
class ExcelReportColumnWidths {

    final double INDUSTRY_COLUMN_WIDTH = 205.595703125
    final double BORROWER_COLUMN_WIDTH = 177.8125
    final double OWNERSHIP_COLUMN_WIDTH = 263.9404296875

    final double TAKER_COLUMN_WIDTH = 119.4677734375
    final double COLLATERALS_COLUMN_WIDTH = 161.142578125
    final double TYPE_OF_CREDIT_COLUMN_WIDTH = 125.0244140625
    final double TENOR_COLUMN_WIDTH = 50.009765625
    final double CONDITIONS_COLUMN_WIDTH = 83.349609375
    final double TYPE_COLUMN_WIDTH = 88.90625
    final double RISK_CLASS_COLUMN_WIDTH = 138.916015625
}
