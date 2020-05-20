package oleh.bilyk.tests;

import io.qameta.allure.Attachment;
import oleh.bilyk.helpers.AllureEnvironmentWriter;
import oleh.bilyk.helpers.Config;
import oleh.bilyk.webDriver.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static oleh.bilyk.webDriver.Driver.getDriver;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 24/04/2020
 * #Comments:
 */
public abstract class BaseTest {
    @BeforeSuite(alwaysRun = true)
    public void writeEnvironmentProp() {
        AllureEnvironmentWriter.write("OS", System.getProperty("os.name"));
        AllureEnvironmentWriter.write("Java ver.", System.getProperty("java.version"));
        AllureEnvironmentWriter.write("Browser", Config.getInstance().BROWSER().toString());
        AllureEnvironmentWriter.write("Base URL", Config.getInstance().BASE_HOST());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            takeScreenShot();
        }
        Driver.kill();
    }

    @Attachment
    private byte[] takeScreenShot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
