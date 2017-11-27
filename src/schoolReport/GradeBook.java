package schoolReport;

public class GradeBook {

	// Constant
	public static final int ZERO = 0;

	// Instance variables
	private int studentID;
	private double midtermGrade;
	private double finalGrade;

	/**
	 * Default constructor
	 */
	public GradeBook() {
		this(0, 0.0, 0.0);
	}

	/**
	 * Specific constructor
	 *
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
	 * Get the Student ID
	 *
	 * @return the studentID
	 */
	public int getStudentID() {
		return this.studentID;
	}

	/**
	 * Get the midterm grade
	 *
	 * @return the midtermGrade
	 */
	public double getMidtermGrade() {
		return this.midtermGrade;
	}

	/**
	 * Get the final grade
	 *
	 * @return the finalGrade
	 */
	public double getFinalGrade() {
		return this.finalGrade;
	}

	/**
	 * Set the Student ID
	 *
	 * @param studentID
	 *            the studentID to set
	 *
	 * @return true or false
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
	 * Set midterm grade
	 *
	 * @param midtermGrade
	 *            the midtermGrade to set
	 *
	 * @return true or false
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
	 * Set the final grade
	 *
	 * @param finalGrade
	 *
	 * @return true or false
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
	 * Create and return a string representation
	 *
	 * @return string representation
	 */
	@Override
	public String toString() {
		return this.getStudentID() + "-" + this.getMidtermGrade() + "-" + this.getFinalGrade();
	}

}
