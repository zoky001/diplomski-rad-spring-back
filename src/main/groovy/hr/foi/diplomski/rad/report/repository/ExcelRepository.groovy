package hr.foi.diplomski.rad.report.repository


import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.ClientAnchor
import org.apache.poi.ss.usermodel.Comment
import org.apache.poi.ss.usermodel.CreationHelper
import org.apache.poi.ss.usermodel.Drawing
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.ss.usermodel.RichTextString
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.springframework.stereotype.Repository

import com.google.common.base.Strings

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import hr.foi.diplomski.rad.model.RispoException
import hr.foi.diplomski.rad.model.client.Client
import hr.foi.diplomski.rad.model.exposure.Collateral
import hr.foi.diplomski.rad.model.exposure.Exposure
import hr.foi.diplomski.rad.model.exposure.InterestRateReference
import hr.foi.diplomski.rad.model.group.Group
import hr.foi.diplomski.rad.core.model.CodebookEntry

@TypeChecked
@Repository
@Slf4j
public class ExcelRepository extends ExcelReportTemplateRepository {

	/**
	 * generateExcelReport
	 * @param command
	 * @return
	 * @throws RispoException
	 */
	String generateExcelReport(hr.foi.diplomski.rad.report.command.GenerateExcelReportCommand command) throws RispoException {

		String retVal
		Workbook resultWorkbook
		HSSFWorkbook resultHSSFWorkbook
		byte[] resultByte

		try {
			resultHSSFWorkbook = this.loadWorkbookReportTemplate()
			resultWorkbook = this.populateWorkbook(resultHSSFWorkbook, command.group, command.date, command.exportCurrency)

			resultByte = this.workbookToByte(resultWorkbook)

			// Path path = Paths.get("D:\\workbook.xls");
			// Files.write(path, resultByte);

			retVal = this.byteToEncodedString(resultByte)

			resultWorkbook.close()

		} catch (Exception e) {
			throw new RispoException("RispoException ExcelRepository.generateExcelReport $e.message")
		}
		retVal
	}


