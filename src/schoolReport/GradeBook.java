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
	 * Specific Constructor
	 *
	 * @param gradeBookID
	 *            -> GradeBook ID
	 */
	public GradeBook(String gradeBookID) {
		this.gradeBookID = gradeBookID;
	}

	/**
	 * Specific Constructor
	 *
	 * @param gradeBookID
	 *            -> GradeBook ID
	 * @param studentGrades
	 *            -> Student grades linked list
	 */
	public GradeBook(String gradeBookID, LinkedList<String> studentGrades) {
		this.gradeBookID = gradeBookID;
		this.studentGrades = studentGrades;
	}

	/**
	 * Method to add the student to the GradeBook
	 *
	 * @param studID
	 *            -> Student ID
	 * @return true
	 */
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
	 * Get the gradebook ID
	 *
	 * @return gradebook ID
	 */
	public String getGradeBookID() {
		return this.gradeBookID;
	}

	/**
	 * Method to add gradebook info to file
	 *
	 * @return String of gradebook info
	 */
	public String gradeBookToFile() {
		String output = "";
		output += this.gradeBookID + ",";

		if (this.studentGrades != null) {
			for (int x = 0; x < this.studentGrades.size(); x++) {
				if (x == (studentGrades.size() - 1)) {
					output += studentGrades.get(x);
				} else {
					output += studentGrades.get(x) + "--";
				}
			}
		} else {
			output += " ";
		}
		return output;
	}

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
