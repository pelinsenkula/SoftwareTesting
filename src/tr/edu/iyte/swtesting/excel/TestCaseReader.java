package tr.edu.iyte.swtesting.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tr.edu.iyte.swtesting.exception.InvalidInputException;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.model.TestCase;
import tr.edu.iyte.swtesting.utils.Cursor;

public class TestCaseReader {
	private ExcelManager excelManager;

	protected TestCaseReader(ExcelManager excelManager) {
		this.excelManager = excelManager;
	}
	
	public List<TestCase> read(String sheetName, String testCasePrefix){
		List<TestCase> testCases = new ArrayList<>();
		excelManager.setActiveSheet(sheetName);
		Cursor c0 = new Cursor(3, 0);
		Cursor c1 = new Cursor(1, 1);
		Cursor c2 = new Cursor(3, 1);
		while(excelManager.cellValue(c0.getRowIndex(), c0.getCellIndex()).contains(testCasePrefix)) {
			TestCase testCase = new TestCase(excelManager.cellValue(c0.getRowIndex(), c0.getCellIndex()));
			
			while (excelManager.cellValue(c1.getRowIndex(), c1.getCellIndex()).contains("IV")) {
				testCase.addInputValue(excelManager.cellValue(c1.getRowIndex(), c1.getCellIndex()),
						excelManager.cellValue(c2.getRowIndex(), c2.getCellIndex()));
				c1.nextCellIndex();
				c2.nextCellIndex();
			}
			
			testCase.setExpected(excelManager.cellValue(c2.getRowIndex(), c2.getCellIndex()));
			c2.nextCellIndex();
			testCase.setObserved(excelManager.cellValue(c2.getRowIndex(), c2.getCellIndex()));
			
			c1.resetCellIndex();
			c2.resetCellIndex();
			c0.nextRowIndex();
			c2.nextRowIndex();
			testCases.add(testCase);
		}
		
		return testCases;
	}

	
	
}
