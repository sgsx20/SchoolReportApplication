package schoolReport;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Ehsan Baig
 *
 */
public class Administrator extends Person {

	/**
	 * Default constructor
	 */
	public Administrator() {
		this(0, "", "", "", "", "", null);
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
			String phoneNumber, LinkedList<Message> messages) {
		super(userID, password, firstName, lastName, emailAddress, phoneNumber, messages);
	}

	public void addMessage(Message message) {
		this.getMessagesList().add(message);
	}

	public String viewMessage() {

		String output = "";

		if ((this.getMessagesList().size() == 0) || (this.getMessages() == null)) {
			throw new NullPointerException("There are no messages to view. Empty inbox");
		} else {

			output = "Your Messages:" + "\n" + "\n";

			Iterator<Message> it = this.getMessagesList().iterator();

			while (it.hasNext()) {
				Message temp = it.next();
				output += temp.getMessage() + "\n";
			}

			return output;
		}
	}

	public String toString() {
		return super.toString();
	}

}
