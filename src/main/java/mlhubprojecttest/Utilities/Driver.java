package mlhubprojecttest.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (ConfigurationReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromiumdriver().setup(); // Chromium için WebDriver'ı ayarla
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);
                    break;
                case "headless-chrome":
                    WebDriverManager.chromiumdriver().setup(); // Chromium için WebDriver'ı ayarla
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup(); // Firefox için WebDriver'ı ayarla
                    driver = new FirefoxDriver();
                    break;
                // Diğer tarayıcılar için gerekirse aynı şekilde ayarlanabilir
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // Süre için sonundaki fazla noktayı kaldırdım
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
