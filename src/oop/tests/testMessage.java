package oop.tests;

import oop.Message;
import oop.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;


public class testMessage {
    private Message message;
    private Timestamp time;

    @Before
    public void Before() {
        time = new Timestamp(System.currentTimeMillis());
      message = new Message("subject", "body", "elpiojo","mimi", time);
    }

    @Test
    public void getSubject() {
        Assert.assertEquals(message.getSubject(),"subject");
    }
    @Test
    public void getSender() {
        Assert.assertEquals(message.getSender(),"elpiojo");
    }

    @Test
    public void getBody() {
        Assert.assertEquals(message.getBody(),"body");
    }

    @Test
    public void getReceiver() {
        Assert.assertEquals(message.getReceiver(),"mimi");
    }

    @Test
    public void getCreationTime() {
        Assert.assertEquals(message.getCreationTime(),time);
    }
}
