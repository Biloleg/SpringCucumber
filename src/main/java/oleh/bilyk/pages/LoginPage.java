package oleh.bilyk.pages;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import oleh.bilyk.helpers.EnumHelper;
import oleh.bilyk.primitives.Button;
import oleh.bilyk.primitives.TextField;
import oleh.bilyk.primitives.TextView;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.springframework.stereotype.Component;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 21/05/2020
 * #Comments:
 */
@Component
public class LoginPage extends BasePage {
    public static final String PAGE_NAME = "Login Page";

    private static final By FIELD_USERNAME = By.cssSelector("input#login_field");
    private static final By FIELD_PASSWORD = By.cssSelector("input#password");
    private static final By BUTTON_SIGN_IN = By.cssSelector(".btn-primary");
    private static final By LABEL_ERROR_MESSAGE = By.cssSelector(".container-lg.px-2");

    public TextField getFieldUsername() {
        return context.getBean(TextField.class, FIELD_USERNAME, "Login page -> Username field");
    }

    public TextField getFieldPassword() {
        return context.getBean(TextField.class, FIELD_PASSWORD, "Login page -> Password field");
    }

    public Button getButtonSignIn() {
        return context.getBean(Button.class, BUTTON_SIGN_IN, "Login page -> Sign In button");
    }

    public TextView getLabelErrorMessage() {
        return context.getBean(TextView.class, LABEL_ERROR_MESSAGE, "Login page -> Error message");
    }

    //<editor-fold desc="Enums">
    @AllArgsConstructor
    public enum LoginPageField {
        FIELD_USERNAME("Username"),
        FIELD_PASSWORD("Password");

        private final String name;

        @Override
        public String toString() {
            return name;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Public Methods">

    public TextField getFieldByName(String fieldName) {
        LoginPageField field = EnumHelper.valueOf(LoginPageField.class, fieldName);
        switch (field) {
            case FIELD_USERNAME:
                return getFieldUsername();
            case FIELD_PASSWORD:
                return getFieldPassword();
            default:
                throw new NoSuchElementException(String.format("Button with name '%s' does not much to any on page.", fieldName));
        }
    }

    @Override
    @Step("Verify that Login page is loaded")
    public boolean verify() {
        return getFieldUsername().isDisplayed()
                && getButtonSignIn().isDisplayed();
    }


    //</editor-fold>
}
