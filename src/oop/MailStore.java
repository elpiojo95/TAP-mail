package oop;

import java.util.List;

public interface MailStore {
    void send (Message msg);
    List<Message> get (String username);
}
