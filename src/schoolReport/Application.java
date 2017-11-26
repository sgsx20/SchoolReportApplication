package schoolReport;

import java.util.LinkedList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * @author Shreejesh Shrestha
 *
 */
public class Application {

	public static void main(String[] args) throws ClassNotFoundException {

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

		Person.incrementUserID();
		Student t = new Student("pass", "Jacob", "Smith", "jsmith@gmail.com", "571-222-1234", x, 10, k);

		FileManager.addNewUserToFile(t);

		Person.incrementUserID();

		Student q = new Student("xx", "Jacob", "Smith", "jsmith@gmail.com", "571-222-1234", x, 10, k);

		FileManager.addNewUserToFile(q);

		Message three = new Message(004, 010, "adminMessage");
		Message four = new Message(006, 100, "adminMessage2");

		LinkedList<Message> messages = new LinkedList<>();
		messages.add(three);
		messages.add(four);

		Person.incrementUserID();
		Administrator A = new Administrator("lol", "Bill", "joe", "b@gmail.com", "321-123-3451", messages);

		FileManager.addNewUserToFile(A);

		Person.incrementUserID();
		Administrator B = new Administrator("test", "asfjk", "xc", "mail.com", "222-111-222", messages);

		FileManager.addNewUserToFile(B);

		// Delete code above later
		JDialog.setDefaultLookAndFeelDecorated(true);
		promptForUserType();
	}

	public static void promptForUserType() {

		String[] choices = { "Teacher", "Parent", "Student", "Administrator", "Quit" };
		Object result = JOptionPane.showInputDialog(null, "Select User Type", "", JOptionPane.QUESTION_MESSAGE, null,
				choices, choices[0]);

		String userType = (String) result;

		if ((result == null) || userType.equalsIgnoreCase("Quit")) {
			displayThankYouMessage();
		} else {
			userType = (String) result;
			getLoginCredentials(userType);
		}

	}

	public static void getLoginCredentials(String userType) {

		boolean valid = false;
		int attempt = 0;
		boolean back = false;

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

			Person verified = FileManager.checkCredentials(userID, password, userType);

			if (verified != null) {
				valid = true;
				back = showMenu(userID, password, verified);
			} else {
				attempt++;
				JOptionPane.showMessageDialog(null, "Invalid credentials entered. Try again!");
			}

		} while (!valid && (attempt < 3));

		if (attempt == 3) {
			JOptionPane.showMessageDialog(null,
					"Maximum attempts reached! The Administrator will be notified to unlock your account.");
			displayThankYouMessage();
		}

		if (back) {
			promptForUserType();
		}

	}

	public static boolean showMenu(int userID, String password, Person user) {

		boolean back = false;

		switch (user.getClass().getSimpleName()) {
		case "Teacher":
			showTeacherMenu(userID);
			back = true;
			break;
		case "Student":
			showStudentMenu(userID, user);
			back = true;
			break;
		case "Parent":
			back = true;
			break;
		case "Administrator":
			back = true;
			break;
		default:
			JOptionPane.showMessageDialog(null, "Code should never get till here");
		}

		if (back) {
			loggedOut();
		}

		return back;

	}

	public static void showStudentMenu(int userID, Person user) {

		Student student = (Student) user;
		boolean keepGoing = true;

		do {

			String[] choices = { "View Grade Report", "View Teacher Hours", "View Messages", "Send Message", "Exit" };

			Object result = JOptionPane.showInputDialog(null, "Select a functionality", "",
					JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

			String selection = (String) result;

			if (result == null) {
				selection = "Exit";
			}

			switch (selection) {
			case "View Grade Report":
				viewGradeReport(userID, student);
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

	public static void viewGradeReport(int userID, Student user) {

		boolean valid = false;
		boolean cancel = false;
		String midtermGrade = "";
		String finalGrade = "";

		do {
			String enterCourseID = JOptionPane
					.showInputDialog("Enter Course ID for the course whose grade you want to view:");

			if (enterCourseID == null) {
				cancel = true;
			} else if (enterCourseID.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Course ID cannot be empty! Try again");
			} else {
				String[] courses = user.getCourses().trim().replace("[", "").replace("]", "").split("--");

				int x = 0;

				while ((x < courses.length) && !valid) {
					String[] course = courses[x].split("-");
					if (course[0].equalsIgnoreCase(enterCourseID)) {
						if (Integer.parseInt(course[4]) == userID) {
							midtermGrade = course[5];
							finalGrade = course[6];
							valid = true;
						}
					} else {
						x++;
					}
				}

				if (!valid) {
					JOptionPane.showMessageDialog(null,
							"The Course ID you entered is not a valid course you're enrolled into. Try again, please.");
				}
			}

		} while (!valid && !cancel);

		if (valid) {
			JOptionPane.showMessageDialog(null,
					"Your Midterm Grade is: " + " " + midtermGrade + "\n" + "Your Final Grade is: " + " " + finalGrade);
		}

	}

	public static void viewTeacherHours(Student student) {
		String prompt = "";

	}

	public static void viewMessages(Student user) {
		JOptionPane.showMessageDialog(null, user.viewMessage());
	}

	public static void sendMessage(int senderID) {

		String[] recipientType = { "Parent", "Student", "Administrator", "Teacher" };

		Object result = JOptionPane.showInputDialog(null, "Select User Type", "", JOptionPane.QUESTION_MESSAGE, null,
				recipientType, recipientType[0]);

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

		if (valid) {
			FileManager.sendMessage(senderID, receipientID, message, (String) result);
		}

	}

	public static void showTeacherMenu(int userID) {

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

			case "View Messages":
			case "Send Message":
				sendMessage(userID);
			case "Exit":
				keepGoing = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Something went wrong");
			}

		} while (keepGoing);
	}

	public static void displayThankYouMessage() {
		JOptionPane.showMessageDialog(null, "Thank you for using the application. Goodbye!");
	}

	public static void loggedOut() {
		JOptionPane.showMessageDialog(null, "You have successfully logged out");
	}

}
