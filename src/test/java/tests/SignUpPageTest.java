package tests;

import org.testng.annotations.Test;
import testenvironment.TestSetup;

public class SignUpPageTest extends TestSetup{
	
	@Test(priority=1)
	public void verifyLandingPage()  {
		String actualTitle = signuppage.pageTitle();
		String expectedTitle = "STORE";
		
        if (actualTitle.equals(expectedTitle)) {
            report.logPass("Actual page title matches with expected value");
        } else {
            report.logFail("Page title does not match the expected value");
        }
	}
	
	@Test(priority=2)
	public void verifyRegisterFunctionality() {
		
        boolean actualResult = signuppage.register();
		
		if (actualResult) {
			report.logPass("Register functionality is working");
		} else {
			report.logFail("Register functionality is not working");
		}
	}
}
