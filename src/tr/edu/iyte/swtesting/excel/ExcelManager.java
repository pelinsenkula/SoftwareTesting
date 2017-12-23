package tr.edu.iyte.swtesting.excel;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	private XSSFWorkbook workbook;
	private XSSFSheet currentSheet;
	private XSSFRow currentRow;
	private XSSFCell currentCell;

	public ExcelManager(InputStream inputStream) throws IOException {
		workbook = new XSSFWorkbook(inputStream);
	}

	public ExcelManager setActiveSheet(String sheetName) {
		currentSheet = workbook.getSheet(sheetName);
		return this;
	}

	public ExcelManager row(Integer index) {
		currentRow = currentSheet.getRow(index);
		return this;
	}

	public ExcelManager cell(Integer index) {
		if(currentRow == null) {
			currentCell = null;
			return this;
		}
		
		currentCell = currentRow.getCell(index);
		return this;
	}

	public String cellValue() {
		if(currentCell == null) {
			return "";
		}
		String val = null;
		try {
			val = currentCell.getStringCellValue();
		} catch (IllegalStateException e) {
			val = Double.toString(currentCell.getNumericCellValue());
		}
		System.out.println(val);
		return val;
	}

	public String cellValue(Integer cellIndex) {
		return this.cell(cellIndex).cellValue();
	}

	public String cellValue(Integer rowIndex, Integer cellIndex) {
		return this.row(rowIndex).cell(cellIndex).cellValue();
	}

}
