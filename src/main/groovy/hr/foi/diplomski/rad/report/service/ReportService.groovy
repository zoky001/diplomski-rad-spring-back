package hr.foi.diplomski.rad.report.service

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.zaba.emaframework.core.model.ServiceResult
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.report.command.GenerateExcelReportCommand
import hr.foi.diplomski.rad.report.repository.ExcelRepository

@Slf4j
@Service
@TypeChecked
class ReportService {

	@Autowired
	ExcelRepository excelRepository

	public ReportService() {
		super()
		this.excelRepository = new ExcelRepository();
	}

	/**
	 * generateExcelReport
	 * @param command
	 * @return
	 */
	ServiceResult generateExcelReport(GenerateExcelReportCommand command) {

		log.info "ReportService.generateExcelReport $command"

		ServiceResult serviceResult = new ServiceResult()
		String result

		try  {
			result = this.excelRepository.generateExcelReport(command)
			serviceResult = new ServiceResult(success: true, result: result)
		} catch (RispoException e) {
			serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["rispo.error"], errorMessageTextList: [e.message])
		}

		log.info "ReportService.generateExcelReport"

		serviceResult
	}

	/**
	 * loadTemplate
	 * @param command
	 * @return
	 */
	ServiceResult loadWorkbookReportTemplate() {

		log.info "ReportService.loadWorkbookReportTemplate"

		ServiceResult serviceResult = new ServiceResult()
		HSSFWorkbook result

		try  {
			result = this.excelRepository.loadWorkbookReportTemplate()
			serviceResult = new ServiceResult(success: true, result: true)
		} catch (RispoException e) {
			serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["rispo.error"], errorMessageTextList: [e.message])
		}

		log.info "ReportService.loadWorkbookReportTemplate"

		serviceResult
	}

	/**
	 * workbookToByte
	 * @param command
	 * @return
	 */
	ServiceResult workbookToByte() {

		log.info "ReportService.workbookToByte"

		ServiceResult serviceResult = new ServiceResult()
		byte[] result
		HSSFWorkbook wb
		
		try  {
			wb = this.excelRepository.loadWorkbookReportTemplate()
			result = this.excelRepository.workbookToByte(wb)
			serviceResult = new ServiceResult(success: true, result: result)
		} catch (RispoException e) {
			serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["rispo.error"], errorMessageTextList: [e.message])
		}

		log.info "ReportService.workbookToBytes"

		serviceResult
	}
	
	/**
	 * byteToEncodedString
	 * @param command
	 * @return
	 */
	ServiceResult byteToEncodedString(byte[] inputByte) {

		log.info "ReportService.byteToEncodedString"

		ServiceResult serviceResult = new ServiceResult()
		String result

		try  {
			result = this.excelRepository.byteToEncodedString(inputByte)
			serviceResult = new ServiceResult(success: true, result: result)
		} catch (RispoException e) {
			serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["rispo.error"], errorMessageTextList: [e.message])
		}

		log.info "ReportService.byteToEncodedString"

		serviceResult
	}
	
	/**
	 * byteToEncodedString
	 * @param command
	 * @return
	 */
	ServiceResult stringToDecodedByte(String inputString) {

		log.info "ReportService.byteToEncodedString"

		ServiceResult serviceResult = new ServiceResult()
		byte[] result

		try  {
			result = this.excelRepository.stringToDecodedByte(inputString)
			serviceResult = new ServiceResult(success: true, result: result)
		} catch (RispoException e) {
			serviceResult = new ServiceResult(success: false, errorMessageCodeList: ["rispo.error"], errorMessageTextList: [e.message])
		}

		log.info "ReportService.byteToEncodedString"

		serviceResult
	}
}