	/**
	 * populateWorkbook
	 * @param group
	 * @param date
	 * @param exportCurrency
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	Workbook populateWorkbook(HSSFWorkbook wb, Group group, Date date, String exportCurrency) throws RispoException {

		try {
			if (wb != null) {
				HSSFSheet sheet = wb.getSheetAt(0)
				this.getCellByReference(sheet, "H1").setCellValue(group.getName())
				this.getCellByReference(sheet, "P4").setCellValue(date)
				this.getCellByReference(sheet, "B53").setCellValue("UniCredit Group (amounts in T" + exportCurrency + ")")
				this.getCellByReference(sheet, "A567").setCellValue("Credit Lines in T" + exportCurrency + " (inclusive of indirect Risks) according UCI Rules")
				this.getCellByReference(sheet, "A583").setCellValue("Credit Lines in T" + exportCurrency + " according BACA Rules")
				this.getCellByReference(sheet, "A610").setCellValue("Credit Lines in T" + exportCurrency + " (inclusive of indirect Risks) according UCI Rules*)")
				this.getCellByReference(sheet, "A626").setCellValue("Credit Lines in T" + exportCurrency + " according BACA Rules")

				this.populateClients(group, sheet)
				this.populateExposures(group, sheet, exportCurrency, wb)

				wb.setForceFormulaRecalculation(true)
			}
		} catch (RispoException e) {
			throw e
		}
		wb
	}

	/**
	 * populateClients
	 * @param group
	 * @param sheet
	 */
	void populateClients(Group group, Sheet sheet) throws RispoException {

		try {
			int index = this.CLIENTS_TABLE_START // 8
			for (Client member: group.getMembers()) {
				if (!member.getExposures().isEmpty()) {
					try {
						Row row = sheet.getRow(index++)
						row.setZeroHeight(false)

						if (member.getBorrower() != null) {
							row.getCell(this.clientColumns.BORROWER).setCellValue(member.getBorrower())
							row.getCell(this.clientColumns.BORROWER).getCellStyle().setWrapText(true)
						} else {
							this.setEmptyCellValue((HSSFRow) row, this.clientColumns.BORROWER)
						}

						if (member.getSndg() != null) {
							row.getCell(this.clientColumns.SNDG).setCellValue(member.getSndg())
						} else {
							this.setEmptyCellValue((HSSFRow) row, this.clientColumns.SNDG)
						}

						if (member.getCountry() != null) {
							row.getCell(this.clientColumns.COUNTRY).setCellValue(member.getCountry())
						} else {
							this.setEmptyCellValue((HSSFRow) row, this.clientColumns.COUNTRY)
						}

						if (member.getIntRating() != null) {
							row.getCell(this.clientColumns.INT_RATING).setCellValue(member.getIntRating().getName())
						} else {
							this.setEmptyCellValue((HSSFRow) row, this.clientColumns.INT_RATING)
						}

						row.getCell(this.clientColumns.FINANCIALS_ENCLOSED).setCellValue(member.isFinancialsEnclosed() ? "Y" : "N")
						if (member.getIndustry() != null) {
							row.getCell(this.clientColumns.ACTIVITY_INDUSTRY).setCellValue(member.getIndustry())
							row.getCell(this.clientColumns.ACTIVITY_INDUSTRY).getCellStyle().setWrapText(true)
						} else {
							this.setEmptyCellValue((HSSFRow) row, this.clientColumns.ACTIVITY_INDUSTRY)
						}

						if (Strings.isNullOrEmpty(member.getOwnerName())) {
							this.setEmptyCellValue((HSSFRow) row, this.clientColumns.OWNERSHIP_SHAREHOLDERS)
						} else {
							row.getCell(this.clientColumns.OWNERSHIP_SHAREHOLDERS).setCellValue(member.getOwnerName())
							row.getCell(this.clientColumns.OWNERSHIP_SHAREHOLDERS).getCellStyle().setWrapText(true)
						}

						if (member.getRatingRelation() != null) {
							row.getCell(this.clientColumns.RATING_RELATION).setCellValue(member.getRatingRelation().getName())
						} else {
							this.setEmptyCellValue((HSSFRow) row, this.clientColumns.RATING_RELATION)
						}

						calculateClientRowHeight(member, row)
					} catch (Exception e) {
						log.error("Greska kod izvoza klijenta sa MB " + member.getMb(), e)
					}
				}
			}

		} catch (Exception e) {
			throw new RispoException("populateClients error message $e.message")
		}
	}

