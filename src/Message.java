public class Message {

	public static final int ZERO = 0;

	// Variables
	private int recipient;
	private String subject;
	private String message;

	/**
	 * Default constructor
	 */
	public Message() {
		this(0, null, null);
	}

	/**
	 * Specific constructor
	 *
	 * @param recipient
	 *            -> ID of recipient
	 * @param subject
	 *            -> Subject of the message
	 * @param message
	 *            -> The Message
	 */
	public Message(int recipient, String subject, String message) {
		this.recipient = recipient;
		this.subject = subject;
		this.message = message;
	}

	/**
	 * Retrieve recipient ID
	 *
	 * @return the recipient
	 */
	public int getRecipient() {
		return this.recipient;
	}

	/**
	 * Set the recipient ID
	 *
	 * @param recipient
	 *            the recipient to set
	 */
	public boolean setRecipient(int recipient) {
		if (recipient <= ZERO) {
			return false;
		} else {
			this.recipient = recipient;
			return true;
		}
	}

	/**
	 * Retrieve the subject of the message
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * Set the subject of the message
	 *
	 * @param subject
	 *            the subject to set
	 */
	public boolean setSubject(String subject) {
		if (subject.equals("") || (subject == null)) {
			return false;
		} else {
			this.subject = subject;
			return true;
		}
	}

	/**
	 * Retrieve the message
	 *
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Set the message
	 *
	 * @param message
	 *            the message to set
	 */
	public boolean setMessage(String message) {
		if (message.equals("") || (message == null)) {
			return false;
		} else {
			this.message = message;
			return true;
		}
	}

	/*
	 * Create and return string representation of the class
	 *
	 * @return -> String representation of the class
	 */
	@Override
	public String toString() {
		return "Message [recipient=" + recipient + ", subject=" + subject + ", message=" + message + "]";
	}

}
