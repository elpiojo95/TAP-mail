package patterns;

import oop.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * main to test the spam of patterns package
 */
public class mainSpam {
    public static void main(String[] args) {

        FileMailStore file = new FileMailStore("Spam.txt");
        EnhancedMailSystem mailSystem = new EnhancedMailSystem(file);
        //users
        User user1 = new User("user1", "name1", new GregorianCalendar(2000, Calendar.JANUARY, 1));
        User user2 = new User("spam", "name2", new GregorianCalendar(1999, Calendar.JANUARY, 1));
        User user3 = new User("user3", "name3", new GregorianCalendar(2001, Calendar.JANUARY, 1));
        //mailboxes
        EnhancedMailbox mbox1 = mailSystem.createUser(user1);
        EnhancedMailbox mbox2 = mailSystem.createUser(user2);
        EnhancedMailbox mbox3 = mailSystem.createUser(user3);

        mbox1.send(user2, "subject", "body");
        mbox2.send(user1, "subject filter", "body");
        mbox3.send(user1, "subject2 filter", "Hey Jude don't make it bad");
        mbox2.send(user1, "subject3", "body");
        mbox3.send(user1, "subject4", "Take a sad song and make it better");
        mbox2.send(user3, "group", "body");
        mbox3.send(user1, "group", "body");
        mbox1.send(user3, "group", "body");

        System.out.println("SPAM LIST:");
        mbox1.update();
        System.out.println(mbox1.spamList());

        System.out.println("MESSAGE LIST:");
        System.out.println(mbox1.messageList());
    }
}
