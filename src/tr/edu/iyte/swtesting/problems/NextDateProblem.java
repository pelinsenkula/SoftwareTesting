package tr.edu.iyte.swtesting.problems;

public class NextDateProblem {
	private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private final int month; // month (between 1 and 12)
	private final int day; // day (between 1 and DAYS[month]
	private final int year; // year

	// do bounds-checking to ensure object represents a valid date
	public NextDateProblem(int m, int d, int y) {
		if (!isValid(m, d, y)) {
			throw new RuntimeException("Invalid date");
		}
		month = m;
		day = d;
		year = y;
	}

	// is the given date valid?
	private static boolean isValid(int m, int d, int y) {
		if (m < 1 || m > 12) {
			return false;
		}
		if (d < 1 || d > DAYS[m]) {
			return false;
		}		
		if (y < 1812 || y > 2012) {
			return false;
		}
		if (m == 2 && d == 29 && !isLeapYear(y)) {
			return false;
		}
		return true;
	}

	// is y a leap year?
	private static boolean isLeapYear(int y) {
		if (y % 400 == 0) {
			return true;
		}
		if (y % 100 == 0) {
			return false;
		}
		return (y % 4 == 0);
	}

	// return the next Date
	public NextDateProblem next() {
		if (isValid(month, day + 1, year)) {
			return new NextDateProblem(month, day + 1, year);
		}
		else if (isValid(month + 1, 1, year)) {
			return new NextDateProblem(month + 1, 1, year);
		}
		else {
			return new NextDateProblem(1, 1, year + 1);
		}
	}

	// is this Date after b?
	public boolean isAfter(NextDateProblem b) {
		return compareTo(b) > 0;
	}

	// is this Date a before b?
	public boolean isBefore(NextDateProblem b) {
		return compareTo(b) < 0;
	}

	// comparison function between two dates
	public int compareTo(NextDateProblem b) {
		if (year != b.year) {
			return year - b.year;
		}
		if (month != b.month) {
			return month - b.month;
		}
		return day - b.day;
	}

	// return a string representation of this date
	public String toString() {
		return month + "/" + day + "/" + year;
	}

	// sample client for testing
	public static void main(String[] args) {
		NextDateProblem today = new NextDateProblem(04,16, 1811);
		System.out.println(today);
		for (int i = 0; i < 1; i++) {
			today = today.next();
			System.out.println(today);
		}

		System.out.println(today.isAfter(today.next()));
		System.out.println(today.isAfter(today));
		System.out.println(today.next().isAfter(today));
	}

}
