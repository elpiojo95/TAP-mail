package oop;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /**
     * Test of OOP package
     * @param args args
     */
    public static void main(String[] args) {
        System.out.println("----Start----");
        System.out.println("----Initializing----");
        MailStore memoryMailStore = new MemoryMailStore();
        MailSystem mailSystem = new MailSystem(memoryMailStore);
        User user1 = new User("user1", "name1", new GregorianCalendar(2000, Calendar.JANUARY, 1));
        User user2 = new User("user2", "name1", new GregorianCalendar(1999, Calendar.JANUARY, 1));
        User user3 = new User("user3", "name3", new GregorianCalendar(2001, Calendar.JANUARY, 1));
        mailSystem.createUser(user1);
        mailSystem.createUser(user2);
        mailSystem.createUser(user3);
        Mailbox mbox1 = new Mailbox(user1, memoryMailStore);
        Mailbox mbox2 = new Mailbox(user2, memoryMailStore);
        Mailbox mbox3 = new Mailbox(user3, memoryMailStore);

        System.out.println("----Sending some messages----");

        try {
            mbox1.send(user2, "subject", "body");
            Thread.sleep(200);
            mbox2.send(user1, "subject filter", "body");
            Thread.sleep(200);
            mbox3.send(user1, "subject2 filter", "body");
            Thread.sleep(200);
            mbox2.send(user1, "subject3", "body");
            Thread.sleep(200);
            mbox2.send(user1, "subject4", "body");
            Thread.sleep(200);
            mbox2.send(user3, "group", "body");
            Thread.sleep(200);
            mbox3.send(user1, "group", "body");
            Thread.sleep(200);
            mbox1.send(user3, "group", "body");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("----Listing messages by creation time----");
        mbox1.update();
        System.out.println(
                mbox1.sorted((o1, o2) ->
                        o2.getCreationTime().compareTo(o1.getCreationTime()))
        );
        System.out.println("----Listing messages by sender----");
        System.out.println(
                mbox1.sorted(Comparator.comparing(Message::getSender))
        );
        System.out.println("----Filtering subject contains \"filter\"----");
        System.out.println(
                mbox1.filterSubject("filter")
        );
        System.out.println("----Filtering sender==\"user3\"----");
        System.out.println(
                mbox1.filterSender(user3)
        );
        System.out.println("----Filtering GLOBALLY----");
        System.out.println("----Filtering subject is a single word----");
        System.out.println(mailSystem.filter(message ->
                        1 == message.getSubject().split(" ").length
                )
        );
        System.out.println("----Filtering sender was born after year 2000----");
        System.out.println(mailSystem.filter(message ->
                0 < message.getSender()
                        .getBirthDate()
                        .compareTo(new GregorianCalendar(2000, Calendar.DECEMBER, 31))
                )
        );
        System.out.println("----Count messages in system----");
        System.out.println(
                mailSystem.numberMessages()
        );
        System.out.println("----Average number of messages per user----");
        System.out.println(
                mailSystem.average()
        );
        System.out.println("----Group messages per subject----");
        System.out.println(
                mailSystem.getMessageList().stream()
                .collect(
                        Collectors.groupingBy(Message::getSubject)
                )
        );
        System.out.println("----Count words of all messages by Name name3----");
        System.out.println(
                mailSystem.wordCounterByName("name3")
        );
        System.out.println("----Count words of all messages by Name name1----");
        System.out.println(
                mailSystem.wordCounterByName("name1")
        );
        System.out.println("----Messages received by users born before 2000----");
        System.out.println(
                mailSystem.bornBefore(2000)
        );
        System.out.println("----Swap to file implementation----");
        mailSystem.swap();
        System.out.println("----Finish----");
    }
}
