package oop;

import java.io.IOException;
import java.util.List;

public interface MailStore {
    void send (Message msg) throws IOException;
    List<Message> get (String username);
}
