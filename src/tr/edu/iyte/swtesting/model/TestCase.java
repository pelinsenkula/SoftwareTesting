package tr.edu.iyte.swtesting.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase {

	private String testCaseNo="";
	private List<InputVariable> inputValues = new ArrayList<>();
	private String expected="";
	private String observed="";

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

	public String getExpected() {
		return expected;
	}

	public void setExpected(String expected) {
		this.expected = expected;
	}

	public String getObserved() {
		return observed;
	}

	public void setObserved(String observed) {
		this.observed = observed;
	}

	public Boolean isTestPass() {
		return getExpected().equals(getObserved());
	}

	public String getValue(String variableId) {
		for(InputVariable iv:inputValues) {
			if(iv.getVariableId().equals(variableId)) {
				return iv.value;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{testCaseNo:" + testCaseNo + ", inputValues:" + inputValues + ", expected:" + expected + ", observed:"
				+ observed + "}\n";
	}

	public class InputVariable {
		private String variableId;
		private String value;

		public InputVariable(String variableId, String value) {
			setVariableId(variableId);
			setValue(value);
		}

		public String getVariableId() {
			return variableId;
		}

		private void setVariableId(String variableId) {
			this.variableId = variableId;
		}

		public String getValue() {
			return value;
		}

		private void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[vid:" + variableId + " ,value:" + value + " ]";
		}
	}
}
