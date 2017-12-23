package tr.edu.iyte.swtesting.model;

public class InputVariables {

	private String id;
	private String variable;
	// private String noOfEC;
	// private String valuesForEcs;
	private Integer minMinus;
	private Integer min;
	private Integer minPlus;
	private Integer nominal;
	private Integer maxMinus;
	private Integer max;
	private Integer maxPlus;

	public InputVariables(String id, String variable, Integer minMinus, Integer min, Integer minPlus, Integer nominal,
			Integer maxMinus, Integer max, Integer maxPlus) {
		setId(id);
		setVariable(variable);
		setMinMinus(minMinus);
		setMin(min);
		setMinPlus(minPlus);
		setNominal(nominal);
		setMaxMinus(maxMinus);
		setMax(max);
		setMaxPlus(maxPlus);		
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

	public Integer getMinMinus() {
		return minMinus;
	}

	public void setMinMinus(Integer minMinus) {
		this.minMinus = minMinus;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMinPlus() {
		return minPlus;
	}

	public void setMinPlus(Integer minPlus) {
		this.minPlus = minPlus;
	}

	public Integer getNominal() {
		return nominal;
	}

	public void setNominal(Integer nominal) {
		this.nominal = nominal;
	}

	public Integer getMaxMinus() {
		return maxMinus;
	}

	public void setMaxMinus(Integer maxMinus) {
		this.maxMinus = maxMinus;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMaxPlus() {
		return maxPlus;
	}

	public void setMaxPlus(Integer maxPlus) {
		this.maxPlus = maxPlus;
	}


}
