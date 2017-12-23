package tr.edu.iyte.swtesting.model;

public class RobustBoundaryValues extends BoundaryValues {

	private String minMinus;
	private String maxPlus;
	
	public RobustBoundaryValues(String minMinus, String min, String minPlus, String nominal, String maxMinus, String max, String maxPlus) {
		super(min, minPlus, nominal, maxMinus, max);
		this.minMinus = minMinus;
		this.maxPlus = maxPlus;
		
	}
	
	public RobustBoundaryValues() {
		super();
	}

	public String getMinMinus() {
		return minMinus;
	}

	public void setMinMinus(String minMinus) {
		this.minMinus = minMinus;
	}

	public String getMaxPlus() {
		return maxPlus;
	}

	public void setMaxPlus(String maxPlus) {
		this.maxPlus = maxPlus;
	}
}
