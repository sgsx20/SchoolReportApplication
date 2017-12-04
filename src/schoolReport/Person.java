package schoolReport;

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

	/**
	 * Default constructor.
	 */
	public Person() {
		this(0, "", "", "", "", "");
	}

	/**
	 * Specific Constructor
	 *
	 * @param userID
	 *            -> User ID
	 * @param password
	 *            -> Password
	 * @param firstName
	 *            -> First Name
	 * @param lastName
	 *            -> Last Name
	 * @param emailAddress
	 *            -> Email address
	 * @param phoneNumber
	 *            -> Phone Number
	 */
	public Person(int userID, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber) {

		// Set the instance variables
		this.userID = userID;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;

	}

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
	 *            -> Password to set
	 * @return true or false
	 */
	public boolean setPassword(String password) {
		boolean valid = false;
		if (!password.equals("")) {
			if (isValidPassword(password)) {
				this.password = password;
				return valid;
			} else {
				throw new IllegalArgumentException("Password isn't valid");
			}
		} else {
			throw new IllegalArgumentException("Password can't be empty!");
		}

	}

	/**
	 * Method for further password validation
	 *
	 * @param password
	 *            -> Password to validate
	 * @return true or false
	 */
	public boolean isValidPassword(String password) {
		boolean valid = false;
		int MIN_PASSWORD_LENGTH = 6;

		if (password.length() >= MIN_PASSWORD_LENGTH) {
			boolean hasLower = false;
			boolean hasUpper = false;
			for (int x = 0; x < password.length(); x++) {
				if (Character.isUpperCase(password.charAt(x))) {
					hasUpper = true;
				}
				if (Character.isLowerCase(password.charAt(x))) {
					hasLower = true;
				}
			}

			if (hasLower && hasUpper) {
				valid = true;
			}
		}

		return valid;
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
		boolean valid = false;
		if (!emailAddress.equals("")) {
			if (isValidEmail(emailAddress)) {
				this.emailAddress = emailAddress;
				return valid;
			} else {
				throw new IllegalArgumentException("Email address isn't valid!");
			}
		} else {
			throw new IllegalArgumentException("Email address can't be empty");
		}

	}

	/**
	 * Method to further validate the email
	 *
	 * @param email
	 *            -> Email to set
	 * @return true or false
	 */
	public boolean isValidEmail(String email) {
		boolean valid = false;
		int MIN_EMAIL_LENGTH = 6;

		if (email.length() >= MIN_EMAIL_LENGTH) {
			int atPos = email.indexOf('@');
			int periodPos = email.indexOf('.');
			if ((atPos > 0) && ((periodPos < (email.length() - 3)) || (periodPos < (email.length() - 4))
					|| (periodPos < (email.length() - 5))) && (periodPos > (atPos + 1))) {
				valid = true;
			}
		}
		return valid;
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
	 * Method to write all the attributes to the file. Method adds the strings
	 * together and returns it
	 *
	 * @return String of the concatenated values
	 */
	public String writeAttributesToFile() {
		String toFile = "";

		toFile += getUserID() + ",";
		toFile += this.getPassword() + ",";
		toFile += this.getFirstName() + ",";
		toFile += this.getLastName() + ",";
		toFile += this.getEmailAddress() + ",";
		toFile += this.getPhoneNumber() + ",";

		return toFile;
	}

	/*
	 * To String representation of the Person
	 *
	 * @return string output
	 */
	@Override
	public String toString() {

		String output = "\n\n";

		output += "User iD: " + this.userID + "\n" + "Name: " + this.firstName + " " + this.lastName + "\n" + "Email: "
				+ this.emailAddress + "\n" + "Phone: " + this.phoneNumber;

		return output;

	}

}
