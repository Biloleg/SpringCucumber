package oleh.bilyk.pages;

import io.qameta.allure.Step;
import oleh.bilyk.primitives.BaseElement;
import oleh.bilyk.primitives.Button;
import org.openqa.selenium.By;
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
public class InitPage extends BasePage {
    public static final String PAGE_NAME = "Init Page";

    @Value("${base.url}")
    private String baseUrl;

    private static final By BUTTON_SIGN_IN = By.cssSelector(".HeaderMenu-link.mr-3.no-underline");
    private static final By IMAGE_LOGO = By.cssSelector(".octicon.octicon-mark-github.text-white");

    public BaseElement getImageLogo() {
        return context.getBean(BaseElement.class, IMAGE_LOGO, "Init Page -> Logo");
    }

    public Button getButtonSignIn() {
        return context.getBean(Button.class, BUTTON_SIGN_IN, "Init Page -> Sign In button");
    }

    //<editor-fold desc="Public Methods">
    public void invoke() {
        if (!verify()) {
            driverManager.getDriver().navigate().to(baseUrl);
            getImageLogo().waitToDisplayed();
        }
    }

    @Override
    @Step("Verify that Main page is loaded")
    public boolean verify() {
        return getImageLogo().isDisplayed(1000)
                && getButtonSignIn().isDisplayed(1000);
    }

    @Step
    public void navigateToLoginPage() {
        getButtonSignIn().click();
        getButtonSignIn().waitToNotDisplayed();
    }

    @Step("Wait until leave the Main page")
    public void waitUntilLeave() {
        getButtonSignIn().waitToNotDisplayed();
    }
    //</editor-fold>
}
