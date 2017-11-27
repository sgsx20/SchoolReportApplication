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
			boolean flag = false;

			while (((hasLine = br.readLine()) != null) && !flag) {

				String line = hasLine;
				String[] split = line.split(",");

				int id = Integer.parseInt(split[0]);
				String pwd = split[1];

				if (userId == id) {

					if (password.equalsIgnoreCase(pwd)) {
						getUser = getUserObject(userType, split);
						flag = true;
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
		BufferedWriter bw = null;

		try {

			File path = new File(filePath);
			FileReader reader = new FileReader(path);
			br = new BufferedReader(reader);

			File tempFile = new File("./src/schoolReport/tempFile.txt");
			FileWriter fw = new FileWriter(tempFile, true);
			bw = new BufferedWriter(fw);

			String hasLine = null;

			while (((hasLine = br.readLine()) != null)) {

				String line = hasLine;

				String[] split = line.split(",");

				int id = Integer.parseInt(split[0]);

				if (receipientID == id) {
					getUser = getUserObject(userType, split);
					getUser.addMessage(new Message(senderID, receipientID, message));
					bw.write(getUser.toString());
					bw.newLine();
				} else {
					bw.write(line);
					bw.newLine();
				}
			}

			br.close();
			bw.close();
			path.delete();
			tempFile.renameTo(path);

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
			bw = new BufferedWriter(new FileWriter(new File(filePath), true));
			bw.write(userToAdd.toString());
			bw.newLine();
			bw.close();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public static String getTeacherHours(Student object) {

		BufferedReader br = null;
		String output = "Teacher Office Hours\n\n";

		try {
			br = new BufferedReader(new FileReader(new File("./src/schoolReport/teacherRecords.txt")));

			String hasLine = null;

			while ((hasLine = br.readLine()) != null) {

				String line = hasLine;
				String[] split = line.split(",");

				int id = Integer.parseInt(split[0]);

				for (int x = 0; x < object.coursesList().size(); x++) {
					if (object.coursesList().get(x).getInstructorID() == id) {
						output += "Course Title: " + " " + object.coursesList().get(x).getCourseTitle() + "\n";
						output += "Office Hours:" + " " + split[7] + "\n" + "\n";
					}
				}

			}

			br.close();

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return output;

	}

	private static Person getUserObject(String userType, String[] recordLine) {

		Person userCreated = null;
		String[] messages = null;
		LinkedList<Message> addMessages = null;

		switch (userType) {
		case "Teacher":
			break;
		case "Student":
			messages = recordLine[6].trim().replace("[", "").replace("]", "").split("--");
			addMessages = new LinkedList<>();
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

			userCreated = new Student(Integer.parseInt(recordLine[0]), recordLine[1], recordLine[2], recordLine[3],
					recordLine[4], recordLine[5], addMessages, Integer.parseInt(recordLine[7]), setCourses);
			break;
		case "Parent":
			messages = recordLine[6].trim().replace("[", "").replace("]", "").split("--");
			addMessages = new LinkedList<>();
			for (int x = 0; x < messages.length; x++) {
				String[] each = messages[x].split("-");
				Message temp = new Message(Integer.parseInt(each[0]), Integer.parseInt(each[1]), each[2]);
				addMessages.add(temp);
			}

			String[] children = recordLine[7].trim().replace("{", "").replace("}", "").split("--");
			LinkedList<Integer> studentList = new LinkedList<>();
			for (int x = 0; x < children.length; x++) {
				studentList.add(Integer.parseInt(children[x]));
			}

			userCreated = new Parent(Integer.parseInt(recordLine[0]), recordLine[1], recordLine[2], recordLine[3],
					recordLine[4], recordLine[5], addMessages, studentList);
			break;
		case "Administrator":
			String[] getAdminMessages = recordLine[6].trim().replace("[", "").replace("]", "").split("--");
			LinkedList<Message> adminMessages = new LinkedList<>();
			for (int x = 0; x < getAdminMessages.length; x++) {
				String[] each = getAdminMessages[x].split("-");
				Message temp = new Message(Integer.parseInt(each[0]), Integer.parseInt(each[1]), each[2]);
				adminMessages.add(temp);
			}

			userCreated = new Administrator(Integer.parseInt(recordLine[0]), recordLine[1], recordLine[2],
					recordLine[3], recordLine[4], recordLine[5], adminMessages);
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

	// ======================================== EB
	// =====================================

	/**
	 * This method will return a user based on the parameters.
	 *
	 * @param -
	 *            userType is of type String, holds the type of object requested.
	 * @param -
	 *            userID is of type int, id of the user requested.
	 */
	public static Person getUser(String userType, int userId) {
		Person getUser = null;
		String filePath = getFilePath(userType);
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
			String hasLine = null;
			while ((hasLine = br.readLine()) != null) {
				String line = hasLine;

				// array to hold record details
				String[] split = line.split(",");

				int id = Integer.parseInt(split[0]);

				if (userId == id) {
					getUser = getUserObject(userType, split);
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

	/**
	 * This method will use the user type and user object to modify user info and
	 * update the user record to reflect the changes.
	 *
	 * @param -
	 *            lookupType is of type String, holds the type of object to modify.
	 * @param -
	 *            userToUpdate is of type Person, it is the object being modified..
	 */
	public static void updateUser(String lookupType, Person userToUpdate) {
		Person getUser = null;
		String filePath = getFilePath(lookupType);
		BufferedReader br = null;
		BufferedWriter bw = null;

		// open usertype file to retrieve record
		// all records are written into temp file which will replace the previous
		// record file
		try {
			File path = new File(filePath);
			FileReader reader = new FileReader(path);
			br = new BufferedReader(reader);

			File tempFile = new File("./src/schoolReport/tempFile.txt");
			FileWriter fw = new FileWriter(tempFile, true);
			bw = new BufferedWriter(fw);

			String hasLine = null;

			while (((hasLine = br.readLine()) != null)) {
				String line = hasLine;
				String[] split = line.split(",");

				int id = Integer.parseInt(split[0]);

				if (userToUpdate.getUserID() == id) {
					getUser = userToUpdate;
					bw.write(getUser.toString());
					bw.newLine();
				} else {
					bw.write(line);
					bw.newLine();
				}
			}

			br.close();
			bw.close();
			path.delete();
			tempFile.renameTo(path);
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}// end updateUser method

	/**
	 * This method will get the most recent ID used by the Administrator class to
	 * create the last user account. It will open the appropriate file to retrieve
	 * the userID and increment to use it for a new account. Then, the new
	 * incremented ID will be stored in the file to be used next time.
	 *
	 * @param
	 */
	public static int getNextUserID() {
		int newID = -1;
		String filePath = "./src/schoolReport/LastUserID.txt";
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			File path = new File(filePath);
			FileReader reader = new FileReader(path);
			br = new BufferedReader(reader);

			String hasLine = null;

			while (((hasLine = br.readLine()) != null)) {
				String line = hasLine;

				newID = Integer.parseInt(line) + 1;
			}
			br.close();

			FileWriter writer = new FileWriter(path, true);
			bw = new BufferedWriter(writer);

			bw.append(Integer.toString(newID));
			bw.close();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return newID;
	}

}
