package tr.edu.iyte.swtesting.excel;

import java.util.List;
import java.util.Map;

public class TestCaseWriter {
	private ExcelManager excelManager;
	private Integer rowIndex;
	private Integer cellIndex;

	public TestCaseWriter(ExcelManager excelManager) {
		this.excelManager = excelManager;
	}

	public void write(String sheetName, List<Map<String, String>> testCases) {
		rowIndex = 2;
		cellIndex = 1;
		excelManager.setActiveSheet(sheetName);
		for (Map<String, String> testCase : testCases) {
			for (String variable : testCase.keySet()) {
				excelManager.row(rowIndex).cell(cellIndex).getCell().setCellValue(testCase.get(variable));
				cellIndex++;
				System.out.print(" " + variable + ":" + testCase.get(variable));
			}
			rowIndex++;
			cellIndex = 1;
			System.out.println();
		}

	}

}