	/**
	 * populateExposures
	 * @param group
	 * @param sheet
	 * @param exportCurrency
	 * @param workbook
	 */
	void populateExposures(Group group, HSSFSheet sheet, String exportCurrency, HSSFWorkbook workbook) throws RispoException {

		try {
			int index = this.EXPOSURES_TABLE_START //54
			int numberOfShiftedRows = 0
			int clientIndex = 1

			if (sheet != null) {

				List < HSSFCellStyle > styles = this.prepareStyles(sheet.getRow(index), workbook)
				for (Client member: group.getMembers()) {
					if (!member.getExposures().isEmpty()) {
						int numberOfRowsToShift = member.getExposures().size() - 5 // koliko redaka je već insertirano
						numberOfRowsToShift = Math.max(0, numberOfRowsToShift)
						if (numberOfRowsToShift > 0) {
							sheet.shiftRows(index + 1, sheet.getLastRowNum(), numberOfRowsToShift)
						}
						sheet.getRow(index).setHeightInPoints(this.NORMAL_ROW_HEIGHT)
						sheet.getRow(index).setZeroHeight(false)
						sheet.getRow(index).setHeight((short) - 1)
						numberOfShiftedRows += numberOfRowsToShift
						for (Exposure e: member.getExposures()) {
							HSSFRow row = sheet.getRow(index)
							if (row == null) {
								row = sheet.createRow(index)
							}
							populateExposure(e, row, styles, clientIndex, exportCurrency)
							index++
							calculateExposureRowHeight(e, exportCurrency, row)
						}

						// inicijalno je postavljeno 5 redaka, ako je manje sakrij one koji nisu popunjeni.
						int exposuresTotalRows = member.getExposures().size()
						// while (exposuresTotalRows > 0 && exposuresTotalRows < 5) {
						// sheet.getRow(index++).setZeroHeight(true)
						// ++exposuresTotalRows }
						for (int i = 5; i > exposuresTotalRows; i--) {
							sheet.getRow(index++).setZeroHeight(true)
						}

						sheet.getRow(index).setHeightInPoints(this.SMALL_ROW_HEIGHT)
						sheet.getRow(index++).setZeroHeight(false)
						sheet.getRow(index).setHeightInPoints(this.NORMAL_ROW_HEIGHT)
						sheet.getRow(index++).setZeroHeight(false)
						index = index + this.TOTAL_LEVEL_2_ROWS
						clientIndex++
					}
				}
				//NT
				int lastIndex = index
				index = this.EXPOSURES_TABLE_END
				if (lastIndex > this.EXPOSURES_TABLE_END) {

					//numberOfRowsToHide is last populated row - original table ending + (max clients - populated clients + 1) * 2
					//last part is a calculation based that a function have pre-populated 2 rows per client, so we need to take it into account
					//int numberOfRowsToHide = lastIndex - EXPOSURES_TABLE_END  + (40 - clientIndex + 1) * 2
					int numberOfRowsToHide = (40 - clientIndex + 1) * 12
					numberOfRowsToHide = numberOfRowsToHide + this.ROWS_TO_HIDE // ROWS_TO_HIDE - sakrivanje do "Customer Group AL 1 Total*)"
					for (int i = 0; i < numberOfRowsToHide; i++) {
						sheet.getRow(lastIndex++).setZeroHeight(true)
					}
				} else {
					for (int i = 0; i < (numberOfShiftedRows + this.ROWS_TO_HIDE); i++) { // ROWS_TO_HIDE - sakrivanje do "Customer Group AL 1 Total*)"
						sheet.getRow(index++).setZeroHeight(true)
					}
				}

				hideEmptyExposuresFromTable(sheet, index, numberOfShiftedRows, clientIndex);

				populateInterestsAndFees(group, sheet, numberOfShiftedRows, exportCurrency);

				hideOrShowTables(sheet);
			}

		} catch (Exception e) {
			throw new RispoException("populateExposures message $e.message")
		}
	}

	/**
	 * hideEmptyExposuresFromTable
	 * @param sheet
	 * @param index
	 * @param numberOfShiftedRows
	 * @param clientIndex
	 */
	void hideEmptyExposuresFromTable(HSSFSheet sheet, int index, int numberOfShiftedRows, int clientIndex)  throws RispoException {

		try {
			int lastIndex = index;
			index = EXPOSURES_TABLE_END;
			if (sheet != null) {
				HSSFRow redak = null
				if (lastIndex > EXPOSURES_TABLE_END) {
					//numberOfRowsToHide is last populated row - original table ending + (max clients - populated clients + 1) * 2
					//last part is a calculation based that a function have pre-populated 2 rows per client, so we need to take it into account
					//int numberOfRowsToHide = lastIndex - EXPOSURES_TABLE_END  + (40 - clientIndex + 1) * 2;
					int numberOfRowsToHide = (40 - clientIndex + 1) * 12;
					numberOfRowsToHide = numberOfRowsToHide + ROWS_TO_HIDE; // ROWS_TO_HIDE - sakrivanje do "Customer Group AL 1 Total*)"
					for (int i = 0; i < numberOfRowsToHide; i++) {
						redak = null
						redak = sheet.getRow(lastIndex++)
						if (redak!= null) {
							redak.setZeroHeight(true)
						}
					}
				} else {
					for (int i = 0; i < (numberOfShiftedRows + ROWS_TO_HIDE); i++) // ROWS_TO_HIDE - sakrivanje do "Customer Group AL 1 Total*)"
					{
						redak = null
						redak = sheet.getRow(index++)
						if (redak!= null) {
							redak.setZeroHeight(true)
						}
					}
				}
			}

		} catch (Exception e) {
			throw new RispoException("hideEmptyExposuresFromTable message $e.message")
		}
	}

