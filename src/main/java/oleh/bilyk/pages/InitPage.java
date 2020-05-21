package oleh.bilyk.pages;

import io.qameta.allure.Step;
import oleh.bilyk.webDriver.DriverManager;
import oleh.bilyk.webDriver.DriverWaiter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 21/05/2020
 * #Comments:
 */
@Component
public class InitPage {
    @Value("${base.url}")
    private String baseUrl;
    @Autowired
    private DriverManager driverManager;
    @Autowired
    private DriverWaiter driverWaiter;
    @Autowired
    private Logger log;
    private static final By BUTTON_SIGN_IN = By.cssSelector(".HeaderMenu-link.mr-3.no-underline");
    private static final By IMAGE_LOGO = By.cssSelector(".octicon.octicon-mark-github.text-white");

    public void invoke() {
        if (!verify()) {
            driverManager.getDriver().navigate().to(baseUrl);
            driverWaiter.waitForElementDisplayed(BUTTON_SIGN_IN);
        }
    }

    //<editor-fold desc="Public Methods">
    @Step("Verify that Main page is loaded")
    public boolean verify() {
        try {
            return driverManager.getDriver().findElement(IMAGE_LOGO).isDisplayed()
                    && driverManager.getDriver().findElement(BUTTON_SIGN_IN).isDisplayed();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return false;
    }

    @Step
    public void navigateToLoginPage() {
        driverWaiter.waitForElementIsNotDisplayed(BUTTON_SIGN_IN, 1000);
    }

    @Step("Wait until leave the Main page")
    public void waitUntilLeave() {
        driverWaiter.waitForElementIsNotDisplayed(BUTTON_SIGN_IN, 1000);
    }
    //</editor-fold>
}
