package hr.foi.diplomski.rad.report.repository

import org.springframework.core.io.ClassPathResource

import java.awt.Font

import java.awt.font.FontRenderContext
import java.awt.font.LineBreakMeasurer
import java.awt.font.TextAttribute

import java.text.AttributedString

import org.apache.poi.EncryptedDocumentException
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.util.CellReference
import org.apache.poi.openxml4j.exceptions.InvalidFormatException
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.model.RispoException

@TypeChecked
@Slf4j
@Component
class ExcelReportTemplateRepository extends ExcelReportConverterRepository {

    static final String EMPTY_CELL_VALUE = "-"

    final float NORMAL_ROW_HEIGHT = 12.75f
    final float SMALL_ROW_HEIGHT = 3

    final int CLIENTS_TABLE_START = 8

    final int EXPOSURES_TABLE_START = 55 //počinje tablica s plasmanima po klijentima
    final int EXPOSURES_TABLE_END = 535  // završava tablica s plasmanima

    final int INTERESTS_ROW = 638 // dodano za sumarne stavke naknada interests i fees
    final int FEES_ROW = 639 // dodano za sumarne stavke naknada interests i fees

    final int TOTAL_LEVEL_2_ROWS = 5 //Total level 2 - novo u novoj excell tablici 7.12.2017

    final int ROWS_TO_HIDE = 13 // nakon izloženosti sakrij još redova


    public final String resourceTemplateName = "reportTemplate.xls"
    public final String resourceTemplateLocation = "templates"
    public final String resourceTemplateSubdirectory = "rispo"

    @Autowired
    hr.foi.diplomski.rad.report.model.ExcelReportClientColumns clientColumns

    @Autowired
    hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns exposureColumns

    /**
     * loadWorkbookTemplate
     * @param templateName
     * @return
     * @throws RispoException
     */
    HSSFWorkbook loadWorkbookReportTemplate() throws RispoException {

        ClassLoader classLoader
        File file
//        FileInputStream fileInputStream
        InputStream fileInputStream

        HSSFWorkbook wb

        try {

//			classLoader = getClass().getClassLoader();
//			file = new File(classLoader.getResource("$resourceTemplateLocation/$resourceTemplateSubdirectory/$resourceTemplateName").getFile());
//			fileInputStream = new FileInputStream(file);

            fileInputStream = new ClassPathResource(
                    "/$resourceTemplateLocation/$resourceTemplateSubdirectory/$resourceTemplateName").getInputStream()
            if (fileInputStream) {
                log.info("Ima template-a ")
            }

            if (fileInputStream != null) {
                wb = (HSSFWorkbook) WorkbookFactory.create(fileInputStream)
            } else {
                throw new RispoException("loadWorkbookTemplate template ne postoji")
            }

        } catch (Exception e) {
            log.error("create excel IOException")
            throw new RispoException("loadWorkbookTemplate $e.message")
        }

        wb
    }

    /**
     * workbookToByte
     * @param wb
     * @return
     */
    byte[] workbookToByte(Workbook wb) throws RispoException {

        byte[] resultByte = null
        ByteArrayOutputStream baos = new ByteArrayOutputStream()

        try {
            if (wb != null) {
                wb.write(baos)
                resultByte = baos.toByteArray()
            }
        } catch (Exception e) {
            throw new RispoException("ExcelTemplateRepository.workbookToByte IOException $e.message")
        } finally {
            baos.close()
        }
        resultByte
    }

    /**
     * byteToEncodedString
     * @param byteFile
     * @return
     */
    String byteToEncodedString(byte[] byteFile) throws RispoException {

        String retVal
        try {

            if (byteFile != null) {
                retVal = byteFile.encodeBase64().toString()
            }
        } catch (Exception e) {
            throw new RispoException("ExcelTemplateRepository.byteToEncodedString IOException $e.message")
        }
        retVal
    }

    /**
     * byteToEncodedString
     * @param byteFile
     * @return
     */
    byte[] stringToDecodedByte(String inputString) throws RispoException {

        byte[] retVal
        try {
            retVal = inputString.decodeBase64()

        } catch (Exception e) {
            throw new RispoException("ExcelTemplateRepository.stringToDecodedByte IOException $e.message")
        }
        retVal
    }

    /**
     * calculateCellHeight
     * @param cellValue
     * @param cellWidth
     * @return
     */
    public int calculateCellHeight(String cellValue, double cellWidth) throws RispoException {

        int lineCnt = 1

        if (cellValue == null || cellValue.isEmpty() || cellValue.trim().size() == 0) {
            lineCnt = 1
        } else {
            try {
                FontRenderContext frc = new FontRenderContext(null, true, true)
                Font currentFont = new Font("Arial", Font.PLAIN, 10)
                AttributedString attrStr = new AttributedString(cellValue)
                attrStr.addAttribute(TextAttribute.FONT, currentFont)
                LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc)
                int nextPos = 0
                lineCnt = 0
                while (measurer.getPosition() < cellValue.length()) {
                    nextPos = measurer.nextOffset((float) cellWidth) // mergedCellWidth is the max width of each line
                    lineCnt++
                    measurer.setPosition(nextPos)
                }
            } catch (Exception e) {
                lineCnt = 3
            }
        }
        lineCnt
    }

    /**
     * getCellByReference
     * @param sh
     * @param reference
     * @return
     */
    public Cell getCellByReference(Sheet sh, String reference) {
        CellReference ref = new CellReference(reference)
        Row row = sh.getRow(ref.getRow())
        Cell c = row.getCell(ref.getCol())
        return c
    }


    /**
     * setEmptyCellValue
     * @param row
     * @param column
     */
    public void setEmptyCellValue(HSSFRow row, int column) {
        if (row != null) {
            row.getCell(column).setCellValue(EMPTY_CELL_VALUE)
            row.getCell(column).getCellStyle().setAlignment(HorizontalAlignment.CENTER)
        }
    }

    /**
     * prepareStyles
     * @param row
     * @param wb
     * @return
     */
    public List<HSSFCellStyle> prepareStyles(HSSFRow row, HSSFWorkbook wb) {

        List<HSSFCellStyle> styles = new ArrayList<HSSFCellStyle>()
        if (row != null) {
            for (int i = 0; i < row.getLastCellNum(); i++) {
                HSSFCellStyle newCellStyle = wb.createCellStyle()
                newCellStyle.cloneStyleFrom(row.getCell(i).getCellStyle())
                newCellStyle.setWrapText(false)
                styles.add(newCellStyle)
            }
        }
        styles
    }
}
