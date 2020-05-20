package oleh.bilyk.pages.charts;

import io.qameta.allure.Step;
import oleh.bilyk.helpers.StringHelper;
import oleh.bilyk.webDriver.DriverWaiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

import static oleh.bilyk.webDriver.Driver.getDriver;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 24/04/2020
 * #Comments:
 */
public class PieDrilldownChart implements TestableChart {
    private static final By POINTS = By.cssSelector(".highcharts-point");
    private static final By TOOLTIP = By.cssSelector(".highcharts-tooltip > text > tspan");
    private static final By LEGEND_LABELS = By.cssSelector(".highcharts-data-label");
    DriverWaiter waiter = new DriverWaiter();

    public PieDrilldownChart() {
        waiter.waitForElementDisplayed(LEGEND_LABELS);
        waiter.sleep(1500);
    }

    //<editor-fold desc="Public Methods">
    @Step("Compare tooltips value to each chart's point")
    public void checkTooltipsValue() {
        Actions action = new Actions(getDriver());
        for (WebElement point : getPoints()) {
            action.moveToElement(point).build().perform();
            waiter.waitForElementDisplayed(TOOLTIP, 3000);
            compareTooltipAndPointValues(
                    getPointLabel(point),
                    getPointValue(point),
                    getTooltipValue());
        }
    }

    //</editor-fold>

    //<editor-fold desc="Private Methods">
    @Step("Compare Tooltip '{2}' And Point '{1}' Values for point '{0}'")
    private void compareTooltipAndPointValues(String pointLabel, double pointValue, double tooltipValue) {
        Assert.assertEquals(pointValue, tooltipValue,
                String.format("Tooltip value '%s' is not equals to point value '%s' for point '%s'",
                        tooltipValue, pointValue, pointLabel));
    }

    private double getTooltipValue() {
        return StringHelper.parseStringToDouble(
                getDriver().findElements(TOOLTIP)
                        .get(3)
                        .getAttribute("innerHTML")
                        .replace("%", ""));
    }

    private double getPointValue(WebElement point) {
        String pointData = point.getAttribute("aria-label");
        return StringHelper.parseStringToDouble(
                StringHelper.getMatchedGroup(
                        ".* (\\d+\\.?\\d+)%.*", pointData, 1));
    }

    private String getPointLabel(WebElement point) {
        String pointData = point.getAttribute("aria-label");
        return StringHelper.getMatchedGroup(
                "\\d+\\. (.*),.*", pointData, 1);
    }

    private List<WebElement> getPoints() {
        return getDriver().findElements(POINTS);
    }
    //</editor-fold>
}
