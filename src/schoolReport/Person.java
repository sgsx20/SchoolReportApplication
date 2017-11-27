package schoolReport;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class Person {

	// Constants
	public static final int PHONE_NUM_LENGTH = 10;
	public static final int ZERO = 0;

	// Instance variables
	private int userID;
	private String password;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String phoneNumber;
	private LinkedList<Message> messages;

	/**
	 * Default constructor.
	 */
	public Person() {
		this(0, "", "", "", "", "", null);
	}

	/**
	 * Specific Constructor.
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
	public Person(int userID, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber, LinkedList<Message> messages) {

		// Set the instance variables
		this.userID = userID;
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

	// Abstract methods
	public abstract void addMessage(Message message);

	public abstract String viewMessage();

	/**
	 * Get User ID
	 *
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Get Password
	 *
	 * @return password
	 */
	public String getPassword() {
		return this.password;
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
	 * Retrieve the messages as a string
	 *
	 * @return the messages
	 */
	public String getMessages() {

		Iterator<Message> it = this.messages.iterator();

		String messages = "";

		// Split the messages based on a delimiter if more than one
		while (it.hasNext()) {
			if (this.messages.size() <= 1) {
				messages += it.next().toString();
			} else {
				messages += it.next().toString() + "--";
			}

		}

		return messages;
	}

	/**
	 * Retrieve the messages as a linked list
	 *
	 * @return the messages linked list
	 */
	public LinkedList<Message> getMessagesList() {
		return this.messages;
	}

	/**
	 * Set User ID
	 *
	 * @param userID
	 *            the userID to set
	 *
	 * @return true or false
	 */
	public boolean setUserID(int userID) {

		if (userID <= ZERO) {
			throw new IllegalArgumentException("User ID can't be <= zero");
		} else {
			this.userID = userID;
			return true;
		}
	}

	/**
	 * Set the password
	 *
	 * @param password
	 *
	 * @return true or false
	 */
	public boolean setPassword(String password) {
		if (password.equalsIgnoreCase("")) {
			throw new IllegalArgumentException("Password is empty!");
		} else {
			this.password = password;
			return true;
		}
	}

	/**
	 * Set the first name
	 *
	 * @param firstName
	 *            the firstName to set
	 *
	 * @return true or false
	 */
	public boolean setFirstName(String firstName) {
		if ((firstName == null) || firstName.equalsIgnoreCase("")) {
			throw new IllegalArgumentException("First name can't be empty!");
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
	 *
	 * @return true or false
	 */
	public boolean setLastName(String lastName) {
		if ((lastName == null) || lastName.equalsIgnoreCase("")) {
			throw new IllegalArgumentException("Last name can't be empty!");
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
	 *
	 * @return true or false
	 */
	public boolean setEmailAddress(String emailAddress) {

		// If email is empty, throw exception.
		if ((emailAddress == null) || emailAddress.equalsIgnoreCase("")) {
			throw new IllegalArgumentException("Email address can't be empty!");
		} else {

			boolean atFlag = false;
			boolean dotFlag = false;
			int position = 0;

			// Check to see if the email has a @ sign and a . symbol in it
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

			}

			/*
			 * If email has @ sign and . symbol, set it and return true. else, throw
			 * exception
			 */
			if (atFlag && dotFlag) {
				this.emailAddress = emailAddress;
				return true;
			} else {
				throw new IllegalArgumentException("Email address isn't valid!");
			}

		}

	}

	/**
	 * Set the phone number
	 *
	 * @param phoneNumber
	 *            the phoneNumber to set
	 *
	 * @return true or false
	 */
	public boolean setPhoneNumber(String phoneNumber) {

		// Phone number validation.
		if ((phoneNumber == null) || phoneNumber.equalsIgnoreCase("")) {
			throw new IllegalArgumentException("Phone number can't be empty!");
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

			// Check to see if the phone number is all digits
			while ((position < tempNumber.length()) && !invalid) {

				if (!Character.isDigit(tempNumber.charAt(position))) {
					invalid = true;
				} else {
					position++;
				}

			}

			// If phone number is not all digits, display error. Else, set it.
			if (invalid == true) {
				throw new IllegalArgumentException("Phone number contains a non-digit character");
			} else {
				this.phoneNumber = phoneNumber;
				return true;
			}

		}

	}

	/**
	 * Set the message
	 *
	 * @param messages
	 *            linked list
	 * @return true or false
	 */
	public boolean setMessage(LinkedList<Message> messages) {

		// if messages is null, display error. Else, set it.
		if ((messages == null)) {
			throw new NullPointerException("Messages is empty");
		} else {

			this.messages.addAll(messages);

			return true;

		}

	}

	/*
	 * To String representation of the person
	 *
	 * @return string output
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
