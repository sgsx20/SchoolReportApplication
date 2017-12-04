package schoolReport;

import java.util.Iterator;
import java.util.LinkedList;

public class Student extends Person {

	// Constants
	public static final int NUM_OF_COURSES = 8;
	public static final int STUDENT_GRADE_MIN = 0;
	public static final int STUDENT_GRADE_MAX = 100;
	public static final int STUDENT_GRADE_LEVEL_MIN = 8;
	public static final int STUDENT_GRADE_LEVEL_MAX = 12;

	// Instance variables
	private int gradeLevel;
	private LinkedList<String> courseIDS;

	/**
	 * Default constructor
	 */
	public Student() {
		this(0, "", "", "", "", "", 0, null);
	}

	/**
	 * Specific Constructor
	 *
	 * @param userID
	 *            -> User ID
	 * @param password
	 *            -> Password for student
	 * @param firstName
	 *            -> First name of student
	 * @param lastName
	 *            -> Last name of student
	 * @param emailAddress
	 *            -> Email address of student
	 * @param phoneNumber
	 *            -> Phone number of student
	 * @param gradeLevel
	 *            -> Grade level of student
	 * @param courseIDS
	 *            -> Course IDs of the courses for the student
	 */
	public Student(int userID, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber, int gradeLevel, LinkedList<String> courseIDS) {

		super(userID, password, firstName, lastName, emailAddress, phoneNumber);
		this.gradeLevel = gradeLevel;

		if (courseIDS == null) {
			this.courseIDS = new LinkedList<>();
		} else {
			this.courseIDS = new LinkedList<>();
			this.courseIDS.addAll(courseIDS);
		}
	}

	/**
	 * Retrieve grade level of student
	 *
	 * @return the gradeLevel
	 */
	public int getGradeLevel() {
		return this.gradeLevel;
	}

	/**
	 * Retrieve the courses linked list
	 *
	 * @return courses Linked List
	 */
	public LinkedList<String> listOfCourseID() {
		return this.courseIDS;
	}

	/**
	 * Set grade level of student
	 *
	 * @param gradeLevel
	 *            the gradeLevel to set
	 * @throws IllegalArgumentException
	 *             -> Exception if the grade level is not in a valid range
	 */
	public boolean setGradeLevel(int gradeLevel) {
		if ((gradeLevel < STUDENT_GRADE_LEVEL_MIN) || (gradeLevel > STUDENT_GRADE_LEVEL_MAX)) {
			throw new IllegalArgumentException("Invalid Student Grade Level! (8 - 12) ");
		} else {
			this.gradeLevel = gradeLevel;
			return true;
		}
	}

	/**
	 * The method below converts the course IDs into a string that is split by
	 * delimiters. This is then used to write to the student records text file.
	 *
	 * @return String of course ids
	 */
	public String convertCourseIds() {
		String IdList = "";

		Iterator<String> it = this.listOfCourseID().iterator();

		while (it.hasNext()) {

			// if there are less than 1 course Ids, do this. Else, add with a "-" after it.
			if (this.listOfCourseID().size() <= 1) {
				IdList += it.next().toString();
			} else {
				IdList += it.next().toString() + "-";
			}
		}

		return IdList;
	}

	/*
	 * The method concatenates and returns the student attributes to the student
	 * records text file
	 *
	 * @return String of the student information
	 */
	public String writeAttributesToFile() {

		return super.writeAttributesToFile() + this.getGradeLevel() + "," + this.convertCourseIds();
	}

	/*
	 * Student string representation
	 *
	 * @return string representation
	 */
	@Override
	public String toString() {

		String output = super.toString() + "\n" + "Grade: " + this.gradeLevel;

		return output;
	}

}
