package oleh.bilyk.spring;

import oleh.bilyk.webDriver.DriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.plugin.BeansApplet;


/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 20/05/2020
 * #Comments:
 */
@Configuration
@ComponentScan(basePackages = "oleh.bilyk")
@PropertySource("classpath:project.properties")
public class ApplicationConfig {
    @Autowired
    DriverManager driverManager;

    @Bean
    public Logger log(){
        return Logger.getLogger("mylog");
    }
}
