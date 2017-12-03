package schoolReport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JOptionPane;

/**
 * @author Shreejesh Shrestha
 *
 */
public class FileManager {

	/**
	 * Method to check the credentials of the user that is trying to log in. If
	 * credentials match, an object of that user is returned. If creds don't match,
	 * an object is returned still but it's going to contain the value of null.
	 *
	 * @param userId
	 *            -> User ID
	 * @param password
	 *            -> Password
	 * @param userType
	 *            -> User Type
	 * @return User Object
	 */
	public static Person checkCredentials(int userID, String password, String userType) {

		Person getUser = null;

		// Get the file path for the user type
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

				if (userID == id) {

					if (password.equalsIgnoreCase(pwd)) {

						// If user ID and password match, create user object
						getUser = getUserObject(userType, split);
						flag = true;
					}

				} // user ID

			} // while loop

			br.close();

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return getUser;

	}

	public static LinkedList<Course> getCourseList() {

		LinkedList<Course> courses = new LinkedList<>();

		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(new File("./src/schoolReport/Courses.txt")));

			String hasLine = null;

			while (((hasLine = br.readLine()) != null)) {
				String[] split = hasLine.split(",");

				Course temp = new Course(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]),
						Integer.parseInt(split[4]));
				courses.add(temp);
			}

			br.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Courses file not found!");
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "No Courses to view");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return courses;

	}

	/**
	 * Method to convert the text file record into an object and return it
	 *
	 * @param userType
	 *            -> User type
	 * @param recordLine
	 *            -> Record to tokenize and make an object
	 * @return -> Type of user Object
	 */
	private static Person getUserObject(String userType, String[] recordLine) {

		Person userCreated = null;

		// Depending on user type, create that user and send it back
		switch (userType) {
		case "Teacher":
			LinkedList<String> courseList = new LinkedList<>();

			String[] courseListSplit = recordLine[8].split("-");

			for (int x = 0; x < courseListSplit.length; x++) {
				courseList.add(courseListSplit[x]);
			}

			LinkedList<Integer> teacherStudentIDs = new LinkedList<>();

			String[] teacherStudentIDSplit = recordLine[7].split("-");

			for (int x = 0; x < teacherStudentIDSplit.length; x++) {
				teacherStudentIDs.add(Integer.parseInt(teacherStudentIDSplit[x]));
			}

			userCreated = new Teacher(Integer.parseInt(recordLine[0]), recordLine[1], recordLine[2], recordLine[3],
					recordLine[4], recordLine[5], recordLine[6], teacherStudentIDs, courseList);

			break;
		case "Student":

			LinkedList<String> courseIDs = new LinkedList<>();

			String[] courseIDSplit = recordLine[7].split("-");

			for (int x = 0; x < courseIDSplit.length; x++) {
				courseIDs.add(courseIDSplit[x]);
			}

			userCreated = new Student(Integer.parseInt(recordLine[0]), recordLine[1], recordLine[2], recordLine[3],
					recordLine[4], recordLine[5], Integer.parseInt(recordLine[6]), courseIDs);
			break;
		case "Parent":
			LinkedList<Integer> studentIDs = new LinkedList<>();

			String[] studentIDSplit = recordLine[6].split("-");

			for (int x = 0; x < studentIDSplit.length; x++) {
				studentIDs.add(Integer.parseInt(studentIDSplit[x]));
			}

			userCreated = new Parent(Integer.parseInt(recordLine[0]), recordLine[1], recordLine[2], recordLine[3],
					recordLine[4], recordLine[5], studentIDs);
			break;
		case "Administrator":
			userCreated = new Administrator(Integer.parseInt(recordLine[0]), recordLine[1], recordLine[2],
					recordLine[3], recordLine[4], recordLine[5]);
			break;
		}

		return userCreated;
	}

	public static String viewMessages(int userID) {

		String messages = "";
		boolean found = false;

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File("./src/schoolReport/Messages.txt")));

			String hasLine = null;

			messages = "Your Messages: \n\n";

			while (((hasLine = br.readLine()) != null)) {

				String[] splitMessages = hasLine.split(",");

				for (int x = 0; x < splitMessages.length; x++) {

					String[] eachMessage = splitMessages[x].split("-");

					if (Integer.parseInt(eachMessage[1]) == userID) {
						found = true;
						messages += eachMessage[2] + "\n";
					}
				}
			}

			if (!found) {
				messages = "You have no messages to view";
			}

			br.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Messages file not found!");
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "You have no messages to view");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return messages;
	}

	/**
	 * Method to send a message to another user.
	 *
	 * @param senderID
	 *            -> ID of sender
	 * @param receipientID
	 *            -> Recipient ID
	 * @param message
	 *            -> Message to be added
	 * @param userType
	 *            -> user type
	 */
	public static void sendMessage(int senderID, int receipientID, String message) {

		// Get the path of the file
		String filePath = "./src/schoolReport/Messages.txt";

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
			boolean found = false;

			// Loop through each record in the file
			while (((hasLine = br.readLine()) != null)) {

				String line = hasLine;

				String[] split = line.split("-");

				int id = Integer.parseInt(split[1]);

				if (receipientID == id) {
					bw.write(line + Integer.toString(senderID) + "-" + Integer.toString(receipientID) + "-" + message);
					bw.newLine();
					found = true;
				} else {
					bw.write(line);
					bw.newLine();
				}
			}

			if (!found) {
				bw.write(senderID + "-" + receipientID + "-" + message + ",");
				bw.newLine();
				bw.close();
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

	/**
	 * Method to add a new user to the file
	 *
	 * @param userToAdd
	 *            -> User object to add
	 */
	public static void addNewUserToFile(Person userToAdd) {

		String filePath = "";

		BufferedWriter bw = null;

		try {
			if (userToAdd instanceof Student) {
				filePath = "./src/schoolReport/studentRecords.txt";
				bw = new BufferedWriter(new FileWriter(new File(filePath), true));
				bw.write(userToAdd.writeAttributesToFile());
				bw.newLine();
				bw.close();
			} else if (userToAdd instanceof Administrator) {
				filePath = "./src/schoolReport/administratorRecords.txt";
				bw = new BufferedWriter(new FileWriter(new File(filePath), true));
				bw.write(userToAdd.writeAttributesToFile());
				bw.newLine();
				bw.close();
			} else if (userToAdd instanceof Parent) {
				filePath = "./src/schoolReport/parentRecords.txt";
				bw = new BufferedWriter(new FileWriter(new File(filePath), true));
				bw.write(userToAdd.writeAttributesToFile());
				bw.newLine();
				bw.close();
			} else if (userToAdd instanceof Teacher) {
				filePath = "./src/schoolReport/teacherRecords.txt";
				bw = new BufferedWriter(new FileWriter(new File(filePath), true));
				bw.write(userToAdd.writeAttributesToFile());
				bw.newLine();
				bw.close();
			}

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	/**
	 * Method to get the teacher hours for the student
	 *
	 * @param object
	 *            -> Student object
	 * @return string output of the teacher hours
	 */
	public static String getTeacherHours(int userID) {

		BufferedReader br = null;
		String output = "Teacher Office Hours\n\n";

		try {
			br = new BufferedReader(new FileReader(new File("./src/schoolReport/teacherRecords.txt")));

			String hasLine = null;

			while ((hasLine = br.readLine()) != null) {

				String line = hasLine;
				String[] split = line.split(",");

				int id = Integer.parseInt(split[0]);

				if (userID == id) {
					output += "Office Hours: " + split[6];
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

	/**
	 * Get the file path
	 *
	 * @param userType
	 *            -> User type
	 * @return the file path
	 */
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

	public static void enterGrades(String courseID, String studentID, int midtermGrade, int finalGrade) {

		BufferedReader br = null;
		BufferedWriter bw = null;

		try {

			File path = new File("./src/schoolReport/GradeBook.txt");
			FileReader reader = new FileReader(path);
			br = new BufferedReader(reader);

			File tempFile = new File("./src/schoolReport/gradeBookTempFile.txt");
			FileWriter fw = new FileWriter(tempFile, true);
			bw = new BufferedWriter(fw);

			String hasLine = null;

			while (((hasLine = br.readLine()) != null)) {

				String line = hasLine;

				String[] split = line.split(",");
				String[] eachGrade = null;

				if (split[0].equalsIgnoreCase("G" + courseID)) {

					if (split.length <= 1) {
						bw.write(line + studentID + "-" + midtermGrade + "-" + finalGrade + "--");
						bw.newLine();
					} else {
						eachGrade = split[1].trim().split("--");
						Vector<String> grades = new Vector<>();
						for (int x = 0; x < eachGrade.length; x++) {
							grades.add(eachGrade[x].trim() + "--");
						}
						grades.add(studentID.trim() + "-" + midtermGrade + "-" + finalGrade);

						Iterator<String> it = grades.iterator();
						String temp = "";

						while (it.hasNext()) {
							temp += it.next();
						}

						bw.write(split[0] + "," + temp);
						bw.newLine();
					}
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

	public static String viewGradeReport(Student user) {

		String output = "";

		if ((user.listOfCourseID().size() == 0) || (user.listOfCourseID() == null)) {
			throw new NullPointerException("No courses to view grade report");
		} else {

			output += user.getFirstName().substring(0, 1).toUpperCase() + user.getFirstName().substring(1)
					+ "'s Grade Report\n\n";

			for (int x = 0; x < user.listOfCourseID().size(); x++) {
				output += "Course:  " + user.listOfCourseID().get(x) + "\n";
				output += getCourseGrade(user.listOfCourseID().get(x), user.getUserID()) + "\n\n";
			}

		}

		return output;
	}

	public static String getCourseGrade(String courseID, int userID) {
		BufferedReader br = null;
		String gradeBookID = "";
		String output = "";
		boolean found = false;

		try {

			br = new BufferedReader(new FileReader(new File("./src/schoolReport/Courses.txt")));
			String hasLine = null;
			while ((hasLine = br.readLine()) != null) {
				String line = hasLine;

				String[] split = line.split(",");

				String storedID = split[0];

				if (courseID.equalsIgnoreCase(storedID)) {
					gradeBookID = "G" + courseID;
					String readGradeBook = getGradeBookRecords(gradeBookID);

					if (!readGradeBook.equalsIgnoreCase("")) {

						String[] studentRecords = readGradeBook.split("--");

						if (studentRecords.length > 0) {
							for (int x = 0; x < studentRecords.length; x++) {
								String[] eachStudent = studentRecords[x].split("-");

								if (Integer.parseInt(eachStudent[0]) == userID) {
									output += "\n" + "Midterm Grade: " + eachStudent[1] + "\n" + "Final Grade: "
											+ eachStudent[2];

								}
							}
						}

					} else {
						found = true;
					}
				}

			}

			if (found == true) {
				output = "No grades available for this course!";
			}
			br.close();

		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return output;
	}

	public static String getGradeBookRecords(String gradeBookID) {
		BufferedReader br = null;
		String output = "";

		try {

			br = new BufferedReader(new FileReader(new File("./src/schoolReport/GradeBook.txt")));
			String hasLine = null;

			while ((hasLine = br.readLine()) != null) {

				String line = hasLine;

				String[] split = line.split(",");

				String storedGradeBookID = split[0];

				if (gradeBookID.equalsIgnoreCase(storedGradeBookID)) {
					if (split.length > 1) {
						output = split[1];
					} else {
						output = "";
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

}
