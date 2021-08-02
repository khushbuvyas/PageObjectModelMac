package testcases;

import org.testng.annotations.AfterSuite;

import base.Page;

public class Base {

	@AfterSuite
	public void tearDown() {
		
		if(Page.driver != null) {
			
			Page.quit();
		}
	}
	
}
