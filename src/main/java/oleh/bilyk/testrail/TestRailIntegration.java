package oleh.bilyk.testrail;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.*;
import io.cucumber.java.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestRailIntegration {
    @Autowired
    private TestResults testResults;
    static int projectId = 6;
    static int suiteId = 10;
    static int sectionId = 9;
    // create a TestRail instance
    static TestRail testRail = TestRail.builder("https://bilyk.testrail.io/", "oleh.bilyk@penske.com", "Mentoring1").applicationName("playground").build();
    // add a new test suite
    static Suite suite = testRail.suites().get(suiteId).execute();
    // add a new section
    static Section section = testRail.sections().get(sectionId).execute();
    // add a new test run
    static Run run = testRail.runs().add(projectId, new Run().setSuiteId(suiteId).setName("Weekly Regression")).execute();
    static List<ResultField> customResultFields = testRail.resultFields().list().execute();

    public static void main(String[] s) {

// add a test case
        List<CaseField> customCaseFields = testRail.caseFields().list().execute();
        Case testCase = testRail.cases().list(projectId, customCaseFields).execute().get(5);
        //Case testCase = testRail.cases().add(sectionId, new Case().setTitle("Several results"), customCaseFields).execute();

// add test result
        List<ResultField> customResultFields = testRail.resultFields().list().execute();
        testRail.results().addForCase(run.getId(), testCase.getId(), new Result().setStatusId(5), customResultFields).execute();
        List<Result> x = testRail.results().listForRun(run.getId(), customResultFields).execute();
        testRail.results().addForCase(run.getId(), testCase.getId(), new Result().setStatusId(1), customResultFields).execute();

// close the run
        testRail.runs().close(run.getId()).execute();

    }

    public void write(Scenario scenario) {
        scenario.getStatus();
        testResults.addResult(scenario);

    }
    {
        List<CaseField> customCaseFields = testRail.caseFields().list().execute();
        Case testCase = testRail.cases().get(61, customCaseFields).execute();
        //Case testCase = testRail.cases().add(sectionId, new Case().setTitle("Several results"), customCaseFields).execute();

        testRail.results().addForCase(run.getId(), testCase.getId(), testResult, customResultFields).execute();


    }
}
