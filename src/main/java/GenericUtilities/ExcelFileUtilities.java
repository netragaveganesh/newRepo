package GenericUtilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtilities {
	Workbook wb;
	
	/**
	 * This method is used to fetch the data from excel file by providing sheet name, row and index   
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String fetchDataFromExcelFile(String sheetName, int rowIndex, int cellIndex) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/vtigerHRMS_Data.xlsx");
		 wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		Row r=sh.getRow(rowIndex);
		Cell c=r.getCell(cellIndex);
		String data=c.getStringCellValue();				
		return data;
	}
	
	
	/**
	 * This method is used to create the row ad cell newly.
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @param cellData
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeBackDataToExcel(String sheetName, int rowIndex, int cellIndex,String cellData) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/vtigerHRMS_Data.xlsx");
		 wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName);
		Row r=sh.createRow(rowIndex);
		Cell c=r.createCell(cellIndex);
		
		c.setCellValue(cellData);
	}
	
	public void closeExcel() throws IOException {
		wb.close();
	}
	
	

	/**
	 * This method is used to fetch multiple data from excel sheet
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String fetchMultipleDataFromExcel(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/vtigerHRMS_Data.xlsx");
		 wb=WorkbookFactory.create(fis);
		 Sheet sh=wb.getSheet(sheetName);
		 String data=null;
		 
		 for(int i=0; i<=sh.getLastRowNum(); i++) {
			 for(int j=0; j<=sh.getRow(i).getLastCellNum(); j++) {
			 data= sh.getRow(i).getCell(j).toString();
			 }
		 }
		 return data;
	}

}
