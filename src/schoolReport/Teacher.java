package schoolReport;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Shreejesh Shrestha
 *
 */
public class Teacher extends Person {

	// constant
	public static final int MAX_CLASSES_TO_TEACH = 4;

	// Instance variables
	private LinkedList<String> courseIDS;
	private String officeHours;

	/**
	 * Default constructor
	 */
	public Teacher() {
		this(0, "", "", "", "", "", "", null);
	}

	/**
	 * Specific Constructor
	 *
	 * @param userID
	 *            -> User ID
	 * @param password
	 *            -> Password
	 * @param firstName
	 *            -> First Name
	 * @param lastName
	 *            -> Last Name
	 * @param emailAddress
	 *            -> Email Address
	 * @param phoneNumber
	 *            -> Phone Number
	 * @param officeHours
	 *            -> Office Hours
	 * @param courseIDS
	 *            -> Course IDs
	 */
	public Teacher(int userID, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber, String officeHours, LinkedList<String> courseIDS) {
		super(userID, password, firstName, lastName, emailAddress, phoneNumber);

		this.officeHours = officeHours;
		if (courseIDS == null) {
			this.courseIDS = new LinkedList<>();
		} else {
			this.courseIDS = new LinkedList<>();
			this.courseIDS.addAll(courseIDS);
		}

	}

	/**
	 * Get the list of course IDs
	 *
	 * @return linked list of Course ID
	 */
	public LinkedList<String> listOfCourseID() {
		return this.courseIDS;
	}

	/**
	 * Method used to add new course to student courseList
	 */
	public void addCourseToList(String courseID) {
		this.courseIDS.add(courseID);
	}

	/**
	 * Get the list of course IDs as a String separated with a delimiter comma
	 *
	 * @return String with course IDs separated by comma
	 */
	public String convertCourseIds() {
		String IdList = "";

		Iterator<String> it = this.listOfCourseID().iterator();

		while (it.hasNext()) {
			if (this.listOfCourseID().size() <= 1) {
				IdList += it.next().toString();
			} else {
				IdList += it.next().toString() + "-";
			}
		}

		return IdList;
	}

	/*
	 * Method to write the Teacher attributes to the file
	 *
	 * @return String of the teacher attributes to write
	 */
	public String writeAttributesToFile() {

		return super.writeAttributesToFile() + this.getOfficeHours() + "," + this.convertCourseIds();
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
	 * String representation of the Teacher
	 *
	 * @return String representation
	 */
	@Override
	public String toString() {
		String output = super.toString() + "\n" + "Office Hours: " + this.officeHours;

		return output;
	}
}
