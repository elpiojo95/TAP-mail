package oop;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.*;

public class Mailbox implements Iterable<Message> {
    private String username;
    private List<Message> messageList = new ArrayList<Message>();

    public Mailbox(String username) {
        this.username = username;
    }

    @Override
    public Iterator<Message> iterator() {
        return messageList.iterator();
    }

    public void Update(List<Message> messageList){
        this.messageList = messageList;
    }

    public List<Message> List() {
        return this.messageList;
    }

    public Message Send(String destination,String subject, String body) {
        return new Message(subject,body,this.username,destination, new Timestamp(System.currentTimeMillis()));
    }

    public ArrayList<Message> Sorted() {
        return new ArrayList<Message>(messageList).sort(Comparator.comparing(Message::getCreationTime));
    }
}
