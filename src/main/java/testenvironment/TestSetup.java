package testenvironment;

import pages.SignInPage;
import pages.SignUpPage;
import pages.ProductPage;
import pages.CartPage;
import pages.OrderPage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;
import testreporting.TestReport;

public class TestSetup {
	protected static WebDriver driver;
	protected static Properties prop;
	protected static TestReport report;
	protected static SignUpPage signuppage;
	protected static SignInPage signinpage;
	protected static ProductPage productpage;
	protected static CartPage cartpage;
	protected static OrderPage orderPage;
	
	
	
	public void testEnvironmentSetup() {
		
		try {
			prop = new Properties();
			FileInputStream config = new FileInputStream(System.getProperty("user.dir") + "/resources/config.properties");
			prop.load(config);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String browserName = prop.getProperty("browser");
		
	    if (browserName.equalsIgnoreCase("chrome")) {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	    } else if (browserName.equalsIgnoreCase("firefox")) {
	        WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();
	    } else if (browserName.equalsIgnoreCase("edge")) {
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();  
	    } else {
	        throw new RuntimeException("Browser not supported: " + browserName);
	    }

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
    public static String generateUsername() {
        String lowercaseChars = "abcdef";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(6); 
            result.append(lowercaseChars.charAt(index));
        }

        return result.toString();
    }
    
    String username = generateUsername();
    String password = "Admin@123"; 
    String productCategory = "Laptops"; 
    String name = "Gal Ritiche";
    String productName = "2017 Dell 15.6 Inch"; 
    String country = "United Kingdom"; 
    String city = "London"; 
    String card = "1746882045041"; 
    String month = "03"; 
    String year = "2027"; 
	
	@BeforeSuite
	public void startTestSuite() {
		
		testEnvironmentSetup();
		report = new TestReport(driver);
		signuppage = new SignUpPage(driver, username, password);
		signinpage = new SignInPage(driver, username, password);
		productpage = new ProductPage(driver, productCategory, productName);
		cartpage = new CartPage(driver);
        orderPage = new OrderPage(driver, productName, name, country, city, card, month, year);
	}
	
	@BeforeMethod
	public void startTest(Method m) {
		
		report.startTest(m.getName());
	}
	
	@AfterMethod
	public void endTest() {
		report.endTest();
	}
	
	@AfterSuite
	public void endTestSuite() {
		report.endTestSuite();
		driver.quit();
	}
	
	
	
}
