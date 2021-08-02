package testcases;

import org.testng.annotations.Test;

import base.Page;
import pages.ZohoAppPage;
import pages.crm.CRMAccountPage;
import pages.crm.CRMHomePage;
import pages.crm.CreateAccountPage;

public class CreateAccountTest extends Base{
	
	@Test
	public void createAccount() {
		
		ZohoAppPage zp = new ZohoAppPage();
		zp.goToCRM();
		
		CRMAccountPage accPage = Page.menu.goToAccounts();
		
		CreateAccountPage crtAcc = accPage.goToCreateAccount();
		
		crtAcc.createAccount("selenium");
		
		
	}

	
	


}
