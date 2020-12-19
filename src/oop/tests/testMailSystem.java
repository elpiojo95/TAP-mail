package oop.tests;

import oop.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class testMailSystem {
    private MailSystem mailSystem;
    private MailStore mailStore;
    private Calendar userBirth;
    private Mailbox userMailbox;
    private List<User> userList;

    @Before
    public void Before() {
        mailStore = new FileMailStore(new File("mailStore.txt"));
        mailSystem = new MailSystem(mailStore);
        //createUser
        userBirth = Calendar.getInstance();
        userBirth.set(1995, Calendar.SEPTEMBER,7);
        User userCreated =  new User("elpiojo", "Leo", userBirth);
        userMailbox = new Mailbox(userCreated, mailStore);
        //userList
        userList = new ArrayList<>();
        userList.add( new User("elpiojo", "Leo", userBirth));
        mailSystem.createUser("elpiojo", "Leo", userBirth);
    }

    @Test
    public void  createUser(){
        Assert.assertEquals(mailSystem.createUser("elpiojo", "Leo", userBirth), userMailbox);
    }

    @Test
    public void  getMessageList(){

    }

    @Test
    public void  getUserList(){
        Assert.assertEquals(mailSystem.getUserList(),userList);
    }

    @Test
    public void  getMailbox(){

    }

    @Test
    public void  filter(){

    }

    @Test
    public void  NumberMessages(){

    }

    @Test
    public void  average(){

    }

    @Test
    public void  filterPerSubject(){

    }

    @Test
    public void  filterPerWord(){

    }

    @Test
    public void  filterPerNumWords(){

    }

    @Test
    public void  wordCounterByName(){

    }

    @Test
    public void  bornBefore(){

    }
}
