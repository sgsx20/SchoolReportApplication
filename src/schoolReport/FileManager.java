package schoolReport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * @author Shreejesh Shrestha
 *
 */
public class FileManager {

	public static boolean checkCredsInFile(int userId, String password, String userType) {

		String filePath = "";
		switch (userType) {
		case "Teacher":
			filePath = "./src/schoolReport/teacherRecords.txt";
			break;
		case "Student":
			filePath = "./src/schoolReport/studentRecords.txt";
			break;
		case "Parent":
			filePath = "./src/schoolReport/parentRecords.txt";
			break;
		case "Administrator":
			filePath = "./src/schoolReport/administratorRecords.txt";
			break;
		}

		BufferedReader br = null;
		boolean exists = false;

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
						exists = true;
					}

				} // if userid closing bracket

			} // while loop closing bracket

			br.close();

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return exists;

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
