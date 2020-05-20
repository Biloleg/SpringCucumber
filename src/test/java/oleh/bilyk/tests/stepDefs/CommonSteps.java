package oleh.bilyk.tests.stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import oleh.bilyk.pages.MainPage;
import oleh.bilyk.spring.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ApplicationConfig.class)
public class CommonSteps {
    @Autowired
    private MainPage mainPage;
    @When("I open main page")
    public void iOpenMainPage() {
        mainPage.invoke();
    }
}
