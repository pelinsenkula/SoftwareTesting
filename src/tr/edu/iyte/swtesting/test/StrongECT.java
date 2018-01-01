package tr.edu.iyte.swtesting.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.contract.TestCaseGenerator;
import tr.edu.iyte.swtesting.excel.ExcelManager;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.model.TestCase;
import tr.edu.iyte.swtesting.utils.ChainCounter;

public class StrongECT implements TestCaseGenerator {

	private List<InputVariables> inputVariablesList;
	private String testCasePrefix;

	public StrongECT(List<InputVariables> inputVariablesList,String testCasePrefix) {
		this.inputVariablesList = inputVariablesList;
		this.testCasePrefix = testCasePrefix;
	}
	
	@Deprecated
	public List<Map<String, String>> generateStrongECTTestCases() {
		List<Map<String, String>> testCases = new ArrayList<>();
		List<ChainCounter> counters = new ArrayList<>();
		ChainCounter c1 = null;
		for (InputVariables iv: inputVariablesList) {
			c1 = new ChainCounter(0,iv.getNamesForECs().size()-1,c1);
			counters.add(c1);
		}
		
		while (!counters.get(0).isChainFinished()) {
			Map<String, String> testCase = new HashMap<>();
			for (int i = 0; i < inputVariablesList.size(); i++) {
//				 System.out.print(counters.get(i).value() + " ");
				 String key = inputVariablesList.get(i).getNamesForECs()
							.get(counters.get(i).value());
					testCase.put(inputVariablesList.get(i).getId(), inputVariablesList.get(i).getValuesForECs().get(key));
			}
			testCases.add(testCase);
//			System.out.println();
			counters.get(counters.size() - 1).increment();
		}
		
		return testCases;
	}
	
	@Override
	public List<TestCase> generateTestCases() {
		List<Map<String, String>> _testCases = generateStrongECTTestCases();
		List<TestCase> testCases = new ArrayList<>();
		Integer i=1;
		for(Map<String, String> _testCase:_testCases) {
			TestCase testCase = new TestCase(testCasePrefix+(i++));
			for(String key:_testCase.keySet()) {
				testCase.addInputValue(key, _testCase.get(key));
			}
			testCases.add(testCase);
		}
		return testCases;
	}
	
	public static void main(String[] args) throws tr.edu.iyte.swtesting.exception.InvalidInputException, FileNotFoundException, IOException {
		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\triangleInput.xlsx"));
		List<InputVariables> inputVariablesList = excelManager.readInputVariables();
//		System.out.println(Arrays.asList(new StrongECT(inputVariablesList).generateTestCases()));
	}
}
