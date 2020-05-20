package oleh.bilyk.pages.charts;

import oleh.bilyk.helpers.StringHelper;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class AjaxLineMultiChart extends AbstractMultiChart implements TestableChart {
    private static final By CHARTS = By.xpath("//*[contains(@class,'highcharts-markers')]");
    private static final By POINTS = By.xpath("*[contains(@class,'highcharts-point')]");
    private static final By TOOLTIP = By.cssSelector(".highcharts-tooltip > text");

    public AjaxLineMultiChart() {
        super(CHARTS, POINTS, TOOLTIP);
    }

    @Override
    protected double getTooltipValueForChart(String chartName) {
        String tooltipText = getDriver().findElement(TOOLTIP).getText();
        List<String> tooltipData = new ArrayList<>(Arrays.asList(tooltipText.split("●")));
        tooltipData.remove(0);
        Map<String, Double> tooltipValues = tooltipData.stream()
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(
                        a -> String.valueOf(a[0]).trim().toLowerCase(),
                        a -> StringHelper.parseStringToDouble(a[1])));
        return tooltipValues.get(chartName.toLowerCase());
    }
}
