package oleh.bilyk.primitives;

import lombok.Getter;
import oleh.bilyk.webDriver.DriverManager;
import oleh.bilyk.webDriver.DriverWaiter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 23/05/2020
 * #Comments:
 */
public abstract class AbstractElement {
    private static ThreadLocal<WebElement> lastElem = new ThreadLocal<>();
    private static ThreadLocal<String> lastBorder = new ThreadLocal<>();
    @Autowired
    protected DriverManager driverManager;
    @Autowired
    protected DriverWaiter driverWaiter;
    @Autowired
    protected Logger log;
    @Getter
    protected By locator;
    @Getter
    protected String description;

    public AbstractElement(By locator, String description) {
        this.locator = locator;
        this.description = description;
    }

    public int count() {
        return getAll().size();
    }

    protected WebElement get() {
        WebElement forReturn = driverManager.getDriver().findElement(locator);
        highlightElement(forReturn);
        return forReturn;
    }

    public void waitToDisplayed(long... msToWait) {
        driverWaiter.waitForElementDisplayed(locator, msToWait);
    }

    public boolean isDisplayed(long... msToWait) {
        try {
            return driverWaiter.isElementDisplayed(locator, msToWait);
        } catch (Exception e) {
            return false;
        }
    }

    protected WebElement get(int index) {
        return getAll().get(index);
    }

    protected List<WebElement> getAll() {
        return driverManager.getDriver().findElements(locator);
    }

    private JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) driverManager.getDriver();
    }

    private void highlightElement(WebElement elem) {
        unhighlightLast();
        lastElem.set(elem);
        lastBorder.set((String) (getJsExecutor().executeScript("arguments[0].setAttribute('style', arguments[1]);",
                elem, "color: red; border: 2px solid red;")));
    }

    private void unhighlightLast() {
        if (lastElem.get() != null) {
            try {
                getJsExecutor().executeScript("arguments[0].setAttribute('style', arguments[1]);", lastElem.get(), lastBorder.get());
            } catch (StaleElementReferenceException ignored) {
            } finally {
                lastElem.remove();
            }
        }
    }

    public void waitToNotDisplayed(long... msToWait) {
        driverWaiter.waitForElementIsNotDisplayed(locator, msToWait);
    }
}
