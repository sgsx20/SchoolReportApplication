package schoolReport;

import java.util.LinkedList;

public abstract class Person {

	public static final int PHONE_NUM_LENGTH = 10;
	public static final int MAX_MESSAGES = 100;

	// Person Attributes/ variables.
	public static int userID;
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
		this("", "", "", "", null);
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
	public Person(String firstName, String lastName, String emailAddress, String phoneNumber,
			LinkedList<Message> messages) {
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
			return false;
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
			return false;
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
			return false;
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
				return false;
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
			return false;
		} else if (phoneNumber.trim().replace("-", "").length() != PHONE_NUM_LENGTH) {
			return false;
		} else if (phoneNumber.charAt(3) != ('-')) {
			return false;
		} else if (phoneNumber.charAt(7) != ('-')) {
			return false;
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
				return false;
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
	public Object[] getMessages() {

		Object[] temp = this.messages.toArray();

		return temp;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public boolean setMessage(Message messages) {

		if ((messages == null)) {
			return false;
		} else {

			this.messages.add(messages);

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
	 * abstract method to send message.
	 *
	 * @param userType
	 *            - User to send message to
	 * @return - true or false
	 */
	public abstract boolean sendMessage(String userType);

	/**
	 * abstract method to view message
	 *
	 * @return array of messages to display
	 */
	public abstract void viewMessage();

	/*
	 * Create and return string representation of the class
	 *
	 * @return - String representation
	 */
	@Override
	public String toString() {
		return "Person [userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress="
				+ emailAddress + ", phoneNumber=" + phoneNumber + ", messages=" + getMessages() + "]";
	}

}
