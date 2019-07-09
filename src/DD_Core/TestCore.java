package DD_Core;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import DD_Util.ExcelReader;
import DD_Util.TestUtil;

public class TestCore {

	public static WebDriver driver = null;
	public static Properties config = null;
	public static Properties object = null;
	public static FileInputStream fis = null;
	public static ExcelReader excel = null;
	public static Logger logs = null;

	@BeforeSuite
	public void init() throws IOException {
		if (driver == null) {
			config = new Properties();
			object = new Properties();
			logs = Logger.getLogger(TestCore.class);

			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\DD_Properties\\config.properties");
			config.load(fis);
			logs.debug("Loaded the config Property file ");

			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\DD_Properties\\object.properties");
			config.load(fis);
			logs.debug("Loaded the config Property file ");

			excel = new ExcelReader(System.getProperty("user.dir") +"\\src\\DD_Properties\\Testlogin.xlsx");

			if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "E:\\selenium\\jar file\\Selenium jar\\geckodriver.exe");
				driver = new FirefoxDriver();
				logs.debug("Load Firefox Driver");

			} else if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"E:\\selenium\\jar file\\Selenium jar\\chrome\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver();
				logs.debug("Load chrome Driver");

			}

			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			logs.debug("URL is Loading");

		}

	}

	@AfterSuite
	public void stop() {

		// mail sending code write here
		driver.quit();

	}

	public static WebElement findElement(String key) throws IOException {
	
		try {
			
			return driver.findElement(By.xpath(object.getProperty(key)));
	
		} catch (Throwable t) {
			

		logs.debug("Captured Screenshot");
		TestUtil.CaptureScreenshot();
		return null;
	}
	}
}
	
	


