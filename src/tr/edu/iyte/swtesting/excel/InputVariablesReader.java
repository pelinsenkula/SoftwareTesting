package tr.edu.iyte.swtesting.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import tr.edu.iyte.swtesting.model.BoundaryValues;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.utils.Cursor;

public class InputVariablesReader {
	private ExcelManager excelManager;

	public InputVariablesReader(ExcelManager excelManager) {
		this.excelManager = excelManager;
	}

	public List<InputVariables> read() throws InvalidInputException {
		List<InputVariables> ivList = new ArrayList<InputVariables>();
		excelManager.setActiveSheet("Input Variables");
		
		Integer inputVariablesCellOffset = null;
		Integer TypesCellOffset = null;
		Integer ECsAndValuesCellOffset = null;
		Integer BoundaryValuesCellOffset = null;
		
		Cursor cursorCellOffset = new Cursor(0, 0);
		while(BoundaryValuesCellOffset==null) {
			String val = excelManager.cellValue(cursorCellOffset.getRowIndex(),cursorCellOffset.getCellIndex());
			switch(val) {
			case "Input Variables":
				inputVariablesCellOffset = cursorCellOffset.getCellIndex(); 
				break;
			case "Types":
				TypesCellOffset = cursorCellOffset.getCellIndex(); 
				break;
			case "ECs and Values":
				ECsAndValuesCellOffset = cursorCellOffset.getCellIndex(); 
				break;
			case "Boundary Values":
				BoundaryValuesCellOffset = cursorCellOffset.getCellIndex(); 
				break;
			}
			if(cursorCellOffset.nextCellIndex()>200) {
				throw new InvalidInputException("Invalid Excel Input : Boundary Values couldn't found up to cellIndex : 200");
			};
		}
		

		Cursor cursorIV = new Cursor(2, 0);
		Cursor cursorType = new Cursor(2, 0);
		Cursor cursorValuesECs = new Cursor(2, 3);
		Cursor cursorNamesForECs = new Cursor(1, 3);
		Cursor cursorValues = new Cursor(2, 5);
		while (excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.getCellIndex()).contains("IV")) {

			String id = excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.getCellIndex());
			String variable = excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.nextCellIndex());
			String type = "NO_TYPE";
			Map<String, String> valuesForECs = new HashMap<String, String>();
			List<String> namesForECs = new ArrayList<String>();
			String minMinus = excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.getCellIndex());
			String min = excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex());
			String minPlus = excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex());
			String nominal = excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex());
			String maxMinus = excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex());
			String max = excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex());
			String maxPlus = excelManager.cellValue(cursorValues.getRowIndex(), cursorValues.nextCellIndex());

			
			ivList.add(new InputVariables(id, variable, type, valuesForECs, namesForECs, minMinus, min, minPlus,
					nominal, maxMinus, max, maxPlus));

			cursorIV.nextRowIndex();
			cursorValues.nextRowIndex();
			cursorIV.resetCellIndex();
			cursorValues.resetCellIndex();
		}
		return ivList;
	}
}
