public class Student extends Person {

	// Constants
	public static final int NUM_OF_COURSES = 8;
	public static final int STUDENT_GRADE_MIN = 0;
	public static final int STUDENT_GRADE_MAX = 100;
	public static final int STUDENT_GRADE_LEVEL_MIN = 8;
	public static final int STUDENT_GRADE_LEVEL_MAX = 12;

	// Attributes
	private int gradeLevel;
	private Course[] courses;

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
	public Student(String firstName, String lastName, String emailAddress, String phoneNumber, Message[] messages,
			int gradeLevel, Course[] courses) {

		super(firstName, lastName, emailAddress, phoneNumber, messages);
		this.gradeLevel = gradeLevel;

		if (courses == null) {
			// Change later?
			this.courses = new Course[20];
		} else {
			this.courses = new Course[courses.length];
			for (int x = 0; x < courses.length; x++) {
				this.courses[x] = courses[x];
			}
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
	// public boolean setGradeLevel(int gradeLevel) {
	// if ((gradeLevel < STUDENT_GRADE_LEVEL_MIN) || (gradeLevel >
	// STUDENT_GRADE_LEVEL_MAX)) {
	// return false;
	// } else {
	//
	// }
	// }

	/**
	 * @return the courses
	 */
	public Course[] getCourses() {
		return courses;
	}

	/**
	 * @param courses
	 *            the courses to set
	 */
	public void setCourses(Course[] courses) {
		this.courses = courses;
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
	public Message[] viewMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
