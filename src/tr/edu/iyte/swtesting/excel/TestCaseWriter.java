package tr.edu.iyte.swtesting.excel;

import java.util.ArrayList;
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
		List<String> IVList=new ArrayList<String>();
		rowIndex = 2;
		cellIndex = 1;
		excelManager.setActiveSheet(sheetName);
		while (excelManager.cellValue(1, cellIndex).contains("IV")) {
			IVList.add(excelManager.cellValue(1, cellIndex));
			cellIndex++;
		}
		for (Map<String, String> testCase : testCases) {
			for (String IVid : IVList) {
				excelManager.row(rowIndex).cell(cellIndex).getCell().setCellValue(testCase.get(IVid));
				cellIndex++;
			}
			rowIndex++;
			cellIndex = 1;
		}

	}

}
