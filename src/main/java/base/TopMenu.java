package base;

import org.openqa.selenium.WebDriver;

import pages.crm.CRMAccountPage;

public class TopMenu {
	
	public WebDriver driver;

	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}
	
	public void goToLeads() {

		Page.click("lead_XPATH");
	}

	public void goToContact() {

		Page.click("contact_XPATH");
	}

	public CRMAccountPage goToAccounts() {
		Page.click("accounts_XPATH");
		return new CRMAccountPage();
	}

	public void goToDeals() {
		Page.click("deals_XPATH");
	}

	public void goToActivities() {

	}

}
