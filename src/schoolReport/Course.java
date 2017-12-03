package schoolReport;

public class Course {

	// Constants
	public static final int ZERO = 0;
	public static final int NUM_OF_STUDENTS_MIN = 1;
	public static final int NUM_OF_STUDENTS_MAX = 35;

	// Instance variables
	private String courseID;
	private String courseTitle;
	private int numStudents;
	private int instructorID;
	private int gradeBookID;

	/**
	 * Default constructor
	 */
	public Course() {
		this("", "", 0, 0, 0);
	}

	/**
	 * SPecific Constructor
	 *
	 * @param courseID
	 * @param numStudents
	 * @param courseTitle
	 * @param instructorID
	 * @param grades
	 */
	public Course(String courseID, String courseTitle, int numStudents, int instructorID, int gradeBookID) {
		this.courseID = courseID;
		this.courseTitle = courseTitle;
		this.numStudents = numStudents;
		this.instructorID = instructorID;
		this.gradeBookID = gradeBookID;
	}

	/**
	 * Get the course ID
	 *
	 * @return the courseID
	 */
	public String getCourseID() {
		return courseID;
	}

	/**
	 * Get number of students
	 *
	 * @return the numStudents
	 */
	public int getNumStudents() {
		return numStudents;
	}

	/**
	 * Get the course Title
	 *
	 * @return the courseTitle
	 */
	public String getCourseTitle() {
		return courseTitle;
	}

	/**
	 * Get the instructor ID
	 *
	 * @return the instructorID
	 */
	public int getInstructorID() {
		return instructorID;
	}

	public int getGradeBookID() {
		return this.gradeBookID;
	}

	/**
	 * Set the course ID
	 *
	 * @param courseID
	 *            - Course ID to set
	 * @return true
	 */
	public boolean setCourseID(String courseID) {
		if (courseID.equalsIgnoreCase("")) {
			throw new NullPointerException("Course ID cannot be mepty");
		} else {
			this.courseID = courseID;
			return true;
		}
	}

	/**
	 * Set the number of students
	 *
	 * @param numStudents
	 * @return true
	 */
	public boolean setNumStudents(int numStudents) {
		if ((numStudents >= Course.NUM_OF_STUDENTS_MIN) && (numStudents <= Course.NUM_OF_STUDENTS_MAX)) {
			this.numStudents = numStudents;
			return true;
		} else {
			throw new IllegalArgumentException("Number of students is out of valid range");
		}
	}

	/**
	 * Set the course title
	 *
	 * @param courseTitle
	 *            the courseTitle to set
	 * @return true
	 */
	public boolean setCourseTitle(String courseTitle) {
		if (courseTitle.equalsIgnoreCase("")) {
			throw new NullPointerException("Course Title cannot be mepty");
		} else {
			this.courseTitle = courseTitle;
			return true;
		}
	}

	/**
	 * Set the instructor ID
	 *
	 * @param instructorID
	 *            the instructorID to set
	 * @return true
	 */
	public boolean setInstructorID(int instructorID) {
		if (instructorID <= ZERO) {
			throw new IllegalArgumentException("Instructor ID cannot be <= 0");
		} else {
			this.instructorID = instructorID;
			return true;
		}
	}

	/*
	 * Create and return string representation of course
	 *
	 * @return string representation of course
	 *
	 */
	@Override
	public String toString() {
		String output = "";
		output += "Course ID: " + this.courseID + "\n" + "Title: " + this.courseTitle + "\n" + "Student Count: "
				+ this.numStudents + "\n" + "Teacher: " + this.instructorID + "\n\n";

		return output;
	}

}