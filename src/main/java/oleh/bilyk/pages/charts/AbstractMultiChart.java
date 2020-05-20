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
 * #Creation Date: 23/04/2020
 * #Comments:
 */
public abstract class AbstractMultiChart {
    protected final By CHARTS;
    protected final By POINTS;
    protected final By TOOLTIP;
    protected DriverWaiter waiter = new DriverWaiter();
    protected Actions action = new Actions(getDriver());

    protected AbstractMultiChart(By chartsBy, By pointsBy, By tooltipBy) {
        CHARTS = chartsBy;
        POINTS = pointsBy;
        TOOLTIP = tooltipBy;
        waiter.waitForElementDisplayed(CHARTS);

    }

    //<editor-fold desc="Public Methods">
    @Step("Check tooltips value for all charts")
    public void checkTooltipsValue() {
        for (WebElement chart : getCharts()) {
            action.moveToElement(chart).build().perform();
            String chartLabel = getChartLabel(chart);
            checkTooltipsValueForChart(chart, chartLabel);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Protected Methods">
    @Step("Check tooltips value for {1} chart")
    private void checkTooltipsValueForChart(WebElement chart, String chartLabel) {
        for (WebElement point : getPoints(chart)) {
            action.moveToElement(point).build().perform();
            waiter.waitForElementDisplayed(TOOLTIP, 3000);
            compareTooltipAndPointValues(
                    chartLabel,
                    getPointValue(point),
                    getTooltipValueForChart(chartLabel));
        }
    }

    @Step("Compare Tooltip '{2}' And Point '{1}' Values")
    private void compareTooltipAndPointValues(String chartLabel, double pointValue, double tooltipValue) {
        Assert.assertEquals(pointValue, tooltipValue,
                String.format("Tooltip value '%s' is not equals to point value '%s' for chart '%s'",
                        tooltipValue, pointValue, chartLabel));
    }

    protected abstract double getTooltipValueForChart(String chartName);

    protected double getPointValue(WebElement point) {
        String pointData = point.getAttribute("aria-label");
        return StringHelper.parseStringToDouble(
                StringHelper.getMatchedGroup(
                        ".* (\\d+\\.?\\d+).*", pointData, 1));
    }

    protected List<WebElement> getCharts() {
        return getDriver().findElements(CHARTS);
    }

    protected List<WebElement> getPoints(WebElement chart) {
        return chart.findElements(POINTS);
    }

    protected String getChartLabel(WebElement chart) {
        return chart.getAttribute("aria-label").split(",")[0];
    }
    //</editor-fold>
}
