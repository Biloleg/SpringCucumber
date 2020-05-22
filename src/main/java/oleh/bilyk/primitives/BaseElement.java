package oleh.bilyk.primitives;

import lombok.Getter;
import oleh.bilyk.webDriver.DriverManager;
import oleh.bilyk.webDriver.DriverWaiter;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseElement extends AbstractElement{
    public BaseElement(By locator, String description) {
        super(locator, description);
    }
}
