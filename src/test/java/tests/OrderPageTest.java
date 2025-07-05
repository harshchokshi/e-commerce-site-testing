package tests;



import org.testng.annotations.Test;
import testenvironment.TestSetup;

public class OrderPageTest extends TestSetup{
	
	@Test(priority=1)
	public void verifyorderplacingFunctionality()  {
		boolean actualResult = orderPage.placeOrder();
        if (actualResult) {
            report.logPass("Order placing functionality is working");
        } else {
            report.logFail("Order placing functionality is not working");
        }
	}
	

}
