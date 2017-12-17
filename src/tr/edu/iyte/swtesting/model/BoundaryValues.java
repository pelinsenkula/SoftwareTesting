package tr.edu.iyte.swtesting.model;

public class BoundaryValues {

	private Integer min;
	private Integer minPlus;
	private Integer nominal;
	private Integer maxMinus;
	private Integer max;
	
	public BoundaryValues(Integer min, Integer minPlus, Integer nominal, Integer maxMinus, Integer max) {
		this.min = min;
		this.minPlus = minPlus;
		this.nominal = nominal;
		this.maxMinus = maxMinus;
		this.max = max;
		
	}
	
	public BoundaryValues() {
		
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
	
}
