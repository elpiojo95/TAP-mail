package oop.tests;

import oop.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testMailbox {
    private Mailbox mailbox;
    private User user;
    private MailStore mailStore;

    @Before
    public void Before() {
        //memory mailStore
        mailStore = new FileMailStore();
        //user: Leo
        Calendar leoBirth = Calendar.getInstance();
        leoBirth.set(1995, Calendar.SEPTEMBER,7);
        user = new User("Leo","Leandro",leoBirth);
        //mailboxes
        mailbox = new Mailbox(user,mailStore);

    }

    @Test
    public void update(){

    }

    @Test
    public void messageList() {
        List<Message> expected = new ArrayList<>();
        //mailStore.get("Leo").sort(Comparator.comparing(Message::getCreationTime));
        assertEquals(mailbox.messageList(),expected);
    }

    @Test
    public void getUser() {
        assertEquals(mailbox.getUser(), user);
    }

    @Test
    public void send() {
        //Assert.assertEquals(mailbox.send(), );
    }

    @Test
    public void sorted(){
        /*List<Message> expected = new ArrayList<>();
        Message msg = new Message("Prueba5","Leo bon examen","JordiPtoAmo","Leo", new Timestamp(2020,12,18,13,3,52,311));
        expected.add(msg);
        Message msg1 = new Message("to me","recordatorio no ser tonto","Leo","Leo", new Timestamp(2020,12,18,13,3,52,273));
        expected.add(msg1);
        Message msg2 = new Message("Prueba2","Cute red panda","Mimi","Leo", new Timestamp(2020,12,18,13,3,52,307));
        expected.add(msg2);
        assertEquals(mailbox.sorted(Comparator.comparing(Message::getSender)),expected);*/
    }

    @Test
    public void filter(){
        List<Message> expected = new ArrayList<>();
        /*Message msg1 = new Message("Prueba2","Cute red panda","Mimi","Leo", new Timestamp(2020,12,18,13,3,52,307));
        expected.add(msg1);
        Message msg = new Message("Prueba5","Leo bon examen","JordiPtoAmo","Leo", new Timestamp(2020,12,18,13,3,52,311));
        expected.add(msg);
        assertEquals(mailbox.filter(MessageUtils.filterSubject("prueba")),expected);
    */}
}
