package oleh.bilyk.pages.submenus;

import oleh.bilyk.pages.charts.PieDrilldownChart;
import oleh.bilyk.webDriver.Driver;
import org.openqa.selenium.By;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 22/04/2020
 * #Comments:
 */
public class PieChartSubmenu {
    private static final By PIE_CHART_ITEM = By.cssSelector("[href='\\/demo\\/pie-basic']");
    private static final By DONUT_CHART_ITEM = By.cssSelector("[href='\\/demo\\/pie-donut']");
    private static final By PIE_DRILLDOWN_ITEM = By.cssSelector("[href='\\/demo\\/pie-drilldown']");

    //<editor-fold desc="Public Methods">
    public boolean verify(){
        return Driver.getDriver().findElement(PIE_CHART_ITEM).isDisplayed();
    }

    public PieDrilldownChart openPieDrilldownChart(){
        Driver.getDriver().findElement(PIE_DRILLDOWN_ITEM).click();
        return new PieDrilldownChart();
    }
    //</editor-fold>
}
