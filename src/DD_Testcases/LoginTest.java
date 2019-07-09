package DD_Testcases;

import java.io.IOException;


import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.annotations.DataProvider;

import DD_Core.TestCore;
import DD_Util.TestUtil;

public class LoginTest extends TestCore {

	public void doLogin(String username, String password) throws IOException {
		logs.debug("Inside Login Test");

		findElement("username").sendKeys(username);
		findElement("password").sendKeys(password);
		findElement("login").click();

	}

	@DataProvider
	public static Object[][] getData(String sheetname) {
		return TestUtil.getData("LoginTest");
	}

}
