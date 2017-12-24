package tr.edu.iyte.swtesting.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jdt.core.compiler.InvalidInputException;

import tr.edu.iyte.swtesting.model.InputVariables;

public class ExcelManager {
	private XSSFWorkbook workbook;
	private XSSFSheet currentSheet;
	private XSSFRow currentRow;
	private XSSFCell currentCell;
	private TestCaseWriter testCaseWriter;
	private InputVariablesReader inputVariablesReader;

	public ExcelManager(InputStream inputStream) throws IOException {
		workbook = new XSSFWorkbook(inputStream);
		testCaseWriter = new TestCaseWriter(this);
		inputVariablesReader = new InputVariablesReader(this);
	}
	
	public void writeTestCases(String sheetName, List<Map<String, String>> testCases) {
		testCaseWriter.write(sheetName, testCases);
	}
	
	public List<InputVariables> readInputVariables() throws InvalidInputException {
		return inputVariablesReader.read();
	}

	public ExcelManager setActiveSheet(String sheetName) {
		currentSheet = workbook.getSheet(sheetName);
		if(currentSheet==null) {
			currentSheet = workbook.createSheet(sheetName);
		}
		return this;
	}

	public ExcelManager row(Integer index) {
		currentRow = currentSheet.getRow(index);
		if (currentRow == null) {
			currentRow = currentSheet.createRow(index);
		}
		return this;
	}

	public ExcelManager cell(Integer index) {
		currentCell = currentRow.getCell(index);
		if (currentCell == null) {
			currentCell = currentRow.createCell(index);
		}
		return this;
	}

	public String cellValue() {
		String val = null;
		try {
			val = currentCell.getStringCellValue();
		} catch (IllegalStateException e) {
			val = Double.toString(currentCell.getNumericCellValue());
		}
//		System.out.println(val);
		return val;
	}

	public String cellValue(Integer cellIndex) {
		return this.cell(cellIndex).cellValue();
	}

	public String cellValue(Integer rowIndex, Integer cellIndex) {
		return this.row(rowIndex).cell(cellIndex).cellValue();
	}

	public XSSFCell getCell() {
		return currentCell;
	}

	public void save(OutputStream outputStream) throws IOException{
		workbook.write(outputStream);
	}
}
