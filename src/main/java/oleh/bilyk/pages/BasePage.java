package oleh.bilyk.pages;

import oleh.bilyk.webDriver.DriverManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class BasePage {
    @Autowired
    protected Logger log;
    @Autowired
    protected DriverManager driverManager;
    @Autowired
    protected ApplicationContext context;

    public abstract boolean verify();
}
