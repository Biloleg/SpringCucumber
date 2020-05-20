package oleh.bilyk.webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import oleh.bilyk.helpers.Config;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 20/05/2020
 * #Comments:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Driver {
    public static final Logger log = Logger.getLogger("mylog");
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //<editor-fold desc="Enums">
    public enum Browser {
        CHROME,
        FIREFOX,
        OPERA,
        IE;
    }
    //</editor-fold>

    //<editor-fold desc="Public Methods">
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(init());
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void kill() {
        driver.get().close();
        driver.get().quit();
        driver.remove();
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private static WebDriver init() {
        switch (Config.getInstance().BROWSER()) {
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
                throw new IllegalStateException("Unexpected value: " + Config.getInstance().BROWSER());
        }
    }
    //</editor-fold>
}
