package pages.crm;

import base.Page;

public class CreateAccountPage extends Page{
	
	
	public void createAccount(String accName) {
		
		type("accName_ID", accName);
		
	}

}
