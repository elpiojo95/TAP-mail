package oop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;

public class MessageUtils {
    public static Predicate<Message> filterSender(String username) {
        return (Message m) -> m.getSender().equals(username);
    }

    public static Predicate<Message> filterSubject(String string) {
        return (Message m) -> m.getSubject().toLowerCase().contains(string.toLowerCase());
    }
}
