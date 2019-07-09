package DD_Util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import DD_Core.TestCore;

public class TestUtil extends TestCore {

	public static String mailscreenshot;
	public static Object[][] getData(String sheetname) {

		int row = excel.getRowCount("Sheet1") - 1;
		int Cell = excel.getCellCount("Sheet1");

		System.out.println("RowCount is :  " + row + "  Cell count is: " + Cell);

		Object[][] data = new Object[row][Cell];
		int k = 0;
		for (int i = 1; i <= row; i++) {
			for (int j = 0; j < Cell; j++) {
				data[k][j] = excel.getCellData("Sheet1", i, j);
			}
			k++;
		}
		return data;
	}
	
	public static void CaptureScreenshot() throws IOException
	{
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year= cal.get(Calendar.YEAR);
		int sec= cal.get(Calendar.SECOND);
		int min= cal.get(Calendar.MINUTE);
		int date= cal.get(Calendar.DATE);
		int day= cal.get(Calendar.HOUR_OF_DAY);
		
		mailscreenshot = System.getProperty("user.dir")+"\\screenshot\\"+year+"-"+date+"-"+(month-1)+"-"+min+"-"+sec+".jpeg";
		
		
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(f, new File("E:\\Screenshot\\ " + mailscreenshot + ".jpg"));
	}
	
	
	

}
