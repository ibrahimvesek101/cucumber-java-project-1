package mlhubprojecttest.StepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import mlhubprojecttest.Utilities.ConfigurationReader;
import mlhubprojecttest.Utilities.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class Hooks {

    @Before
    public void setUp() {

        Driver.getDriver().get(ConfigurationReader.getProperty("aimpedUrl"));

    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            TakesScreenshot ts = (TakesScreenshot) Driver.driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            File file = ts.getScreenshotAs(OutputType.FILE);

            String destine = System.getProperty("user.dir") + "/test-output/Screenshots" + "failed" + scenario.getName() + ".png";

            try {
                FileUtils.copyFile(file, new File(destine));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            scenario.attach(screenshot, "image/png", "screenshots");

        }

           Driver.closeDriver();

    }


}
