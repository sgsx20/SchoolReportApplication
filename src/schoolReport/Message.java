package schoolReport;

public class Message {

	public static final int ZERO = 0;

	// Variables
	private int senderID;
	private int recipientID;
	private String message;

	/**
	 * Default constructor
	 */
	public Message() {
		this(0, 0, null);
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
	public Message(int sender, int recipient, String message) {
		this.senderID = sender;
		this.recipientID = recipient;
		this.message = message;
	}

	public int getSender() {
		return this.senderID;
	}

	public boolean setSender(int sender) {
		if (sender <= ZERO) {
			throw new IllegalArgumentException("The Sender ID cannot be less than zero. Invalid argument value");
		} else {
			this.senderID = sender;
			return true;
		}
	}

	/**
	 * Retrieve recipient ID
	 *
	 * @return the recipient
	 */
	public int getRecipient() {
		return this.recipientID;
	}

	/**
	 * Set the recipient ID
	 *
	 * @param recipient
	 *            the recipient to set
	 */
	public boolean setRecipient(int recipient) {
		if (recipient <= ZERO) {
			throw new IllegalArgumentException("The Reciepient ID cannot be less than zero. Invalid argument value");
		} else {
			this.recipientID = recipient;
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
			throw new NullPointerException("Message cannot be empty");
		} else {
			this.message = message;
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + this.getSender() + "-" + this.getRecipient() + "-" + this.getMessage() + "]";
	}

}
