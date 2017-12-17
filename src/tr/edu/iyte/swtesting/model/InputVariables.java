package tr.edu.iyte.swtesting.model;

public class InputVariables {

	private String id;
	private String variable;
	// private String noOfEC;
	// private String valuesForEcs;
	private BoundaryValues boundaryValues;

	public InputVariables(String id, String variable, BoundaryValues boundaryValues) {
		this.id = id;
		this.variable = variable;
		this.boundaryValues = boundaryValues;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public BoundaryValues getBoundaryValues() {
		return boundaryValues;
	}

	public void setBoundaryValues(BoundaryValues boundaryValues) {
		this.boundaryValues = boundaryValues;
	}
	
}
