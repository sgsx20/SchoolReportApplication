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
	private LinkedList<Course> courses;

	/**
	 * Default constructor
	 */
	public Student() {
		this(0, "", "", "", "", "", null, 0, null);
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
	public Student(int userID, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber, LinkedList<Message> messages, int gradeLevel, LinkedList<Course> courses) {

		super(userID, password, firstName, lastName, emailAddress, phoneNumber, messages);
		this.gradeLevel = gradeLevel;

		if (courses == null) {
			this.courses = new LinkedList<>();
		} else {
			this.courses = new LinkedList<>();
			this.courses.addAll(courses);
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
	 * Retrieves the courses as a string
	 *
	 * @return courses string
	 */
	public String getCourses() {

		String courses = "";

		Iterator<Course> it = this.courses.iterator();

		while (it.hasNext()) {
			if (this.courses.size() <= 1) {
				courses += it.next().toString();
			} else {
				courses += it.next().toString() + "--";
			}
		}

		return courses;

	}

	/**
	 * Retrieve the courses linked list
	 *
	 * @return courses Linked List
	 */
	public LinkedList<Course> coursesList() {
		return this.courses;
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
	 * Set the courses
	 *
	 * @param courses
	 *            linked list
	 * @return true or false
	 */
	public boolean setCourses(LinkedList<Course> courses) {

		if (courses.isEmpty()) {
			throw new NullPointerException("The list of courses is empty!");
		} else {
			this.courses.addAll(courses);
			return true;
		}
	}

	/**
	 * Method to view the grade report for the student of all the courses he or she
	 * is enrolled in
	 *
	 * @return grade report string
	 */
	public String viewMyGradeReport() {

		String output = "";

		// If courses is empty, show error. Else, add to string of grade report
		if ((this.coursesList().size() == 0) || (this.coursesList() == null)) {
			throw new NullPointerException("No courses to view grade report");
		} else {

			output += "Student Grade Report\n\n";

			Iterator<Course> it = this.coursesList().iterator();

			while (it.hasNext()) {
				Course temp = it.next();
				output += "Course Title:" + " " + temp.getCourseTitle() + " | " + "Midterm Grade: "
						+ temp.getGradeBook().getMidtermGrade() + " | " + "Final Grade: "
						+ temp.getGradeBook().getFinalGrade() + "\n";
			}

		}

		return output;

	}

	/*
	 * Add message method to add a message to the linked list of messages
	 */
	@Override
	public void addMessage(Message message) {
		this.getMessagesList().add(message);
	}

	/*
	 * View message to view the messages of the student
	 */
	@Override
	public String viewMessage() {

		if ((this.getMessagesList().size() == 0) || (this.getMessages() == null)) {
			throw new NullPointerException("There are no messages to view. Empty inbox");
		} else {

			String output = "Your Messages:" + "\n" + "\n";

			Iterator<Message> it = this.getMessagesList().iterator();

			while (it.hasNext()) {
				Message temp = it.next();
				output += temp.getMessage() + "\n";
			}

			return output;
		}
	}

	/*
	 * Student string representation
	 *
	 * @return string representation
	 */
	@Override
	public String toString() {

		String output = super.toString() + "," + this.getGradeLevel() + "," + this.getCourses();

		return output;
	}

}
