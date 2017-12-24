package tr.edu.iyte.swtesting.utils;

public class CursorBounded extends Cursor {
	private Integer cellEnd;
	private Integer rowEnd;

	public CursorBounded(Integer rowDefault, Integer rowEnd, Integer cellDefault, Integer cellEnd) {
		super(rowDefault, cellDefault);
		this.cellEnd = cellEnd;
		this.rowEnd = rowEnd;
	}

	@Override
	public Integer nextCellIndex() {
		Integer val = super.nextCellIndex();
		if (cellEnd != null && val > cellEnd) {
			super.resetCellIndex();
			val = super.getCellIndex();
		}
		return val;
	}

	@Override
	public Integer nextRowIndex() {
		Integer val = super.nextRowIndex();
		if (rowEnd != null && val > rowEnd) {
			super.resetRowIndex();
			val = super.getRowIndex();
		}
		return val;
	}

}
