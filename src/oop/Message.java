package oop;

import java.sql.Timestamp;
import java.util.Date;

public class Message {
    private String subject;
    private String body;
    private String sender;
    private String  reciver;
    private Timestamp creationTime;

    public Message(String subject, String body, String sender, String reciver, Timestamp creationTime) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.reciver = reciver;
        this.creationTime = creationTime;
    }
}
