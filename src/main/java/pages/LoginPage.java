package pages;

import base.Page;

public class LoginPage extends Page{

	
	public ZohoAppPage doLogin() {
		
		type("emailAdd_XPATH","seleniumpractice3@gmail.com");
		click("nextBtn_XPATH");
		type("password_XPATH","Selenium@1234");
		click("signinBtn_XPATH");
		
		return new ZohoAppPage();
	}
	
}
