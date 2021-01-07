package patterns;

import oop.MailStore;
import oop.Message;
import oop.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Decorator pattern to cipher the body messages in the mailStore
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class MailstoreCipherDecorator extends MailStoreDecorator{
    public MailstoreCipherDecorator(MailStore ms) {
        super(ms);
        bodyStrategy= new OperationCipher();
    }

    /**
     * Send the message to the storage but with the body ciphered
     * @param msg message to send
     * @throws IOException file exception
     */
    @Override
    public void send(Message msg) throws IOException {
        Message message = new Message(msg.getSubject(), bodyStrategy.encrypt(msg.getBody()), msg.getSender(), msg.getReceiver(), msg.getCreationTime());
        mailStore.send(message);
    }

    /**
     * gets the list of messages of the username but deciphered
     * @param username username
     * @return list of messages
     */
    @Override
    public List<Message> getMessages(User username) {
        List<Message> list = mailStore.getMessages(username);
        List<Message> reversed = new ArrayList<>();
        for (Message msg:list) {
            Message msgRev = new Message(msg.getSubject(), bodyStrategy.decrypt(msg.getBody()), msg.getSender(), msg.getReceiver(), msg.getCreationTime());
            reversed.add(msgRev);
        }
        return reversed;
    }

    /**
     * gets the list of all messages of the system but deciphered
     * @return list of messages
     */
    @Override
    public List<Message> getAllMessages() {
        List<Message> list = mailStore.getAllMessages();
        List<Message> reversed = new ArrayList<>();
        for (Message msg:list) {
            Message msgRev = new Message(msg.getSubject(), bodyStrategy.decrypt(msg.getBody()), msg.getSender(), msg.getReceiver(), msg.getCreationTime());
            reversed.add(msgRev);
        }
        return reversed;
    }

    /**
     * gets all the users of the system
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
       return mailStore.getAllUsers();
    }
}
