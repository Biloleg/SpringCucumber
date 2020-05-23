package oleh.bilyk.primitives;

import org.openqa.selenium.By;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 23/05/2020
 * #Comments:
 */
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
