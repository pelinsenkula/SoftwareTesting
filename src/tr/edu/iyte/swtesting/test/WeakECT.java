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
import tr.edu.iyte.swtesting.excel.InputVariablesReader;
import tr.edu.iyte.swtesting.model.InputVariables;

public class WeakECT {

	private List<InputVariables> inputVariablesList;

	public WeakECT(List<InputVariables> inputVariablesList) {
		this.inputVariablesList = inputVariablesList;
	}

	public List<String> generateWeakECTestCases() {
		List<Integer> sizeList = new ArrayList<>();
		List<String> testCaseKeys = new ArrayList<>();
		for(int i=0; i<inputVariablesList.size();i++) {
			sizeList.add(inputVariablesList.get(i).getNamesForECs().size());
		}
		int maxSize = Collections.max(sizeList);
		for (int k=0; k<maxSize; k++) {
			for(int j=0; j<inputVariablesList.size(); j++) {
				String a = inputVariablesList.get(j).getNamesForECs().get((k%inputVariablesList.get(j).getNamesForECs().size()));				
				testCaseKeys.add(a);
			}		
		}
		return testCaseKeys;

	}
	public static void main(String[] args) throws FileNotFoundException, IOException, InvalidInputException {	
//		Map<String, String> v1 = new HashMap<>();
//		List<String> v1Names = new ArrayList<>();
//		v1.put("m1", "5");
//		v1Names.add("m1");
//		v1.put("m2", "2");
//		v1Names.add("m2");
//		v1.put("m3", "2");
//		v1Names.add("m3");
//		
//		Map<String, String> v2 = new HashMap<>();
//		List<String> v2Names = new ArrayList<>();
//		v2.put("D1", "5");
//		v2Names.add("D1");
//		v2.put("D2", "2");
//		v2Names.add("D2");
//		v2.put("D3", "3");
//		v2Names.add("D3");
//		v2.put("D4", "4 ");
//		v2Names.add("D4");
//		
//		Map<String, String> v3 = new HashMap<>();
//		List<String> v3Names = new ArrayList<>();
//		v3.put("y1", "5");
//		v3Names.add("y1");
//		v3.put("y2", "3");
//		v3Names.add("y2");
//		v3.put("y3", "2");
//		v3Names.add("y3");
//		
//		InputVariables iv = new InputVariables("a", "a","Integer", v1 ,v1Names, "0","1","2","100","199","200","201");
//		InputVariables iv2 = new InputVariables("b", "b","Integer",v2,v2Names,"0", "1","2","100","199","200","201");
//		InputVariables iv3 = new InputVariables("c", "c","Integer",v3,v3Names,"0", "1","2","100","199","200","201");
//		
//		List<InputVariables> inputVariablesList = new ArrayList<InputVariables>();
//		inputVariablesList.add(iv);
//		inputVariablesList.add(iv2);
//		inputVariablesList.add(iv3);
//		
//		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\triangleInput.xlsx"));
		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\dateInput.xlsx"));
		List<InputVariables> inputVariablesList = excelManager.readInputVariables();
		
		WeakECT wect = new WeakECT(inputVariablesList); 
		System.out.println(wect.generateWeakECTestCases());
	}
	
}
