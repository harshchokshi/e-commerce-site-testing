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


public class OrderPage extends TestSetup {
	
	private String title;
	private String name;
	private String country;
	private String city;
	private String card;
	private String month;
	private String year;

	@FindBy(id = "cartur")
	private WebElement cartButton;

	@FindBy(xpath = "//button[text()='Place Order']")
	private WebElement placeOrderButton;

	@FindBy(id = "name")
	private WebElement nameTextbox;

	@FindBy(id = "country")
	private WebElement countryTextbox;

	@FindBy(id = "city")
	private WebElement cityTextbox;

	@FindBy(id = "card")
	private WebElement cardTextbox;

	@FindBy(id = "month")
	private WebElement monthTextbox;

	@FindBy(id = "year")
	private WebElement yearTextbox;

	@FindBy(xpath = "//button[text()='Purchase']")
	private WebElement purchaseButton;

	@FindBy(xpath = "//h2[contains(text(),'Thank you for your purchase!')]")
	private WebElement thankYouText;

	@FindBy(xpath = "//p[@class='lead text-muted ']")
	private WebElement confirmationDetails;

    
    public OrderPage(WebDriver driver, String title, String name, String country, String city, String card, String month, String year) {
        PageFactory.initElements(driver, this);
        this.title = title;
        this.name = name;
        this.country = country;
        this.city = city;
        this.card = card;
        this.month = month;
        this.year = year;
       
    }

    public boolean placeOrder() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            

            cartButton.click();
            
       
            WebElement productTitleElement = driver.findElement(By.xpath("//td[text()='" + title + "']"));
            WebElement productPriceElement = driver.findElement(By.xpath("//td[text()='" + productpage.getProductPrice() + "']"));
            wait.until(ExpectedConditions.visibilityOf(productTitleElement));
            wait.until(ExpectedConditions.visibilityOf(productPriceElement));
     
            placeOrderButton.click();
            
            nameTextbox.clear();
            nameTextbox.sendKeys(name);
            
            countryTextbox.clear();
            countryTextbox.sendKeys(country);
            
            cityTextbox.clear();
            cityTextbox.sendKeys(city);
            
            cardTextbox.clear();
            cardTextbox.sendKeys(card);
            
            monthTextbox.clear();
            monthTextbox.sendKeys(month);
            
            yearTextbox.clear();
            yearTextbox.sendKeys(year);
            
            purchaseButton.click();
            
    
            wait.until(ExpectedConditions.visibilityOf(thankYouText));
            wait.until(ExpectedConditions.visibilityOf(confirmationDetails));
            
    
            String confirmationText = confirmationDetails.getText();
            
        
            Pattern namePattern = Pattern.compile("Name: (.*)");
            Pattern cardPattern = Pattern.compile("Card Number: (.*)");
            Pattern amountPattern = Pattern.compile("Amount:\\s+(\\d+)\\s+(\\w+)");
            
            Matcher nameMatch = namePattern.matcher(confirmationText);
            Matcher cardMatch = cardPattern.matcher(confirmationText);
            Matcher amountMatch = amountPattern.matcher(confirmationText);
            
            boolean nameValid = nameMatch.find() && nameMatch.group(1).trim().equals(name);
            boolean cardValid = cardMatch.find() && cardMatch.group(1).trim().equals(card);
            boolean priceValid = amountMatch.find() && amountMatch.group(1).trim().equals(productpage.getProductPrice());
            boolean thankYouTextVisible = thankYouText.isDisplayed();
            boolean confirmationDetailsVisible = confirmationDetails.isDisplayed();
            
            return nameValid && cardValid && priceValid && thankYouTextVisible && confirmationDetailsVisible;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
}

    

