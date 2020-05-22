package oleh.bilyk.pages;

import lombok.AllArgsConstructor;
import oleh.bilyk.helpers.EnumHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PageFactory {
    @Autowired
    ApplicationContext applicationContext;

    //<editor-fold desc="Enums">
    @AllArgsConstructor
    public enum Page {
        INIT_PAGE(InitPage.PAGE_NAME),
        LOGIN_PAGE(LoginPage.PAGE_NAME),
        MAIN_PAGE(MainPage.PAGE_NAME);

        private final String name;

        @Override
        public String toString() {
            return name;
        }
    }

    public BasePage getPageByName(String pageName) {
        Page page = EnumHelper.valueOf(Page.class, pageName);
        switch (page) {
            case INIT_PAGE:
                return (BasePage) applicationContext.getBean("initPage");
            case LOGIN_PAGE:
                return (BasePage) applicationContext.getBean("loginPage");
            case MAIN_PAGE:
                return (BasePage) applicationContext.getBean("mainPage");
            default:
                throw new RuntimeException(String.format("Page with name '%s' does not much to any.", pageName));
        }
    }
    //</editor-fold>
}
