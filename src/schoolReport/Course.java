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
	private String gradeBookID;

	/**
	 * Default constructor
	 */
	public Course() {
		this("", "", 0, 0, "");
	}

	/**
	 * Specific Constructor
	 *
	 * @param courseID
	 *            -> Course ID
	 * @param courseTitle
	 *            -> Course Title
	 * @param numStudents
	 *            -> Number of students
	 * @param instructorID
	 *            -> Instructor ID
	 * @param gradeBookID
	 *            -> Grade Book ID
	 */
	public Course(String courseID, String courseTitle, int numStudents, int instructorID, String gradeBookID) {
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

	/**
	 * Get the grade book ID
	 *
	 * @return gradeBookID
	 */
	public String getGradeBookID() {
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
	 *            -> Num of students
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

	/**
	 * Set the grade book ID
	 *
	 * @return true
	 */
	public boolean setGradeBookID() {
		if (this.courseID.equals("")) {
			throw new NullPointerException("Course ID does not exist");
		} else {
			this.gradeBookID = "G" + this.courseID;
			return true;
		}
	}

	/**
	 * Method to write the course info to the file
	 *
	 * @return string of course attributes
	 */
	public String writeCourseToFile() {
		String output = "";

		output += this.courseID + "," + this.courseTitle + "," + Integer.toString(this.numStudents) + ","
				+ Integer.toString(this.instructorID) + "," + this.gradeBookID;

		return output;
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