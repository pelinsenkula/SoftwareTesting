package tr.edu.iyte.swtesting.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.model.BoundaryValues;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.model.TestCase;
import tr.edu.iyte.swtesting.contract.TestCaseGenerator;

public class Bvt implements TestCaseGenerator {
	private Map<String, String> nominalValues;
	private List<InputVariables> inputVariablesList;
	private String testCasePrefix;
	public Bvt(List<InputVariables> inputVariablesList,String testCasePrefix) {
		this.inputVariablesList = inputVariablesList;
		this.nominalValues = calculateNominalValues(inputVariablesList);
		this.testCasePrefix = testCasePrefix;
	}

	@Deprecated
	public List<Map<String, String>> generateBVTTestCases() {
		String id;
		Map<String, String> testCase = new HashMap<String, String>();
		List<Map<String, String>> testCases = new ArrayList<Map<String, String>>();

		for (int i = 0; i < inputVariablesList.size(); i++) {
			// remove the value which will be used with boundary values and the rest with
			// nominal values
			String nominalOfI = nominalValues.get(inputVariablesList.get(i).getId());
			nominalValues.remove(inputVariablesList.get(i).getId());

			id = inputVariablesList.get(i).getId();
			BoundaryValues boundaryValues = inputVariablesList.get(i).getBoundaryValues();

			testCase = new HashMap<>();
			testCase.put(id, boundaryValues.getMin());
			testCase.putAll(nominalValues);
			if (!testCases.contains(testCase)) {
				testCases.add(testCase);
			}

			testCase = new HashMap<>();
			testCase.put(id, boundaryValues.getMinPlus());
			testCase.putAll(nominalValues);
			if (!testCases.contains(testCase)) {
				testCases.add(testCase);
			}

			testCase = new HashMap<>();
			testCase.put(id, boundaryValues.getNominal());
			testCase.putAll(nominalValues);
			if (!testCases.contains(testCase)) {
				testCases.add(testCase);
			}

			testCase = new HashMap<>();
			testCase.put(id, boundaryValues.getMaxMinus());
			testCase.putAll(nominalValues);
			if (!testCases.contains(testCase)) {
				testCases.add(testCase);
			}

			testCase = new HashMap<>();
			testCase.put(id, boundaryValues.getMax());
			testCase.putAll(nominalValues);
			if (!testCases.contains(testCase)) {
				testCases.add(testCase);
			}

			nominalValues.put(inputVariablesList.get(i).getId(), nominalOfI);
		}
		return testCases;
	}

	public Map<String, String> calculateNominalValues(List<InputVariables> inputVariablesList) {
		Map<String, String> nominalValues = new HashMap<String, String>();
		for (int j = 0; j < inputVariablesList.size(); j++) {
			String id = inputVariablesList.get(j).getId();
			String nominal = inputVariablesList.get(j).getBoundaryValues().getNominal();
			nominalValues.put(id, nominal);
		}
		return nominalValues;
	}

	public Map<String, String> getNominalValues() {
		return nominalValues;
	}

	public void setNominalValues(Map<String, String> nominalValues) {
		this.nominalValues = nominalValues;
	}

	public List<InputVariables> getInputVariablesList() {
		return inputVariablesList;
	}

	public void setInputVariablesList(List<InputVariables> inputVariablesList) {
		this.inputVariablesList = inputVariablesList;
	}

	@Override
	public List<TestCase> generateTestCases() {
		List<Map<String, String>> _testCases = generateBVTTestCases();
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

	// public static void main(String[] args) {
	// InputVariables iv = new InputVariables("a", "a",
	// "0","1","2","100","199","200","201");
	// InputVariables iv2 = new InputVariables("b", "b","0",
	// "1","2","100","199","200","201");
	// InputVariables iv3 = new InputVariables("c", "c","0",
	// "1","2","100","199","200","201");
	//
	//// InputVariables iv4 = new InputVariables("d", "c", new
	// BoundaryValues(1,2,100,199,200));
	////
	//// InputVariables iv5 = new InputVariables("e", "c", new
	// BoundaryValues(1,2,100,199,200));
	// List<InputVariables> inputVariablesList = new ArrayList<InputVariables>();
	// inputVariablesList.add(iv);
	// inputVariablesList.add(iv2);
	// inputVariablesList.add(iv3);
	//// inputVariablesList.add(iv4);
	//// inputVariablesList.add(iv5);
	//
	// Bvt bvt = new Bvt(inputVariablesList);
	// List<Map<String, String>> a = bvt.generateBvtTestCases();
	//
	// System.out.println(Arrays.asList(a));
	// }

}