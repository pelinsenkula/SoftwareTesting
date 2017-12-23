package tr.edu.iyte.swtesting.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.model.BoundaryValues;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.model.RobustBoundaryValues;

public class RobustBvt extends Bvt{

	public RobustBvt(List<InputVariables> inputVariablesList) {
		super(inputVariablesList);
	}

	public List<Map<String,String>> generateRobustBvtTestCases() {
		List<Map<String,String>> testCases = super.generateBvtTestCases();
		Map<String,String> testCase = new HashMap<String,String>();
		String id;		
		for(int i=0; i<getInputVariablesList().size();i++) {	
//			remove the value which will be used with boundary values and the rest with nominal values
			String nominalOfI = getNominalValues().get(getInputVariablesList().get(i).getId());
			getNominalValues().remove(getInputVariablesList().get(i).getId());
			
			id = getInputVariablesList().get(i).getId();
			RobustBoundaryValues robustBoundaryValues = getInputVariablesList().get(i).getRobustBoundaryValues();
			
			testCase = new HashMap<>();
			testCase.put(id, robustBoundaryValues.getMinMinus());
			testCase.putAll(getNominalValues());			
			if (!testCases.contains(testCase)) {
				testCases.add(testCase);
			}	
			
			testCase = new HashMap<>();
			testCase.put(id, robustBoundaryValues.getMaxPlus());
			testCase.putAll(getNominalValues());			
			if (!testCases.contains(testCase)) {
				testCases.add(testCase);
			}
			getNominalValues().put(getInputVariablesList().get(i).getId(), nominalOfI);
		}
		
		return testCases;
		
	}
	
	public static void main(String[] args) {
		InputVariables iv = new InputVariables("a", "a", "0","1","2","100","199","200","201");
		InputVariables iv2 = new InputVariables("b", "b","0", "1","2","100","199","200","201");
		InputVariables iv3 = new InputVariables("c", "c","0", "1","2","100","199","200","201");

		List<InputVariables> inputVariablesList = new ArrayList<InputVariables>();
		inputVariablesList.add(iv);
		inputVariablesList.add(iv2);
		inputVariablesList.add(iv3);
		
		RobustBvt bvt = new RobustBvt(inputVariablesList);
		List<Map<String, String>> a = bvt.generateRobustBvtTestCases();
		
		System.out.println(Arrays.asList(a));
	}
}
