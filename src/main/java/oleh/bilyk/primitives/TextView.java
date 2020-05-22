package oleh.bilyk.primitives;

import org.openqa.selenium.By;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TextView extends AbstractTextElement {
    public TextView(By locator, String description) {
        super(locator, description);
    }
}
