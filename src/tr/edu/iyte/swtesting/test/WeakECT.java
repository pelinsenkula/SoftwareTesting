package tr.edu.iyte.swtesting.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import tr.edu.iyte.swtesting.excel.ExcelManager;
import tr.edu.iyte.swtesting.model.InputVariables;

public class WeakECT {

	private List<InputVariables> inputVariablesList;

	public WeakECT(List<InputVariables> inputVariablesList) {
		this.inputVariablesList = inputVariablesList;
	}

	public List<Map<String, String>> generateWeakECTestCases() {
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

	public static void main(String[] args) throws FileNotFoundException, IOException, InvalidInputException {

//		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\triangleInput.xlsx"));
		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\dateInput.xlsx"));
		List<InputVariables> inputVariablesList = excelManager.readInputVariables();

		WeakECT wect = new WeakECT(inputVariablesList);
		System.out.println(wect.generateWeakECTestCases());
	}

}
