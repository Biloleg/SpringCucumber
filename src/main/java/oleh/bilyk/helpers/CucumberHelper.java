package oleh.bilyk.helpers;

import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 15/06/2020
 * #Comments:
 */
@Component
public class CucumberHelper {
    //<editor-fold desc="Public Methods">
    public String getFailMessage(Scenario scenario) {
        if (!scenario.isFailed()) {
            return "";
        }
        return getStepsResultFromScenario(scenario).stream()
                .filter(step -> step.getStatus() == Status.FAILED)
                .map(step -> step.getError().getLocalizedMessage())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("FILED step not found"));
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private static List<Result> getStepsResultFromScenario(Scenario scenario) {
        try {
            Field scenarioField = scenario.getClass().getDeclaredField("delegate");
            scenarioField.setAccessible(true);
            Object scenarioObj = scenarioField.get(scenario);
            Field stepResults = scenarioObj.getClass().getDeclaredField("stepResults");
            stepResults.setAccessible(true);
            return (List<Result>) stepResults.get(scenarioObj);
        } catch (Exception e) {
            throw new RuntimeException("Impossible to get Pickle from Scenario");
        }
    }
    //</editor-fold>
}
