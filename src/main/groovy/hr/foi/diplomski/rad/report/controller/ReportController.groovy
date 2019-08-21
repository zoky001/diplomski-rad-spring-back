package hr.foi.diplomski.rad.report.controller

import groovy.transform.TypeChecked
import hr.foi.diplomski.rad.report.command.GenerateExcelReportCommand
import hr.foi.diplomski.rad.report.service.ReportService
import hr.foi.diplomski.rad.service.DataConversionService
import hr.zaba.emaframework.core.model.ServiceResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/report")
@TypeChecked
class ReportController {

    @Autowired
    ReportService reportService

    DataConversionService dataConversionService

    @Autowired
    ReportController(ReportService reportService, DataConversionService dataConversionService) {
        this.dataConversionService = dataConversionService
        this.reportService = reportService;
    }

    @PostMapping("/generateExcelReport")
    def generateExcelReportCommand(@RequestBody GenerateExcelReportCommand command) {
        ServiceResult serviceResult = reportService.generateExcelReport(command)
        return dataConversionService.parseServiceResult(serviceResult)
    }

    @GetMapping("/loadWorkbookReportTemplate")
    def loadWorkbookReportTemplate() {
        ServiceResult serviceResult = reportService.loadWorkbookReportTemplate()
        return dataConversionService.parseServiceResult(serviceResult)
    }

    @GetMapping("/workbookToByte")
    def workbookToByte() {
        ServiceResult serviceResult = reportService.workbookToByte()
        return dataConversionService.parseServiceResult(serviceResult)
    }
}
