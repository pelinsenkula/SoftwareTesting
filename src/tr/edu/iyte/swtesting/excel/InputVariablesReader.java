package tr.edu.iyte.swtesting.excel;

import java.util.ArrayList;
import java.util.List;

import tr.edu.iyte.swtesting.model.BoundaryValues;
import tr.edu.iyte.swtesting.model.InputVariables;

public class InputVariablesReader {
	private ExcelManager excelManager;

	public InputVariablesReader(ExcelManager excelManager) {
		this.excelManager = excelManager;
	}

	public List<InputVariables> read() {
		List<InputVariables> ivList = new ArrayList<InputVariables>();
		excelManager.setActiveSheet("Input Variables");
		Integer i = 2;
		while (excelManager.cellValue(i, 0).contains("IV")) {

			InputVariables iv = new InputVariables(excelManager.cellValue(0), excelManager.cellValue(1),
					excelManager.cellValue(5), excelManager.cellValue(6), excelManager.cellValue(7),
					excelManager.cellValue(8), excelManager.cellValue(9), excelManager.cellValue(10),
					excelManager.cellValue(11));
			ivList.add(iv);
			System.out.println(iv);
			i++;
		}
		return ivList;
	}
}
