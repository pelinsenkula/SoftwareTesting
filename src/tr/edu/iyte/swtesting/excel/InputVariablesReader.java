package tr.edu.iyte.swtesting.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import tr.edu.iyte.swtesting.model.BoundaryValues;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.test.Bvt;
import tr.edu.iyte.swtesting.test.RobustBvt;
import tr.edu.iyte.swtesting.test.WorstCaseBvt;
import tr.edu.iyte.swtesting.utils.Cursor;
import tr.edu.iyte.swtesting.utils.CursorBounded;

public class InputVariablesReader {
	private ExcelManager excelManager;

	public InputVariablesReader(ExcelManager excelManager) {
		this.excelManager = excelManager;
	}

	public List<InputVariables> read() throws InvalidInputException {
		List<InputVariables> ivList = new ArrayList<InputVariables>();
		excelManager.setActiveSheet("Input Variables");

		Integer inputVariablesCellOffset = null;
		Integer typesCellOffset = null;
		Integer ECsAndValuesCellOffset = null;
		Integer boundaryValuesCellOffset = null;

		Integer valuesRowOffset = 2;

		Cursor cursorCellOffset = new Cursor(0, 0);
		while (boundaryValuesCellOffset == null) {
			String val = excelManager.cellValue(cursorCellOffset.getRowIndex(), cursorCellOffset.getCellIndex());
			switch (val) {
			case "Input Variables":
				inputVariablesCellOffset = cursorCellOffset.getCellIndex();
				break;
			case "Types":
				typesCellOffset = cursorCellOffset.getCellIndex();
				break;
			case "ECs and Values":
				ECsAndValuesCellOffset = cursorCellOffset.getCellIndex();
				break;
			case "Boundary Values":
				boundaryValuesCellOffset = cursorCellOffset.getCellIndex();
				break;
			}
			if (cursorCellOffset.nextCellIndex() > 200) {
				throw new InvalidInputException(
						"Invalid Excel Input : Boundary Values couldn't found up to cellIndex : 200");
			}
			;
		}

		Cursor cursorIV = new CursorBounded(valuesRowOffset, null, inputVariablesCellOffset, typesCellOffset);
		Cursor cursorType = new CursorBounded(valuesRowOffset, null, typesCellOffset, ECsAndValuesCellOffset);

		Cursor cursorValuesForECs = new CursorBounded(valuesRowOffset, null, ECsAndValuesCellOffset,boundaryValuesCellOffset);
		Cursor cursorNamesForECs = new CursorBounded(1, valuesRowOffset, ECsAndValuesCellOffset, boundaryValuesCellOffset);

		Cursor cursorValues = new Cursor(valuesRowOffset, boundaryValuesCellOffset);

		while (excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.getCellIndex()).contains("IV")) {

			String id = excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.getCellIndex());
			String variable = excelManager.cellValue(cursorIV.getRowIndex(), cursorIV.nextCellIndex());
			String type = excelManager.cellValue(cursorType.getRowIndex(), cursorType.getCellIndex());
			
			Map<String, String> valuesForECs = new HashMap<String, String>();
			List<String> namesForECs = new ArrayList<String>();
			while (true) {
				String key = excelManager.cellValue(cursorNamesForECs.getRowIndex(), cursorNamesForECs.getCellIndex());
				String value = excelManager.cellValue(cursorValuesForECs.getRowIndex(),cursorValuesForECs.getCellIndex());
				if(!value.trim().equals("")) {
					valuesForECs.put(key, value);
					namesForECs.add(key);
				}
				cursorValuesForECs.nextCellIndex();
				if(cursorNamesForECs.getCellIndex() > cursorNamesForECs.nextCellIndex()) {
					break;//break if cursor automatically turns default value
				}
			}

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
			cursorType.nextRowIndex();
			cursorValuesForECs.nextRowIndex();
			cursorValues.nextRowIndex();
			
			cursorIV.resetCellIndex();
			cursorValues.resetCellIndex();
		}
		return ivList;
	}
	
	public static void main(String[] args) throws IOException, InvalidInputException {

		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\dateInput.xlsx"));
		List<InputVariables> inputVariablesList = new InputVariablesReader(excelManager).read();
		for(InputVariables i : inputVariablesList) {
			System.out.println(i);
		}
		
		excelManager = new ExcelManager(new FileInputStream("resource\\triangleInput.xlsx"));
		inputVariablesList = new InputVariablesReader(excelManager).read();
		for(InputVariables i : inputVariablesList) {
			System.out.println(i);
		}

	}
}
