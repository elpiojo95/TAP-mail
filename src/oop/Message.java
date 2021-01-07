package oop;

import java.sql.Timestamp;

/**
 * Represents the Message structure and implements message operations
 *  @author Leandro F. Gomez Racca
 *  @author Miriam Gertrudix Pedrola
 */
public class Message {
    private String subject;
    private String body;
    private User sender;
    private User receiver;
    private Timestamp creationTime;

    /**
     * Class constructor
     * @param subject subject of the message
     * @param body text of the message
     * @param sender user sender
     * @param receiver user receiver
     * @param creationTime creation time of the message
     */
    public Message(String subject, String body, User sender, User receiver, Timestamp creationTime) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.creationTime = creationTime;
    }

    /**
     * getter of subject
     * @return String subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * getter of sender
     * @return User Sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * getter of body
     * @return String body
     */
    public String getBody() {
        return body;
    }

    /**
     * getter of receiver
     * @return User receiver
     */
    public User getReceiver() {
        return receiver;
    }

    /**
     * getter of crationTime
     * @return Timestamp creationTime
     */
    public Timestamp getCreationTime() {
        return creationTime;
    }

    /**
     * Message to string
     * @return String Message
     */
    @Override
    public String toString() {
        return "Message{" +
                "\n\tsubject='" + subject + '\'' +
                ",\n\tbody='" + body + '\'' +
                ",\n\tsender='" + sender + '\'' +
                ",\n\treceiver='" + receiver + '\'' +
                ",\n\tcreationTime=" + creationTime +
                "\n}";
    }
}
