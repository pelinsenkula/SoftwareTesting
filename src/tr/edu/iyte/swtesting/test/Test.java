package tr.edu.iyte.swtesting.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@ManagedBean(name = "testing")
public class Test {

	private String test = "test";

	public Test() {

	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String download() throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet(" Employee Info ");

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
		return "OK!";

	}

}
