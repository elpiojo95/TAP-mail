package patterns;

import oop.MailStore;
import oop.Message;
import oop.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailstoreReverseDecorator extends MailStoreDecorator{
    public MailstoreReverseDecorator(MailStore ms) {
        super(ms);
        bodyStrategy= new OperationReverse();
    }

    @Override
    public void send(Message msg) throws IOException {
        Message message = new Message(msg.getSubject(), bodyStrategy.encrypt(msg.getBody()), msg.getSender(), msg.getReceiver(), msg.getCreationTime());
        mailStore.send(message);
    }

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

    @Override
    public List<User> getAllUsers() {
        return mailStore.getAllUsers();
    }


}
