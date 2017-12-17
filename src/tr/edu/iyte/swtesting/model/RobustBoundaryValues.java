package tr.edu.iyte.swtesting.model;

public class RobustBoundaryValues extends BoundaryValues {

	private Integer minMinus;
	private Integer maxPlus;
	
	public RobustBoundaryValues(Integer minMinus, Integer min, Integer minPlus, Integer nominal, Integer maxMinus, Integer max, Integer maxPlus) {
		super(min, minPlus, nominal, maxMinus, max);
		this.minMinus = minMinus;
		this.maxPlus = maxPlus;
		
	}
	
	public RobustBoundaryValues() {
		super();
	}

	public Integer getMinMinus() {
		return minMinus;
	}

	public void setMinMinus(Integer minMinus) {
		this.minMinus = minMinus;
	}
}
