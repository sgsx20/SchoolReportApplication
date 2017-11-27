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
		this(0, "", "", "", "", "", null, null, "");
	}

	/**
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param phoneNumber
	 * @param messages
	 */
	public Teacher(int userID, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber, LinkedList<Message> messages, Course[] coursesTeaching, String officeHours) {
		super(userID, password, firstName, lastName, emailAddress, phoneNumber, messages);

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
	public boolean setCoursesTeaching(Course[] coursesTeaching) {
		if (coursesTeaching.length < MAX_CLASSES_TO_TEACH) {
			throw new IllegalArgumentException(
					"Number of classes out of valid range. Teacher can only teach 4 courses");
		} else {
			for (int x = 0; x < coursesTeaching.length; x++) {
				this.coursesTeaching[x] = coursesTeaching[x];
			}

			return true;
		}
	}

	/**
	 * @param officeHours
	 *            the officeHours to set
	 */
	public boolean setOfficeHours(String officeHours) {
		if (officeHours.equalsIgnoreCase("") || (officeHours == null)) {
			throw new NullPointerException("Office hours can't be empty");
		} else {
			this.officeHours = officeHours;
			return true;
		}
	}

	@Override
	public void addMessage(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public String viewMessage() {
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
