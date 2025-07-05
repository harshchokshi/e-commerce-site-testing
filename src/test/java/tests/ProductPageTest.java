package tests;

import org.testng.annotations.Test;
import testenvironment.TestSetup;

public class ProductPageTest extends TestSetup{
	
	@Test(priority=1)
	public void verifyProductSearchFunctionality()  {
		boolean actualResult = productpage.searchProduct();
        if (actualResult) {
            report.logPass("Product search functionality is working");
        } else {
            report.logFail("Product search functionality is not working");
        }
	}
	
}
