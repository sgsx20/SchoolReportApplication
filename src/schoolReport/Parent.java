package schoolReport;

import java.util.LinkedList;
import java.util.ListIterator;

public class Parent extends Person {

	private LinkedList<Student> studentList;
	private LinkedList<Teacher> teacherList;
	private LinkedList<Administrator> adminList;

	// Constructor*****
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
	 */
	public Parent(int userID, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber, LinkedList<Message> messages, Student aStudent, Teacher aTeacher,
			Administrator anAdmin) {
		super(userID, password, firstName, lastName, emailAddress, phoneNumber, messages);

		if ((aStudent != null) && (aTeacher != null) && (anAdmin != null)) {
			this.studentList.add(aStudent);
			this.teacherList.add(aTeacher);
			this.adminList.add(anAdmin);
		} else {
			throw new IllegalArgumentException("Parent could not be added.");
		}
	}

	// Accessor Methods*****
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
	 * Retrieve the teacher list
	 *
	 * @return the tList
	 */
	public LinkedList<Teacher> getTeacherList() {
		LinkedList<Teacher> tList = new LinkedList<>(this.teacherList);
		return tList;
	}

	/**
	 * Retrieve the adminList
	 *
	 * @return the aList
	 */
	public LinkedList<Administrator> getAdminList() {
		LinkedList<Administrator> aList = new LinkedList<>(this.adminList);
		return aList;
	}

	// Set Methods*****
	/**
	 * Adds the student to the list
	 *
	 * @param aStudent
	 *            (Student object to set)
	 * @return true or false for validation
	 */
	public boolean setStudentList(Student aStudent) {
		if (aStudent != null) {
			this.studentList.add(aStudent);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds the Teacher to the list
	 *
	 * @param aTeacher
	 *            (Teacher object to set)
	 * @return true or false for validation
	 */
	public boolean setTeacherList(Teacher aTeacher) {
		if (aTeacher != null) {
			this.teacherList.add(aTeacher);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds the Admin to the list
	 *
	 * @param anAdmin
	 *            (Administrator object to set)
	 * @return true or false for validation
	 */
	public boolean setAdminList(Administrator anAdmin) {
		if (anAdmin != null) {
			this.adminList.add(anAdmin);
			return true;
		} else {
			return false;
		}
	}

	// Special Methods*****
	public String viewGradeReport() {
		return "";
	}

	public String viewTeacherInformation() {
		return "";
	}

	public String viewAdministratorInformation() {
		return "";
	}

	@Override
	public void addMessage(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public String viewMessage() {
		// TODO Auto-generated method stub
		return "";
	}

	/**
	 * Converts list to a formatted String version of the student list
	 *
	 * @return String studentList
	 */
	public String convertStudentList() {
		String studentList = "================\nParent's Children: ";
		ListIterator<Student> listIterator = this.studentList.listIterator();
		while (listIterator.hasNext()) {
			studentList += listIterator.next();
		}

		studentList += "================";
		return studentList;

	}

	/**
	 * Converts list to a formatted String version of the teacher list
	 *
	 * @return String teacherList
	 */
	public String convertTeacherList() {
		String teacherList = "================\nTeacher List: ";
		ListIterator<Teacher> listIterator = this.teacherList.listIterator();
		while (listIterator.hasNext()) {
			teacherList += listIterator.next();
		}
		teacherList += "================";
		return teacherList;
	}

	/**
	 * Converts list to a formatted String version of the admin list
	 *
	 * @return String adminList
	 */
	public String convertAdminList() {
		String adminList = "================\nAdmin List: ";
		ListIterator<Administrator> listIterator = this.adminList.listIterator();
		while (listIterator.hasNext()) {
			adminList += listIterator.next();
		}
		adminList += "================";
		return adminList;

	}

	// ToString Method*****
	/*
	 * Create and return string representation of the class
	 *
	 * @return - String representation
	 */
	@Override
	public String toString() {
		String output = "Parent*****\n" + super.toString() + "\n" + convertStudentList() + "\n" + convertTeacherList()
				+ "\n" + convertAdminList();
		return output;
	}

}