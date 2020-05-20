package oleh.bilyk.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static oleh.bilyk.webDriver.Driver.log;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 20/05/2020
 * #Comments:
 */
public class AllureEnvironmentWriter {
    private static final String PATH_TO_PROP_FILE = "target/allure-results/environment.properties";

    public static synchronized void write(String propName, String propValue) {
        Properties prop = new Properties();
        try (FileInputStream inputStream = new FileInputStream(PATH_TO_PROP_FILE)) {
            prop.load(inputStream);
        } catch (IOException e) {
            log.warn(e.getMessage());
            new File(PATH_TO_PROP_FILE)
                    .getParentFile()
                    .mkdirs();
        }

        try (FileOutputStream outputStream = new FileOutputStream(PATH_TO_PROP_FILE)) {
            prop.put(propName, propValue);
            prop.store(outputStream, null);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }
}
