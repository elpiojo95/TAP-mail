package oop;

import java.io.IOException;
import java.util.List;

/**
 * Interface for the MailStore
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public interface MailStore {
    void send (Message msg) throws IOException;
    List<Message> getMessages(User username);
    List<Message> getAllMessages();
    List<User> getAllUsers();
}
