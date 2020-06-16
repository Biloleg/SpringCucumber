package oleh.bilyk.spring;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.*;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 20/05/2020
 * #Comments:
 */
@Configuration
@ComponentScan(basePackages = "oleh.bilyk")
@PropertySources({
        @PropertySource("classpath:project.properties"),
        @PropertySource("classpath:test.rails.properties")
})
@PropertySource({"classpath:project.properties", "classpath:test.rails.properties"})
public class ApplicationConfig {
    @Bean
    public Logger log() {
        return Logger.getLogger("mylog");
    }
}
