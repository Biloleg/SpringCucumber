package oleh.bilyk.pages;

import io.qameta.allure.Step;
import oleh.bilyk.helpers.Config;
import oleh.bilyk.pages.submenus.ColumnBarChartSubmenu;
import oleh.bilyk.pages.submenus.LineChartSubmenu;
import oleh.bilyk.pages.submenus.PieChartSubmenu;
import oleh.bilyk.webDriver.Driver;
import oleh.bilyk.webDriver.DriverWaiter;
import org.openqa.selenium.By;

import static oleh.bilyk.webDriver.Driver.log;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 22/04/2020
 * #Comments:
 */
public class MainPage {
    private static final By LINE_CHARTS_ITEM = By.xpath("//span[.='Line charts']");
    private static final By AREA_CHARTS_ITEM = By.xpath("//span[.='Area charts']");
    private static final By COLUMN_BAR_CHARTS_ITEM = By.xpath("//span[.='Column and bar charts']");
    private static final By PIE_CHARTS_ITEM = By.xpath("//span[.='Pie charts']");
    private static final By BASIC_LINE_ITEM = By.cssSelector("[href='\\/demo\\/line-basic']");

    public MainPage() {
        if (!verify()) {
            Driver.getDriver().navigate().to(Config.getInstance().BASE_HOST());
            new DriverWaiter().waitForElementDisplayed(LINE_CHARTS_ITEM);
        }
    }

    //<editor-fold desc="Public Methods">
    @Step("Verify that Main page is loaded")
    public boolean verify() {
        try {
            return Driver.getDriver().findElement(LINE_CHARTS_ITEM).isDisplayed()
                    && Driver.getDriver().findElement(AREA_CHARTS_ITEM).isDisplayed();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return false;
    }

    @Step("Wait until leave the Main page")
    public void waitUntilLeave() {
        new DriverWaiter().waitForElementIsNotDisplayed(BASIC_LINE_ITEM, 1000);
    }

    @Step
    public LineChartSubmenu openLineCharts() {
        if (!Driver.getDriver().findElement(LINE_CHARTS_ITEM).isDisplayed()) {
            throw new IllegalStateException("Control is not displayed");
        }
        Driver.getDriver().findElement(LINE_CHARTS_ITEM).click();
        return new LineChartSubmenu();
    }

    @Step
    public ColumnBarChartSubmenu openColumnBarChartSubmenu() {
        if (!Driver.getDriver().findElement(COLUMN_BAR_CHARTS_ITEM).isDisplayed()) {
            throw new IllegalStateException("Control is not displayed");
        }
        Driver.getDriver().findElement(COLUMN_BAR_CHARTS_ITEM).click();
        waitUntilLeave();
        return new ColumnBarChartSubmenu();
    }

    @Step
    public PieChartSubmenu openPieChartSubmenu() {
        if (!Driver.getDriver().findElement(PIE_CHARTS_ITEM).isDisplayed()) {
            throw new IllegalStateException("Control is not displayed");
        }
        Driver.getDriver().findElement(PIE_CHARTS_ITEM).click();
        waitUntilLeave();
        return new PieChartSubmenu();
    }
    //</editor-fold>
}
