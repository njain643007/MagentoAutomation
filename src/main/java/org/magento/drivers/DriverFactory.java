package org.magento.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            logger.info("Driver is set up and browser window maximized.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("Driver is closed.");
        }
    }
}
