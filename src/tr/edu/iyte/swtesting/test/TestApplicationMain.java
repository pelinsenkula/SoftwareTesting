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

@ManagedBean(name = "testApplicationMain")
@ViewScoped
public class TestApplicationMain {

	private String message = "Message Field";
	private Part file;
	private String selectedProblem = "";

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public TestApplicationMain() {

	}

	public String getSelectedProblem() {
		return selectedProblem;
	}

	public void setSelectedProblem(String selectedProblem) {
		this.selectedProblem = selectedProblem;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String info) {
		this.message = info;
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

			if (selectedProblem.equals("nextdate")) {
				ProblemTester.testNextDateProblem(bvt);
				ProblemTester.testNextDateProblem(worstCasebvt);
				ProblemTester.testNextDateProblem(robustbvt);
				ProblemTester.testNextDateProblem(strongECT);
				ProblemTester.testNextDateProblem(weakECT);
				ProblemTester.testNextDateProblem(traditionalECT);
				System.out.println("Nextdate problem.");
			}else if(selectedProblem.equals("triangle")){
				ProblemTester.testTriangleProblem(bvt);
				ProblemTester.testTriangleProblem(worstCasebvt);
				ProblemTester.testTriangleProblem(robustbvt);
				ProblemTester.testTriangleProblem(strongECT);
				ProblemTester.testTriangleProblem(weakECT);
				ProblemTester.testTriangleProblem(traditionalECT);
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
		String fname="generated_testcases.xlsx";
//		if(selectedProblem.equals("nextdate")) {
//			fname="nextdate_problem_test_cases.xlsx";
//		}else if(selectedProblem.equals("triangle")) {
//			fname="triangle_problem_test_cases.xlsx";
//		}
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType("vnd.ms-excel");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\""+fname+"\"");
		try {
			generateTestCases(getFile().getInputStream(), ec.getResponseOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			setMessage(e.getMessage() + getMessage());
		}
		fc.responseComplete();

	}

	public void uploadTestCases() {
		String fname="output.xlsx";
		if(selectedProblem.equals("nextdate")) {
			fname="nextdate_problem_test_result.xlsx";
		}else if(selectedProblem.equals("triangle")) {
			fname="triangle_problem_test_result.xlsx";
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.responseReset();
		ec.setResponseContentType("vnd.ms-excel");
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\""+fname+"\"");

		try {
			readAndTestTestCases(getFile().getInputStream(), ec.getResponseOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			setMessage(e.getMessage() + getMessage());
		}
		fc.responseComplete();
	}

	public static void main(String[] args) throws IOException, InvalidInputException {

		TestApplicationMain testingApplicationMain = new TestApplicationMain();
		testingApplicationMain.setSelectedProblem("nextdate");
//		testingApplicationMain.setSelectedProblem("triangle");
		InputStream inputStreamInputVariables = new FileInputStream("resource\\dateInput.xlsx");
		OutputStream outputStreamGeneratedTestCases = new FileOutputStream("resource\\dateInput_generated_testCases.xlsx");
		testingApplicationMain.generateTestCases(inputStreamInputVariables, outputStreamGeneratedTestCases);
		System.out.println("Test Cases are generated.But Expected results areas in the excel file should be filled."
				+"\n you can fill the expected results or you can use and manupulate prepared excel file\n");
		
		//prepared excel file that contains expected result fields
//		InputStream inputStreamGeneratedTestCases = new FileInputStream("resource\\dateInput_generated_testCases_prepared.xlsx");
		
		// if user filled expected results, you can use this.
		InputStream inputStreamGeneratedTestCases = new FileInputStream("resource\\dateInput_generated_testCases.xlsx");
		OutputStream outputStreamTestResult = new FileOutputStream("resource\\dateInput_tested_testCases.xlsx");
		testingApplicationMain.readAndTestTestCases(inputStreamGeneratedTestCases, outputStreamTestResult);
		
		
		System.out.println("Test Cases are generated.");

	}
}
