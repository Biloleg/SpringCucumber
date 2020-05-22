package oleh.bilyk.pages;

import io.qameta.allure.Step;
import oleh.bilyk.components.RepoListItemComponent;
import oleh.bilyk.primitives.BaseElement;
import oleh.bilyk.primitives.Button;
import oleh.bilyk.primitives.TextField;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 21/05/2020
 * #Comments:
 */
@Component
public class MainPage extends BasePage {
    public static final String PAGE_NAME = "Main Page";

    private static final By IMAGE_AVATAR = By.cssSelector("summary .avatar-user");
    private static final By FIELD_SEARCH = By.cssSelector("input[name='q']");
    private static final By FIELD_NOT_FOUND = By.cssSelector("Biloleg/SpringCucumber");
    private static final By ELEMENT_REPO_LIST = By.cssSelector(".repo-list");
    private static final By ELEMENT_REPO_LIST_ITEM = By.cssSelector(".repo-list-item");

    public BaseElement getElementRepoList() {
        return context.getBean(BaseElement.class, ELEMENT_REPO_LIST, "Repo List -> Container ");
    }

    public BaseElement getImageAvatar(){
        return context.getBean(BaseElement.class, IMAGE_AVATAR, "Main Page -> Avatar");
    }

    public TextField getFieldSearch(){
        return context.getBean(TextField.class, FIELD_SEARCH, "Main Page -> Search Field");
    }

    public TextField getFieldNotFound (){
        return context.getBean(TextField.class, FIELD_NOT_FOUND, "Main Page -> Not Found Field");
    }

    //<editor-fold desc="Public Methods">
    @Override
    @Step("Verify that Main page is loaded")
    public boolean verify() {
        return getImageAvatar().isDisplayed(1000)
                && getFieldSearch().isDisplayed(1000);
    }

    public RepoListItemComponent getRepoListItemById(int id){
    }
    //</editor-fold>
}
