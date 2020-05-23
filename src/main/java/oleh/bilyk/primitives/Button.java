package oleh.bilyk.primitives;

import org.openqa.selenium.By;
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
public class Button extends AbstractTextElement {
    public Button(By locator, String description) {
        super(locator, description);
    }

    public void click() {
        log.info(String.format("Click on %s", description));
        get().click();
    }
}
