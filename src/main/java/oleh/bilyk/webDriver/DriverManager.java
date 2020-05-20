package oleh.bilyk.webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 20/05/2020
 * #Comments:
 */
@Component
public class DriverManager {
    private Browser browser;
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Value("${browser}")
    private void setBrowser(String browser) {
        this.browser = Browser.valueOf(browser.toUpperCase());
    }

    //<editor-fold desc="Enums">
    public enum Browser {
        CHROME,
        FIREFOX,
        OPERA,
        IE;
    }
    //</editor-fold>

    //<editor-fold desc="Public Methods">
    public WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(init());
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    @PreDestroy
    public void kill() {
        driver.get().close();
        driver.get().quit();
        driver.remove();
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private WebDriver init() {
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case OPERA:
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            case IE:
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }
    }
    //</editor-fold>
}
