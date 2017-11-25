package schoolReport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

/**
 * @author Shreejesh Shrestha
 *
 */
public class FileManager {

	public static Person checkCredentials(int userId, String password, String userType) {

		Person getUser = null;

		String filePath = getFilePath(userType);

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
						getUser = getUserObject(userType, split);
					}

				}

			}

			br.close();

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return getUser;

	}

	public static void sendMessage(int senderID, int receipientID, String message, String userType) {

		Person getUser = null;

		String filePath = getFilePath(userType);

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(new File(filePath)));

			String hasLine = null;

			while ((hasLine = br.readLine()) != null) {

				String line = hasLine;
				String[] split = line.split(",");

				int id = Integer.parseInt(split[0]);
				String pwd = split[1];

				if (receipientID == id) {
					getUser = getUserObject(userType, split);
					getUser.addMessage(new Message(senderID, receipientID, message));
					BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath)));
					System.out.println(getUser.toString());
					bw.write(getUser.toString());
				}
			}

			br.close();

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public static void addNewUserToFile(Object userToAdd) {

		String filePath = "";

		if (userToAdd instanceof Student) {
			filePath = "./src/schoolReport/studentRecords.txt";
		} else if (userToAdd instanceof Administrator) {
			filePath = "./src/schoolReport/administratorRecords.txt";
		} else if (userToAdd instanceof Parent) {
			filePath = "./src/schoolReport/parentRecords.txt";
		} else if (userToAdd instanceof Teacher) {
			filePath = "./src/schoolReport/teacherRecords.txt";
		}

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(new File(filePath)));

			bw.write(userToAdd.toString());

			bw.close();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	private static Person getUserObject(String userType, String[] recordLine) {

		Person userCreated = null;

		switch (userType) {
		case "Teacher":
			break;
		case "Student":
			String[] messages = recordLine[6].trim().replace("[", "").replace("]", "").split("--");
			LinkedList<Message> addMessages = new LinkedList<>();
			for (int x = 0; x < messages.length; x++) {
				String[] each = messages[x].split("-");
				Message temp = new Message(Integer.parseInt(each[0]), Integer.parseInt(each[1]), each[2]);
				addMessages.add(temp);
			}
			String[] courses = recordLine[8].split("--");
			LinkedList<Course> setCourses = new LinkedList<>();
			for (int x = 0; x < courses.length; x++) {
				String[] eachCourse = courses[x].trim().replace("[", "").replace("]", "").split("-");
				Course temp = new Course(eachCourse[0], Integer.parseInt(eachCourse[1]), eachCourse[2],
						Integer.parseInt(eachCourse[3]), new GradeBook(Integer.parseInt(eachCourse[4]),
								Double.parseDouble(eachCourse[5]), Double.parseDouble(eachCourse[6])));
				setCourses.add(temp);
			}

			userCreated = new Student(recordLine[1], recordLine[2], recordLine[3], recordLine[4], recordLine[5],
					addMessages, Integer.parseInt(recordLine[7]), setCourses);
			break;
		case "Parent":
			break;
		case "Administrator":
			String[] getAdminMessages = recordLine[6].trim().replace("[", "").replace("]", "").split("--");
			LinkedList<Message> adminMessages = new LinkedList<>();
			for (int x = 0; x < getAdminMessages.length; x++) {
				String[] each = getAdminMessages[x].split("-");
				Message temp = new Message(Integer.parseInt(each[0]), Integer.parseInt(each[1]), each[2]);
				adminMessages.add(temp);
			}

			userCreated = new Administrator(recordLine[1], recordLine[2], recordLine[3], recordLine[4], recordLine[5],
					adminMessages);
			break;
		}

		return userCreated;
	}

	private static String getFilePath(String userType) {

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

		return filePath;
	}

}
