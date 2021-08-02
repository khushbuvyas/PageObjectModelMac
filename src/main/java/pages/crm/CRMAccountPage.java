package pages.crm;

import base.Page;

public class CRMAccountPage extends Page{

	
	public CreateAccountPage goToCreateAccount() {
		
		click("crtAccBtn_XPATH");
		
		return new CreateAccountPage();
	}
	
	public void viewAccountDropdown() {
		
	}
	
	public void selectAccountFromListed() {
		
		
	}
}
