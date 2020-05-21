package oleh.bilyk.tests.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import oleh.bilyk.pages.InitPage;
import oleh.bilyk.pages.LoginPage;
import oleh.bilyk.spring.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

@ContextConfiguration(classes = ApplicationConfig.class)
public class InitPageSteps {
    @Autowired
    private InitPage initPage;

    @When("I open browser and navigate by configured URL")
    public void iOpenBrowserAndNavigateByURL() {
        initPage.invoke();
    }

    @When("I navigate to Login page")
    public void iNavigateToLoginPage() {
        initPage.navigateToLoginPage();
    }
}
