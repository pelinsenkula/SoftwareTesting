package tr.edu.iyte.swtesting.utils;

public class Cursor {
	private Integer rowIndex;
	private Integer cellIndex;
	private Integer rowDefault;
	private Integer cellDefault;

	public Cursor(Integer rowDefault, Integer cellDefault) {
		this.rowDefault = rowDefault;
		this.cellDefault = cellDefault;
		resetIndices();
	}

	public void resetIndices() {
		resetCellIndex();
		resetRowIndex();
	}

	public void resetCellIndex() {
		this.cellIndex = cellDefault;
	}

	public void resetRowIndex() {
		this.rowIndex = rowDefault;
	}

	public Integer nextRowIndex() {
		return ++rowIndex;
	}

	public Integer nextCellIndex() {
		return ++cellIndex;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public Integer getCellIndex() {
		return cellIndex;
	}
}
