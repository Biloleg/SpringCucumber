package oleh.bilyk.testrail;

import com.codepine.api.testrail.model.Result;
import lombok.Builder;
import lombok.Data;

/**
 * #Summary:
 * #Author: Oleh_Bilyk
 * #Author’s Email: oleh_bilyk@epam.com
 * #Creation Date: 16/06/2020
 * #Comments:
 */
@Data
@Builder
public class TestResult {
    private int testId;
    private Result result;

    @Override
    public int hashCode() {
        return getResult().getStatusId() * 1_000_000_000 + testId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TestResult other = (TestResult) obj;
        if (testId != other.getTestId())
            return false;
        if (!result.equals(other.getResult()))
            return false;
        return true;
    }
}
