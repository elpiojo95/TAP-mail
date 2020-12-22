package oop.tests;

import oop.Message;
import oop.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class testMessage {
    private Message message;
    private Timestamp time;
    private User sender;
    private User receiver;

    @Before
    public void Before() {
        time = new Timestamp(System.currentTimeMillis());
        Calendar leoBirth = new GregorianCalendar(1995, Calendar.SEPTEMBER,7);
        sender =  new User("elpiojo", "Leo", leoBirth);
        Calendar mimiBirth = new GregorianCalendar(1999, Calendar.JULY,22);
        receiver =  new User("mimi", "Miriam", mimiBirth);
        message = new Message("subject", "body", sender, receiver, time);
    }

    @Test
    public void getSubject() {
        Assert.assertEquals(message.getSubject(),"subject");
    }
    @Test
    public void getSender() {
        Assert.assertEquals(message.getSender(),sender);
    }

    @Test
    public void getBody() {
        Assert.assertEquals(message.getBody(),"body");
    }

    @Test
    public void getReceiver() {
        Assert.assertEquals(message.getReceiver(),receiver);
    }

    @Test
    public void getCreationTime() {
        Assert.assertEquals(message.getCreationTime(),time);
    }
}
