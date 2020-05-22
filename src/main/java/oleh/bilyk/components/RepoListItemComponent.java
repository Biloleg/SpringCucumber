package oleh.bilyk.components;

import lombok.Getter;
import oleh.bilyk.primitives.BaseElement;
import org.openqa.selenium.By;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RepoListItemComponent extends BaseComponent {
    @Getter
    private final String itemId;

    private static final String LABEL_LINK_CSS = "li:nth-of-type(%s) > .mt-n1 > .f4.text-normal > .v-align-middle";

    public RepoListItemComponent(int id) {
        this.itemId = String.valueOf(id);
    }

    public BaseElement getElementRepoList() {
        return context.getBean(BaseElement.class,
                By.cssSelector(String.format(LABEL_LINK_CSS, getItemId())), "Repo List -> Container ");
    }

    @Override
    public boolean verify() {
        return getElementRepoList().isDisplayed(1000);
    }
}
