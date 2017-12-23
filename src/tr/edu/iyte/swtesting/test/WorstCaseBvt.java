package tr.edu.iyte.swtesting.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.model.BoundaryValues;
import tr.edu.iyte.swtesting.model.InputVariables;

public class WorstCaseBvt {

	private List<InputVariables> inputVariablesList;

	public WorstCaseBvt (List<InputVariables> inputVariablesList) {
		this.inputVariablesList = inputVariablesList;		
	}
	
		
	public void generateWorstCaseBvtTestCases() {
		List<Integer> counters = new ArrayList<Integer>();
		
		for(int i=0;i<inputVariablesList.size();i++) {
			Integer c = 0;
			counters.add(c);
		}
		//System.out.println(Arrays.asList(counters));
		
//		while(counters.get(0)<5) {
//			for(int i=1;i<counters.size();i++) {
//				if(counters.get(i)==4) {
//					Integer c = counters.get(i-1);
//					c = i==1?(c+1):(c+1)%5;
//					counters.set(i-1, c);
//				}
//			}
//			Integer c = counters.get(counters.size()-1);
//			c = (c+1)%5;
//			counters.set(counters.size()-1, c);
//			System.out.println(Arrays.asList(counters));
//		}
		
		for(int i=0; i<inputVariablesList.size();i++) {	
			BoundaryValues boundaryValues = inputVariablesList.get(i).getBoundaryValues();
		}
			
	}
	
	public static void main(String[] args) {
		InputVariables iv = new InputVariables("a", "a", "0","1","2","100","199","200","201");
		InputVariables iv2 = new InputVariables("b", "b","0", "1","2","100","199","200","201");
		InputVariables iv3 = new InputVariables("c", "c","0", "1","2","100","199","200","201");
		InputVariables iv4 = new InputVariables("c", "c","0", "1","2","100","199","200","201");
		
		List<InputVariables> inputVariablesList = new ArrayList<InputVariables>();
		inputVariablesList.add(iv);
		inputVariablesList.add(iv2);
		inputVariablesList.add(iv3);
		inputVariablesList.add(iv4);
		
		WorstCaseBvt bvt = new WorstCaseBvt(inputVariablesList);
		bvt.generateWorstCaseBvtTestCases();
	}
	
}
