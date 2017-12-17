package tr.edu.iyte.swtesting.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.model.BoundaryValues;
import tr.edu.iyte.swtesting.model.InputVariables;

public class Bvt {
	
	public Bvt () {
		
	}
	
	public List<Map<String,Integer>> generateBvtTestCases(List<InputVariables> inputVariablesList) {
		String id;
		int nominal;
		Map<String,Integer> nominalValues = new HashMap<String,Integer>();
		Map<String,Integer> testCase = new HashMap<String,Integer>();
		List<Map<String,Integer>> testCases = new ArrayList<Map<String,Integer>>();		
		
		for(int i=0; i<inputVariablesList.size();i++) {
			nominalValues = new HashMap<String,Integer>();
			for (int j=i+1; j<inputVariablesList.size();j++) {
				id = inputVariablesList.get(j).getId();
				nominal = inputVariablesList.get(j).getBoundaryValues().getNominal();
				nominalValues.put(id, nominal);
			}
			
			for (int k=i-1;k>=0;k--) {
				id = inputVariablesList.get(k).getId();
				nominal = inputVariablesList.get(k).getBoundaryValues().getNominal();
				nominalValues.put(id, nominal);
			}
			
			id = inputVariablesList.get(i).getId();
			BoundaryValues boundaryValues = inputVariablesList.get(i).getBoundaryValues();
			
			testCase = new HashMap<>();
			testCase.put(id, boundaryValues.getMin());
			testCase.putAll(nominalValues);
			
			testCases.add(testCase);
			
			testCase = new HashMap<>();
			testCase.put(id, boundaryValues.getMinPlus());
			testCase.putAll(nominalValues);
			
			testCases.add(testCase);
			
			testCase = new HashMap<>();
			testCase.put(id, boundaryValues.getNominal());
			testCase.putAll(nominalValues);
			
			testCases.add(testCase);
			
			testCase = new HashMap<>();
			testCase.put(id, boundaryValues.getMaxMinus());
			testCase.putAll(nominalValues);
			
			testCases.add(testCase);
			
			testCase = new HashMap<>();
			testCase.put(id, boundaryValues.getMax());
			testCase.putAll(nominalValues);
			
			testCases.add(testCase);						
		}
		return testCases;
	}
	
	
	public static void main(String[] args) {
		InputVariables iv = new InputVariables("a", "a", new BoundaryValues(1,2,100,199,200));
		InputVariables iv2 = new InputVariables("b", "b", new BoundaryValues(1,2,100,199,200));
		InputVariables iv3 = new InputVariables("c", "c", new BoundaryValues(1,2,100,199,200));

		InputVariables iv4 = new InputVariables("d", "c", new BoundaryValues(1,2,100,199,200));

		InputVariables iv5 = new InputVariables("e", "c", new BoundaryValues(1,2,100,199,200));
		List<InputVariables> inputVariablesList = new ArrayList<InputVariables>();
		inputVariablesList.add(iv);
		inputVariablesList.add(iv2);
		inputVariablesList.add(iv3);
		inputVariablesList.add(iv4);
		inputVariablesList.add(iv5);
		
		Bvt bvt = new Bvt();
		//Map<String, List<Integer>> map = bvt.generateBvtTestCases(inputVariablesList);
		List<Map<String, Integer>> a = bvt.generateBvtTestCases(inputVariablesList);
		
		System.out.println(Arrays.asList(a));
	}
	
}
