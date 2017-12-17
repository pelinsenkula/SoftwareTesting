package tr.edu.iyte.swtesting.test;

import javax.faces.bean.ManagedBean;

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
}
