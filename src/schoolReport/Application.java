package schoolReport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * @author Shreejesh Shrestha
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JDialog.setDefaultLookAndFeelDecorated(true);
		promptForUserType();

	}

	public static void displayThankYouMessage() {
		JOptionPane.showMessageDialog(null, "Thank you for using the application. Goodbye!");
	}

	/**
	 * The method prompts user for a user type selection.
	 *
	 * @return - The type of user that is trying to log in
	 */
	public static void promptForUserType() {

		String[] choices = { "Teacher", "Parent", "Student", "Administrator", "Quit" };

		Object result = JOptionPane.showInputDialog(null, "Select User Type", "", JOptionPane.QUESTION_MESSAGE, null,
				choices, choices[0]);

		String userType = "";

		if ((result == null) || ((String) result).equalsIgnoreCase("Quit")) {
			displayThankYouMessage();
		} else {
			userType = (String) result;
			getLoginCredentials(userType);
		}

	}

	public static void getLoginCredentials(String userType) {
		//
		// JLabel userNameLabel = new JLabel("Enter username here:");
		// JTextField enterUserName = new JTextField();
		// JLabel passwordLabel = new JLabel("Enter password here:");
		// JPasswordField enterPassword = new JPasswordField();
		//
		// Object[] displayToUser = { userNameLabel, enterUserName, passwordLabel,
		// enterPassword };


		int attempt = 0;
		boolean valid = false;

		do {

			int userId = 0;

			do {
				try {
					userId = Integer.parseInt(JOptionPane.showInputDialog("Enter user id"));
					valid = true;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input form");
				}

			} while (!valid);

			valid = false;

			String password = "";

			do {

				password = JOptionPane.showInputDialog("Enter password");

				if (!password.equalsIgnoreCase("")) {
					valid = true;
				}

			} while (!valid);

			valid = false;
			// Ok / Cancel option
			// int enter = JOptionPane.showConfirmDialog(null, displayToUser, "Enter Login
			// Credentials",
			// JOptionPane.OK_CANCEL_OPTION);

			// If ok, do something
			// if (enter == JOptionPane.OK_OPTION) {
			// if (!enterUserName.getText().equals("") &&
			// (enterPassword.getPassword().length != 0)) {
			// System.out.println(enterUserName.getText());
			// valid = true;
			// } else {
			// System.out.println(attempt);
			// attempt++;
			// }
			// } else {
			// promptForUserType();
			// }

			boolean verified = verifyCredentials(userId, password, userType);

			if (verified) {
				valid = true;
				// continue
			} else {
				attempt++;
			}

		} while (!valid && (attempt < 3));

		if (attempt == 3) {
			JOptionPane.showMessageDialog(null, "Maximum attempts reached");
			displayThankYouMessage();
		}

		if (valid) {
			promptForUserType();
		}

	}

	public static void openFile(int userId, String password) {

		String filePath = "./src/schoolReport/teacherRecords.txt";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(new File(filePath)));

			String hasLine = null;

			while ((hasLine = br.readLine()) != null) {

				String line = hasLine;
				String[] split = line.split(",");

				int id = Integer.parseInt(split[0]);
				String pwd = split[1];

				if (userId == id) {

					if (password.equalsIgnoreCase(pwd)) {
						System.out.println("Matched");
					} else {
						System.out.println("Not found");
					}

				}

			}
			br.close();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}


	}

	public static boolean verifyCredentials(int userId, String password, String userType) {

		boolean valid = false;

		switch (userType) {
		case "Teacher":
			openFile(userId, password);
			// Open teacher file
			// valid = verify credentials
			// Call method that shows the respective menu for the user
			// Close file
			// Log out message
			valid = true;
			showTeacherMenu(userId);
			break;
		case "Parent":

		case "Student":
		case "Administrator":
		default:
			JOptionPane.showMessageDialog(null, "Something went wrong");
		}

		return valid;
	}

	public static void showTeacherMenu(int userId) {

		boolean keepGoing = true;

		do {

			String[] choices = { "Enter grades", "View Contact", "View Messages", "Send Message", "Exit" };

			Object result = JOptionPane.showInputDialog(null, "Select a functionality", "", JOptionPane.QUESTION_MESSAGE,
					null, choices, choices[0]);

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
			writeToFile(senderID, receipientID, message);
		}

	}

	public static void writeToFile(int senderID, int receipientID, String message) {

		String filePath = "./src/schoolReport/messages.txt";
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(new File(filePath)));

			String newMessage = senderID + "," + receipientID + "," + message + "\n";
			bw.write(newMessage);

			bw.close();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}



	}

}
