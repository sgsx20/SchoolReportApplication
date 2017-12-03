package schoolReport;

import java.util.LinkedList;

public class GradeBook {

	// Constant
	public static final int ZERO = 0;

	// Instance variables
	private String gradeBookID;
	private LinkedList<String> studentGrades = new LinkedList<>();

	/**
	 * Default constructor
	 */
	public GradeBook() {
		this("");
	}

	/**
	 * Specific constructor
	 *
	 * @param studentID
	 * @param midtermGrade
	 * @param finalGrade
	 */
	public GradeBook(String gradeBookID) {
		this.gradeBookID = gradeBookID;
	}

	///////////////////////////////////////////////////////////
	//////////////// add student
	///////////////////////////////////////////////////////////
	public boolean addStudent(int studID) {
		String record = "";
		if ((studentGrades.size() < Course.NUM_OF_STUDENTS_MAX) && (studID > 0)) {
			record = "[" + Integer.toString(studID) + ", " + ", " + "]";
			this.studentGrades.add(record);
			return true;
		}

		return false;

	}

	/**
	 * Set the Student ID
	 *
	 * @param studentID
	 *            the studentID to set
	 *
	 * @return true or false
	 */
	// public boolean setStudentID(int studentID) {
	// if (studentID <= ZERO) {
	// throw new IllegalArgumentException("Student ID cannot be less than 0");
	// } else {
	// this.studentID = studentID;
	// return true;
	// }
	// }
	//
	// /**
	// * Set midterm grade
	// *
	// * @param midtermGrade
	// * the midtermGrade to set
	// *
	// * @return true or false
	// */
	// public boolean setMidtermGrade(double midtermGrade) {
	// if ((midtermGrade >= Student.STUDENT_GRADE_MIN) && (midtermGrade <=
	// Student.STUDENT_GRADE_MAX)) {
	// this.midtermGrade = midtermGrade;
	// return true;
	// } else {
	// throw new IllegalArgumentException("Midterm Grade out of valid range");
	// }
	// }
	//
	// /**
	// * Set the final grade
	// *
	// * @param finalGrade
	// *
	// * @return true or false
	// */
	// public boolean setFinalGrade(double finalGrade) {
	// if ((finalGrade >= Student.STUDENT_GRADE_MIN) && (finalGrade <=
	// Student.STUDENT_GRADE_MAX)) {
	// this.finalGrade = finalGrade;
	// return true;
	// } else {
	// throw new IllegalArgumentException("Final Grade out of valid range");
	// }
	// }

	/*
	 * Create and return a string representation
	 *
	 * @return string representation
	 */
	@Override
	public String toString() {
		return this.gradeBookID;
	}

}
