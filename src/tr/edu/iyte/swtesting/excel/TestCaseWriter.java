package tr.edu.iyte.swtesting.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.model.TestCase;
import tr.edu.iyte.swtesting.utils.Cursor;

public class TestCaseWriter {
	private ExcelManager excelManager;

	protected TestCaseWriter(ExcelManager excelManager) {
		this.excelManager = excelManager;
	}

	public void write(String sheetName, List<TestCase> testCases) {
		List<String> IVList = new ArrayList<String>();
		Cursor c1 = new Cursor(1, 1);
		excelManager.setActiveSheet(sheetName);
		while (excelManager.cellValue(c1.getRowIndex(), c1.getCellIndex()).contains("IV")) {
			IVList.add(excelManager.cellValue(c1.getRowIndex(), c1.getCellIndex()));
			c1.nextCellIndex();
		}

		c1 = new Cursor(3, 1);
		Cursor c2 = new Cursor(3, 0);

		for (TestCase testCase : testCases) {
			excelManager.row(c2.getRowIndex()).cell(c2.getCellIndex()).getCell().setCellValue(testCase.getTestCaseNo());
			for (String IVid : IVList) {
				excelManager.row(c1.getRowIndex()).cell(c1.getCellIndex()).getCell()
						.setCellValue(testCase.getValue(IVid));
				c1.nextCellIndex();
			}
			if (testCase.isTested()) {
				excelManager.row(c1.getRowIndex()).cell(c1.getCellIndex()).getCell()
						.setCellValue(testCase.getExpected());
				c1.nextCellIndex();
				excelManager.row(c1.getRowIndex()).cell(c1.getCellIndex()).getCell()
						.setCellValue(testCase.getObserved());
				c1.nextCellIndex();
				if (testCase.isTestPass()) {
					excelManager.row(c1.getRowIndex()).cell(c1.getCellIndex()).getCell().setCellValue("Pass");
				} else {
					excelManager.row(c1.getRowIndex()).cell(c1.getCellIndex()).getCell().setCellValue("Fail");
				}
			}
			c1.nextRowIndex();
			c2.nextRowIndex();
			c1.resetCellIndex();
		}

	}

	public List<Map<String, String>> toListMap(String sheetName, String testCasePrefix, List<TestCase> testCases) {
		List<Map<String, String>> testCasesList = new ArrayList<>();
		for (TestCase tc : testCases) {

		}
		return null;
	}

}
