package tests;

import org.testng.annotations.Test;
import testenvironment.TestSetup;

public class SignInPageTest extends TestSetup{
	
	@Test(priority=1)
	public void verifySignInFunctionality()  {
		boolean actualResult = signinpage.login();
        if (actualResult==true) {
            report.logPass("Sign in functionality is working");
        } else {
            report.logFail("Sign in functionality is not working");
        }
	}
}
