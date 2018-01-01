package tr.edu.iyte.swtesting.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase {

	private String testCaseNo;
	private List<InputVariable> inputValues = new ArrayList<>();
	private List<OutputPairValues> outputValues = new ArrayList<>();

	public TestCase(String testCaseNo) {
		setTestCaseNo(testCaseNo);
	}

	private void setTestCaseNo(String testCaseNo) {
		this.testCaseNo = testCaseNo;
	}

	public String getTestCaseNo() {
		return testCaseNo;
	}
	
	public void addInputValue(String variableId, String value) {
		inputValues.add(new InputVariable(variableId, value));
	}
	
	public void addOutputValue(String expected, String observed) {
		outputValues.add(new OutputPairValues(expected, observed));
	}

	private class OutputPairValues {
		private String expected;
		private String observed;

		private OutputPairValues(String expected, String observed) {
			setExpected(expected);
			setObserved(observed);
		}

		private String getExpected() {
			return expected;
		}

		private void setExpected(String expected) {
			this.expected = expected;
		}

		private String getObserved() {
			return observed;
		}

		private void setObserved(String observed) {
			this.observed = observed;
		}
		
		private Boolean isTestDone() {
			return false;
		}
	}

	private class InputVariable {
		private String variableId;
		private String value;

		private InputVariable(String variableId, String value) {
			setVariableId(variableId);
			setValue(value);
		}

		private String getVariableId() {
			return variableId;
		}

		private void setVariableId(String variableId) {
			this.variableId = variableId;
		}

		private String getValue() {
			return value;
		}

		private void setValue(String value) {
			this.value = value;
		}
	}
}
