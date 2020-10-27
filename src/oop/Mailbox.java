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

    public void Update(ArrayList<Message> messageList){
        this.messageList = messageList;
    }

    public ArrayList<Message> List() {
        return this.messageList;
    }

    public Message Send(String destination,String subject, String body) {
        return new Message(subject,body,this.username,destination, new Timestamp(System.currentTimeMillis()));
    }

    public ArrayList<Message> Sorted(Comparator<Message> comparator) {
        ArrayList<Message> l = new ArrayList<>(messageList);
        l.sort(comparator);
        return l;
    }

    public ArrayList<Message> FilterSender(String username) {
        return this.Filter(MessageUtils.filterSender(username));
    }

    public ArrayList<Message> FilterSubject(String string) {
        return this.Filter(MessageUtils.filterSubject(string));
    }

    public ArrayList<Message> Filter(java.util.function.Predicate<Message> predicate) {
        ArrayList<Message> list = new ArrayList<>();
        messageList
                .stream()
                .filter(predicate)
                .forEach(list::add);
        return list;
    }
}
