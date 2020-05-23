package oleh.bilyk.primitives;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 23/05/2020
 * #Comments:
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TextField extends AbstractTextElement {
    public TextField(By locator, String description) {
        super(locator, description);
    }

    public void setText(String text) {
        log.info(String.format("Set text '%s' to %s", text, description));
        get().sendKeys(text);
    }

    public void setText(Keys text) {
        log.info(String.format("Send key '%s' to %s", text.name(), description));
        get().sendKeys(text);
    }
}
