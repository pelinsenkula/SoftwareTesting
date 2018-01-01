package tr.edu.iyte.swtesting.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.contract.TestCaseGenerator;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.model.TestCase;
import tr.edu.iyte.swtesting.utils.ChainCounter;

public class WorstCaseBvt implements TestCaseGenerator {

	private List<InputVariables> inputVariablesList;
	private String testCasePrefix;

	public WorstCaseBvt(List<InputVariables> inputVariablesList, String testCasePrefix) {
		this.inputVariablesList = inputVariablesList;
		this.testCasePrefix = testCasePrefix;
	}

	@Deprecated
	public List<Map<String, String>> generateWorstCaseBVTTestCases() {
		List<ChainCounter> counters = new ArrayList<>();
		List<Map<String, String>> testCases = new ArrayList<>();

		ChainCounter c1 = null;
		for (int i = 0; i < inputVariablesList.size(); i++) {
			c1 = new ChainCounter(0, 4, c1);
			counters.add(c1);
		}

		while (!counters.get(0).isChainFinished()) {
			Map<String, String> testCase = new HashMap<>();
			for (int i = 0; i < inputVariablesList.size(); i++) {
				// System.out.print(counters.get(i).value() + " ");
				String val = null;
				switch (counters.get(i).value()) {
				case 0:
					val = inputVariablesList.get(i).getMin();
					break;
				case 1:
					val = inputVariablesList.get(i).getMinPlus();
					break;
				case 2:
					val = inputVariablesList.get(i).getNominal();
					break;
				case 3:
					val = inputVariablesList.get(i).getMaxMinus();
					break;
				case 4:
					val = inputVariablesList.get(i).getMax();
					break;
				}
				testCase.put(inputVariablesList.get(i).getId(), val);
			}
			testCases.add(testCase);
			counters.get(counters.size() - 1).increment();
		}
		return testCases;

	}
	@Override
	public List<TestCase> generateTestCases() {
		List<Map<String, String>> _testCases = generateWorstCaseBVTTestCases();
		List<TestCase> testCases = new ArrayList<>();
		Integer i = 1;
		for (Map<String, String> _testCase : _testCases) {
			TestCase testCase = new TestCase(testCasePrefix + (i++));
			for (String key : _testCase.keySet()) {
				testCase.addInputValue(key, _testCase.get(key));
			}
			testCases.add(testCase);
		}
		return testCases;
	}
//	public static void main(String[] args) {
//		InputVariables iv = new InputVariables("a", "a", "0", "1", "2", "100", "199", "200", "201");
//		InputVariables iv2 = new InputVariables("b", "b", "0", "1", "2", "100", "199", "200", "201");
//		InputVariables iv3 = new InputVariables("c", "c", "0", "1", "2", "100", "199", "200", "201");
//		InputVariables iv4 = new InputVariables("d", "d", "0", "1", "2", "100", "199", "200", "201");
//
//		List<InputVariables> inputVariablesList = new ArrayList<InputVariables>();
//		inputVariablesList.add(iv);
//		inputVariablesList.add(iv2);
//		 inputVariablesList.add(iv3);
//		 inputVariablesList.add(iv4);
//
//		WorstCaseBvt bvt = new WorstCaseBvt(inputVariablesList);
//		System.out.println(Arrays.asList(bvt.generateTestCases()));
//	}

}
