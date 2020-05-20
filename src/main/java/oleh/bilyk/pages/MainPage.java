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
 * #Creation Date: 22/04/2020
 * #Comments:
 */
@Component
public class MainPage {
    @Value("${base.url}")
    private String baseUrl;
    @Autowired
    private DriverManager driverManager;
    @Autowired
    private DriverWaiter driverWaiter;
    @Autowired
    private Logger log;
    private static final By BUTTON_SIGN_IN = By.cssSelector(".HeaderMenu-link.mr-3.no-underline");


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
            return driverManager.getDriver().findElement(BUTTON_SIGN_IN).isDisplayed();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return false;
    }

    @Step("Wait until leave the Main page")
    public void waitUntilLeave() {
        driverWaiter.waitForElementIsNotDisplayed(BUTTON_SIGN_IN, 1000);
    }
    //</editor-fold>
}
