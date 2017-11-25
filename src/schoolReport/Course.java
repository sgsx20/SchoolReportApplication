package schoolReport;

public class Course {

	public static final int ZERO = 0;
	public static final int NUM_OF_STUDENTS_MIN = 1;
	public static final int NUM_OF_STUDENTS_MAX = 35;

	private String courseID;
	private int numStudents;
	private String courseTitle;
	private int instructorID;
	private GradeBook grades;

	/**
	 *
	 */
	public Course() {
		this("", 0, "", 0, null);
	}

	/**
	 * @param courseID
	 * @param numStudents
	 * @param courseTitle
	 * @param instructorID
	 * @param grades
	 */
	public Course(String courseID, int numStudents, String courseTitle, int instructorID, GradeBook grades) {
		this.courseID = courseID;
		this.numStudents = numStudents;
		this.courseTitle = courseTitle;
		this.instructorID = instructorID;

		if (grades == null) {
			this.grades = new GradeBook();
		} else {
			this.grades = new GradeBook(grades.getStudentID(), grades.getMidtermGrade(), grades.getFinalGrade());
		}

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
	 * Set the course ID
	 *
	 * @param courseID
	 *            the courseID to set
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
	 * Get number of students
	 *
	 * @return the numStudents
	 */
	public int getNumStudents() {
		return numStudents;
	}

	/**
	 * Set the number of students
	 *
	 * @param numStudents
	 *            the numStudents to set
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
	 * Get the course Title
	 *
	 * @return the courseTitle
	 */
	public String getCourseTitle() {
		return courseTitle;
	}

	/**
	 * Set the course title
	 *
	 * @param courseTitle
	 *            the courseTitle to set
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
	 * Get the instructor ID
	 *
	 * @return the instructorID
	 */
	public int getInstructorID() {
		return instructorID;
	}

	/**
	 * Set the instructor ID
	 *
	 * @param instructorID
	 *            the instructorID to set
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
	 * Get the grades
	 *
	 * @return the gradeBook
	 */
	public GradeBook getGradeBook() {

		GradeBook temp = new GradeBook();

		temp = this.grades;

		return temp;

	}

	/**
	 * Set grades
	 *
	 * @param gradeBook
	 *            the gradeBook to set
	 */
	public boolean setGradeBook(GradeBook gradeBook) {

		if (gradeBook == null) {
			throw new NullPointerException("GradeBook is empty");
		} else {
			this.grades = gradeBook;
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + this.getCourseID() + "-" + this.getNumStudents() + "-" + this.getCourseTitle() + "-"
				+ this.getInstructorID() + "-" + this.getGradeBook() + "]";
	}

}
