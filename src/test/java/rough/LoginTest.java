package rough;

import base.Page;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest {

	public static void main(String[] args) {
		
		HomePage home = new HomePage();
		LoginPage login = home.goToSignIn();
		login.doLogin();
		
		Page.quit();
		
	}

}
