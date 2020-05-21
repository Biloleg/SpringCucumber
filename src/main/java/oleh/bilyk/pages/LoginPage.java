package oleh.bilyk.pages;

import io.qameta.allure.Step;
import oleh.bilyk.webDriver.DriverManager;
import oleh.bilyk.webDriver.DriverWaiter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 21/05/2020
 * #Comments:
 */
@Component
public class LoginPage {
    @Autowired
    private DriverManager driverManager;
    @Autowired
    private DriverWaiter driverWaiter;
    @Autowired
    private Logger log;
    private static final By FIELD_USERNAME = By.cssSelector("input#login_field");
    private static final By FIELD_PASSWORD = By.cssSelector("input#password");
    private static final By BUTTON_SIGN_IN = By.cssSelector(".btn-primary");
    private static final By LABEL_ERROR_MESSAGE = By.cssSelector(".container-lg.px-2");

    //<editor-fold desc="Public Methods">
    @Step("Type '{0}' to username field")
    public void fillUsernameField(String username) {
        driverManager.getDriver().findElement(FIELD_USERNAME).sendKeys(username);
    }

    @Step("Type '{0}' to password field")
    public void fillPasswordField(String password) {
        driverManager.getDriver().findElement(FIELD_PASSWORD).sendKeys(password);
    }

    @Step
    public void clickOnSignInButton() {
        driverManager.getDriver().findElement(BUTTON_SIGN_IN).click();
    }

    @Step
    public boolean isLoginErrorMessagePresent() {
        return driverWaiter.isElementDisplayed(LABEL_ERROR_MESSAGE, 1000);
    }

    @Step
    public String getLoginErrorText() {
        driverWaiter.waitForElementDisplayed(LABEL_ERROR_MESSAGE, 1000);
        return  driverManager.getDriver().findElement(LABEL_ERROR_MESSAGE).getText();
    }

    @Step("Verify that Login page is loaded")
    public boolean verify() {
        try {
            return driverManager.getDriver().findElement(FIELD_USERNAME).isDisplayed()
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

    @Step("Wait until leave the Login page")
    public void waitUntilLeave() {
        driverWaiter.waitForElementIsNotDisplayed(BUTTON_SIGN_IN, 1000);
    }
    //</editor-fold>
}
