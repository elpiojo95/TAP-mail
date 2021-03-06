package oop;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Represents a mail account and implements the user mailing operations.
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class Mailbox implements Iterable<Message> {

    protected User user;
    protected List<Message> messageList = new ArrayList<>();
    protected MailStore mailStore;

    /**
     * Class constructor
     * @param user user
     */
    public Mailbox(User user, MailStore mailStore) {
        this.user = user;
        this.mailStore = mailStore;
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
     */
    public void update(){
        this.messageList = mailStore.getMessages(this.user);
    }

    /**
     * getter of messageList
     * @return messageList
     */
    public List<Message> messageList() {
        messageList.sort(Comparator.comparing(Message::getCreationTime));
        return this.messageList;
    }

    /**
     * getter of user
     * @return user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Send a message to another user
     * returns the message created
     * @param destination receiver user
     * @param subject topic of the message
     * @param body main text of the message
     */
    public void send(User destination, String subject, String body) {
        try {
            mailStore.send(new Message(
                    subject,
                    body,
                    this.user,
                    destination,
                    new Timestamp(System.currentTimeMillis())));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public ArrayList<Message> filterSender(User username) {
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
