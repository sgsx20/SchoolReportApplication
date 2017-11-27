package schoolReport;

import java.util.LinkedList;

/**
 * @author Shreejesh Shrestha
 *
 */
public class Administrator extends Person {

	/**
	 *
	 */
	public Administrator() {
		// TODO Auto-generated constructor stub
		this(0, "", "", "", "", "", null);
	}

	/**
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param phoneNumber
	 * @param messages
	 */
	public Administrator(int userID, String password, String firstName, String lastName, String emailAddress,
			String phoneNumber, LinkedList<Message> messages) {
		super(userID, password, firstName, lastName, emailAddress, phoneNumber, messages);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addMessage(Message message) {
		this.getMessagesList().add(message);
	}

	@Override
	public String viewMessage() {
		// TODO Auto-generated method stub
		return "";
	}

}
