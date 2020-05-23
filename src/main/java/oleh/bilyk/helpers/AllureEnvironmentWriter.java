package oleh.bilyk.helpers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 20/05/2020
 * #Comments:
 */
@Component
public class AllureEnvironmentWriter {
    @Autowired
    private Logger log;
    @Value("${allure.prop.file}")
    private String pathToPropFile;

    public void write(String propName, String propValue) {
        Properties prop = new Properties();
        try (FileInputStream inputStream = new FileInputStream(pathToPropFile)) {
            prop.load(inputStream);
        } catch (IOException e) {
            log.warn(e.getMessage());
            new File(pathToPropFile)
                    .getParentFile()
                    .mkdirs();
        }
        try (FileOutputStream outputStream = new FileOutputStream(pathToPropFile)) {
            prop.put(propName, propValue);
            prop.store(outputStream, null);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }
}
