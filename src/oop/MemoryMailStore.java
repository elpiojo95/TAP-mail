package oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class MemoryMailStore implements MailStore{
    private List<Message> messageList = new ArrayList<>();



    public MemoryMailStore() {
    }

    public MemoryMailStore(FileMailStore mStore) {
        mStore.getAllMessages().forEach(this::send);
    }

    @Override
    public void send(Message msg) {
        this.messageList.add(msg);
    }

    @Override
    public List<Message> getMessages(User username) {
        ArrayList<Message> list = new ArrayList<>();
        messageList.stream().filter((Message m) ->
                m.getReceiver().equals(username)).forEach(list::add);
        return list;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageList;
    }

    @Override
    public List<User> getAllUsers() {
        Set<User> set = messageList.stream()
                .map(Message::getReceiver)
                .collect(Collectors.toSet());
        messageList.stream()
                .map(Message::getSender)
                .forEach(set::add);

        return List.copyOf(set);
    }
}
