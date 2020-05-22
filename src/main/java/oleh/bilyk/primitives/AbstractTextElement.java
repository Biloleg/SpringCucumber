package oleh.bilyk.primitives;

import lombok.Getter;
import oleh.bilyk.webDriver.DriverManager;
import oleh.bilyk.webDriver.DriverWaiter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


public abstract class AbstractTextElement extends AbstractElement {
    public AbstractTextElement(By locator, String description) {
        super(locator, description);
    }

    public String getText() {
        String text = get().getText();
        log.info(String.format("Get text '%s' from %s", text, description));
        return text;
    }
}
