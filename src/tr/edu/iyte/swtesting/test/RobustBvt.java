package tr.edu.iyte.swtesting.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.model.BoundaryValues;
import tr.edu.iyte.swtesting.model.InputVariables;

public class RobustBvt extends Bvt{

	public RobustBvt(List<InputVariables> inputVariablesList) {
		super(inputVariablesList);
	}

	public List<Map<String,String>> generateRobustBvtTestCases(List<InputVariables> inputVariablesList) {
		List<Map<String,String>> testCases = super.generateBvtTestCases();
		
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
		List<Map<String, String>> a = bvt.generateBvtTestCases();
		
		System.out.println(Arrays.asList(a));
	}
}
