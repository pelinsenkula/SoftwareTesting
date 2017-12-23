package tr.edu.iyte.swtesting.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		System.out.println(val);
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
