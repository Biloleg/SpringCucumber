package oleh.bilyk.tests.stepDefs;

import io.cucumber.java.en.When;
import oleh.bilyk.pages.InitPage;
import oleh.bilyk.pages.LoginPage;
import oleh.bilyk.spring.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

@ContextConfiguration(classes = ApplicationConfig.class)
public class CommonSteps {
    @Autowired
    private InitPage initPage;
    @Autowired
    private LoginPage loginPage;

    @When("I open main page")
    public void iOpenMainPage() {
        initPage.invoke();
        initPage.navigateToLoginPage();
        loginPage.fillUsernameField("dsfasfasdf");
        loginPage.fillPasswordField("asfasfasdf");
        loginPage.clickOnSignInButton();
        Assert.assertTrue(loginPage.isLoginErrorMessagePresent());
    }
}
