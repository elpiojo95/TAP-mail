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
    public List<Message> get(String username) {
        ArrayList<Message> list = new ArrayList<>();
        messageList.stream().filter((Message m) -> m.getReceiver().toLowerCase().contains(username.toLowerCase())).forEach(list::add);
        return list;
    }
}
