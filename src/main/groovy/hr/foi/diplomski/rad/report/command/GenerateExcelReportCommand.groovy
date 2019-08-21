package hr.foi.diplomski.rad.report.command

import org.apache.poi.EncryptedDocumentException

import groovy.transform.ToString
import hr.foi.diplomski.rad.model.group.Group

@ToString
class GenerateExcelReportCommand implements Serializable {

    Group group
    Date  date
    String exportCurrency
}
