package pages;

import base.Page;
import pages.crm.CRMHomePage;

public class ZohoAppPage extends Page{

	public CRMHomePage goToCRM() {
		
		click("crmApp_XPATH");
		
		return new CRMHomePage();
		
	}
	
	public void goToBooks() {
		
		
	}
	
	public void goToConnect() {
		
	}
	
	public void goToDesk() {
		
		
	}
}
