package patterns;

import oop.MailStore;
import oop.MailSystem;
import oop.User;

import java.util.Calendar;

public class EnhancedMailSystem extends MailSystem {

    public EnhancedMailSystem(MailStore mailStore) {
        super(mailStore);
    }

    @Override
    public EnhancedMailbox createUser(String username, String name, Calendar birthDate) {
        User user = new User(username, name, birthDate);
        userList.add(user);
        return new EnhancedMailbox(user, mStore);
    }

    @Override
    public EnhancedMailbox createUser(User user){
        userList.add(user);
        return new EnhancedMailbox(user, mStore);
    }
}
