package pages.crm;

import org.openqa.selenium.By;

import base.Page;

public class CRMHomePage extends Page{

	
	public void verifyHeaderPage() {
		
		String expected="Welcome";
		String actual=driver.findElement(By.xpath(or.getProperty("titlePage_XPATH"))).getText();
		isVerifyEquals(expected,actual);

		
	}

	public void navigateClassicView() {

	}

	public void navigateUserHome() {

	}

	public void navigateManagerHome() {

	}

}
