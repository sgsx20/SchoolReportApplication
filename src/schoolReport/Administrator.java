package schoolReport;

/**
 * @author Ehsan Baig
 *
 */
public class Administrator extends Person {

	/**
	 * Default constructor
	 */
	public Administrator() {
		this(0, "", "", "", "", "");
	}

	/**
	 * Specific Constructor
	 *
	 * @param userID
	 *            -> user ID
	 * @param password
	 *            -> Password
	 * @param firstName
	 *            -> First name
	 * @param lastName
	 *            -> Last Name
	 * @param emailAddress
	 *            -> Email address
	 * @param phoneNumber
	 *            -> Phone Number
	 */
	public Administrator(int userID, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber) {
		super(userID, password, firstName, lastName, emailAddress, phoneNumber);
	}

	/*
	 * Method to write attributes of the administrator to the file
	 *
	 * @return String of attributes of administrator to write to file
	 */
	public String writeAttributesToFile() {
		return super.writeAttributesToFile();
	}

	/*
	 * String representation of the administrator
	 * 
	 * @return String representation of the administrator
	 */
	public String toString() {
		return super.toString();
	}

}
