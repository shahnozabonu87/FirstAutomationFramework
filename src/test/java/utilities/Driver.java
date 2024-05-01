package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    /**
     * This class is contains the implementation for
     * switching and setting up drivers.
     * the method .getDriver();  will set up WebDriver
     * type based on the browser value provided in the
     * Configuration.properties file.
     */

    private static WebDriver driver;
    public static WebDriver getDriver() {
        String browser = ConfigReader.getProperty("browser");
        if (driver == null || ((RemoteWebDriver) driver).getSessionId() == null) {
            if (browser.equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                driver = new ChromeDriver();
            } else if (browser.equals("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else if (browser.equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (browser.equals("safari")) {
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
            } else if (browser.equals("opera")) {
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
            } else {
                return driver;
            }

        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }



}
