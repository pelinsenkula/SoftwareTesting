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
			InputVariables iv = new InputVariables(// input variables
					excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.getCellIndex()), // ID
					excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.nextCellIndex()), // variable name
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.getCellIndex()), // min-
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()), // min
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()), // min+
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()), // nominal
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()), // max-
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()), // max
					excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex()));// max+
			ivList.add(iv);

			cursorIV.nextRowIndex();
			cursorValues.nextRowIndex();
			cursorIV.resetCellIndex();
			cursorValues.resetCellIndex();
		}
		return ivList;
	}
}
