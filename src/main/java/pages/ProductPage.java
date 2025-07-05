package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testenvironment.TestSetup;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductPage extends TestSetup {
	
	private String productCategory;
	private String productName;
	String priceInNumber;

    @FindBy(xpath = "//h3[@class='price-container']")
    private WebElement productPrice;
    
    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextButton;
    
    public ProductPage(WebDriver driver, String productCategory, String productName) {
        PageFactory.initElements(driver, this);
        this.productCategory = productCategory;
        this.productName = productName;
    }

    public boolean searchProduct() {
        int maxAttempts = 10;
        int attempts = 0;
        boolean productFound = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By productNameLocator = By.xpath("//a[text()='" + productName + "']");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='" + productCategory + "']"))).click();

        while (!productFound && attempts < maxAttempts) {
            try {
                WebElement name = driver.findElement(productNameLocator);
                if (name.isDisplayed()) {
                    productFound = true;
                    break;
                }
            } catch (Exception e) {
                e.getMessage();
            }
            nextButton.click();
            attempts++;
            wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        }

        if (!productFound) {
            throw new RuntimeException("Product not found after " + maxAttempts + " attempts");
        }
   
        wait.until(ExpectedConditions.elementToBeClickable(productNameLocator)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='" + productName + "']")));
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        String priceText = productPrice.getText();
        Pattern pattern = Pattern.compile("\\$(.*?)\\*");
        Matcher matcher = pattern.matcher(priceText);
        
       
        if (matcher.find()) {
            priceInNumber = matcher.group(1).trim();
            
             return productFound;
        }
        return false;
    }
    
    public String getProductPrice() {
		return priceInNumber;
	}
    
}

    

