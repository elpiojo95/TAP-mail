package oop.tests;

import oop.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class testMailbox {
    private Mailbox mailbox;
    private Mailbox mailbox2;
    private Mailbox mailbox3;
    private User user1;
    private User user2;
    private User user3;
    private File file;

    @Before
    public void Before() {
        System.out.println("before");
        file = new File("test.txt");
        //memory mailStore
        MailStore mailStore = new FileMailStore("test.txt");
        //user1: Leo
        Calendar leoBirth = new GregorianCalendar(1995,Calendar.SEPTEMBER,7);
        user1 = new User("Leo","Leandro",leoBirth);
        mailbox = new Mailbox(user1, mailStore);
        //user2: Mimi
        Calendar mimiBirth = new GregorianCalendar(1999,Calendar.JULY,22);
        user2 = new User("mimi","Miriam Gertrudix",mimiBirth);
        mailbox2 = new Mailbox(user2, mailStore);
        //user3: Joshua
        Calendar alegoBirth = new GregorianCalendar(1999,Calendar.JULY,22);
        user3 = new User("alego23","Joshua",alegoBirth);
        mailbox3 = new Mailbox(user3, mailStore);
        //user2 send messages
        mailbox2.send(user1,"subject", "body");
        //user3 send messages
        mailbox3.send(user1,"vaca", "muuu");
    }

    @After
    public void after(){
        System.out.println("after");
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }



    @Test
    public void messageList() {
        System.out.println("messageList() -> testing");
        List<Message> expected = new ArrayList<>();
        mailbox.update();
        Message msg2 =  new Message("subject","body", user2, user1,new Timestamp(System.currentTimeMillis()));
        expected.add(msg2);
        Message msg1 = new Message("vaca","muuu",user3,user1,new Timestamp(System.currentTimeMillis()));
        expected.add(msg1);

        assertEquals(mailbox.messageList().size(),expected.size());
        for (int i = 0; i < mailbox.messageList().size(); i++){
            assertEquals(mailbox.messageList().get(i).getReceiver().getUsername(),expected.get(i).getReceiver().getUsername());
            assertEquals(mailbox.messageList().get(i).getSender().getUsername(),expected.get(i).getSender().getUsername());
            assertEquals(mailbox.messageList().get(i).getSubject(),expected.get(i).getSubject());
            assertEquals(mailbox.messageList().get(i).getBody(),expected.get(i).getBody());
        }
        System.out.println("messageList() -> tested");
    }

    @Test
    public void getUser() {
        System.out.println("getUser() -> testing");
        assertEquals(mailbox.getUser(), user1);
        System.out.println("getUser() -> tested");
    }


    @Test
    public void sorted(){
        System.out.println("sorted() -> testing");
        List<Message> expected = new ArrayList<>();
        Message msg1 = new Message("vaca","muuu",user3,user1,new Timestamp(System.currentTimeMillis()));
        expected.add(msg1);
        Message msg2 =  new Message("subject","body", user2, user1,new Timestamp(System.currentTimeMillis()));
        expected.add(msg2);
        for (int i = 0; i < (mailbox.sorted(Comparator.comparing(Message::getSender)).size()); i++){
            assertEquals((mailbox.sorted(Comparator.comparing(Message::getSender)).get(i).getReceiver().getUsername()),expected.get(i).getReceiver().getUsername());
            assertEquals((mailbox.sorted(Comparator.comparing(Message::getSender)).get(i).getSender().getUsername()),expected.get(i).getSender().getUsername());
            assertEquals((mailbox.sorted(Comparator.comparing(Message::getSender)).get(i).getSubject()),expected.get(i).getSubject());
            assertEquals((mailbox.sorted(Comparator.comparing(Message::getSender)).get(i).getBody()),expected.get(i).getBody());
        }
        System.out.println("sorted() -> tested");
    }

    @Test
    public void filter(){
        List<Message> expected = new ArrayList<>();
        System.out.println("filter() -> testing");
        mailbox2.send(user1,"gato", "miau");
        mailbox3.send(user1,"gato", "miauumiauu");
        Message msg3 = new Message("gato","miau",user2,user1,new Timestamp(System.currentTimeMillis()));
        expected.add(msg3);
        Message msg4 = new Message("gato","miauumiauu",user3,user1,new Timestamp(System.currentTimeMillis()));
        expected.add(msg4);
        for (int i = 0; i < (mailbox.filter(MessageUtils.filterSubject("gato")).size()); i++){
            assertEquals((mailbox.filter(MessageUtils.filterSubject("gato")).get(i).getReceiver().getUsername()),expected.get(i).getReceiver().getUsername());
            assertEquals((mailbox.filter(MessageUtils.filterSubject("gato")).get(i).getSender().getUsername()),expected.get(i).getSender().getUsername());
            assertEquals((mailbox.filter(MessageUtils.filterSubject("gato")).get(i).getSubject()),expected.get(i).getSubject());
            assertEquals((mailbox.filter(MessageUtils.filterSubject("gato")).get(i).getBody()),expected.get(i).getBody());
        }
        expected.clear();
        Message msg1 = new Message("vaca","muuu",user3,user1,new Timestamp(System.currentTimeMillis()));
        expected.add(msg1);
        expected.add(msg4);
        for (int i = 0; i < (mailbox.filter(MessageUtils.filterSender(user3)).size()); i++){
            assertEquals((mailbox.filter(MessageUtils.filterSender(user3)).get(i).getReceiver().getUsername()),expected.get(i).getReceiver().getUsername());
            assertEquals((mailbox.filter(MessageUtils.filterSender(user3)).get(i).getSender().getUsername()),expected.get(i).getSender().getUsername());
            assertEquals((mailbox.filter(MessageUtils.filterSender(user3)).get(i).getSubject()),expected.get(i).getSubject());
            assertEquals((mailbox.filter(MessageUtils.filterSender(user3)).get(i).getBody()),expected.get(i).getBody());
        }
        System.out.println("filter() -> tested");
    }
}
