package schoolReport;

import java.util.LinkedList;

public class Student extends Person {

	// Constants
	public static final int NUM_OF_COURSES = 8;
	public static final int STUDENT_GRADE_MIN = 0;
	public static final int STUDENT_GRADE_MAX = 100;
	public static final int STUDENT_GRADE_LEVEL_MIN = 8;
	public static final int STUDENT_GRADE_LEVEL_MAX = 12;

	// Attributes
	private int gradeLevel;
	private LinkedList<Course> courses;

	/**
	 * Default constructor
	 */
	public Student() {
		this("", "", "", "", null, 0, null);
	}

	/**
	 * Specific Constructor
	 *
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param phoneNumber
	 * @param messages
	 */
	public Student(String firstName, String lastName, String emailAddress, String phoneNumber,
			LinkedList<Message> messages, int gradeLevel, LinkedList<Course> courses) {

		super(firstName, lastName, emailAddress, phoneNumber, messages);
		this.gradeLevel = gradeLevel;

		if (courses == null) {
			this.courses = new LinkedList<>();
		} else {
			this.courses = new LinkedList<>();
			this.courses.addAll(courses);
		} // else closing bracket
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
	 * Set grade level of student
	 *
	 * @param gradeLevel
	 *            the gradeLevel to set
	 */
	public boolean setGradeLevel(int gradeLevel) {
		if ((gradeLevel < STUDENT_GRADE_LEVEL_MIN) || (gradeLevel > STUDENT_GRADE_LEVEL_MAX)) {
			return false;
		} else {
			this.gradeLevel = gradeLevel;
			return true;
		}
	}

	/**
	 * @return the courses
	 */
	public Object[] getCourses() {

		Object[] temp = this.courses.toArray();

		return temp;

	}

	/**
	 * @param courses
	 *            the courses to set
	 */
	public boolean setCourses(LinkedList<Course> courses) {

		if (courses == null) {
			return false;
		} else {
			this.courses.addAll(courses);
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see applicationPackage.Person#addMessage(applicationPackage.Message)
	 */
	@Override
	public void addMessage(Message message) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see applicationPackage.Person#sendMessage(java.lang.String)
	 */
	@Override
	public boolean sendMessage(String userType) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see applicationPackage.Person#viewMessage()
	 */
	@Override
	public void viewMessage() {
		// TODO Auto-generated method stub

	}

}
