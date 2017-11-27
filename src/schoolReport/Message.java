package schoolReport;

public class Message {

	// Constants
	public static final int ZERO = 0;

	// Instance variables
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

	/**
	 * Get the sender ID
	 *
	 * @return sender ID
	 */
	public int getSenderID() {
		return this.senderID;
	}

	/**
	 * Retrieve recipient ID
	 *
	 * @return the recipient ID
	 */
	public int getRecipientID() {
		return this.recipientID;
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
	 * Set the sender ID
	 *
	 * @param sender
	 *            - Sender ID
	 * @return true or false
	 */
	public boolean setSender(int sender) {
		if (sender <= ZERO) {
			throw new IllegalArgumentException("The Sender ID cannot be less than zero. Invalid argument value");
		} else {
			this.senderID = sender;
			return true;
		}
	}

	/**
	 * Set the recipient ID
	 *
	 * @param recipient
	 *            the recipient to set
	 *
	 * @return true or false
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
	 * Set the message
	 *
	 * @param message
	 *            the message to set
	 *
	 * @return true or false
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
	 * Create and send string representation
	 *
	 * @return string representation
	 */
	@Override
	public String toString() {
		return "[" + this.getSenderID() + "-" + this.getRecipientID() + "-" + this.getMessage() + "]";
	}

}
