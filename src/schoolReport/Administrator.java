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
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param phoneNumber
	 * @param messages
	 */
	public Administrator(int userID, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber) {
		super(userID, password, firstName, lastName, emailAddress, phoneNumber);
	}

	public String writeAttributesToFile() {
		return super.writeAttributesToFile();
	}

	public String toString() {
		return super.toString();
	}

}
