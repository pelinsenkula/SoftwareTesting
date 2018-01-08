package tr.edu.iyte.swtesting.test;

import java.util.List;

import tr.edu.iyte.swtesting.model.TestCase;
import tr.edu.iyte.swtesting.problems.NextDateProblem;
import tr.edu.iyte.swtesting.problems.TriangleProblem;

public class ProblemTester {

	public static void testNextDateProblem(List<TestCase> testCases) {
		for (TestCase testCase : testCases) {
			Integer m = Integer.parseInt(testCase.getValue("IV1"));
			Integer d = Integer.parseInt(testCase.getValue("IV2"));
			Integer y = Integer.parseInt(testCase.getValue("IV3"));
			try {
				NextDateProblem today = new NextDateProblem(m, d, y);
				String observed = today.next().toString();
				testCase.setObserved(observed);
			} catch (RuntimeException e) {
				testCase.setObserved(e.getMessage());
			}
			testCase.tested();
		}
	}
	
	public static void testTriangleProblem(List<TestCase> testCases) {
		for (TestCase testCase : testCases) {			
			try {
				Integer a = Integer.parseInt(testCase.getValue("IV1"));
				Integer b = Integer.parseInt(testCase.getValue("IV2"));
				Integer c = Integer.parseInt(testCase.getValue("IV3"));
				TriangleProblem triangle = new TriangleProblem();
				String observed = triangle.tri_type(a, b, c);
				testCase.setObserved(observed);
			} catch (RuntimeException e) {
				testCase.setObserved(e.getMessage());
				System.out.println(testCase);
			}
			testCase.tested();
		}
	}
}
