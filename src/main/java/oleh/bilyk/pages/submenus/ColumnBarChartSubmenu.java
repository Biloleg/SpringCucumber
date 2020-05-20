package oleh.bilyk.pages.submenus;

import io.qameta.allure.Step;
import oleh.bilyk.pages.MainPage;
import oleh.bilyk.pages.charts.BasicColumnMultiChart;
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
public class ColumnBarChartSubmenu extends MainPage {
    private static final By BASIC_BAR_ITEM = By.cssSelector("[href='\\/demo\\/bar-basic']");
    private static final By BASIC_COLUMN_ITEM = By.cssSelector("[href='\\/demo\\/column-basic']");
    private static final By LABELS_LINE_ITEM = By.cssSelector("[href='\\/demo\\/line-labels']");

    public ColumnBarChartSubmenu() {
        new DriverWaiter().sleep(1000);
    }

    //<editor-fold desc="Public Methods">
    public boolean verify() {
        return Driver.getDriver().findElement(BASIC_BAR_ITEM).isDisplayed();
    }

    @Step
    public BasicColumnMultiChart openBasicColumnChart() {
        Driver.getDriver().findElement(BASIC_COLUMN_ITEM).click();
        return new BasicColumnMultiChart();
    }
    //</editor-fold>
}
