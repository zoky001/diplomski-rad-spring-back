package hr.foi.diplomski.rad.report.model

import org.springframework.stereotype.Component

import groovy.transform.ToString

@ToString
@Component
class ExcelReportClientColumns {

    public final static Integer INDEX = 0
    public final static Integer BORROWER = 1
    public final static Integer SNDG = 3
    public final static Integer COUNTRY = 4
    public final static Integer INT_RATING = 5
    public final static Integer FINANCIALS_ENCLOSED = 6
    public final static Integer ACTIVITY_INDUSTRY = 7
    public final static Integer OWNERSHIP_SHAREHOLDERS = 9
    public final static Integer RATING_RELATION = 14
}
