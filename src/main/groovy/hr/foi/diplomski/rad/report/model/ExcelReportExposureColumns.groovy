package hr.foi.diplomski.rad.report.model

import org.springframework.stereotype.Component

import groovy.transform.ToString

@ToString
@Component
class ExcelReportExposureColumns {

    public static Integer INDEX = 0
    public static Integer TYPE_OF_CREDIT = 1
    public static Integer TENOR = 2
    public static Integer PREVIOUS = 3
    public static Integer CHANGE = 4
    public static Integer PROPOSED = 5
    public static Integer BALANCE = 6
    public static Integer CONDITIONS = 7
    public static Integer COLLATERALS = 8
    public static Integer SECURED_PREVIOUS = 9
    public static Integer SECURED_PROPOSED = 10
    public static Integer SECURED_BALANCE = 11
    public static Integer ACTUAL_BORROWER = 12 // DODANO U NOVOJ EXCELL TABLICI
    public static Integer BALANCE_BORROWER = 13 // DODANO U NOVOJ EXCELL TABLICI
    public static Integer LESS_THAN_YEAR = 14
    public static Integer TYPE = 15
    public static Integer COMITTED = 16
    public static Integer TAKER = 17
    public static Integer RISK_CLASS = 18

}
