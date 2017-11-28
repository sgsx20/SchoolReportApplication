package schoolReport;

import java.util.LinkedList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * @author Shreejesh Shrestha
 *
 *	Group 4 Phase IV
 *
 * 		Team Members:
 *
 * - Shreejesh Shrestha
 * - Adonai Orellana
 * - Ehsan Baig
 *
 */
public class Application {

	/**
	 * Main Method
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		Message one = new Message(001, 002, "message");
		Message two = new Message(003, 004, "message2");

		GradeBook g = new GradeBook(1, 50, 100);
		GradeBook g2 = new GradeBook(10, 80, 50);

		Course c = new Course("IT100", 5, "BS Course", 3, g);
		Course c2 = new Course("IT003", 10, "Test", 10, g2);

		LinkedList<Message> x = new LinkedList<>();
		x.add(one);
		x.add(two);

		LinkedList<Course> k = new LinkedList<>();
		k.add(c);
		k.add(c2);

		Student t = new Student(1, "pass", "Jacob", "Smith", "jsmith@gmail.com", "571-222-1234", x, 10, k);

		FileManager.addNewUserToFile(t);

		Student q = new Student(2, "xx", "Jacob", "Smith", "jsmith@gmail.com", "571-222-1234", x, 10, k);

		FileManager.addNewUserToFile(q);

		Message three = new Message(004, 010, "adminMessage");
		Message four = new Message(006, 100, "adminMessage2");

		LinkedList<Message> messages = new LinkedList<>();
		messages.add(three);
		messages.add(four);

		Administrator A = new Administrator(3, "lol", "Bill", "joe", "b@gmail.com", "321-123-3451", messages);

		FileManager.addNewUserToFile(A);

		Administrator B = new Administrator(4, "test", "asfjk", "xc", "mail.com", "222-111-222", messages);

		FileManager.addNewUserToFile(B);

		LinkedList<Integer> stews = new LinkedList<>();
		stews.add(1);
		stews.add(2);
		Parent aParent = new Parent(3, "pass", "Tom", "Smith", "t.smith@gmu.edu", "703-303-2020", x, stews);
		FileManager.addNewUserToFile(aParent);

		JDialog.setDefaultLookAndFeelDecorated(true);

		String userType = "";
		boolean exit = false;
		do {
			userType = promptForUserType();
			if ((userType == null) || userType.equalsIgnoreCase("Quit")) {
				displayThankYouMessage();
				exit = true;
			} else {
				getLoginCredentials(userType);
			}
		} while (!exit);
	}

	/**
	 * Prompt the user for the type of user to select
	 *
	 * @return the selected user type string
	 */
	public static String promptForUserType() {

		String[] choices = { "Teacher", "Parent", "Student", "Administrator", "Quit" };
		Object result = JOptionPane.showInputDialog(null, "Select User Type", "", JOptionPane.QUESTION_MESSAGE, null,
				choices, choices[0]);

		String userType = (String) result;

		return userType;

	}

	/**
	 * Prompt user for their user ID and password to log in
	 *
	 * @param userType
	 *            - The type of user that is trying to log in
	 */
	public static void getLoginCredentials(String userType) {

		boolean valid = false;
		int attempt = 0;

		do {

			int userID = 0;

			do {
				try {
					userID = Integer.parseInt(JOptionPane.showInputDialog("Enter your User ID"));
					valid = true;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input for User ID. Try again.");
				}

			} while (!valid);

			valid = false;

			String password = "";

			do {

				password = JOptionPane.showInputDialog("Enter your Password");

				if (!password.equalsIgnoreCase("")) {
					valid = true;
				}

			} while (!valid);

			valid = false;

			// Send the credentials for verification in checkCredentials method
			Person verified = FileManager.checkCredentials(userID, password, userType);

			/*
			 * If credentials are valid, an user object is returned. Else, null is returned.
			 * If user exists, proceed. Else, prompt user again for credentials
			 */
			if (verified != null) {
				valid = true;
				showMenu(userID, password, verified);
			} else {
				attempt++;
				JOptionPane.showMessageDialog(null, "Invalid credentials entered. Try again!");
			}

		} while (!valid && (attempt < 3));

		// If the user makes three consecutive login failures, their account is locked
		if (attempt == 3) {
			JOptionPane.showMessageDialog(null,
					"Maximum attempts reached! The Administrator will be notified to unlock your account.");
			displayThankYouMessage();
		}

	}

