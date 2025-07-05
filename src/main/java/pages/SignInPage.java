package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testenvironment.TestSetup;

public class SignInPage extends TestSetup {

    @FindBy(xpath = "//h5[@id='logInModalLabel']")
    private WebElement loginPopUp;

    @FindBy(xpath = "//input[@id='loginusername']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='loginpassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement submitButton;

    @FindBy(xpath = "//a[@id='nameofuser']")
    private WebElement welcomeUser;
    
    @FindBy(xpath = "//a[@id='login2']")
    private WebElement loginMenuOption;

    private String username;
    private String password;
    
    public SignInPage(WebDriver driver, String username, String password) {
        PageFactory.initElements(driver, this);
        this.username = username;
        this.password = password;
    }

    public boolean login() {
    	
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginMenuOption)).click();
        wait.until(ExpectedConditions.visibilityOf(loginPopUp));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
        wait.until(ExpectedConditions.invisibilityOf(loginPopUp));
        wait.until(ExpectedConditions.visibilityOf(welcomeUser));
        String actualText = welcomeUser.getText();
        boolean isDisplayed = welcomeUser.isDisplayed();
        if (!actualText.equals("Welcome " + username)) {
            throw new AssertionError("Expected welcome text but got: " + actualText);
        }
        
        return isDisplayed;
    }
}

    

