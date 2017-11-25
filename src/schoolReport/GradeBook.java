package schoolReport;

public class GradeBook {

	public static final int ZERO = 0;

	private int studentID;
	private double midtermGrade;
	private double finalGrade;

	public GradeBook() {
		this(0, 0.0, 0.0);
	}

	/**
	 * @param studentID
	 * @param midtermGrade
	 * @param finalGrade
	 */
	public GradeBook(int studentID, double midtermGrade, double finalGrade) {
		this.studentID = studentID;
		this.midtermGrade = midtermGrade;
		this.finalGrade = finalGrade;
	}

	/**
	 * @return the studentID
	 */
	public int getStudentID() {
		return this.studentID;
	}

	/**
	 * @param studentID
	 *            the studentID to set
	 */
	public boolean setStudentID(int studentID) {
		if (studentID <= ZERO) {
			throw new IllegalArgumentException("Student ID cannot be less than 0");
		} else {
			this.studentID = studentID;
			return true;
		}
	}

	/**
	 * @return the midtermGrade
	 */
	public double getMidtermGrade() {
		return this.midtermGrade;
	}

	/**
	 * @param midtermGrade
	 *            the midtermGrade to set
	 */
	public boolean setMidtermGrade(double midtermGrade) {
		if ((midtermGrade >= Student.STUDENT_GRADE_MIN) && (midtermGrade <= Student.STUDENT_GRADE_MAX)) {
			this.midtermGrade = midtermGrade;
			return true;
		} else {
			throw new IllegalArgumentException("Midterm Grade out of valid range");
		}
	}

	/**
	 * @return the finalGrade
	 */
	public double getFinalGrade() {
		return this.finalGrade;
	}

	/**
	 * @param finalGrade
	 *            the finalGrade to set
	 */
	public boolean setFinalGrade(double finalGrade) {
		if ((finalGrade >= Student.STUDENT_GRADE_MIN) && (finalGrade <= Student.STUDENT_GRADE_MAX)) {
			this.finalGrade = finalGrade;
			return true;
		} else {
			throw new IllegalArgumentException("Final Grade out of valid range");
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getStudentID() + "-" + this.getMidtermGrade() + "-" + this.getFinalGrade();
	}

}
