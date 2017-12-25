package tr.edu.iyte.swtesting.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InputVariables {

	private String id;
	private String variable;
	private String type;
	// private String noOfEC;
	// private String valuesForEcs;
	private Map<String, String> valuesForECs;
	private List<String> namesForECs;

	private Map<String, String> invalidValuesForECs;
	private List<String> invalidNamesForECs;

	private String minMinus;
	private String min;
	private String minPlus;
	private String nominal;
	private String maxMinus;
	private String max;
	private String maxPlus;

	public InputVariables(String id, String variable, String type, Map<String, String> valuesForECs,
			List<String> namesForECs, Map<String, String> invalidValuesForECs, List<String> invalidNamesForECs,
			String minMinus, String min, String minPlus, String nominal, String maxMinus, String max, String maxPlus) {
		setId(id);
		setVariable(variable);
		setType(type);
		setValuesForECs(valuesForECs);
		setNamesForECs(namesForECs);
		setInvalidNamesForECs(invalidNamesForECs);
		setInvalidValuesForECs(invalidValuesForECs);
		setMinMinus(minMinus);
		setMin(min);
		setMinPlus(minPlus);
		setNominal(nominal);
		setMaxMinus(maxMinus);
		setMax(max);
		setMaxPlus(maxPlus);
	}



	public BoundaryValues getBoundaryValues() {
		return new BoundaryValues(min, minPlus, nominal, maxMinus, max);
	}

	public RobustBoundaryValues getRobustBoundaryValues() {
		return new RobustBoundaryValues(minMinus, min, minPlus, nominal, maxMinus, max, maxPlus);
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

	public String getMinMinus() {
		return minMinus;
	}

	public void setMinMinus(String minMinus) {
		this.minMinus = minMinus;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMinPlus() {
		return minPlus;
	}

	public void setMinPlus(String minPlus) {
		this.minPlus = minPlus;
	}

	public String getNominal() {
		return nominal;
	}

	public void setNominal(String nominal) {
		this.nominal = nominal;
	}

	public String getMaxMinus() {
		return maxMinus;
	}

	public void setMaxMinus(String maxMinus) {
		this.maxMinus = maxMinus;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMaxPlus() {
		return maxPlus;
	}

	public void setMaxPlus(String maxPlus) {
		this.maxPlus = maxPlus;
	}

	public Map<String, String> getValuesForECs() {
		return valuesForECs;
	}

	public void setValuesForECs(Map<String, String> valuesForECs) {
		this.valuesForECs = valuesForECs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getNamesForECs() {
		return namesForECs;
	}

	public void setNamesForECs(List<String> namesForECs) {
		this.namesForECs = namesForECs;
	}
	
	public Map<String, String> getInvalidValuesForECs() {
		return invalidValuesForECs;
	}

	public void setInvalidValuesForECs(Map<String, String> invalidValuesForECs) {
		this.invalidValuesForECs = invalidValuesForECs;
	}

	public List<String> getInvalidNamesForECs() {
		return invalidNamesForECs;
	}

	public void setInvalidNamesForECs(List<String> invalidNamesForECs) {
		this.invalidNamesForECs = invalidNamesForECs;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ID:");
		sb.append(getId());
		sb.append(", VAR_NAME:");
		sb.append(getVariable());
		sb.append(", TYPE:");
		sb.append(getType());
		sb.append(", ECsNames:");
		sb.append(Arrays.asList(getNamesForECs()));
		sb.append(", ECsValues:");
		sb.append(Arrays.asList(getValuesForECs()));
		sb.append(", invalidECsNames:");
		sb.append(Arrays.asList(getInvalidNamesForECs()));
		sb.append(", invalidECsValues:");
		sb.append(Arrays.asList(getInvalidValuesForECs()));
		sb.append(", MIN-:");
		sb.append(getMinMinus());
		sb.append(", MIN:");
		sb.append(getMin());
		sb.append(", MIN+:");
		sb.append(getMinPlus());
		sb.append(", NOMINAL:");
		sb.append(getNominal());
		sb.append(", MAX-:");
		sb.append(getMaxMinus());
		sb.append(", MAX:");
		sb.append(getMax());
		sb.append(", MAX+ :");
		sb.append(getMaxPlus());
		return sb.toString();
	}
}