	/**
	 * Show the menu for the type of user that is logged in
	 *
	 * @param userID
	 *            -> user ID
	 * @param password
	 *            -> Password
	 * @param user
	 *            -> User object
	 */
	public static void showMenu(int userID, String password, Person user) {

		// Depending on the type of user, show that respective user menu
		switch (user.getClass().getSimpleName()) {
		case "Teacher":
			showTeacherMenu(userID, user);
			break;
		case "Student":
			showStudentMenu(userID, user);
			break;
		case "Parent":
			showParentMenu(userID, user);
			break;
		case "Administrator":
			showAdminMenu(userID, user);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Code should never get till here");
		}

	}

	/**
	 * Method displays the student menu
	 *
	 * @param userID
	 *            -> User ID
	 * @param user
	 *            -> The user object
	 */
	public static void showStudentMenu(int userID, Person user) {

		// Cast to student
		Student student = (Student) user;

		boolean keepGoing = true;

		do {

			String[] choices = { "View Grade Report", "View Teacher Hours", "View Messages", "Send Message", "Exit" };

			Object result = JOptionPane.showInputDialog(null, "Select a student functionality", "",
					JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

			String selection = (String) result;

			if (result == null) {
				selection = "Exit";
			}

			// Depending on what functionality is selected, run that case method
			switch (selection) {
			case "View Grade Report":
				viewGradeReport(student);
				break;
			case "View Teacher Hours":
				viewTeacherHours(student);
				break;
			case "View Messages":
				viewMessages(student);
				break;
			case "Send Message":
				sendMessage(userID);
				break;
			case "Exit":
				keepGoing = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Something went wrong");
			}

		} while (keepGoing);

	}

	/**
	 * View the grade report of the student
	 *
	 * @param user
	 *            -> Student object
	 */
	public static void viewGradeReport(Student user) {

		try {
			JOptionPane.showMessageDialog(null, user.viewMyGradeReport());
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	/**
	 * View the teacher office hours for the student
	 *
	 * @param student
	 *            -> Student object
	 */
	public static void viewTeacherHours(Student student) {
		JOptionPane.showMessageDialog(null, FileManager.getTeacherHours(student));
	}

	/**
	 * View messages of the users
	 *
	 * @param user
	 *            -> Type of user whose messages you want to view
	 */
	public static void viewMessages(Person user) {
		if (user instanceof Teacher) {
			JOptionPane.showMessageDialog(null, user.viewMessage());
		} else if (user instanceof Student) {
			JOptionPane.showMessageDialog(null, user.viewMessage());
		} else if (user instanceof Parent) {
			JOptionPane.showMessageDialog(null, user.viewMessage());
		} else if (user instanceof Administrator) {
			JOptionPane.showMessageDialog(null, user.viewMessage());
		}
	}

	/**
	 * Send message method to send a message to another user
	 *
	 * @param senderID
	 *            -> the sender ID
	 */
	public static void sendMessage(int senderID) {

		String[] recipientType = { "Parent", "Student", "Administrator", "Teacher" };

		Object result = JOptionPane.showInputDialog(null, "Select User to send Message to:", "",
				JOptionPane.QUESTION_MESSAGE, null, recipientType, recipientType[0]);

		boolean valid = false;
		int receipientID = 0;

		do {
			try {
				receipientID = Integer
						.parseInt(JOptionPane.showInputDialog("Enter the recipient ID of the recipient:"));
				valid = true;
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		} while (!valid);

		valid = false;
		String message = "";

		do {
			message = JOptionPane.showInputDialog("Enter the message you want to send");

			if (!message.equals("")) {
				valid = true;
			}
		} while (!valid);

		// If all inputs are valid, send it to sendMessage in FileManager
		if (valid) {
			FileManager.sendMessage(senderID, receipientID, message, (String) result);
		}

	}

	/**
	 * The Parent's menu
	 *
	 * @param userId,
	 *            user (Person object)
	 */
	public static void showParentMenu(int userId, Person user) {

		Parent aParent = (Parent) user;
		boolean keepGoing = true;

		do {

			String[] choices = { "View Grade Report", "View Contact Information", "View Messages", "Send Message",
					"Exit" };

			Object result = JOptionPane.showInputDialog(null, "Select a functionality", "",
					JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

			String selection = (String) result;

			if (result == null) {
				selection = "Exit";
			}

			switch (selection) {
			case "View Grade Report":
				viewGradeReport(aParent);
				// viewGradeReport(userID, student);
				break;
			case "View Contact Information":
				System.out.println(selection);
				// viewTeacherHours(student);
				break;
			case "View Messages":
				viewMessages(aParent);
				// viewMessages(student);
				break;
			case "Send Message":
				System.out.println(selection);
				// sendMessage(userID);
				break;
			case "Exit":
				keepGoing = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Something went wrong");
			}

		} while (keepGoing);
	}

	/**
	 * Allows the Parent to view grade report
	 *
	 * @param user
	 *            (Person object)
	 */
	public static void viewGradeReport(Person user) {
		try {
			JOptionPane.showMessageDialog(null, ((Parent) user).viewStudentReport());
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	/**
	 * Allows the Parent to view messages
	 *
	 * @param userId,
	 *            user (Person object)
	 */
	public static void viewMessages(Parent user) {

		try {
			JOptionPane.showMessageDialog(null, user.viewMessage());
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	/**
	 * This method displays all Administrator user functionalities, including
	 * viewing and updating user accounts, create and adding new users into the
	 * records or send and view messages.
	 *
	 * @param -
	 *            sendID: type int, ID of the user logged in and is used as an
	 *            argument for the sendMessage method as a 'sender'.
	 * @param -
	 *            user: type Teacher, reference for Teacher object to populate
	 *            specific properties.
	 */
	private static void showAdminMenu(int senderID, Person user) {
		Administrator admin = (Administrator) user;
		boolean keepGoing = true;
		String lookupType = "";

		// loop until the user decides to exit - prompt user for menu option and conduct
		// actions to finish request
		do {
			String[] choices = { "View Account", "Update Account", "Add New", "Send Message", "View Messages", "Exit" };

			Object result = JOptionPane.showInputDialog(null, "Select an Administrator functionality", "",
					JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

			String selection = (String) result;

			if (result == null) {
				selection = "Exit";
			}

			// switch case for user selection of tasks they wish to execute
			// lookupType stores the type of user account the logged user wishes to interact
			// with
			switch (selection) {
			case "View Account":
				lookupType = promptForUserType();
				viewUser(lookupType);
				break;
			case "Update Account":
				lookupType = promptForUserType();
				updateAccount(lookupType);
				break;
			case "Add New":
				lookupType = promptForUserType();
				createUser(lookupType);
				break;
			case "Send Message":
				sendMessage(senderID);
				break;
			case "View Messages":
				viewMessages(user);
			case "Exit":
				keepGoing = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Something went wrong");
			}
		} while (keepGoing);

	}

	/**
	 * This method adds the additional specific properties of the Teacher class.
	 * Prompts admin for teachers office hours, if the property is set properly, DDC
	 * returns true. Else, an exception may be caught and handled and admin
	 * reprompted to fix the error.
	 *
	 * @param -
	 *            newUser: type Teacher, reference for Teacher object to populate
	 *            specific properties.
	 *
	 */
	public static void populateUserDetails(Teacher newUser) {
		boolean valid = false;
		do {
			try {
				valid = newUser.setOfficeHours(JOptionPane.showInputDialog("Assign teacher's office hours: "));
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} while (!valid);
		valid = false;

		// courses
	}

	/**
	 * This method adds the additional specific properties of the Student class.
	 *
	 * @param -
	 *            newUser: type Student, used to update list of course(s) for the
	 *            student.
	 */
	public static void populateUserDetails(Student newUser) {
		boolean valid = false;
		do {
			try {
				valid = newUser
						.setGradeLevel(Integer.parseInt(JOptionPane.showInputDialog("Enter student grade level: ")));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} while (!valid);

		// courses LL
	}

	/**
	 * This method adds the detail properties of the Parent class.
	 *
	 * @param -
	 *            newUser: type Parent, used to update list of child(ren) for the
	 *            parent.
	 */
	public static void populateUserDetails(Parent newUser) {
		// children LL
	}

	/**
	 * This method adds general user info and adds new user into records.
	 *
	 * @param -
	 *            newUser is of type Person, user object that is to be populated.
	 */
	public static void populateUser(Person newUser) {
		int userID;
		String password;
		boolean valid = false;

		// get a new userID for new user
		userID = FileManager.getNextUserID();
		try {
			newUser.setUserID(userID);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		// get password for new user
		do {
			password = JOptionPane.showInputDialog("Enter the new user's password");
			try {
				valid = newUser.setPassword(password);
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please enter a password, must not be empty!");
			}
			if (!valid) {
				JOptionPane.showMessageDialog(null, "Please enter a valid password");
			}
		} while (!valid);
		valid = false;

		// input user info - will loop until all values are properly set
		do {
			try {
				newUser.setFirstName(JOptionPane.showInputDialog("Enter user's first name."));
				newUser.setLastName(JOptionPane.showInputDialog("Enter user's last name."));
				newUser.setEmailAddress(
						JOptionPane.showInputDialog("Enter user's email address in format: 'user@provider.domain'."));
				newUser.setPhoneNumber(
						JOptionPane.showInputDialog("Enter user's phone number in format: '(xxx)-xxx-xxxx'."));
				valid = true;
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} while (!valid);
		valid = false;

		if (newUser instanceof Parent) {
			populateUserDetails((Parent) newUser);

		} else if ((newUser instanceof Student)) {
			populateUserDetails((Student) newUser);

		} else if ((newUser instanceof Teacher)) {
			populateUserDetails((Teacher) newUser);

		}

		// will use utility class method to append new user to appropriate file
		FileManager.addNewUserToFile(newUser);
	}

	/**
	 * This method will add a new user into records.
	 *
	 * @param -
	 *            lookupType: String holding the type of user to look up
	 */
	public static void createUser(String createType) {
		Person newUser = null;

		if (createType.equalsIgnoreCase("Teacher")) {
			newUser = new Teacher();
		} else if (createType.equalsIgnoreCase("Parent")) {
			newUser = new Parent();
		} else if (createType.equalsIgnoreCase("Student")) {
			newUser = new Student();
		} else if (createType.equalsIgnoreCase("Administrator")) {
			newUser = new Administrator();
		}

		populateUser(newUser);

	}// end createUser method

	/**
	 * This method will update user account field selected.
	 *
	 * @param -
	 *            lookupType: String holding the type of user to look up
	 */
	public static void changeField(String modField, Person modifyUser) {
		if (modField.equalsIgnoreCase("Password")) {
			try {
				modifyUser.setPassword(JOptionPane.showInputDialog("Enter a new password: "));
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else if (modField.equalsIgnoreCase("First Name")) {
			try {
				modifyUser.setFirstName(JOptionPane.showInputDialog("Enter new First Name: "));
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else if (modField.equalsIgnoreCase(JOptionPane.showInputDialog("Last Name"))) {
			try {
				modifyUser.setLastName(JOptionPane.showInputDialog("Enter new Last Name: "));
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else if (modField.equalsIgnoreCase("Phone")) {
			try {
				modifyUser.setPhoneNumber(JOptionPane.showInputDialog("Enter a new phone number: "));
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else if (modField.equalsIgnoreCase("Email")) {
			try {
				modifyUser.setEmailAddress(JOptionPane.showInputDialog("Enter a new email: "));
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	/**
	 * This method will update user account info.
	 *
	 * @param -
	 *            lookupType: String holding the type of user to look up
	 */
	public static void updateAccount(String lookupType) {
		int lookupID;
		lookupID = getUserID();
		boolean found = false;
		Person modifyUser = null;

		try {
			modifyUser = FileManager.getUser(lookupType, lookupID);
			if (modifyUser != null) {
				found = true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		if (found) {
			String[] choices = { "Password", "First Name", "Last Name", "Phone", "Email", "Exit" };

			Object result = JOptionPane.showInputDialog(null, "Which property would you like to change?", "",
					JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

			String selection = (String) result;

			if (result == null) {
				selection = "Exit";
			}

			if (selection != null) {
				changeField(selection, modifyUser);
				JOptionPane.showMessageDialog(null, modifyUser.toString());
			}

			FileManager.updateUser(lookupType, modifyUser);

		}
	} // end updateAccount method

	/**
	 * This method will display user account info.
	 *
	 * @param -
	 *            lookupType: String holding the type of user to look up
	 */
	public static void viewUser(String lookupType) {
		int lookupID;
		lookupID = getUserID();

		Person findUser = FileManager.getUser(lookupType, lookupID);

		JOptionPane.showMessageDialog(null, findUser.toString());
	}

	/**
	 * This method is used to prompt the logged user for the ID of the user they
	 * wish to interact with.
	 *
	 * @param
	 */
	public static int getUserID() {
		int userID = -1;
		boolean valid = false;
		do {
			try {
				userID = Integer.parseInt(JOptionPane.showInputDialog("Enter userID to use!"));
				valid = true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a userID (xxxxx)");
			}
		} while (!valid);

		return userID;
	}

	/**
	 * Method to display the teacher menu
	 *
	 * @param userID
	 */
	public static void showTeacherMenu(int userID, Person user) {

		boolean keepGoing = true;

		do {

			String[] choices = { "Enter grades", "View Contact", "View Messages", "Send Message", "Exit" };

			Object result = JOptionPane.showInputDialog(null, "Select a functionality", "",
					JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

			String selection = (String) result;

			if (result == null) {
				selection = "Exit";
			}

			switch (selection) {
			case "Enter grades":
				break;
			case "View Contact":
				break;
			case "View Messages":
				break;
			case "Send Message":
				sendMessage(userID);
				break;
			case "Exit":
				keepGoing = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Something went wrong");
			}

		} while (keepGoing);
	}

	/**
	 * Method to display a thank you message
	 */
	public static void displayThankYouMessage() {
		JOptionPane.showMessageDialog(null, "Thank you for using the application. Goodbye!");
	}

	/**
	 * Method to display a logged out message
	 */
	public static void loggedOut() {
		JOptionPane.showMessageDialog(null, "You have successfully logged out");
	}

}
