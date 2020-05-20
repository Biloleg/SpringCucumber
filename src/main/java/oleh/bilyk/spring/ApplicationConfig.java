package oleh.bilyk.spring;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
    @Bean
    public Logger log(){
        return Logger.getLogger("mylog");
    }

}
