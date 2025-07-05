package pages;

import testenvironment.TestSetup;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends TestSetup {

    @FindBy(xpath = "//a[text()='Add to cart']")
    private WebElement addCartButton;
    
    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean addToCart() {
        try {

            addCartButton.click();
            
            Thread.sleep(2000); 
            Alert alert = driver.switchTo().alert();
            
            String alertText = alert.getText();

            alert.accept();

            return alertText.contains("Product added");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}

    

