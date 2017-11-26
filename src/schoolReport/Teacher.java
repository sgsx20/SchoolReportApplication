package schoolReport;

import java.util.LinkedList;

/**
 * @author Shreejesh Shresta
 *
 */
public class Teacher extends Person {

	public static final int MAX_CLASSES_TO_TEACH = 4;

	private Course[] coursesTeaching;
	private String officeHours;

	/**
	 *
	 */
	public Teacher() {
		this("", "", "", "", "", null, null, "");
	}

	/**
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param phoneNumber
	 * @param messages
	 */
	public Teacher(String password, String firstName, String lastName, String emailAddress, String phoneNumber,
			LinkedList<Message> messages, Course[] coursesTeaching, String officeHours) {
		super(password, firstName, lastName, emailAddress, phoneNumber, messages);

		if (coursesTeaching == null) {
			this.coursesTeaching = new Course[MAX_CLASSES_TO_TEACH];
		} else {
			this.coursesTeaching = new Course[coursesTeaching.length];
		}

	}

	/**
	 * @return the coursesTeaching
	 */
	public Course[] getCoursesTeaching() {
		Course[] temp = new Course[this.coursesTeaching.length];

		for (int x = 0; x < temp.length; x++) {
			temp[x] = this.coursesTeaching[x];
		}

		return temp;

	}

	/**
	 * @return the officeHours
	 */
	public String getOfficeHours() {
		return this.officeHours;
	}

	/**
	 * @param coursesTeaching
	 *            the coursesTeaching to set
	 */
	public void setCoursesTeaching(Course[] coursesTeaching) {
		this.coursesTeaching = coursesTeaching;
	}

	/**
	 * @param officeHours
	 *            the officeHours to set
	 */
	public void setOfficeHours(String officeHours) {
		this.officeHours = officeHours;
	}

	@Override
	public void addMessage(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean sendMessage(String userType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String viewMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
