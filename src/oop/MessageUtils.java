package oop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Predicate;

public class MessageUtils {
    public static Predicate<Message> filterSender(User sender) {
        return (Message m) -> m.getSender().getUsername().equals(sender.getUsername());
    }

    public static Predicate<Message> filterSenderName(String username) {
        return (Message m) -> m.getSender().getUsername().equals(username);
    }

    public static Predicate<Message> filterNumChars(int num) {
        return (Message m) -> m.getBody().split("(?!^)").length > num;
    }

    public static Predicate<Message> filterSubject(String string) {
        return (Message m) -> m.getSubject().toLowerCase().contains(string.toLowerCase());
    }
}
