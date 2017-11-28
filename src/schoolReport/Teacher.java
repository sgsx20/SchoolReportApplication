package schoolReport;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Shreejesh Shresta
 *
 */
public class Teacher extends Person {

	// constant
	public static final int MAX_CLASSES_TO_TEACH = 4;

	// Instance variables
	private Course[] coursesTeaching;
	private String officeHours;

	/**
	 * Default constructor
	 */
	public Teacher() {
		this(0, "", "", "", "", "", null, null, "");
	}

	/**
	 * Specific constructor
	 *
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
	 * Get the courses teacher is teaching
	 *
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
	 * Get office hours
	 *
	 * @return the officeHours
	 */
	public String getOfficeHours() {
		return this.officeHours;
	}

	/**
	 * Set the courses teacher is teaching
	 *
	 * @param coursesTeaching
	 *            the coursesTeaching to set
	 * @return true
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
	 * Set office hours
	 *
	 * @param officeHours
	 *            the officeHours to set
	 * @return true
	 */
	public boolean setOfficeHours(String officeHours) {
		if (officeHours.equalsIgnoreCase("") || (officeHours == null)) {
			throw new NullPointerException("Office hours can't be empty");
		} else {
			this.officeHours = officeHours;
			return true;
		}
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

	@Override
	public String toString() {
		return super.toString() + "," + this.getOfficeHours() + "," + this.getCoursesTeaching();
	}
}
