package schoolReport;

import java.util.LinkedList;
import java.util.ListIterator;

public class Parent extends Person {

	// instance variables
	private LinkedList<Student> studentList;
	private LinkedList<Integer> studentIds;

	/**
	 * Default constructor
	 */
	public Parent() {
		this(0, "", "", "", "", "", null);
	}

	/**
	 * Specific Constructor with all fields. It will also generate the user ID by
	 * the system.
	 *
	 * @param firstName
	 *            -> First name
	 * @param lastName
	 *            -> Last name
	 * @param emailAddress
	 *            -> Email
	 * @param phoneNumber
	 *            -> Phone number
	 * @param messages
	 *            -> Messages
	 * @param aStudent
	 *            -> Student
	 * @param aTeacher
	 *            -> Teacher
	 * @param anAdmin
	 *            -> Administrator
	 * @param studentIds
	 *            -> List of StudentIDs
	 *
	 */
	public Parent(int userId, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber, LinkedList<Integer> studentIds) {
		super(userId, password, firstName, lastName, emailAddress, phoneNumber);

		if (studentIds == null) {
			   this.studentIds = new LinkedList<>();
			  } else {
			   this.studentIds = new LinkedList<>();
			   this.studentIds.addAll(studentIds);
			  }

	}

	/**
	 * Retrieve the Student List
	 *
	 * @return the sList
	 */
	public LinkedList<Student> getStudentList() {
		LinkedList<Student> sList = new LinkedList<>(this.studentList);
		return sList;
	}

	/**
	 * Retrieve the Student Id's
	 *
	 * @return the sList
	 */
	public LinkedList<Integer> getStudentIds() {
		LinkedList<Integer> sList = new LinkedList<>(this.studentIds);
		return sList;
	}

	/**
	 * Adds the student to the list
	 *
	 * @param aStudent
	 *            (Student object to set)
	 * @return true or false for validation
	 */
	public boolean setStudentList(LinkedList<Student> aStudent) {
		if (aStudent != null) {
			this.studentList = new LinkedList<>();
			this.studentList.addAll(aStudent);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds the student to the list
	 *
	 * @param aStudent
	 *            (Student object to set)
	 * @return true or false for validation
	 */
	public boolean setStudentIds(LinkedList<Integer> aStudent) {
		if (aStudent != null) {
			this.studentIds = new LinkedList<>();
			this.studentIds.addAll(aStudent);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Converts list to a formatted String version of the student list
	 *
	 * @return String studentList
	 */
	public String convertStudentList() {
		String studentList = "";
		ListIterator<Student> listIterator = this.studentList.listIterator();
		while (listIterator.hasNext()) {
			studentList += "{" + listIterator.next() + "}" + "--";
		}

		return studentList;
	}

	/**
	 * Converts list to a formatted String version of the student list
	 *
	 * @return String studentList
	 */
	public String convertStudentIds() {
		String studentList = "";
		ListIterator<Integer> listIterator = this.studentIds.listIterator();
		while (listIterator.hasNext()) {
			studentList += listIterator.next() + "-";
		}
		return studentList;
	}

	/*
	 * Method to write parent attributes to the file
	 *
	 * @return String of the parent attributes
	 */
	public String writeAttributesToFile() {
		return super.writeAttributesToFile() + this.convertStudentIds();
	}

	/**
	 * Create and return string representation of the class
	 *
	 * @return - String representation
	 */
	@Override
	public String toString() {
		String output = "";
		if (this.getStudentIds().isEmpty()) {
			output = super.toString();
		} else {
			output = super.toString() + "\nChildren Count: " + this.getStudentIds().size();
		}
		return output;
	}

}