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

public class MyAccountPage {

    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private WebDriverWait wait;

    @FindBy(css=".customer-menu .authorization-link")
    private WebElement signoutEle;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public boolean isUserOnMyAccountPage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-action='customer-menu-toggle']"))) != null;
    }

    public boolean signOutFromApplication(){
        driver.findElement(By.xpath("//button[@data-action='customer-menu-toggle']")).click();
        signoutEle.click();
        logger.info("Clicked sign out button.");
        return wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//span[text()='You are signed out']")))) != null;
    }
}
