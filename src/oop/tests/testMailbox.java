package oop.tests;

import oop.*;

import org.junit.Test;

import java.io.File;

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





    @Test
    public void messageList() {
        System.out.println("messageList() -> testing");
        List<Message> expected = new ArrayList<>();
        mailbox.update();
        expected.add(new Message("subject","body", user2, user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("vaca","muuu",user3,user1,new Timestamp(System.currentTimeMillis())));

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
        expected.add(new Message("vaca","muuu",user3,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("subject","body", user2, user1,new Timestamp(System.currentTimeMillis())));
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
        expected.add(new Message("gato","miau",user2,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("gato","miauumiauu",user3,user1,new Timestamp(System.currentTimeMillis())));
        for (int i = 0; i < (mailbox.filter(MessageUtils.filterSubject("gato")).size()); i++){
            assertEquals((mailbox.filter(MessageUtils.filterSubject("gato")).get(i).getReceiver().getUsername()),expected.get(i).getReceiver().getUsername());
            assertEquals((mailbox.filter(MessageUtils.filterSubject("gato")).get(i).getSender().getUsername()),expected.get(i).getSender().getUsername());
            assertEquals((mailbox.filter(MessageUtils.filterSubject("gato")).get(i).getSubject()),expected.get(i).getSubject());
            assertEquals((mailbox.filter(MessageUtils.filterSubject("gato")).get(i).getBody()),expected.get(i).getBody());
        }
        expected.clear();
        expected.add(new Message("vaca","muuu",user3,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("gato","miauumiauu",user3,user1,new Timestamp(System.currentTimeMillis())));
        for (int i = 0; i < (mailbox.filter(MessageUtils.filterSender(user3)).size()); i++){
            assertEquals((mailbox.filter(MessageUtils.filterSender(user3)).get(i).getReceiver().getUsername()),expected.get(i).getReceiver().getUsername());
            assertEquals((mailbox.filter(MessageUtils.filterSender(user3)).get(i).getSender().getUsername()),expected.get(i).getSender().getUsername());
            assertEquals((mailbox.filter(MessageUtils.filterSender(user3)).get(i).getSubject()),expected.get(i).getSubject());
            assertEquals((mailbox.filter(MessageUtils.filterSender(user3)).get(i).getBody()),expected.get(i).getBody());
        }
        System.out.println("filter() -> tested");
    }
}
