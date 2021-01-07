package patterns;

import oop.FileMailStore;
import oop.MailSystem;
import oop.Mailbox;
import oop.User;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class mainEncoding {
    public static void main(String[] args) {
        System.out.println("---- NORMAL: ----");
        FileMailStore file = new FileMailStore("Normal.txt");
        EnhancedMailSystem mailSystem = new EnhancedMailSystem(file);

        User user1 = new User("user1", "name1", new GregorianCalendar(2000, Calendar.JANUARY, 1));
        User user2 = new User("user2", "name2", new GregorianCalendar(1999, Calendar.JANUARY, 1));
        User user3 = new User("user3", "name3", new GregorianCalendar(2001, Calendar.JANUARY, 1));
        Mailbox mbox1 = mailSystem.createUser(user1);
        Mailbox mbox2 = mailSystem.createUser(user2);
        Mailbox mbox3 = mailSystem.createUser(user3);


        mbox1.send(user2, "subject", "Student marks");
        mbox2.send(user1,"heey","hola que tal bro");
        mbox3.send(user1, "subject2", "body");
        mbox2.send(user3, "group", "body");
        mbox1.send(user3, "group2", "body2");
        mbox3.send(user2, "subject3", "body");

        mbox1.update();
        mbox2.update();
        mbox3.update();
        System.out.println("user1 messages: "+mbox1.messageList());
        System.out.println("user2 messages: "+mbox2.messageList());
        System.out.println("user3 messages: "+mbox3.messageList());

        System.out.println("---- DECORATOR: ----");
        FileMailStore file2 = new FileMailStore("Encoding.txt");
        MailStoreDecorator cipher = new MailStoreCipherDecorator(file2);
        MailStoreDecorator reverse = new MailStoreReverseDecorator(cipher);
        MailSystem mailSystem2 = new MailSystem(reverse);
        User user1dec = new User("user1", "name1", new GregorianCalendar(2000, Calendar.JANUARY, 1));
        User user2dec = new User("user2", "name2", new GregorianCalendar(1999, Calendar.JANUARY, 1));
        User user3dec = new User("user3", "name3", new GregorianCalendar(2001, Calendar.JANUARY, 1));
        Mailbox mbox1dec = mailSystem2.createUser(user1dec);
        Mailbox mbox2dec = mailSystem2.createUser(user2dec);
        Mailbox mbox3dec = mailSystem2.createUser(user3dec);


        mbox1dec.send(user2dec, "subject", "Student marks");
        mbox2dec.send(user1dec,"heey","hola que tal bro");
        mbox3dec.send(user1dec, "subject2", "body");
        mbox2dec.send(user3dec, "group", "body");
        mbox1dec.send(user3dec, "group2", "body2");
        mbox3dec.send(user2dec, "subject3", "body");

        mbox1dec.update();
        mbox2dec.update();
        mbox3dec.update();
        System.out.println("user1 messages: "+mbox1dec.messageList());
        System.out.println("user2 messages: "+mbox2dec.messageList());
        System.out.println("user3 messages: "+mbox3dec.messageList());
    }
}
