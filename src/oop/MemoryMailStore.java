package oop;

import java.util.ArrayList;
import java.util.List;


public class MemoryMailStore implements MailStore{
    private List<Message> messageList = new ArrayList<>();

    @Override
    public void send(Message msg) {
        this.messageList.add(msg);
    }

    @Override
    public List<Message> getMessages(User username) {
        ArrayList<Message> list = new ArrayList<>();
        messageList.stream().filter((Message m) -> m.getReceiver().equals(username)).forEach(list::add);
        return list;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageList;
    }

    @Override
    public List<User> getAllUsers() {
        //to-do
        return null;
    }
}
