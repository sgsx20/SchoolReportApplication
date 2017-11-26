package schoolReport;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class Person {

	public static final int PHONE_NUM_LENGTH = 10;

	// Person Attributes/ variables.
	public static int userID;
	private String password;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	private LinkedList<Message> messages;

	/**
	 * Default constructor. Constructor calls another constructor with initial
	 * values.
	 */
	public Person() {
		this("", "", "", "", "", null);
	}

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
	 */
	public Person(String password, String firstName, String lastName, String emailAddress, String phoneNumber,
			LinkedList<Message> messages) {
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;

		if (messages == null) {
			this.messages = new LinkedList<>();
		} else {

			this.messages = new LinkedList<>();
			this.messages.addAll(messages);

		}

	}

	public static void incrementUserID() {
		userID++;
	}

	/**
	 * Retrieve the userID
	 *
	 * @return the userID
	 */
	public static int getUserID() {
		return userID;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 * @return
	 */
	public boolean setPassword(String password) {
		if (password.equalsIgnoreCase("")) {
			throw new NullPointerException("Password is empty!");
		} else {
			this.password = password;
			return true;
		}
	}

	/**
	 * Retrieve the first name
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Retrieve the last name
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Retrieve the email address
	 *
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Retrieve the phone number
	 *
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the first name
	 *
	 * @param firstName
	 *            the firstName to set
	 */
	public boolean setFirstName(String firstName) {
		if ((firstName == null) || firstName.equalsIgnoreCase("")) {
			throw new NullPointerException("First name can't be empty!");
		} else {
			this.firstName = firstName;
			return true;
		}
	}

	/**
	 * Set the last name
	 *
	 * @param lastName
	 *            the lastName to set
	 */
	public boolean setLastName(String lastName) {
		if ((lastName == null) || lastName.equalsIgnoreCase("")) {
			throw new NullPointerException("Last name can't be empty!");
		} else {
			this.lastName = lastName;
			return true;
		}
	}

	/**
	 * Set the email address
	 *
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public boolean setEmailAddress(String emailAddress) {

		if ((emailAddress == null) || emailAddress.equalsIgnoreCase("")) {
			throw new NullPointerException("Email address can't be empty!");
		} else {

			boolean atFlag = false;
			boolean dotFlag = false;
			int position = 0;

			while ((position < emailAddress.length()) && !atFlag && !dotFlag) {

				if (emailAddress.charAt(position) == ('@')) {
					atFlag = true;
					position++;
				} else if (emailAddress.charAt(position) == ('.')) {
					dotFlag = true;
					position++;
				} else {
					position++;
				}

			} // while loop closing bracket

			if (atFlag && dotFlag) {
				this.emailAddress = emailAddress;
				return true;
			} else {
				throw new IllegalArgumentException("Email address isn't valid!");
			}

		} // else closing bracket

	}

	/**
	 * Set the phone number
	 *
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public boolean setPhoneNumber(String phoneNumber) {

		if ((phoneNumber == null) || phoneNumber.equalsIgnoreCase("")) {
			throw new NullPointerException("Phone number can't be empty!");
		} else if (phoneNumber.trim().replace("-", "").length() != PHONE_NUM_LENGTH) {
			throw new IllegalArgumentException("Phone number format or length not valid!");
		} else if (phoneNumber.charAt(3) != ('-')) {
			throw new IllegalArgumentException("Phone number format not valid!");
		} else if (phoneNumber.charAt(7) != ('-')) {
			throw new IllegalArgumentException("Phone number format not valid!");
		} else {

			String tempNumber = phoneNumber.trim().replace("-", "");
			boolean invalid = false;
			int position = 0;

			while ((position < tempNumber.length()) && !invalid) {

				if (!Character.isDigit(tempNumber.charAt(position))) {
					invalid = true;
				} else {
					position++;
				}

			} // while loop closing bracket

			if (invalid == true) {
				throw new IllegalArgumentException("Phone number contains a non-digit character");
			} else {
				this.phoneNumber = phoneNumber;
				return true;
			}

		}

	}

	/**
	 * Retrieve the messages
	 *
	 * @return the messages
	 */
	public String getMessages() {

		Iterator<Message> it = this.messages.iterator();

		String messages = "";

		while (it.hasNext()) {
			if (this.messages.size() <= 1) {
				messages += it.next().toString();
			} else {
				messages += it.next().toString() + "--";
			}

		}

		return messages;
	}

	public LinkedList<Message> getMessagesList() {
		return this.messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public boolean setMessage(LinkedList<Message> messages) {

		if ((messages == null)) {
			throw new NullPointerException("Messages is empty");
		} else {

			this.messages.addAll(messages);

			return true;

		} // Else closing bracket

	}

	/**
	 * abstract addMessage method to add a message
	 *
	 * @param message
	 *            - Message to add
	 */
	public abstract void addMessage(Message message);

	/**
	 * abstract method to view message
	 *
	 * @return array of messages to display
	 */
	public abstract String viewMessage();

	/*
	 * Create and return string representation of the class
	 *
	 * @return - String representation
	 */
	@Override
	public String toString() {

		String output = "";

		output += getUserID() + ",";
		output += this.getPassword() + ",";
		output += this.getFirstName() + ",";
		output += this.getLastName() + ",";
		output += this.getEmailAddress() + ",";
		output += this.getPhoneNumber() + ",";
		output += this.getMessages();

		return output;

	}

}
