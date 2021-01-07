package oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Back-End storage based in memory implementation
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class MemoryMailStore implements MailStore{
    private List<Message> messageList = new ArrayList<>();


    /**
     * Class constructor
     */
    public MemoryMailStore() {
    }

    /**
     * Constructor based in FileMailStore
     * @param mStore FileMailStore instance
     */
    public MemoryMailStore(FileMailStore mStore) {
        mStore.getAllMessages().forEach(this::send);
    }

    /**
     * Interface MailStore
     * Send a mail to the storage
     * @param msg message to storage
     */
    @Override
    public void send(Message msg) {
        this.messageList.add(msg);
    }

    /**
     * Interface MailStore
     * Get the messages of certain receiver of the storage
     * @param username username of the receiver
     * @return list of messages
     */
    @Override
    public List<Message> getMessages(User username) {
        ArrayList<Message> list = new ArrayList<>();
        messageList.stream().filter((Message m) ->
                m.getReceiver().equals(username)).forEach(list::add);
        return list;
    }

    /**
     * Interface MailStore
     * Get all the messages of the system of the storage
     * @return list of messages
     */
    @Override
    public List<Message> getAllMessages() {
        return messageList;
    }

    /**
     * Interface MailStore
     * get all the users of the system of the storage
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
        Set<User> set = messageList.stream()
                .map(Message::getReceiver)
                .collect(Collectors.toSet());
        messageList.stream()
                .map(Message::getSender)
                .forEach(set::add);
        List<User> list = new ArrayList<>(set);
        return list;
    }
}
