package oop;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.*;

public class Mailbox implements Iterable<Message> {
    private String username;
    private ArrayList<Message> messageList = new ArrayList<>();

    public Mailbox(String username) {
        this.username = username;
    }

    @Override
    public Iterator<Message> iterator() {
        return messageList.iterator();
    }

    public void Update(ArrayList<Message> messageList){
        this.messageList = messageList;
    }

    public ArrayList<Message> List() {
        return this.messageList;
    }

    public Message Send(String destination,String subject, String body) {
        return new Message(subject,body,this.username,destination, new Timestamp(System.currentTimeMillis()));
    }

    public ArrayList<Message> Sorted() {
        ArrayList<Message> l = new ArrayList<>(messageList);
        l.sort(Comparator.comparing(Message::getReciver));
        return l;
    }
}
