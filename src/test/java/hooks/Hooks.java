package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.magento.drivers.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Hooks {

    @After
    public void attachLogs(Scenario scenario) {
        if (scenario.isFailed()) {
            // Capture screenshot if the scenario fails
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }

        // Attach logs to the report
        try {
            File logFile = new File("logs/test.log");
            FileInputStream fileInputStream = new FileInputStream(logFile);
            byte[] logContent = new byte[(int) logFile.length()];
            fileInputStream.read(logContent);
            scenario.attach(logContent, "text/plain", "Logs");
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