	/**
	 * hideOrShowTables
	 * @param sheet
	 */
	void hideOrShowTables(HSSFSheet sheet)  throws RispoException {

		try {
			for (Row row: sheet) {
				for (Cell cell: row) {
					if (cell.getCellTypeEnum() == CellType.STRING) {
						if (cell.getRichStringCellValue().getString().matches("(.*)specific information required by BACA only:(.*)")) {
							int currentRow = row.getRowNum();
							for (int i = currentRow; i < currentRow + 10; i++) {
								if (sheet.getRow(i) != null)
									sheet.getRow(i).setZeroHeight(true);
							}
						} else if (cell.getRichStringCellValue().getString().matches("(.*)Credit Lines in THRK according BACA Rules(.*)")) {
							int currentRow = row.getRowNum();
							for (int i = currentRow; i < currentRow + 10; i++) {
								if (sheet.getRow(i) != null)
									sheet.getRow(i).setZeroHeight(true);
							}
						} else if (cell.getRichStringCellValue().getString().matches("(.*)Customer Group AL 1 Total(.*)")) {
							int currentRow = row.getRowNum();
							for (int i = currentRow - 1; i < currentRow + 17; i++) {
								if (sheet.getRow(i) != null)
									sheet.getRow(i).setZeroHeight(false);
							}
						} else if (cell.getRichStringCellValue().getString().matches("(.*)Credit Lines in THRK [(]inclusive of indirect Risks[)] according UCI Rules(.*)")) {
							int currentRow = row.getRowNum();
							for (int i = currentRow - 1; i < currentRow + 17; i++) {
								if (sheet.getRow(i) != null)
									sheet.getRow(i).setZeroHeight(false);
							}
						} else if (cell.getRichStringCellValue().getString().matches("(.*)Control lines")) {
							int currentRow = row.getRowNum();
							for (int i = currentRow - 1; i < currentRow + 12; i++) {
								if (sheet.getRow(i) != null)
									sheet.getRow(i).setZeroHeight(false);
							}
						} else if (cell.getRichStringCellValue().getString().matches("(.*)Total interests")) {
							int currentRow = row.getRowNum();
							for (int i = currentRow - 1; i < currentRow + 7; i++) {
								if (sheet.getRow(i) != null)
									sheet.getRow(i).setZeroHeight(false);
							}
						}

					}
				}
			}
		} catch (Exception e) {
			throw new RispoException("hideOrShowTables message $e.message")
		}
	}

	/**
	 * populateInterestsAndFees
	 * @param group
	 * @param sheet
	 * @param numberOfShiftedRows
	 * @param currency
	 */
	void populateInterestsAndFees(Group group, HSSFSheet sheet, int numberOfShiftedRows, String currency) throws RispoException {

		try {

			if (sheet != null) {
				double interests = this.convertValuesCurrency(group.getIntRateHRK(), group.getIntRateEUR(), true, currency, 2).doubleValue()

				Row interestsRow = sheet.getRow(this.INTERESTS_ROW + numberOfShiftedRows)
				interestsRow.getCell(3).setCellValue(interests)

				interestsRow.getCell(3).getCellStyle().setFillPattern(FillPatternType.NO_FILL)

				interestsRow.getCell(5).setCellValue(interests)
				interestsRow.getCell(6).setCellValue(interests)

				double fees = this.convertValuesCurrency(group.getFeesHRK(), group.getFeesEUR(), true, currency, 2).doubleValue()

				Row feesRow = sheet.getRow(this.FEES_ROW + numberOfShiftedRows)
				feesRow.getCell(3).setCellValue(fees)
				feesRow.getCell(3).getCellStyle().setFillPattern(FillPatternType.NO_FILL)

				feesRow.getCell(5).setCellValue(fees)
				feesRow.getCell(6).setCellValue(fees)
			}

		} catch (RispoException e) {
			throw new RispoException("populateInterestsAndFees message $e.message")
		}
	}

