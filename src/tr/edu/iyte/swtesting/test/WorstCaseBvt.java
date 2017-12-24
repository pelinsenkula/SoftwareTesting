package tr.edu.iyte.swtesting.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.model.BoundaryValues;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.utils.ChainCounter;

public class WorstCaseBvt {

	private List<InputVariables> inputVariablesList;

	public WorstCaseBvt(List<InputVariables> inputVariablesList) {
		this.inputVariablesList = inputVariablesList;
	}

	public List<Map<String, String>> generateWorstCaseBvtTestCases() {
		List<ChainCounter> counters = new ArrayList<>();
		List<Map<String, String>> testCases = new ArrayList<>();

		ChainCounter c1 = null, c2 = null;
		for (int i = 0; i < inputVariablesList.size(); i++) {
			if (i == 0) {
				c2 = new ChainCounter(0, 4, null);
			} else {
				c2 = new ChainCounter(0, 4, c1);
			}
			counters.add(c2);
			c1 = c2;
		}

		while (!counters.get(0).isChainFinished()) {
			Map<String, String> testCase = new HashMap<>();
			for (int i = 0; i < inputVariablesList.size(); i++) {
				// System.out.print(counters.get(i).value() + " ");
				String val = null;
				switch (counters.get(i).value()) {
				case 0:
					val = inputVariablesList.get(i).getMin();
					break;
				case 1:
					val = inputVariablesList.get(i).getMinPlus();
					break;
				case 2:
					val = inputVariablesList.get(i).getNominal();
					break;
				case 3:
					val = inputVariablesList.get(i).getMaxMinus();
					break;
				case 4:
					val = inputVariablesList.get(i).getMax();
					break;
				}
				testCase.put(inputVariablesList.get(i).getId(), val);
			}
			testCases.add(testCase);
			counters.get(counters.size() - 1).increment();
		}
		return testCases;

	}

//	public static void main(String[] args) {
//		InputVariables iv = new InputVariables("a", "a", "0", "1", "2", "100", "199", "200", "201");
//		InputVariables iv2 = new InputVariables("b", "b", "0", "1", "2", "100", "199", "200", "201");
//		InputVariables iv3 = new InputVariables("c", "c", "0", "1", "2", "100", "199", "200", "201");
//		InputVariables iv4 = new InputVariables("d", "d", "0", "1", "2", "100", "199", "200", "201");
//
//		List<InputVariables> inputVariablesList = new ArrayList<InputVariables>();
//		inputVariablesList.add(iv);
//		inputVariablesList.add(iv2);
//		 inputVariablesList.add(iv3);
//		 inputVariablesList.add(iv4);
//
//		WorstCaseBvt bvt = new WorstCaseBvt(inputVariablesList);
//		System.out.println(Arrays.asList(bvt.generateWorstCaseBvtTestCases()));
//	}

}
