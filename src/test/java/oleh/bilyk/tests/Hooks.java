package oleh.bilyk.tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import oleh.bilyk.helpers.AllureEnvironmentWriter;
import oleh.bilyk.testrail.TestRailIntegration;
import oleh.bilyk.webDriver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 23/05/2020
 * #Comments:
 */
public class Hooks {
    @Autowired
    TestRailIntegration testRailIntegration;
    @Autowired
    DriverManager driverManager;
    @Autowired
    AllureEnvironmentWriter allureEnvironmentWriter;
    @Value("${browser}")
    String browser;
    @Value("${base.url}")
    private String baseUrl;
    private static boolean isFirstTest = true;

    //<editor-fold desc="Public Methods">
    @Before
    public void init() {
        if (isFirstTest) {
            isFirstTest = false;
            writeEnvironmentProp();
        }
    }

    @After(order = 3)
    public void after(Scenario scenario) {
        testRailIntegration.write(scenario);
    }

    @After(order = 2)
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenShot = ((TakesScreenshot) driverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenShot, "image/png", "On Failure Screenshot");
        }
    }

    @After(order = 1)
    public void killDriver() {
        driverManager.kill();
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private void writeEnvironmentProp() {
        allureEnvironmentWriter.write("OS", System.getProperty("os.name"));
        allureEnvironmentWriter.write("Java ver.", System.getProperty("java.version"));
        allureEnvironmentWriter.write("Browser", browser);
        allureEnvironmentWriter.write("Base URL", baseUrl);
    }
    //</editor-fold>
}
