package oop;

import java.sql.Timestamp;

public class Message {
    private String subject;
    private String body;
    private String sender;
    private String receiver;
    private Timestamp creationTime;

    public Message(String subject, String body, String sender, String receiver, Timestamp creationTime) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.creationTime = creationTime;
    }

    public String getSubject() {
        return subject;
    }

    public String getSender() {
        return sender;
    }

    public String getBody() {
        return body;
    }

    public String getReceiver() {
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