	/**
	 * populateExposure
	 * @param e
	 * @param row
	 * @param styles
	 * @param index
	 * @param groupCurrency
	 * @param exportCurrency
	 */
	void populateExposure(Exposure e, HSSFRow row, List < HSSFCellStyle > styles, int index, String exportCurrency) throws RispoException {

		try {
			if (row != null) {
				row.createCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.INDEX).setCellStyle(styles.get(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.INDEX))
				row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.INDEX).setCellValue(index)

				row.createCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TYPE_OF_CREDIT).setCellStyle(styles.get(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TYPE_OF_CREDIT))

				String typeOfCredit = e.getTypeOfCredit()

				if (typeOfCredit == null) {
					this.setEmptyCellValue(row, hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TYPE_OF_CREDIT)
				} else {
					row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TYPE_OF_CREDIT).setCellValue(e.getTypeOfCredit())
					row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TYPE_OF_CREDIT).getCellStyle().setWrapText(true)
				}

				row.createCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TENOR).setCellStyle(styles.get(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TENOR))

				String tenor = e.getTenor()

				if (tenor == null) {
					this.setEmptyCellValue(row, this.exposureColumns.TENOR)
				} else {
					row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TENOR).setCellValue(tenor)
					row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TENOR).getCellStyle().setWrapText(true)
				}

				populateBigDecimalColumn(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.PREVIOUS, e.getPreviousHrk(), e.getPreviousEur(), exportCurrency, row, styles)
				populateBigDecimalColumn(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.CHANGE, e.getChangeHrk(), e.getChangeEur(), exportCurrency, row, styles)
				populateBigDecimalColumn(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.PROPOSED, e.getProposedHrk(), e.getProposedEur(), exportCurrency, row, styles)
				populateBigDecimalColumn(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.BALANCE, e.getBalanceHrk(), e.getBalanceEur(), exportCurrency, row, styles)

				row.createCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.CONDITIONS).setCellStyle(styles.get(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.CONDITIONS))

				String conditions = e.getConditions()

				if (conditions.isEmpty()) {
					this.setEmptyCellValue(row, hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.CONDITIONS)
				} else {
					row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.CONDITIONS).setCellValue(conditions)
				}

				row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.CONDITIONS).getCellStyle().setWrapText(true)

				if (e.getInterestRate() != null) {
					addInterestRateComment(row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.CONDITIONS), e.getInterestRate())
				}

				row.createCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.COLLATERALS).setCellStyle(styles.get(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.COLLATERALS))

				String collateralsAsString = getCollateralsAsString(e.getCollaterals(), exportCurrency)

				row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.COLLATERALS).setCellValue(collateralsAsString)
				row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.COLLATERALS).getCellStyle().setWrapText(true)
				if (this.EMPTY_CELL_VALUE.equalsIgnoreCase(collateralsAsString)) {
					row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.COLLATERALS).getCellStyle().setAlignment(HorizontalAlignment.CENTER)
				}

				populateBigDecimalColumn(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.SECURED_PREVIOUS, e.getSecuredPreviousHrk(), e.getSecuredPreviousEur(), exportCurrency, row, styles)
				populateBigDecimalColumn(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.SECURED_PROPOSED, e.getSecuredProposedHrk(), e.getSecuredProposedEur(), exportCurrency, row, styles)
				populateBigDecimalColumn(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.SECURED_BALANCE, e.getSecuredBalanceHrk(), e.getSecuredBalanceEur(), exportCurrency, row, styles)

				row.createCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.ACTUAL_BORROWER).setCellStyle(styles.get(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.ACTUAL_BORROWER))
				this.setEmptyCellValue(row, hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.ACTUAL_BORROWER)
				row.createCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.BALANCE_BORROWER).setCellStyle(styles.get(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.BALANCE_BORROWER))
				this.setEmptyCellValue(row, hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.BALANCE_BORROWER)

				row.createCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.LESS_THAN_YEAR).setCellStyle(styles.get(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.LESS_THAN_YEAR))
				if (e.getTenor() != null) {
					row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.LESS_THAN_YEAR).setCellValue(e.isLessThanYear() ? "Y" : "N")
				} else {
					this.setEmptyCellValue(row, hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.LESS_THAN_YEAR)
				}

				populateCodebookColumn(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TYPE, e.getPlasmanType(), row, styles)

				row.createCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.COMITTED).setCellStyle(styles.get(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.COMITTED))
				row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.COMITTED).setCellValue(e.isCommited() ? "Y" : "N")

				populateCodebookColumn(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TAKER, e.getTaker(), row, styles)
				row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.TAKER).getCellStyle().setWrapText(true)

				populateCodebookColumn(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.RISK_CLASS, e.getRiskClass(), row, styles)
				row.getCell(hr.foi.diplomski.rad.report.model.ExcelReportExposureColumns.RISK_CLASS).getCellStyle().setWrapText(true)

				row.setZeroHeight(false)
			}

		} catch (Exception ex) {
			throw new RispoException("populateExposure $ex.message")
		}
	}

	/**
	 * addInterestRateComment
	 * @param cell
	 * @param interestRate
	 */
	void addInterestRateComment(HSSFCell cell, InterestRateReference interestRate) throws RispoException {

		try {

			if (cell != null) {
				Drawing drawing = cell.getSheet().createDrawingPatriarch()

				CreationHelper factory = cell.getSheet().getWorkbook().getCreationHelper()
				// When the comment box is visible, have it show in a 1x3 space
				ClientAnchor anchor = factory.createClientAnchor()
				anchor.setCol1(cell.getColumnIndex())
				anchor.setCol2(cell.getColumnIndex() + 3)
				anchor.setRow1(cell.getRow().getRowNum())
				anchor.setRow2(cell.getRow().getRowNum() + 3)

				// Create the comment and set the text+author
				Comment comment = drawing.createCellComment(anchor)
				RichTextString str = factory.createRichTextString(interestRate.getName() + " = " + interestRate.getDescription())
				comment.setString(str)

				// Assign the comment to the cell
				cell.setCellComment(comment)
			}

		} catch (Exception e) {
			throw new RispoException("addInterestRateComment message $e.message")
		}
	}

	/**
	 * populateBigDecimalColumn
	 * @param columnIndex
	 * @param columnValueHrk
	 * @param columnValueEur
	 * @param exportCurrency
	 * @param row
	 * @param styles
	 */
	void populateBigDecimalColumn(int columnIndex, BigDecimal columnValueHrk, BigDecimal columnValueEur, String exportCurrency, HSSFRow row, List < HSSFCellStyle > styles) throws RispoException {

		try {
			if (row != null) {
				row.createCell(columnIndex).setCellStyle(styles.get(columnIndex))
				if ((this.CROATIANCURRENCYCODE.equals(exportCurrency) && columnValueHrk != null) || (this.EUROPEANCURRENCYCODE.equals(exportCurrency) && columnValueEur != null)) {
					row.getCell(columnIndex).setCellValue(this.convertValuesCurrency(columnValueHrk, columnValueEur, true, exportCurrency, 2).doubleValue())
				}
			}

		} catch (Exception e) {
			throw new RispoException("populateBigDecimalColumn message $e.message column $columnIndex")
		}
	}

	/**
	 * populateCodebookColumn
	 * @param columnIndex
	 * @param columnValue
	 * @param row
	 * @param styles
	 */
	void populateCodebookColumn(int columnIndex, CodebookEntry columnValue, HSSFRow row, List < HSSFCellStyle > styles) throws RispoException {

		try {
			if (row != null) {
				row.createCell(columnIndex).setCellStyle(styles.get(columnIndex))
				if (columnValue != null) {
					row.getCell(columnIndex).setCellValue(columnValue.getName())
				}
			}
		} catch (Exception e) {
			throw new RispoException("populateCodebookColumn message $e.message column $columnIndex")
		}

	}

	/**
	 * calculateClientRowHeight
	 * @param sheet
	 * @param member
	 * @param row
	 */
	void calculateClientRowHeight(Client member, Row row) throws RispoException {

		try {
			int height = 1
			if (member.getIndustry() != null) {
				int indHeight = 3 //this.calculateCellHeight(member.getIndustry(), this.INDUSTRY_COLUMN_WIDTH)
				height = Math.max(height, indHeight)
			}
			if (!Strings.isNullOrEmpty(member.getBorrower())) {
				int borrowerHeight = 3 //this.calculateCellHeight(member.getBorrower(), this.BORROWER_COLUMN_WIDTH)
				height = Math.max(height, borrowerHeight)
			}
			if (!Strings.isNullOrEmpty(member.getOwnerName())) {
				int borrowerHeight = 3 //this.calculateCellHeight(member.getOwnerName(), this.OWNERSHIP_COLUMN_WIDTH)
				height = Math.max(height, borrowerHeight)
			}
			
			if (row != null)  {
				row.setHeightInPoints(this.NORMAL_ROW_HEIGHT * height as Float)
			}

		} catch (RispoException e) {
			throw e
		}
	}

	/**
	 * calculateExposureRowHeight
	 * @param sheet
	 * @param e
	 * @param currency
	 * @param row
	 */
	void calculateExposureRowHeight(Exposure e, String currency, Row row) throws RispoException {

		try {
			int height = 1
			if (e.getCollaterals() != null) {
				int columnHeight = 3 //this.calculateCellHeight(getCollateralsAsString(e.getCollaterals(), currency), this.COLLATERALS_COLUMN_WIDTH)
				height = Math.max(height, columnHeight)
			}

			if (e.getTaker() != null && e.getTaker().getName() != null) {
				int columnHeight = 3 //this.calculateCellHeight(e.getTaker().getName(), this.TAKER_COLUMN_WIDTH)
				height = Math.max(height, columnHeight)
			}
			if (!Strings.isNullOrEmpty(e.getTypeOfCredit())) {
				int columnHeight = 3 //this.calculateCellHeight(e.getTypeOfCredit(), this.TYPE_OF_CREDIT_COLUMN_WIDTH)
				height = Math.max(height, columnHeight)
			}
			if (!Strings.isNullOrEmpty(e.getTenor())) {
				int columnHeight = 3 //this.calculateCellHeight(e.getTenor(), this.TENOR_COLUMN_WIDTH)
				height = Math.max(height, columnHeight)
			}
			if (!Strings.isNullOrEmpty(e.getConditions())) {
				int columnHeight = 3 //this.calculateCellHeight(e.getConditions(), this.CONDITIONS_COLUMN_WIDTH)
				height = Math.max(height, columnHeight)
			}
			if (e.getPlasmanType() != null && !Strings.isNullOrEmpty(e.getPlasmanType().getName())) {
				int columnHeight = 3 //this.calculateCellHeight(e.getPlasmanType().getName(), this.TYPE_COLUMN_WIDTH)
				height = Math.max(height, columnHeight)
			}
			if (e.getRiskClass() != null && !Strings.isNullOrEmpty(e.getRiskClass().getName())) {
				int columnHeight = 3 //this.calculateCellHeight(e.getRiskClass().getName(), this.RISK_CLASS_COLUMN_WIDTH)
				height = Math.max(height, columnHeight)
			}
			
			if (row != null)  {
				row.setHeightInPoints(this.NORMAL_ROW_HEIGHT * height as Float)
			}

		} catch (Exception ex) {
			throw new RispoException("calculateExposureRowHeight message $ex.message currency $currency")
		}
	}

	/**
	 * getCollateralsAsString
	 * @param collaterals
	 * @param currency
	 * @return
	 */
	String getCollateralsAsString(List < Collateral > collaterals, String currency) throws RispoException {

		String retVal

		try {
			if (collaterals != null && collaterals.size() > 0) {
				StringBuilder builder = new StringBuilder()
				for (Collateral c: collaterals) {
					builder.append(c.getName())
					// Vrijednosti
					// BigDecimal value = converterService.convertValuesCurrency(c.getValueHrk(), c.getValueEur(), true, currency)
					// if (value != null) {
					// builder.append(" (" + value + ")")
					// }
					builder.append(", ")
				}
				builder.delete(builder.length() - 2, builder.length())
				retVal = builder.toString()
			} else {
				retVal = this.EMPTY_CELL_VALUE
			}

		} catch (Exception ex) {
			throw new RispoException("getCollateralsAsString message $ex.message currency $currency")
		}

		retVal
	}

}