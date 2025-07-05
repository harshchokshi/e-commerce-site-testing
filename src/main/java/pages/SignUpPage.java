package pages;

import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testenvironment.TestSetup;

public class SignUpPage extends TestSetup {
	
	private String username;
	private String password;

    public SignUpPage(WebDriver driver, String username, String password) {
        PageFactory.initElements(driver, this);
        this.username = username;
        this.password = password;
        
    }

    @FindBy(xpath = "//a[@id='signin2']")
    private WebElement signUpMenuOption;

    @FindBy(xpath = "//a[@id='login2']")
    private WebElement loginMenuOption;

    @FindBy(xpath = "//h5[@id='signInModalLabel']")
    private WebElement signUpPopUp;

    @FindBy(xpath = "//input[@id='sign-username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='sign-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement submitButton;

    public String pageTitle() {
        return driver.getTitle();
    }
    

    
    public boolean register() {
   
        signUpMenuOption.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signUpPopUp));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        submitButton.click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        
        boolean isAlertTextValid = alertText.contains("Sign up successful");
        
        if (!alertText.contains("Sign up successful")) {
            throw new AssertionError("Expected 'Sign up successful' but got: " + alertText);
        }
        alert.accept();
        
        return isAlertTextValid;
    }
}

    

