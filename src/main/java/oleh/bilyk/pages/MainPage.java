package oleh.bilyk.pages;

import io.qameta.allure.Step;
import oleh.bilyk.components.RepoListItemComponent;
import oleh.bilyk.primitives.BaseElement;
import oleh.bilyk.primitives.TextField;
import oleh.bilyk.primitives.TextView;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

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
    private static final By LABEL_NOT_FOUND = By.cssSelector(".blankslate h3");
    private static final By ELEMENT_REPO_LIST = By.cssSelector(".repo-list");
    private static final By ELEMENT_REPO_LIST_ITEM = By.cssSelector(".repo-list-item");

    public BaseElement getElementRepoList() {
        return context.getBean(BaseElement.class, ELEMENT_REPO_LIST, "Repo List -> Container");
    }

    private BaseElement getElementRepoItem() {
        return context.getBean(BaseElement.class, ELEMENT_REPO_LIST_ITEM, "Repo List -> Item");
    }

    public BaseElement getImageAvatar() {
        return context.getBean(BaseElement.class, IMAGE_AVATAR, "Main Page -> Avatar");
    }

    public TextField getFieldSearch() {
        return context.getBean(TextField.class, FIELD_SEARCH, "Main Page -> Search Field");
    }

    public TextView getLabelNotFound() {
        return context.getBean(TextView.class, LABEL_NOT_FOUND, "Main Page -> Not Found Field");
    }

    //<editor-fold desc="Public Methods">
    @Override
    @Step("Verify that Main page is loaded")
    public boolean verify() {
        return getImageAvatar().isDisplayed(1000)
                && getFieldSearch().isDisplayed(1000);
    }

    public boolean isRepoListItemWithLinkTextExist(String linkText) {
        try {
            getRepoListItemByLinkText(linkText);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public RepoListItemComponent getRepoListItemByLinkText(String linkText) {
        int listSize = getElementRepoItem().count();
        if (listSize == 0) {
            throw new IndexOutOfBoundsException("List with elements is empty.");
        }
        return IntStream.range(1, listSize)
                .mapToObj(this::getRepoListItemById)
                .filter(item -> item.getElementRepoLink().getText().equalsIgnoreCase(linkText))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Repo Item with link '%s' wasn't found", linkText)));
    }

    public RepoListItemComponent getRepoListItemById(int id) {
        int listSize = getElementRepoItem().count();
        if (id > listSize) {
            throw new IndexOutOfBoundsException(String.format("Trying to get element '%s', from list with '%s' elements", id, listSize));
        }
        return context.getBean(RepoListItemComponent.class, id);
    }
    //</editor-fold>
}
