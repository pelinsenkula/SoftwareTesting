package tr.edu.iyte.swtesting.contract;

import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.model.TestCase;


public interface TestCaseGenerator {

	public List<TestCase> generateTestCases();
}
