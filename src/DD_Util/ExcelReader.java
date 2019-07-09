package DD_Util;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	Workbook book;
	String Email;
	
	public ExcelReader(String filename) {
		File f = new File(filename);
		try {

			FileInputStream fis = new FileInputStream(f);
			book = WorkbookFactory.create(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int getRowCount(String sheetname) {
		int count = 0;
		Sheet sheet = book.getSheet(sheetname);
		for (Row row : sheet) {
			count++;
		}
		return count;
	}

	int getCellCount(String sheetname) {
		Sheet sheet = book.getSheet(sheetname);
		int count = 0;
		for (Row row : sheet) {
			for (Cell cell : row) {
				count++;
			}
			break;
		}
		return count;
	}

	Object getCellData(String sheetname, int i, int j) {
		Object obj = new Object();
		Sheet sheet = book.getSheet(sheetname);
		Row row = sheet.getRow(i);
		Cell cell = row.getCell(j);
		CellType type = cell.getCellTypeEnum();
		
		if (type == CellType.STRING) {
			Email = cell.getStringCellValue();
		} 
		else if (type == CellType.NUMERIC) {
			long s = (long) cell.getNumericCellValue();
			Email = String.valueOf(s);
		}
		obj = Email;
		return obj;

	}
}
