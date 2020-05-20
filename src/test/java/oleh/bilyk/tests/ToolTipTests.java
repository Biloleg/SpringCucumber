package oleh.bilyk.tests;

import io.qameta.allure.Feature;
import oleh.bilyk.pages.MainPage;
import org.testng.annotations.Test;


/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 20/04/2020
 * #Comments:
 */
@Feature("Tool Tip Tests")
public class ToolTipTests extends BaseTest {
    @Test
    public void lineChartTest() {
        new MainPage()
                .openLineCharts()
                .openAjaxLineChart()
                .checkTooltipsValue();
    }

    @Test
    public void columnChartTest() {
        new MainPage()
                .openColumnBarChartSubmenu()
                .openBasicColumnChart()
                .checkTooltipsValue();
    }

    @Test
    public void pieChartTest() {
        new MainPage()
                .openPieChartSubmenu()
                .openPieDrilldownChart()
                .checkTooltipsValue();
    }
}
