package oleh.bilyk.components;

import oleh.bilyk.webDriver.DriverManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class BaseComponent {
    @Autowired
    protected Logger log;
    @Autowired
    protected ApplicationContext context;

    public abstract boolean verify();
}
