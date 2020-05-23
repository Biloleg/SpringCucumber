package oleh.bilyk.components;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 23/05/2020
 * #Comments:
 */
public abstract class BaseComponent {
    @Autowired
    protected Logger log;
    @Autowired
    protected ApplicationContext context;

    public abstract boolean verify();
}
