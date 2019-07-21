package ExcelUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Assignment.Change_Password.PasswordVerification;


public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	public static XSSFWorkbook workbook;
	private static org.apache.poi.ss.usermodel.Cell Cell;
	private static XSSFRow Row;
	// private static XSSFRow Row;


	
	public static int getRowCount(String SheetName)throws Exception{
		int iNumber=0;
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			iNumber=ExcelWSheet.getLastRowNum()+1;
			

			
		} catch (Exception e){
			System.out.println("Exception--"+e);
			}
		return iNumber;
		}
	
	public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{
		int iRowNum=0;	
		try {
		    //ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int rowCount = ExcelUtils.getRowCount(SheetName);
			for (; iRowNum<rowCount; iRowNum++){
				if  (ExcelUtils.getCellData(iRowNum,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
					break;
				}
			}       			
		} catch (Exception e){
			System.out.println("Exception--"+e);
			}
		return iRowNum;
		}
	public static void setExcelFile(String Path) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);

		} catch (Exception e) {
			System.out.println("exception -"+e);
		}
	}

	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception {
		try {

			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {

			System.out.println("exception in get cell data" + e);

			return "";
		}
	}

	@SuppressWarnings("static-access")
	public static void setCellData(String Result, int RowNum, int ColNum, String SheetName, String datasheetloc)
			throws Exception {
		try {

			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			 CellStyle stylePass = ExcelWBook.createCellStyle();
			 stylePass.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			 stylePass.setFillPattern(CellStyle.SOLID_FOREGROUND);
			 CellStyle styleFail = ExcelWBook.createCellStyle();
			 styleFail.setFillForegroundColor(IndexedColors.RED.getIndex());
			 styleFail.setFillPattern(CellStyle.SOLID_FOREGROUND);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
				if(Result.equals("Pass")){
					Cell.setCellStyle(stylePass);
				}else if(Result.equals("Fail")){
					Cell.setCellStyle(styleFail);
				}
			} else {
				Cell.setCellValue(Result);
				if(Result.equals("Pass")){
					Cell.setCellStyle(stylePass);
				}else if(Result.equals("Fail")){
					Cell.setCellStyle(styleFail);
				}
			}
			FileOutputStream fileOut = new FileOutputStream(datasheetloc);

			XSSFFormulaEvaluator.evaluateAllFormulaCells(ExcelWBook);
		
			ExcelWBook.write(fileOut);

		
			ExcelWSheet = null;
			ExcelWBook = null;

			ExcelWBook = new XSSFWorkbook(new FileInputStream(datasheetloc));
		} catch (Exception e) {
			System.out.println("exception -"+e);
		}
	}

}