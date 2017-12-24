package tr.edu.iyte.swtesting.excel;

import java.util.ArrayList;
import java.util.List;

import tr.edu.iyte.swtesting.model.BoundaryValues;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.utils.Cursor;

public class InputVariablesReader {
	private ExcelManager excelManager;

	public InputVariablesReader(ExcelManager excelManager) {
		this.excelManager = excelManager;
	}

	public List<InputVariables> read() {
		List<InputVariables> ivList = new ArrayList<InputVariables>();
		excelManager.setActiveSheet("Input Variables");

		Cursor cursorIV = new Cursor(2, 0);
		Cursor cursorValues = new Cursor(2, 5);
		while (excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.getCellIndex()).contains("IV")) {
			InputVariables iv = new InputVariables(
					excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.getCellIndex()),
					excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.nextCellIndex()),
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.getCellIndex()),
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()),
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()),
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()),
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()),
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()),
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()));
			ivList.add(iv);

			cursorIV.nextRowIndex();
			cursorValues.nextRowIndex();
			cursorIV.resetCellIndex();
			cursorValues.resetCellIndex();
		}
		return ivList;
	}
}
