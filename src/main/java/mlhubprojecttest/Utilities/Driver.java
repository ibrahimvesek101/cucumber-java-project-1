package mlhubprojecttest.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import mlhubprojecttest.Utilities.ConfigurationReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Driver {


    public static WebDriver driver;
    public static WebDriver getDriver(){

        if(driver == null){
            switch (ConfigurationReader.getProperty("browser")){
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver();
                    break;
                case "headless-chrome":
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    driver = new InternetExplorerDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));;
        driver.manage().window().maximize();
        return driver;
    }
    public static void closeDriver(){

        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
