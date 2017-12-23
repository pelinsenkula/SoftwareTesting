package tr.edu.iyte.swtesting.model;

public class BoundaryValues {

	private String min;
	private String minPlus;
	private String nominal;
	private String maxMinus;
	private String max;
	
	public BoundaryValues(String min, String minPlus, String nominal, String maxMinus, String max) {
		this.min = min;
		this.minPlus = minPlus;
		this.nominal = nominal;
		this.maxMinus = maxMinus;
		this.max = max;
		
	}
	
	public BoundaryValues() {
		
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
	
}
