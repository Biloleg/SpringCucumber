package oleh.bilyk.pages.submenus;

import io.qameta.allure.Step;
import oleh.bilyk.pages.MainPage;
import oleh.bilyk.pages.charts.AjaxLineMultiChart;
import oleh.bilyk.webDriver.Driver;
import oleh.bilyk.webDriver.DriverWaiter;
import org.openqa.selenium.By;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 22/04/2020
 * #Comments:
 */
public class LineChartSubmenu extends MainPage {
    private static final By BASIC_LINE_ITEM = By.cssSelector("[href='\\/demo\\/line-basic']");
    private static final By AJAX_LINE_ITEM = By.cssSelector(".nav-sidebar [href='\\/demo\\/line-ajax']");
    private static final By LABELS_LINE_ITEM = By.cssSelector("[href='\\/demo\\/line-labels']");

    public LineChartSubmenu() {
        new DriverWaiter().waitForElementDisplayed(AJAX_LINE_ITEM);
    }

    //<editor-fold desc="Public Methods">
    public boolean verify() {
        return Driver.getDriver().findElement(BASIC_LINE_ITEM).isDisplayed();
    }

    @Step
    public AjaxLineMultiChart openAjaxLineChart() {
        Driver.getDriver().findElement(AJAX_LINE_ITEM).click();
        return new AjaxLineMultiChart();
    }
    //</editor-fold>
}
