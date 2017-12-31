package tr.edu.iyte.swtesting.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.contract.TestingTechnique;
import tr.edu.iyte.swtesting.excel.ExcelManager;
import tr.edu.iyte.swtesting.exception.InvalidInputException;
import tr.edu.iyte.swtesting.model.InputVariables;

public class TraditionalECT implements TestingTechnique{

	private List<InputVariables> inputVariablesList;
	private Map<String, String> nominalValues;

	public TraditionalECT(List<InputVariables> inputVariablesList) {
		this.inputVariablesList = inputVariablesList;
		this.nominalValues = calculateNominalValues(inputVariablesList);
	}
	
	@Override
	public List<Map<String, String>> generateTestCases() {
		List<Map<String, String>> testCases = new ArrayList<>();
		
		WeakECT wect = new WeakECT(inputVariablesList);
		testCases.addAll(wect.generateTestCases());
		
		for(int i=0; i<inputVariablesList.size();i++) {	
			String nominalOfI = nominalValues.get(inputVariablesList.get(i).getId());
			nominalValues.remove(inputVariablesList.get(i).getId());
			
			for (int j=0; j<inputVariablesList.get(i).getInvalidNamesForECs().size(); j++) {
				Map<String, String> testCase = new HashMap<>();
				String key = inputVariablesList.get(i).getInvalidNamesForECs().get(j);
				testCase.put(inputVariablesList.get(i).getId(), inputVariablesList.get(i).getInvalidValuesForECs().get(key));
				testCase.putAll(nominalValues);
				testCases.add(testCase);
			}
			
			nominalValues.put(inputVariablesList.get(i).getId(), nominalOfI);
		}
		
		return testCases;
		
	}
	
	public Map<String,String> calculateNominalValues(List<InputVariables> inputVariablesList) {
		Map<String,String> nominalValues = new HashMap<String,String>();
		for (int j=0; j<inputVariablesList.size();j++) {
			String id = inputVariablesList.get(j).getId();
			String key = inputVariablesList.get(j).getNamesForECs().get(0);
			String nominal = inputVariablesList.get(j).getValuesForECs().get(key);
			nominalValues.put(id, nominal);
		}
		return nominalValues;
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, InvalidInputException {

//		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\triangleInput.xlsx"));
		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\dateInput.xlsx"));
		List<InputVariables> inputVariablesList = excelManager.readInputVariables();

		TraditionalECT tect = new TraditionalECT(inputVariablesList);
		System.out.println(tect.generateTestCases());
	}

}
