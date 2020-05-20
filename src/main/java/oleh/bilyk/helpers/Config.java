package oleh.bilyk.helpers;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import oleh.bilyk.webDriver.Driver;

import java.io.FileInputStream;
import java.util.Optional;
import java.util.Properties;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 20/05/2020
 * #Comments:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Config {
    private static final String PROPERTIES_FILE = "src/main/resources/project.properties";
    private static final Properties properties = new Properties();
    private static final Config instance = init();

    //<editor-fold desc="Public Methods">
    public static Config getInstance() {
        return instance;
    }

    public final String BASE_HOST() {
        return getValuePipeline("host");
    }

    public final Driver.Browser BROWSER() {
        return Driver.Browser.valueOf(getValuePipeline("browser").toUpperCase());
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private static Config init() {
        Config config = new Config();
        config.initProperties(PROPERTIES_FILE);
        return config;
    }

    @SneakyThrows
    private void initProperties(String propertiesFile) {
        properties.load(new FileInputStream(propertiesFile));
    }

    private String getSystemProperty(String key) {
        return System.getProperty(key);
    }

    private String getPropertyValue(String key) {
        return properties.getProperty(key);
    }

    private String getValuePipeline(String propName) {
        return Optional.of(getValuePipeline(propName, null))
                .orElseThrow(NullPointerException::new);
    }

    private String getValuePipeline(String propName, String defaultValue) {
        String systemProperty = getSystemProperty(propName);
        String localProperty = getPropertyValue(propName);
        if (systemProperty != null) {
            return systemProperty;
        } else if (localProperty != null) {
            return localProperty;
        }
        return defaultValue;
    }
    //</editor-fold>
}

