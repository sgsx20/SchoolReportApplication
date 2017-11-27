package schoolReport;

import java.util.Iterator;
import java.util.LinkedList;

public class Student extends Person {

	public static final int NUM_OF_COURSES = 8;
	public static final int STUDENT_GRADE_MIN = 0;
	public static final int STUDENT_GRADE_MAX = 100;
	public static final int STUDENT_GRADE_LEVEL_MIN = 8;
	public static final int STUDENT_GRADE_LEVEL_MAX = 12;

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

	public LinkedList<Course> coursesList() {
		return this.courses;
	}

	public boolean setCourses(LinkedList<Course> courses) {

		if (courses.isEmpty()) {
			throw new NullPointerException("The list of courses is empty!");
		} else {
			this.courses.addAll(courses);
			return true;
		}
	}

	public String viewMyGradeReport() {

		String output = "";

		if (this.getCourses().equalsIgnoreCase("") || (this.getCourses() == null)) {
			throw new NullPointerException("No courses to view grade report");
		} else {

			String[] courses = this.getCourses().trim().replace("[", "").replace("]", "").split("--");

			output += "                                           Your Grade Report\n\n";

			int x = 0;

			while ((x < courses.length)) {
				String[] course = courses[x].split("-");
				output += "Course Title: " + " " + course[2] + " | " + "Midterm Grade: " + course[5] + " | "
						+ "Final Grade: " + course[6] + "\n";
				x++;
			}

		}

		return output;

	}

	@Override
	public void addMessage(Message message) {
		this.getMessagesList().add(message);
	}

	@Override
	public String viewMessage() {

		if (this.getMessages().equalsIgnoreCase("") || (this.getMessages() == null)) {
			throw new NullPointerException("There are no messages to view. Empty inbox");
		} else {

			String output = "Your Messages:" + "\n" + "\n";

			String[] format = this.getMessages().trim().replace("[", "").replace("]", "").split("--");
			String[] getMessage = null;

			for (int x = 0; x < format.length; x++) {
				getMessage = format[x].split("-");
				output += getMessage[2] + "\n";
			}

			return output;
		}
	}

	@Override
	public String toString() {

		String output = super.toString() + "," + this.getGradeLevel() + "," + this.getCourses();

		return output;
	}

}
