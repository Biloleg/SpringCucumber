package oleh.bilyk.pages.charts;

import oleh.bilyk.helpers.StringHelper;
import org.openqa.selenium.By;

import java.util.Map;
import java.util.stream.Collectors;

import static oleh.bilyk.webDriver.Driver.getDriver;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 22/04/2020
 * #Comments:
 */
public class BasicColumnMultiChart extends AbstractMultiChart implements TestableChart {
    private static final By CHARTS = By.cssSelector(".highcharts-series-group > .highcharts-series");
    private static final By POINTS = By.xpath("*[contains(@class,'highcharts-point') and contains(@class,'highcharts-color-')]");
    private static final By TOOLTIP = By.cssSelector(".highcharts-container tbody tr");

    public BasicColumnMultiChart() {
        super(CHARTS, POINTS, TOOLTIP);
    }

    @Override
    protected double getTooltipValueForChart(String chartName) {
        Map<String, Double> tooltipValues =
                getDriver().findElements(By.cssSelector(".highcharts-container tbody tr"))
                        .stream()
                        .map(e -> e.getAttribute("innerText").split(":\\t"))
                        .collect(Collectors.toMap(
                                a -> String.valueOf(a[0]).trim().toLowerCase(),
                                a -> StringHelper.parseStringToDouble(
                                        StringHelper.getMatchedGroup("(\\d+\\.\\d+).*", a[1], 1)
                                )));
        return tooltipValues.get(chartName.toLowerCase());
    }
}
