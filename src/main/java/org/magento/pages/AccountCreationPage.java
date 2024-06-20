package org.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class AccountCreationPage {

    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(AccountCreationPage.class);
    private WebDriverWait wait;


    @FindBy(id = "firstname")
    private WebElement firstNameEle;

    @FindBy(id = "lastname")
    private WebElement lastNameEle;

    @FindBy(id = "email_address")
    private WebElement emailEle;

    @FindBy(id = "password")
    private WebElement passwordEle;

    @FindBy(id = "password-confirmation")
    private WebElement passwordConfirmationEle;

    @FindBy(css = "button[title='Create an Account']")
    private WebElement submitBttonEle;

    private By successMsgEle = By.cssSelector(".message-success");
    private By emailErrorEle = By.id("email_address-error");
    private By passwordErrorEle = By.id("password-confirmation-error");
    private By passwordStrengthErrorEle = By.id("password-strength-meter");
    private By messageErrorEle = By.cssSelector(".mage-error");

    public AccountCreationPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public boolean verifyUserIsOnAccountCreationPage(){
        return wait.until(ExpectedConditions.presenceOfElementLocated((By) submitBttonEle)).isDisplayed();
    }
    public void enterFirstName(String firstName){
        firstNameEle.sendKeys(firstName);
        logger.info("Entered first name: {}", firstName);
    }
    public void enterLastName(String lastName){
        lastNameEle.sendKeys(lastName);
        logger.info("Entered last name: {}", lastName);
    }
    public void enterEmail(String email){
        emailEle.sendKeys(email);
        logger.info("Entered email: {}", email);
    }
    public void enterPassword(String password){
        passwordEle.sendKeys(password);
        logger.info("Entered password: {}", password);

    }
    public void enterConfirmPassword(String confirmPassword){
        passwordConfirmationEle.sendKeys(confirmPassword);
        logger.info("Entered confirm password: {}", confirmPassword);

    }
    public void clickOnSubmitButton(){
        submitBttonEle.click();
        logger.info("Submitted the registration form.");
    }

    public boolean isConfirmationMessageVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMsgEle)) != null;
    }
    public boolean isEmailErrorVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailErrorEle)) != null;
    }
    public boolean isPasswordErrorVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorEle)) != null;
    }

    public boolean isWeakPasswordErrorMessageVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordStrengthErrorEle)) != null;
    }
    public boolean isMissingFieldsErrorMessageVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(messageErrorEle)) != null;
    }


}
