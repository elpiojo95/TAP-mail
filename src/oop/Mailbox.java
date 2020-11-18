package oop;

import java.sql.Timestamp;
import java.util.*;

/**
 * Represents a mail account and implements the user mailing operations.
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class Mailbox implements Iterable<Message> {
    private String username;
    private ArrayList<Message> messageList = new ArrayList<>();

    /**
     * Class constructor
     * @param username username
     */
    public Mailbox(String username) {
        this.username = username;
    }

    /**
     * interface Iterator
     * @return iterator of List of messages
     */
    @Override
    public Iterator<Message> iterator() {
        return messageList.iterator();
    }

    /**
     * Updates the message list in the mailbox
     * @param messageList list of the new messages the user receive
     */
    public void update(ArrayList<Message> messageList){
        this.messageList = messageList;
    }

    /**
     * getter of messagelist
     * @return messagelist
     */
    public ArrayList<Message> getMessageListList() {
        return this.messageList;
    }

    /**
     * Send a message to another user
     * returns the message created
     * @param destination username of de receiver
     * @param subject topic of the message
     * @param body main text of the message
     * @return Message
     */
    public Message send(String destination, String subject, String body) {
        return new Message(subject,body,this.username,destination, new Timestamp(System.currentTimeMillis()));
    }

    /**
     * returns a new message list sorted by a given comparator
     * @param comparator comparator method to sort by
     * @return messageList new message list sorted
     */
    public ArrayList<Message> sorted(Comparator<Message> comparator) {
        ArrayList<Message> l = new ArrayList<>(messageList);
        l.sort(comparator);
        return l;
    }

    /**
     * Filter the messages in the mailbox by the sender
     * returns a new message list filtered
     * @param username username of the sender to filter
     * @return messageList new messageList filtered by the sender
     */
    public ArrayList<Message> filterSender(String username) {
        return this.filter(MessageUtils.filterSender(username));
    }

    /**
     * Filter the messages in the mailbox by the subject
     * returns a new message list filtered
     * @param string subject to filter
     * @return messageList new messageList filtered by the subject
     */
    public ArrayList<Message> filterSubject(String string) {
        return this.filter(MessageUtils.filterSubject(string));
    }

    /**
     * returns a new message list filtered by a given predicate
     * @param predicate {@link MessageUtils}
     * @return messageList new messageList filtered by the subject
     */
    public ArrayList<Message> filter(java.util.function.Predicate<Message> predicate) {
        ArrayList<Message> list = new ArrayList<>();
        messageList
                .stream()
                .filter(predicate)
                .forEach(list::add);
        return list;
    }
}
