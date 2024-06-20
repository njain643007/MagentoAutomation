package org.magento.pages;

import org.magento.drivers.DriverFactory;
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

public class LoginPage {

    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private WebDriverWait wait;

    @FindBy(id = "email")
    private WebElement emailEle;

    @FindBy(id = "pass")
    private WebElement passwordEle;

    @FindBy(xpath = "//button[@name='send']/span")
    private WebElement signInEle;

    private By messegeErrorEle= By.cssSelector(".message-error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public boolean isUserOnSignInPage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated((By) emailEle)) != null;
    }

    public void enterEmail(String email) {
        emailEle.sendKeys(email);
        logger.info("Entered email: {}", email);

    }

    public void enterPassword(String password) {
        passwordEle.sendKeys(password);
        logger.info("Entered password: {}", password);

    }

    public void clickSignIn() {
        signInEle.click();
        logger.info("Clicked sign in button.");

    }
    public boolean isAuthenticationErrorMessageVisible(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(messegeErrorEle)) != null;
    }

}
