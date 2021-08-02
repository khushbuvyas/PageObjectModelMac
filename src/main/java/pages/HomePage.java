package pages;

import org.openqa.selenium.By;

import base.Page;

public class HomePage extends Page{

	public void goToCustomer() {
		
		click("customer_XPATH");
	}

	public void goToSupport() {

		click("support_XPATH");
		
	}

	public void goToEvent() {

		click("event_XPATH");
	}

	public LoginPage goToSignIn() {

		click("signIn_XPATH");
		
		return new LoginPage();
	}

	public void goToSignUp() {

		click("signUp_XPATH");
	}

}
