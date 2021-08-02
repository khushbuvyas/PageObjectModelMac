package testcases;

import org.testng.annotations.Test;

import base.Page;
import pages.HomePage;
import pages.LoginPage;
import pages.ZohoAppPage;

public class LoginTestcase extends Base{
	
	@Test
	public void doLoginTest() {
		
		HomePage home = new HomePage();
		LoginPage login = home.goToSignIn();
		login.doLogin();
		
		//Page.quit();
	}

}
