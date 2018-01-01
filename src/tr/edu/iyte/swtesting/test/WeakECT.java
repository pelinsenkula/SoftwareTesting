package tr.edu.iyte.swtesting.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.contract.TestCaseGenerator;
import tr.edu.iyte.swtesting.excel.ExcelManager;
import tr.edu.iyte.swtesting.exception.InvalidInputException;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.model.TestCase;

public class WeakECT implements TestCaseGenerator {

	private List<InputVariables> inputVariablesList;
	private String testCasePrefix;

	public WeakECT(List<InputVariables> inputVariablesList, String testCasePrefix) {
		this.inputVariablesList = inputVariablesList;
		this.testCasePrefix = testCasePrefix;
	}

	@Deprecated
	public List<Map<String, String>> generateWeakECTTestCases() {
		List<Integer> sizeList = new ArrayList<>();
		List<Map<String, String>> testCases = new ArrayList<>();

		for (int i = 0; i < inputVariablesList.size(); i++) {
			sizeList.add(inputVariablesList.get(i).getNamesForECs().size());
		}
		int maxSize = Collections.max(sizeList);
		for (int k = 0; k < maxSize; k++) {
			Map<String, String> testCase = new HashMap<>();
			for (int j = 0; j < inputVariablesList.size(); j++) {
				String key = inputVariablesList.get(j).getNamesForECs()
						.get((k % inputVariablesList.get(j).getNamesForECs().size()));
				testCase.put(inputVariablesList.get(j).getId(), inputVariablesList.get(j).getValuesForECs().get(key));
			}
			testCases.add(testCase);
		}
		return testCases;
	}

	@Override
	public List<TestCase> generateTestCases() {
		List<Map<String, String>> _testCases = generateWeakECTTestCases();
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

	public static void main(String[] args) throws FileNotFoundException, IOException, InvalidInputException {

		// ExcelManager excelManager = new ExcelManager(new
		// FileInputStream("resource\\triangleInput.xlsx"));
		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\dateInput.xlsx"));
		List<InputVariables> inputVariablesList = excelManager.readInputVariables();

//		WeakECT wect = new WeakECT(inputVariablesList);
//		System.out.println(wect.generateTestCases());
	}

}
