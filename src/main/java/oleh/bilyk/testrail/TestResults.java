package oleh.bilyk.testrail;

import com.codepine.api.testrail.model.Result;
import io.cucumber.java.Scenario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import oleh.bilyk.helpers.CucumberHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static oleh.bilyk.testrail.TestResults.ResultCode.FAIL;
import static oleh.bilyk.testrail.TestResults.ResultCode.PASS;

@Component
public class TestResults {
    @Autowired
    CucumberHelper cucumberHelper;
    @Getter
    private final Set<TestResult> testResults = new HashSet<>();

    @AllArgsConstructor
    enum ResultCode {
        PASS(1),
        FAIL(5);

        private int code;

        public int getCode() {
            return code;
        }
    }

    public void addResult(Scenario scenario) {
        int id = getTestId(scenario);
        if (isTestAlreadyFailed(id)) {
            return;
        }
        Result result;
        if (scenario.isFailed()) {
            result = new Result().setStatusId(FAIL.getCode())
                    .setComment(cucumberHelper.getFailMessage(scenario));
        } else {
            result = new Result().setStatusId(PASS.getCode());
        }
        TestResult testResult = TestResult
                .builder()
                .testId(id)
                .result(result)
                .build();
        testResults.add(testResult);
    }

    private int getTestId(Scenario scenario) {
        return scenario.getSourceTagNames().stream()
                .filter(s -> s.contains("@TestRailsId#"))
                .mapToInt(s -> Integer.parseInt(s.replace("@TestRailsId#", "")))
                .findFirst().orElseThrow(() -> new RuntimeException("Scenario doesn't have TestRails ID"));
    }

    private boolean isTestAlreadyFailed(int id) {
        return testResults.stream()
                .anyMatch(r -> r.testId == id && r.result.getStatusId() == FAIL.getCode());
    }

    @Data
    @Builder
    public class TestResult {
        private int testId;
        private Result result;

        @Override
        public int hashCode() {
            return getResult().getStatusId() * 1_000_000_000 + testId;
        }
    }
}
