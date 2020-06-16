package oleh.bilyk.testrail;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import io.cucumber.java.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 16/06/2020
 * #Comments:
 */
@Component
public class TestRailIntegration {
    @Autowired
    private TestResults testResults;
    private TestRail testRail;
    private Run run;

    @Autowired
    public TestRailIntegration(@Value("${test.rails.url}") final String testrailsEndpoint,
                               @Value("${test.rails.username}") final String testrailsUsername,
                               @Value("${test.rails.password}") final String testrailsPassword,
                               @Value("${test.rails.project.id}") final int projectId,
                               @Value("${test.rails.suite.id}") final int suiteId) {
        testRail = TestRail.builder(testrailsEndpoint, testrailsUsername, testrailsPassword).build();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date date = new Date();
        String dateString = format.format(date);
        String runName = "Automation " + dateString;
        run = testRail.runs().add(projectId, new Run().setSuiteId(suiteId).setName(runName)).execute();
        Runtime.getRuntime().addShutdownHook(new Thread(this::pushTestRailsResults));
    }

    //<editor-fold desc="Public Methods">
    public void write(Scenario scenario) {
        testResults.addResult(scenario);
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private void pushTestRailsResults() {
        List<ResultField> customResultFields = testRail.resultFields().list().execute();
        Iterator<TestResult> testResultsIterator = testResults.getTestResults().iterator();
        while (testResultsIterator.hasNext()) {
            TestResult testResult = testResultsIterator.next();
            int testCaseId = testResult.getTestId();
            Result result = testResult.getResult();
            testRail.results().addForCase(run.getId(), testCaseId, result, customResultFields).execute();
        }
        testRail.runs().close(run.getId()).execute();
    }
    //</editor-fold>
}
