package oop.tests;

import oop.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMailStore {
    private User user1;
    private User user2;
    private User user3;
    private Mailbox mailbox1;
    private Mailbox mailbox2;
    private Mailbox mailbox3;
    private File file;
    private MailSystem mailSystem;

    @Before
    public void Before() {
        System.out.println("before");
        file = new File("MailStoreFile.txt");

        MailStore mailStore = new FileMailStore("MailStoreFile.txt");
        mailSystem = new MailSystem(mailStore);

        user1 = new User("Leo","Leandro",new GregorianCalendar(1995,Calendar.SEPTEMBER,7));
        user2 = new User("mimi","Miriam Gertrudix",new GregorianCalendar(1999,Calendar.JULY,22));
        user3 = new User("alego23","Joshua",new GregorianCalendar(1999,Calendar.JULY,22));
        mailbox1 = new Mailbox(user1, mailStore);
        mailbox2 = new Mailbox(user2, mailStore);
        mailbox3 = new Mailbox(user3, mailStore);
        mailbox2.send(user1,"subject", "body");
        mailbox2.send(user1,"gato", "miau");
        mailbox3.send(user1,"vaca", "muuu");
        mailbox3.send(user1,"perro", "guau");
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
    public void FileSend() {
        System.out.println("FileSend() -> testing");
        try {
            int i= 0;
            List<String> expected = new ArrayList<>();
            expected.add("Leo#Leandro#1995/Sep/07;mimi#Miriam Gertrudix#1999/Jul/22;subject;body;");
            expected.add("Leo#Leandro#1995/Sep/07;mimi#Miriam Gertrudix#1999/Jul/22;gato;miau;");
            expected.add("Leo#Leandro#1995/Sep/07;alego23#Joshua#1999/Jul/22;vaca;muuu;");
            expected.add("Leo#Leandro#1995/Sep/07;alego23#Joshua#1999/Jul/22;perro;guau;");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                assertTrue(line.contains(expected.get(i)));
                i++;
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("FileSend() -> tested");
    }

    @Test
    public void FileGetMessages() {
        System.out.println("FileGetMessages() -> testing");
        mailbox1.update();
        List<Message> expected = new ArrayList<>();
        expected.add(new Message("subject","body",user2,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("gato","miau",user2,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("vaca","muuu",user3,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("perro","guau",user3,user1,new Timestamp(System.currentTimeMillis())));
        for (int i = 0; i < mailbox1.messageList().size(); i++){
            assertEquals(mailbox1.messageList().get(i).getReceiver().getUsername(),expected.get(i).getReceiver().getUsername());
            assertEquals(mailbox1.messageList().get(i).getSender().getUsername(),expected.get(i).getSender().getUsername());
            assertEquals(mailbox1.messageList().get(i).getSubject(),expected.get(i).getSubject());
            assertEquals(mailbox1.messageList().get(i).getBody(),expected.get(i).getBody());
        }
    }

    @Test
    public void MemorySend() {
        mailSystem.swap();
        List<Message> expected = new ArrayList<>();
        expected.add(new Message("subject","body",user2,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("gato","miau",user2,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("vaca","muuu",user3,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("perro","guau",user3,user1,new Timestamp(System.currentTimeMillis())));

        for (int i = 0; i < mailbox1.messageList().size(); i++){
            assertEquals(mailbox1.messageList().get(i).getReceiver().getUsername(),expected.get(i).getReceiver().getUsername());
            assertEquals(mailbox1.messageList().get(i).getSender().getUsername(),expected.get(i).getSender().getUsername());
            assertEquals(mailbox1.messageList().get(i).getSubject(),expected.get(i).getSubject());
            assertEquals(mailbox1.messageList().get(i).getBody(),expected.get(i).getBody());
        }

    }

    @Test
    public void MemoryGetMessages() {
        mailSystem.swap();
        List<Message> expected = new ArrayList<>();
        expected.add(new Message("subject","body",user2,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("gato","miau",user2,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("vaca","muuu",user3,user1,new Timestamp(System.currentTimeMillis())));
        expected.add(new Message("perro","guau",user3,user1,new Timestamp(System.currentTimeMillis())));

        for (int i = 0; i < mailbox1.messageList().size(); i++){
            assertEquals(mailbox1.messageList().get(i).getReceiver().getUsername(),expected.get(i).getReceiver().getUsername());
            assertEquals(mailbox1.messageList().get(i).getSender().getUsername(),expected.get(i).getSender().getUsername());
            assertEquals(mailbox1.messageList().get(i).getSubject(),expected.get(i).getSubject());
            assertEquals(mailbox1.messageList().get(i).getBody(),expected.get(i).getBody());
        }
    }
}


