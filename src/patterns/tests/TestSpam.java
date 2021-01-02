package patterns.tests;

import oop.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import patterns.EnhancedMailbox;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSpam {
    private EnhancedMailbox mailbox;
    private EnhancedMailbox mailbox2;
    private EnhancedMailbox mailbox3;
    private User user1;
    private User user2;
    private User user3;
    private File file;

    @Before
    public void Before() {
        System.out.println("before");
        file = new File("test2.txt");
        //memory mailStore
        MailStore mailStore = new FileMailStore("test2.txt");
        //user1: Leo
        Calendar leoBirth = new GregorianCalendar(1995,Calendar.SEPTEMBER,7);
        user1 = new User("Leo","Leandro",leoBirth);
        mailbox = new EnhancedMailbox(user1, mailStore);
        //user2: Mimi
        Calendar mimiBirth = new GregorianCalendar(1999,Calendar.JULY,22);
        user2 = new User("mimi","Miriam Gertrudix",mimiBirth);
        mailbox2 = new EnhancedMailbox(user2, mailStore);
        //user3: Joshua
        Calendar spamBirth = new GregorianCalendar(1999,Calendar.JULY,22);
        user3 = new User("spam","spam",spamBirth);
        mailbox3 = new EnhancedMailbox(user3, mailStore);
        //user2 send messages
        mailbox2.send(user1,"subject", "body");
        mailbox2.send(user1,"cervantes", "En un lugar de la Mancha de cuyo nombre no quiero acordarme");

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
        List<Message> expected= new ArrayList<>();
        mailbox.update();
        Message msg2 =  new Message("subject","body", user2, user1,new Timestamp(System.currentTimeMillis()));
        expected.add(msg2);

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
    public void spamList() {
        System.out.println("spamList() -> testing");
        List<Message> expected = new ArrayList<>();
        mailbox.update();
        Message msg1 = new Message("vaca","muuu",user3,user1,new Timestamp(System.currentTimeMillis()));
        expected.add(msg1);
        Message msg2 =  new Message("cervantes","En un lugar de la Mancha de cuyo nombre no quiero acordarme", user2, user1,new Timestamp(System.currentTimeMillis()));
        expected.add(msg2);

        assertEquals(mailbox.spamList().size(),expected.size());
        for (int i = 0; i < mailbox.spamList().size(); i++){
            assertEquals(mailbox.spamList().get(i).getReceiver().getUsername(),expected.get(i).getReceiver().getUsername());
            assertEquals(mailbox.spamList().get(i).getSender().getUsername(),expected.get(i).getSender().getUsername());
            assertEquals(mailbox.spamList().get(i).getSubject(),expected.get(i).getSubject());
            assertEquals(mailbox.spamList().get(i).getBody(),expected.get(i).getBody());
        }
        System.out.println("spamList() -> tested");

    }
}
