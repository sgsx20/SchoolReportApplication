package schoolReport;

import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*Group 4 Phase V
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
		JDialog.setDefaultLookAndFeelDecorated(true);

		String userType = "";
		boolean exit = false;
		do {

			// Prompt the user for the type of user trying to log in
			userType = promptForUserType();

			// Continue prompting until user hits cancel or quit
			if ((userType == null) || userType.equalsIgnoreCase("Quit")) {
				displayThankYouMessage();
				exit = true;
			} else {
				getLoginCredentials(userType);
			}
		} while (!exit);
	}

	/**
	 * Prompt the user for the type of user to login
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
		boolean exit = false;

		int attempt = 0;

		do {

			String input = "";
			int userID = 0;
			String password = "";

			// Prompt for user id
			input = JOptionPane.showInputDialog("Enter your User ID");

			// If user clicks cancel, exit.
			if (input == null) {
				exit = true;
				break;
			} else {
				try {
					userID = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input for User ID. Try again.");
					input = JOptionPane.showInputDialog("Enter your User ID");
					if (input == null) {
						exit = true;
						break;
					}
				}
			}

			// Prompt for password
			password = JOptionPane.showInputDialog("Enter your Password");

			if (password == null) {
				exit = true;
				break;
			} else if (!password.equalsIgnoreCase("")) {
				valid = true;
			}

			if (valid) {

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

			}

		} while (!valid && (attempt < 3) && !exit);

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

			// Depending on what functionality is selected, run that method
			switch (selection) {
			case "View Grade Report":
				try {
					JOptionPane.showMessageDialog(null, FileManager.viewGradeReport(student));
				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				break;
			case "View Teacher Hours":
				int ID = getUserID();
				JOptionPane.showMessageDialog(null, FileManager.getTeacherHours(ID));
				break;
			case "View Messages":
				JOptionPane.showMessageDialog(null, FileManager.viewMessages(userID));
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
	 * Send message method to send a message to another user
	 *
	 * @param senderID
	 *            -> the sender ID
	 */
	public static void sendMessage(int senderID) {

		// Display and select a user to send message to
		String[] recipientType = { "Parent", "Student", "Administrator", "Teacher" };

		Object result = JOptionPane.showInputDialog(null, "Select User to send Message to:", "",
				JOptionPane.QUESTION_MESSAGE, null, recipientType, recipientType[0]);

		boolean valid = false;
		int receipientID = 0;

		// Prompt for the ID of the recipient ID
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

		// Prompt user to enter the message to send
		do {
			message = JOptionPane.showInputDialog("Enter the message you want to send");

			if (!message.equals("")) {
				valid = true;
			}
		} while (!valid);

		// If all inputs are valid, send it to sendMessage in FileManager to send
		// message
		if (valid) {
			FileManager.sendMessage(senderID, receipientID, message);
		}

	}

	/**
	 * The Parent's menu
	 *
	 * @param userId,
	 *            user (Person object)
	 */
	public static void showParentMenu(int userID, Person user) {

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
				break;
			case "View Contact Information":
				String lookupType = promptForUserType();
				viewContactInfo(lookupType);
				break;
			case "View Messages":
				JOptionPane.showMessageDialog(null, FileManager.viewMessages(userID));
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
	 * Allows the Parent to view grade report
	 *
	 * @param user
	 *            (Person object)
	 */
	public static void viewGradeReport(Person user) {

		String output = "";

		if (((Parent) user).getStudentIds().size() != 0) {

			try {

				LinkedList<Integer> ids = ((Parent) user).getStudentIds();
				LinkedList<Student> stu = new LinkedList<>();
				ListIterator<Integer> listIterator = ids.listIterator();

				while (listIterator.hasNext()) {
					int id = listIterator.next();
					Student temp = (Student) FileManager.getUser("Student", id);
					stu.add(temp);
				}

				((Parent) user).setStudentList(stu);

				for (int x = 0; x < stu.size(); x++) {
					output += FileManager.viewGradeReport(stu.get(x));
				}
			} catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}

			JOptionPane.showMessageDialog(null, output);
		} else {
			JOptionPane.showMessageDialog(null, "No Student report to display");
		}
	}

	/**
	 * This method will display user contact info.
	 *
	 * @param -
	 *            lookupType: String holding the type of user to look up
	 */
	public static void viewContactInfo(String lookupType) {
		int lookupID;
		lookupID = getUserID();

		Person findUser = FileManager.getUser(lookupType, lookupID);

		String output = "";

		if (findUser != null) {
			output = "Contact Information: \n\n" + "Name: " + findUser.getFirstName().substring(0, 1)
					+ findUser.getFirstName().substring(1) + " " + findUser.getLastName() + "\n" + "Email: "
					+ findUser.getEmailAddress() + "\n" + "Phone Number: " + findUser.getPhoneNumber();
		} else {
			output = "User was not found.";
		}

		JOptionPane.showMessageDialog(null, output);
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

		Teacher aTeacher = (Teacher) user;
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
				enterGrades(aTeacher, userID);
				break;
			case "View Contact":
				String lookupType = promptForUserType();
				viewContactInfo(lookupType);
				break;
			case "View Messages":
				JOptionPane.showMessageDialog(null, FileManager.viewMessages(userID));
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
	 * Enter grades method allows the teacher to enter student grades for a course.
	 * The method prompts the user to select a course, then if the course is found,
	 * it prompts the user for the student's ID, midterm, and final grades and
	 * enters it in the gradebook.
	 *
	 * @param user
	 * @param userID
	 */
	public static void enterGrades(Teacher user, int userID) {

		boolean keepGoing = true;

		do {

			// Prompt for the course
			String[] courseOptions = user.listOfCourseID().toArray(new String[user.listOfCourseID().size()]);

			Object result = JOptionPane.showInputDialog(null,
					"Select a Course to Enter Grade for. \n\n Click cancel when you're done entering grades", "",
					JOptionPane.QUESTION_MESSAGE, null, courseOptions, courseOptions[0]);

			String selection = (String) result;

			if (result == null) {
				selection = "Exit";
				break;
			} else {

				int midtermGrade = 0;
				int finalGrade = 0;
				boolean gradeValid = false;
				String askForStudentID = "";
				int studentID = 0;

				// Prompt for student ID
				do {
					askForStudentID = JOptionPane.showInputDialog("Enter Student ID to enter grades for");

					// If user clicks cancel, exit.
					if (askForStudentID == null) {
						break;
					} else {
						try {
							studentID = Integer.parseInt(askForStudentID);
							gradeValid = true;
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Invalid input for User ID. Try again.");
							askForStudentID = JOptionPane.showInputDialog("Enter your User ID");
							if (askForStudentID == null) {
								break;
							}
						}
					}

					// If user ID is valid, continue
					if (gradeValid) {

						gradeValid = false;

						// Prompt for midterm and final grade
						try {
							midtermGrade = Integer.parseInt(JOptionPane.showInputDialog("Enter Midterm Grade:"));
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Midterm Grade can't be empty!");
						}

						if ((midtermGrade < Student.STUDENT_GRADE_MIN) || (midtermGrade > Student.STUDENT_GRADE_MAX)) {
							JOptionPane.showMessageDialog(null, "Midterm grade out of valid range");
						} else {
							try {
								finalGrade = Integer.parseInt(JOptionPane.showInputDialog("Enter Final Grade:"));
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(null, "Final Grade can't be empty!");
							}

							if ((finalGrade < Student.STUDENT_GRADE_MIN) || (finalGrade > Student.STUDENT_GRADE_MAX)) {
								JOptionPane.showMessageDialog(null, "Final grade out of valid range");
							} else {
								gradeValid = true;
							}
						}

					}
				} while (!gradeValid);

				// if midterm and final grade is valid, enter in gradebook
				if (gradeValid) {

					FileManager.enterGrades(selection, askForStudentID, midtermGrade, finalGrade);
				}

			}

		} while (!keepGoing);

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
	private static void showAdminMenu(int userID, Person user) {
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

			/*
			 * switch case for user selection of tasks they wish to execute 
			 * lookupType stores the type of user account the logged user wishes to interact
			 * with
			 */
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
				sendMessage(userID);
				break;
			case "View Messages":
				JOptionPane.showMessageDialog(null, FileManager.viewMessages(userID));
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
		
		// loop until a valid input is received for teacher's office hours
		do {
			try {
				valid = newUser.setOfficeHours(JOptionPane.showInputDialog("Assign teacher's office hours - Format: Day(time - time)"));
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} while (!valid);
		valid = false;
		
		// course selection process, teacher user may be assigned up to 4 courses to instruct
		// parse course file and return objects
		LinkedList<Course> coursesOffered = new LinkedList<>();
		coursesOffered = FileManager.getCourseList();

		// get list of courses offered
		String[] options = new String[coursesOffered.size() + 1];

		// fill options array
		for (int x = 0; x < (options.length - 1); x++) {
			options[x] = coursesOffered.get(x).getCourseID();
		}
		
		// add EXIT as last option
		options[options.length - 1] = "Exit";

		// variable to control loop structure
		boolean addMore = true;
		
		// number of courses that the teacher is assigned to
		int courseCount = 0;

		// continue prompting for course selection until the user is done
		// or max selections is reached
		do {
			Object result = JOptionPane.showInputDialog(null, "Select Course", "", JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);

			String courseSelected = (String) result;
			int index = 0;
			
			// if user select "Exit" - terminate functionality
			if (courseSelected.equals("Exit")) {
				addMore = false;
			} else {
				// check if "Cancel" was hit
				if ((courseSelected != null) && (addMore)) {
					// get index of the selection
					index = 0;
					boolean found = false;
					
					do {
						if (coursesOffered.get(index).getCourseID().equals(courseSelected)) {
							found = true;
						} else {
							index++;
						}
					} while (!found && (index < coursesOffered.size()));

					if (found) {
						// teacher to Course
						coursesOffered.get(index).setInstructorID(newUser.getUserID());
						newUser.addCourseToList(courseSelected);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Done adding courses!");
				}
			}

		} while (addMore && (courseCount < 4));
		
		// write updated course info to file
		FileManager.updateCourseFile(coursesOffered);

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
		GradeBook getBook = new GradeBook();
		
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

		// Course selection
		// parse course file and return objects
		LinkedList<Course> coursesOffered = new LinkedList<>();
		coursesOffered = FileManager.getCourseList();

		// get list of courses offered
		String[] options = new String[coursesOffered.size() + 1];

		// fill options array
		for (int x = 0; x < (options.length - 1); x++) {
			options[x] = coursesOffered.get(x).getCourseID();
		}
		options[options.length - 1] = "Exit";

		boolean addMore = true;
		int courseCount = 0;

		// continue prompting for course selection until the user is done
		// or max selections is reached
		do {
			Object result = JOptionPane.showInputDialog(null, "Select Course", "", JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);

			String courseSelected = (String) result;
			int index = 0;

			// if user select "Exit" - terminate functionality
			if (courseSelected.equals("Exit")) {
				addMore = false;
			} else {
				// check if "Cancel" was hit
				if ((courseSelected != null) && (addMore)) {
					// get index of the selection
					index = 0;
					boolean found = false;
					do {
						if (coursesOffered.get(index).getCourseID().equals(courseSelected)) {
							found = true;
						} else {
							index++;
						}
					} while (!found && (index < coursesOffered.size()));

					if (found) {
						int courseSize = coursesOffered.get(index).getNumStudents();

						if (courseSize < Course.NUM_OF_STUDENTS_MAX) {

							// add student to gradebook
							String gBookID = coursesOffered.get(index).getGradeBookID();
							
							getBook = FileManager.getGradeBook(gBookID);

							if (getBook != null) {
								boolean addedStudent = getBook.addStudent(newUser.getUserID());
								if (addedStudent) {
									// increment courseSize++ and update
									courseCount++;
									courseSize++;
									coursesOffered.get(index).setNumStudents(courseSize);
									
									newUser.addCourseToList(courseSelected);

								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Sorry, course is full!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Done adding courses!");
				}
			}

		} while (addMore && (courseCount < 8));
		
		// rewrite GradeBook record
		FileManager.updateGradeBookFile(getBook);

		// rewrite Course record
		FileManager.updateCourseFile(coursesOffered);
		
	}

	/**
	 * This method adds the detail properties of the Parent class. 
	 * These include general user properties as well as the list of children for the parent
	 * @param - newUser: type Parent, used to update list of child(ren) for the
	 * parent.
	 */
	public static void populateUserDetails(Parent newUser) {
		LinkedList<Integer> childrenIDs = new LinkedList<>();
		boolean valid = false;
		int numChildren = 0;
		int userID = 0;
		do {
			try {
				numChildren = Integer.parseInt(JOptionPane.showInputDialog("Enter # of children to add: "));
				valid = true;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid # ");
			}
		} while (!valid);
		valid = false;

		if (numChildren > 0) {
			for (int x = 0; x < numChildren; x++) {
				do {
					try {
						userID = Integer.parseInt(JOptionPane.showInputDialog("Enter child's userID: "));
						valid = true;
						childrenIDs.add(userID);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Please enter a valid ID (####) ");
					}
				} while (!valid);
				valid = false;
			}

			newUser.setStudentIds(childrenIDs);
		}

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
			catch(IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
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
				valid = true;
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} while (!valid);
		valid = false;
		
		
		do {
			try {
				newUser.setLastName(JOptionPane.showInputDialog("Enter user's last name."));
				valid = true;
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} while (!valid);
		valid = false;
		
		
		do {
			try {
				newUser.setEmailAddress(
						JOptionPane.showInputDialog("Enter user's email address in format: 'user@provider.domain'."));
				valid = true;
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} while (!valid);
		valid = false;
		
		
		do {
			try {
				newUser.setPhoneNumber(
						JOptionPane.showInputDialog("Enter user's phone number in format: '(xxx)-xxx-xxxx'."));
				valid = true;
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} while (!valid);
		valid = false;
		
		/*
		 * Based on the class instance of the object further properties will be filled
		 */

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
		} else if (modField.equalsIgnoreCase("Last Name")) {
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

			if (!selection.equals("Exit")) {
				changeField(selection, modifyUser);
				JOptionPane.showMessageDialog(null, modifyUser.toString());
				FileManager.updateUser(lookupType, modifyUser);
			}

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
