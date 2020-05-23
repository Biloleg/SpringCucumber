package oleh.bilyk.components;

import lombok.Getter;
import oleh.bilyk.primitives.TextView;
import org.openqa.selenium.By;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 23/05/2020
 * #Comments:
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RepoListItemComponent extends BaseComponent {
    @Getter
    private final String itemId;

    private static final String LABEL_LINK_CSS = "li:nth-of-type(%s) > .mt-n1 > .f4.text-normal > .v-align-middle";
    private static final String LABEL_LANGUAGE_XPATH = "//li[contains(@class,'repo-list-item')][%s]//*[@itemprop='programmingLanguage']";
    private static final String LABEL_REPOSITORY_NAME_XPATH = "//li[contains(@class,'repo-list-item')][2]//a/em";

    public RepoListItemComponent(int id) {
        this.itemId = String.valueOf(id);
    }

    public TextView getElementRepoProgramLanguage() {
        return context.getBean(TextView.class,
                By.xpath(String.format(LABEL_LANGUAGE_XPATH, getItemId())), "Repo Item -> Repository Programming Language ");
    }

    public TextView getElementRepoLink() {
        return context.getBean(TextView.class,
                By.cssSelector(String.format(LABEL_LINK_CSS, getItemId())), "Repo Item -> Repository link ");
    }

    public TextView getElementRepoName() {
        return context.getBean(TextView.class,
                By.xpath(String.format(LABEL_REPOSITORY_NAME_XPATH, getItemId())), "Repo Item -> Repository Name ");
    }

    @Override
    public boolean verify() {
        return getElementRepoLink().isDisplayed(1000);
    }
}
