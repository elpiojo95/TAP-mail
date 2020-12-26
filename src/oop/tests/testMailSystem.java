package oop.tests;

import oop.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testMailSystem {
    private MailSystem mailSystem;
    private MailStore mailStore;
    private Calendar leoBirth;
    private User leo;
    private Mailbox leomb;
    private Calendar mimiBirth;
    private User mimi;
    private Mailbox mimimb;
    private File file;

    @Before
    public void Before() {
        System.out.println("before");
        file = new File("test.txt");
        mailStore = new FileMailStore("test.txt");
        mailSystem = new MailSystem(mailStore);
        //createUser
        leoBirth = new GregorianCalendar(1995,Calendar.SEPTEMBER,7);
        mimiBirth = new GregorianCalendar(1999,Calendar.JULY,22);
        leo =  new User("elpiojo", "Leandro", leoBirth);
        mimi =  new User("mimigp", "Miriam", mimiBirth);
        leomb = mailSystem.createUser("elpiojo", "Leandro", leoBirth);
        mimimb = mailSystem.createUser("mimigp", "Miriam", mimiBirth);
        leomb.send(mimi,"subject", "body");
        mimimb.send(leo,"vaca", "muuu");

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
    public void  createUser(){
        System.out.println("createUser() testing ");
        Mailbox leoExpected = new Mailbox( leo , mailStore);
        Mailbox mimiExpected = new Mailbox( mimi , mailStore);
        Assert.assertEquals(leomb.getUser().getUsername(), leoExpected.getUser().getUsername());
        Assert.assertEquals(leomb.getUser().getName(), leoExpected.getUser().getName());
        Assert.assertEquals(mimimb.getUser().getUsername(), mimiExpected.getUser().getUsername());
        Assert.assertEquals(mimimb.getUser().getName(), mimiExpected.getUser().getName());
        System.out.println("createUser() tested");
    }

    @Test
    public void  getMessageList(){
        System.out.println("getMessageList() testing ");
        List<Message> expected = new ArrayList<>();
        Message msg2 =  new Message("subject","body", leo, mimi,new Timestamp(System.currentTimeMillis()));
        expected.add(msg2);
        Message msg1 = new Message("vaca","muuu",mimi,leo,new Timestamp(System.currentTimeMillis()));
        expected.add(msg1);
        for (int i = 0; i < mailSystem.getMessageList().size(); i++){
            assertEquals(mailSystem.getMessageList().get(i).getReceiver().getUsername(),expected.get(i).getReceiver().getUsername());
            assertEquals(mailSystem.getMessageList().get(i).getSender().getUsername(),expected.get(i).getSender().getUsername());
            assertEquals(mailSystem.getMessageList().get(i).getSubject(),expected.get(i).getSubject());
            assertEquals(mailSystem.getMessageList().get(i).getBody(),expected.get(i).getBody());
        }
        System.out.println("getMessageList() tested");
    }

    @Test
    public void  getUserList(){
        System.out.println("getUserList() testing ");
        List<User> expected = new ArrayList<>();
        expected.add(leo);
        expected.add(mimi);
        for (int i = 0; (mailSystem.getUserList()).size() > i; i++){
            assertEquals(mailSystem.getUserList().get(i).getUsername(),expected.get(i).getUsername());
            assertEquals(mailSystem.getUserList().get(i).getName(),expected.get(i).getName());
        }
        System.out.println("getUserList() tested ");
    }



    @Test
    public void  NumberMessages(){
        System.out.println("NumberMessages() testing ");
        assertEquals(2, mailSystem.NumberMessages());
        System.out.println("NumberMessages() tested ");
    }

    @Test
    public void  average(){
        System.out.println("average() testing ");
        assertEquals(1, mailSystem.average());
        System.out.println("average() tested ");
    }

    @Test
    public void  filterPerSubject(){
        System.out.println("filterPerSubject() testing ");
        List<Message> expected = new ArrayList<>();
        Message msg3 =  new Message("subject","body", leo, mimi,new Timestamp(System.currentTimeMillis()));
        expected.add(msg3);
        Message msg1 = new Message("vaca","muuu",mimi,leo,new Timestamp(System.currentTimeMillis()));
        expected.add(msg1);
        Message msg2 = new Message("vaca","heyy",mimi,leo,new Timestamp(System.currentTimeMillis()));
        expected.add(msg2);
        mimimb.send(leo,"vaca", "heyy");
        for (int i = 0; i < mailSystem.filterPerNumWords(1).size(); i++){
            assertEquals(mailSystem.filterPerNumWords(1).get(i).getReceiver().getUsername(),expected.get(i).getReceiver().getUsername());
            assertEquals(mailSystem.filterPerNumWords(1).get(i).getSender().getUsername(),expected.get(i).getSender().getUsername());
            assertEquals(mailSystem.filterPerNumWords(1).get(i).getSubject(),expected.get(i).getSubject());
            assertEquals(mailSystem.filterPerNumWords(1).get(i).getBody(),expected.get(i).getBody());
        }
        System.out.println("filterPerSubject() tested ");
    }

    @Test
    public void  filterPerWord(){
        System.out.println("filterPerWord() testing ");
        List<Message> expected = new ArrayList<>();
        leomb.send(leo,"subject", "vaca lechera");

        Message msg1 = new Message("vaca","muuu",mimi,leo,new Timestamp(System.currentTimeMillis()));
        expected.add(msg1);
        Message msg3 = new Message("subject","vaca lechera",leo,leo,new Timestamp(System.currentTimeMillis()));
        expected.add(msg3);
        Message msg2 = new Message("vaca","heyy",mimi,leo,new Timestamp(System.currentTimeMillis()));
        expected.add(msg2);

        for (int i = 0; i < mailSystem.filterPerWord("vaca").size(); i++){
            assertEquals(mailSystem.filterPerWord("vaca").get(i).getReceiver().getUsername(),expected.get(i).getReceiver().getUsername());
            assertEquals(mailSystem.filterPerWord("vaca").get(i).getSender().getUsername(),expected.get(i).getSender().getUsername());
            assertEquals(mailSystem.filterPerWord("vaca").get(i).getSubject(),expected.get(i).getSubject());
            assertEquals(mailSystem.filterPerWord("vaca").get(i).getBody(),expected.get(i).getBody());
        }
        System.out.println("filterPerWord() tested ");
    }

    @Test
    public void  filterPerNumWords(){
        System.out.println("filterPerNumWords() testing ");
        List<Message> expected = new ArrayList<>();
        Message msg1 = new Message("vaca","muuu",mimi,leo,new Timestamp(System.currentTimeMillis()));
        expected.add(msg1);
        Message msg2 = new Message("vaca","heyy",mimi,leo,new Timestamp(System.currentTimeMillis()));
        expected.add(msg2);
        for (int i = 0; i < mailSystem.filterPerSubject("vaca").size(); i++){
            assertEquals(mailSystem.filterPerSubject("vaca").get(i).getReceiver().getUsername(),expected.get(i).getReceiver().getUsername());
            assertEquals(mailSystem.filterPerSubject("vaca").get(i).getSender().getUsername(),expected.get(i).getSender().getUsername());
            assertEquals(mailSystem.filterPerSubject("vaca").get(i).getSubject(),expected.get(i).getSubject());
            assertEquals(mailSystem.filterPerSubject("vaca").get(i).getBody(),expected.get(i).getBody());
        }
        System.out.println("filterPerNumWords() tested ");
    }


    @Test
    public void  wordCounterByName(){
        System.out.println("wordCounterByName() testing ");
        assertEquals(1, mailSystem.wordCounterByName("Miriam"));
        System.out.println("wordCounterByName() tested ");
    }

    @Test
    public void  bornBefore(){
        System.out.println("bornBefore() testing ");
        List<Message> expected = new ArrayList<>();
        Message msg1 = new Message("vaca","muuu",mimi,leo,new Timestamp(System.currentTimeMillis()));
        expected.add(msg1);
        for (int i = 0; i < mailSystem.bornBefore(1997).size(); i++){
            assertEquals(mailSystem.bornBefore(1997).get(i).getReceiver().getUsername(),expected.get(i).getReceiver().getUsername());
            assertEquals(mailSystem.bornBefore(1997).get(i).getSender().getUsername(),expected.get(i).getSender().getUsername());
            assertEquals(mailSystem.bornBefore(1997).get(i).getSubject(),expected.get(i).getSubject());
            assertEquals(mailSystem.bornBefore(1997).get(i).getBody(),expected.get(i).getBody());
        }
        System.out.println("bornBefore() tested ");
    }
}
