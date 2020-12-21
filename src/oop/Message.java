package oop;

import java.sql.Timestamp;

public class Message {
    private String subject;
    private String body;
    private User sender;
    private User receiver;
    private Timestamp creationTime;

    public Message(String subject, String body, User sender, User receiver, Timestamp creationTime) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.creationTime = creationTime;
    }

    public String getSubject() {
        return subject;
    }

    public User getSender() {
        return sender;
    }

    public String getBody() {
        return body;
    }

    public User getReceiver() {
        return receiver;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

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
