package schoolReport;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * @author Shreejesh Shrestha
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDialog.setDefaultLookAndFeelDecorated(true);
		promptForUserType();
	}

	public static void promptForUserType() {

		String[] choices = { "Teacher", "Parent", "Student", "Administrator", "Quit" };
		Object result = JOptionPane.showInputDialog(null, "Select User Type", "", JOptionPane.QUESTION_MESSAGE, null,
				choices, choices[0]);

		// Parse the selection a string
		String userType = (String) result;

		// if selection is cancel or quit, display thank you message.
		if ((result == null) || userType.equalsIgnoreCase("Quit")) {
			displayThankYouMessage();
		} else {
			// Parse selection and send to get log in credentials
			userType = (String) result;
			getLoginCredentials(userType);
		}

	}

	public static void getLoginCredentials(String userType) {

		boolean valid = false;
		int attempt = 0;
		boolean back = false;

		do {

			int userId = 0;

			do {
				try {
					userId = Integer.parseInt(JOptionPane.showInputDialog("Enter your User ID"));
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

			boolean verified = FileManager.checkCredsInFile(userId, password, userType);

			if (verified) {
				valid = true;
				back = showMenu(userId, password, userType);
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

	public static boolean showMenu(int userId, String password, String userType) {

		boolean back = false;

		switch (userType) {
		case "Teacher":
			showTeacherMenu(userId);
			back = true;
			break;
		case "Student":
			showStudentMenu(userId);
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

	public static void showStudentMenu(int userId) {

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
				viewGradeReport();
				break;
			case "View Teacher Hours":
				viewTeacherHours();
				break;
			case "View Messages":
				viewMessages();
				break;
			case "Send Message":
				sendMessage(userId);
				break;
			case "Exit":
				keepGoing = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Something went wrong");
			}

		} while (keepGoing);

	}

	public static void viewGradeReport() {

	}

	public static void viewTeacherHours() {

	}

	public static void viewMessages() {

	}

	public static void sendMessage(int senderID) {

		String[] recipientType = { "Parent", "Student", "Administrator", "Teacher" };

		Object result = JOptionPane.showInputDialog(null, "Select User Type", "", JOptionPane.QUESTION_MESSAGE, null,
				recipientType, recipientType[0]);

		boolean valid = false;
		int receipientID = 0;

		do {
			try {
				receipientID = Integer.parseInt(JOptionPane.showInputDialog("Enter your ID:"));
				valid = true;
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		} while (!valid);

		String message = "Message to send";

		if (valid) {
			String userType = (String) result;
			FileManager.writeToFile(senderID, receipientID, message);
		}

	}

	public static void showTeacherMenu(int userId) {

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
				sendMessage(userId);
			case "Exit":
				keepGoing = false;
				// close file and display log out message
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
