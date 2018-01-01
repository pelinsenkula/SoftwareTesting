package tr.edu.iyte.swtesting.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tr.edu.iyte.swtesting.contract.TestingTechnique;
import tr.edu.iyte.swtesting.excel.ExcelManager;
import tr.edu.iyte.swtesting.excel.InputVariablesReader;
import tr.edu.iyte.swtesting.excel.TestCaseWriter;
import tr.edu.iyte.swtesting.exception.InvalidInputException;
import tr.edu.iyte.swtesting.model.InputVariables;

@ManagedBean(name = "testing")
@ViewScoped
public class Test {
	
	private String test;
	private Part file;

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public Test() {

	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public void download() throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Employee Info");

		// Create row object
		XSSFRow row;

		// This data needs to be written (Object[])
		Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
		empinfo.put("1", new Object[] { "EMP ID", "EMP NAME", "DESIGNATION" });
		empinfo.put("2", new Object[] { "tp01", "Gopal", "Technical Manager" });
		empinfo.put("3", new Object[] { "tp02", "Manisha", "Proof Reader" });
		empinfo.put("4", new Object[] { "tp03", "Masthan", "Technical Writer" });
		empinfo.put("5", new Object[] { "tp04", "Satish", "Technical Writer" });
		empinfo.put("6", new Object[] { "tp05", "Krishna", "Technical Writer" });

		// Iterate over data and write to sheet
		Set<String> keyid = empinfo.keySet();
		int rowid = 0;

		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = empinfo.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}

		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		ec.responseReset();
		ec.setResponseContentType("vnd.ms-excel");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"excel.xlsx\"");
		OutputStream output = ec.getResponseOutputStream();

		workbook.write(output);

		fc.responseComplete();
		output.close();
		System.out.println("Writesheet.xlsx written successfully");

	}

	public void upload() {
		System.out.println("uploaded!");
		XSSFWorkbook workbook;
		try {
			workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet worksheet = workbook.getSheet("Employee Info");
			XSSFRow row1 = worksheet.getRow(0);
			XSSFCell cellA1 = row1.getCell((short) 0);
			System.out.println(cellA1.getStringCellValue());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, InvalidInputException {

		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\dateInput.xlsx"));
		List<InputVariables> inputVariablesList = excelManager.readInputVariables();
		
		TestingTechnique bvt = new Bvt(inputVariablesList);
		TestingTechnique worstCasebvt = new WorstCaseBvt(inputVariablesList);
		TestingTechnique robustbvt = new RobustBvt(inputVariablesList);
		TestingTechnique strongECT = new StrongECT(inputVariablesList);
		TestingTechnique weakECT = new WeakECT(inputVariablesList);
		TestingTechnique traditionalECT = new TraditionalECT(inputVariablesList);
		
		excelManager.writeTestCases("Boundary Value Analysis","BVT", bvt);
		excelManager.writeTestCases("Robustness Test Cases","RT", robustbvt);
		excelManager.writeTestCases("Strong Equivalance Test Cases","SET", strongECT);
		excelManager.writeTestCases("Weak Eqivalance Test Cases","WET", weakECT);
		excelManager.writeTestCases("Worst Case Test Cases","WCT", worstCasebvt);
		excelManager.writeTestCases("Traditional Equivalence", "TR", traditionalECT);
		
		OutputStream outputStream = new FileOutputStream("resource\\output.xlsx");
		excelManager.save(outputStream);
		excelManager.close();
		System.out.println("Test Cases are generated.");

	}
}
