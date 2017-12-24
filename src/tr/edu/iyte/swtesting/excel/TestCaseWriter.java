package tr.edu.iyte.swtesting.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.utils.Cursor;

public class TestCaseWriter {
	private ExcelManager excelManager;

	public TestCaseWriter(ExcelManager excelManager) {
		this.excelManager = excelManager;
	}

	public void write(String sheetName, List<Map<String, String>> testCases) {
		List<String> IVList = new ArrayList<String>();
		Cursor c1 = new Cursor(1, 1);
		excelManager.setActiveSheet(sheetName);
		while (excelManager.cellValue(c1.getRowIndex(), c1.getCellIndex()).contains("IV")) {
			IVList.add(excelManager.cellValue(c1.getRowIndex(), c1.getCellIndex()));
			c1.nextCellIndex();
		}

		c1 = new Cursor(3, 1);
		for (Map<String, String> testCase : testCases) {
			for (String IVid : IVList) {
				excelManager.row(c1.getRowIndex()).cell(c1.getCellIndex()).getCell().setCellValue(testCase.get(IVid));
				c1.nextCellIndex();
			}
			c1.nextRowIndex();
			c1.resetCellIndex();
		}

	}
	

}
