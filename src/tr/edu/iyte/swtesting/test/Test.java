package tr.edu.iyte.swtesting.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import tr.edu.iyte.swtesting.contract.TestCaseGenerator;
import tr.edu.iyte.swtesting.excel.ExcelManager;
import tr.edu.iyte.swtesting.excel.InputVariablesReader;
import tr.edu.iyte.swtesting.excel.TestCaseWriter;
import tr.edu.iyte.swtesting.exception.InvalidInputException;
import tr.edu.iyte.swtesting.model.InputVariables;
import tr.edu.iyte.swtesting.model.TestCase;
import tr.edu.iyte.swtesting.problems.NextDateProblem;

@ManagedBean(name = "testing")
@ViewScoped
public class Test {

	private String message = "Message Field";
	private Part file;
	private Boolean testNextDateProblem = true;

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public Test() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String info) {
		this.message = info;
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

	public void generateTestCases(InputStream inputStream, OutputStream outputStream) {
		ExcelManager excelManager;
		try {
			excelManager = new ExcelManager(inputStream);
			List<InputVariables> inputVariablesList = excelManager.readInputVariables();
			List<TestCase> bvt = new Bvt(inputVariablesList, "BVT").generateTestCases();
			List<TestCase> worstCasebvt = new WorstCaseBvt(inputVariablesList, "WCT").generateTestCases();
			List<TestCase> robustbvt = new RobustBvt(inputVariablesList, "RT").generateTestCases();
			List<TestCase> strongECT = new StrongECT(inputVariablesList, "SET").generateTestCases();
			List<TestCase> weakECT = new WeakECT(inputVariablesList, "WET").generateTestCases();
			List<TestCase> traditionalECT = new TraditionalECT(inputVariablesList, "TR").generateTestCases();

			excelManager.writeTestCases("Boundary Value Analysis", bvt);
			excelManager.writeTestCases("Robustness Test Cases", robustbvt);
			excelManager.writeTestCases("Strong Equivalance Test Cases", strongECT);
			excelManager.writeTestCases("Weak Eqivalance Test Cases", weakECT);
			excelManager.writeTestCases("Worst Case Test Cases", worstCasebvt);
			excelManager.writeTestCases("Traditional Equivalence", traditionalECT);

			excelManager.save(outputStream);
			excelManager.close();
			outputStream.close();
			setMessage("Excel dosyasý baþarýlý bir þekilde yüklendi.\n Testcaseler uretildi.");

		} catch (IOException | InvalidInputException e1) {
			// TODO Auto-generated catch block
			setMessage(e1.getMessage());
			e1.printStackTrace();
		}
	}

	public void readAndTestTestCases(InputStream inputStream, OutputStream outputStream) {
		ExcelManager excelManager;
		try {
			excelManager = new ExcelManager(inputStream);
			List<TestCase> bvt = excelManager.readTestCases("Boundary Value Analysis", "BVT");
			List<TestCase> worstCasebvt = excelManager.readTestCases("Worst Case Test Cases", "WCT");
			List<TestCase> robustbvt = excelManager.readTestCases("Robustness Test Cases", "RT");
			List<TestCase> strongECT = excelManager.readTestCases("Strong Equivalance Test Cases", "SET");
			List<TestCase> weakECT = excelManager.readTestCases("Weak Eqivalance Test Cases", "WET");
			List<TestCase> traditionalECT = excelManager.readTestCases("Traditional Equivalence", "TR");

			if (testNextDateProblem) {
				ProblemTester.testNextDateProblem(bvt);
				ProblemTester.testNextDateProblem(worstCasebvt);
				ProblemTester.testNextDateProblem(robustbvt);
				ProblemTester.testNextDateProblem(strongECT);
				ProblemTester.testNextDateProblem(weakECT);
				ProblemTester.testNextDateProblem(traditionalECT);
			}else {
				System.out.println("Triangle problem.");
			}

			excelManager.writeTestCases("Boundary Value Analysis", bvt);
			excelManager.writeTestCases("Robustness Test Cases", robustbvt);
			excelManager.writeTestCases("Strong Equivalance Test Cases", strongECT);
			excelManager.writeTestCases("Weak Eqivalance Test Cases", weakECT);
			excelManager.writeTestCases("Worst Case Test Cases", worstCasebvt);
			excelManager.writeTestCases("Traditional Equivalence", traditionalECT);

			excelManager.save(outputStream);
			excelManager.close();
			outputStream.close();
			setMessage("Excel dosyasý baþarýlý bir þekilde yüklendi.\n Testler yapýldý.");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			setMessage(e1.getMessage());
			e1.printStackTrace();
		}
	}

	public void uploadInputVariables() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType("vnd.ms-excel");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"output.xlsx\"");
		try {
			generateTestCases(getFile().getInputStream(), ec.getResponseOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			setMessage(e.getMessage() + getMessage());
		}
		fc.responseComplete();

	}

	public void uploadTestCases() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType("vnd.ms-excel");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"output.xlsx\"");
		try {
			readAndTestTestCases(getFile().getInputStream(), ec.getResponseOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			setMessage(e.getMessage() + getMessage());
		}
		fc.responseComplete();
	}

	public static void main(String[] args) throws IOException, InvalidInputException {

		ExcelManager excelManager = new ExcelManager(new FileInputStream("resource\\dateInput_prepared.xlsx"));
		List<InputVariables> inputVariablesList = excelManager.readInputVariables();
		
		
		
		// List<TestCase> bvt = new Bvt(inputVariablesList, "BVT").generateTestCases();
		// List<TestCase> worstCasebvt = new WorstCaseBvt(inputVariablesList,
		// "WCT").generateTestCases();
		// List<TestCase> robustbvt = new RobustBvt(inputVariablesList,
		// "RT").generateTestCases();
		// List<TestCase> strongECT = new StrongECT(inputVariablesList,
		// "SET").generateTestCases();
		// List<TestCase> weakECT = new WeakECT(inputVariablesList,
		// "WET").generateTestCases();
		// List<TestCase> traditionalECT = new TraditionalECT(inputVariablesList,
		// "TR").generateTestCases();

		List<TestCase> bvt = excelManager.readTestCases("Boundary Value Analysis", "BVT");
		List<TestCase> worstCasebvt = excelManager.readTestCases("Worst Case Test Cases", "WCT");
		List<TestCase> robustbvt = excelManager.readTestCases("Robustness Test Cases", "RT");
		List<TestCase> strongECT = excelManager.readTestCases("Strong Equivalance Test Cases", "SET");
		List<TestCase> weakECT = excelManager.readTestCases("Weak Eqivalance Test Cases", "WET");
		List<TestCase> traditionalECT = excelManager.readTestCases("Traditional Equivalence", "TR");

		// ProblemTester.testTriangleProblem(testCases);

		excelManager.writeTestCases("Boundary Value Analysis", bvt);
		excelManager.writeTestCases("Robustness Test Cases", robustbvt);
		excelManager.writeTestCases("Strong Equivalance Test Cases", strongECT);
		excelManager.writeTestCases("Weak Eqivalance Test Cases", weakECT);
		excelManager.writeTestCases("Worst Case Test Cases", worstCasebvt);
		excelManager.writeTestCases("Traditional Equivalence", traditionalECT);

		OutputStream outputStream = new FileOutputStream("resource\\output.xlsx");
		excelManager.save(outputStream);
		excelManager.close();
		System.out.println("Test Cases are generated.");

	}
}
