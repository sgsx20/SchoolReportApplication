package schoolReport;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Shreejesh Shresta
 *
 */
public class Teacher extends Person {

	// constant
	public static final int MAX_CLASSES_TO_TEACH = 4;

	// Instance variables
	private LinkedList<Integer> studentIds;
	private LinkedList<String> courseIDS;
	private String officeHours;

	/**
	 * Default constructor
	 */
	public Teacher() {
		this(0, "", "", "", "", "", "", null, null);
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
			String phoneNumber, String officeHours, LinkedList<Integer> studentIds, LinkedList<String> courseIDS) {
		super(userID, password, firstName, lastName, emailAddress, phoneNumber);

		this.officeHours = officeHours;
		if (courseIDS == null) {
			this.courseIDS = new LinkedList<>();
		} else {
			this.courseIDS = new LinkedList<>();
			this.courseIDS.addAll(courseIDS);
		}

		if (!studentIds.isEmpty()) {
			this.studentIds = new LinkedList<>();
			this.studentIds.addAll(studentIds);
		} else {
			throw new IllegalArgumentException("Parent was not created.");
		}

	}

	public LinkedList<String> listOfCourseID() {
		return this.courseIDS;
	}

	public LinkedList<Integer> getStudentIds() {
		LinkedList<Integer> sList = new LinkedList<>(this.studentIds);
		return sList;
	}

	public LinkedList<String> convertStudentIDtoString() {
		LinkedList<String> convertedStudentIDs = new LinkedList<>();

		Iterator it = this.getStudentIds().iterator();

		while (it.hasNext()) {
			String temp = it.next().toString();
			convertedStudentIDs.add(temp);
		}

		return convertedStudentIDs;
	}

	public String convertStudentIds() {
		String studentList = "";
		ListIterator<Integer> listIterator = this.studentIds.listIterator();
		while (listIterator.hasNext()) {
			studentList += listIterator.next() + "-";
		}
		return studentList;
	}

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

	public String writeAttributesToFile() {

		return super.writeAttributesToFile() + this.getOfficeHours() + "," + this.convertStudentIds() + ","
				+ this.convertCourseIds();
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

	@Override
	public String toString() {
		String output = super.toString() + "\n" + "Office Hours: " + this.officeHours;

		return output;
	}
}
