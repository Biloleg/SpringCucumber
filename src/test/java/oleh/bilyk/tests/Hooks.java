package oleh.bilyk.tests;

import io.cucumber.java.After;
import oleh.bilyk.webDriver.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks {
    @Autowired
    DriverManager driverManager;

    @After
    public void killDriver(){
        driverManager.kill();
    }
}
