package oop;

import java.util.function.Predicate;

/**
 * Predicates for filter methods
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class MessageUtils {
    /**
     * filters messages by sender
     * @param sender user sender
     * @return predicate
     */
    public static Predicate<Message> filterSender(User sender) {
        return (Message m) -> m.getSender().getUsername().equals(sender.getUsername());
    }

    /**
     * filters Message by sender name
     * @param username name of sender
     * @return predicate
     */
    public static Predicate<Message> filterSenderName(String username) {
        return (Message m) -> m.getSender().getUsername().equals(username);
    }

    /**
     * filters per number of characters contain the body
     * @param num number of characters
     * @return predicate
     */
    public static Predicate<Message> filterNumChars(int num) {
        return (Message m) -> m.getBody().split("(?!^)").length > num;
    }

    /**
     * filters per subject
     * @param string subject
     * @return predicate
     */
    public static Predicate<Message> filterSubject(String string) {
        return (Message m) -> m.getSubject().toLowerCase().contains(string.toLowerCase());
    }
}
