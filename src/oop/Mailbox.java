package oop;

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

    public void update(ArrayList<Message> messageList){
        this.messageList = messageList;
    }

    public ArrayList<Message> List() {
        return this.messageList;
    }

    public Message send(String destination, String subject, String body) {
        return new Message(subject,body,this.username,destination, new Timestamp(System.currentTimeMillis()));
    }

    public ArrayList<Message> sorted(Comparator<Message> comparator) {
        ArrayList<Message> l = new ArrayList<>(messageList);
        l.sort(comparator);
        return l;
    }

    public ArrayList<Message> filterSender(String username) {
        return this.filter(MessageUtils.filterSender(username));
    }

    public ArrayList<Message> filterSubject(String string) {
        return this.filter(MessageUtils.filterSubject(string));
    }

    public ArrayList<Message> filter(java.util.function.Predicate<Message> predicate) {
        ArrayList<Message> list = new ArrayList<>();
        messageList
                .stream()
                .filter(predicate)
                .forEach(list::add);
        return list;
    }
}
