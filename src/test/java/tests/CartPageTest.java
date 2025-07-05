package tests;



import org.testng.annotations.Test;
import testenvironment.TestSetup;

public class CartPageTest extends TestSetup{
	
	@Test(priority=1)
	public void verifyaddtocardFunctionality()  {
		boolean actualResult = cartpage.addToCart();
        if (actualResult) {
            report.logPass("Add to cart functionality is working");
        } else {
            report.logFail("Add to card functionality is not working");
        }
	}
	

}
