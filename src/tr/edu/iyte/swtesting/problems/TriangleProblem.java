package tr.edu.iyte.swtesting.problems;

public class TriangleProblem {

	public String tri_type(int a, int b, int c) {
		String type;
		if (a > b) {
			int t = a;
			a = b;
			b = t;
		}
		if (a > c) {
			int t = a;
			a = c;
			c = t;
		}
		if (b > c) {
			int t = b;
			b = c;
			c = t;
		}
		if (a + b <= c) {
			type = "NOT_A_TRIANGLE";
		} else {
			type = "SCALENE";
			if (a == b && b == c) {
				type = "EQUILATERAL";
			} else if (a == b || b == c) {
				type = "ISOSCELES";
			}
		}
		return type;
	}

}
