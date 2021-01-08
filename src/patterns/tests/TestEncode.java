package patterns.tests;

import oop.FileMailStore;
import oop.MailSystem;
import oop.Mailbox;
import oop.User;
import org.junit.Before;
import org.junit.Test;
import patterns.MailStoreCipherDecorator;
import patterns.MailStoreDecorator;
import patterns.MailStoreReverseDecorator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestEncode {
    private User user1;
    private User user2;
    private User user3;
    private Mailbox mbox1;
    private Mailbox mbox2;
    private Mailbox mbox3;
    private File file;

    @Before
    public void Before() {
        file = new File("EncodingJUnit.txt");
        FileMailStore file = new FileMailStore("EncodingJUnit.txt");
        MailStoreDecorator cipher = new MailStoreCipherDecorator(file);
        MailStoreDecorator reverse = new MailStoreReverseDecorator(cipher);
        MailSystem mailSystem = new MailSystem(reverse);

        user1 = new User("user1", "name1", new GregorianCalendar(2000, Calendar.JANUARY, 1));
        user2 = new User("user2", "name2", new GregorianCalendar(1999, Calendar.JANUARY, 1));
        user3 = new User("user3", "name3", new GregorianCalendar(2001, Calendar.JANUARY, 1));

        mbox1 = mailSystem.createUser(user1);
        mbox2 = mailSystem.createUser(user2);
        mbox3 = mailSystem.createUser(user3);

        mbox1.send(user2, "subject", "Student marks");
        mbox2.send(user1,"heey","hola que tal bro");
        mbox3.send(user1, "subject2", "body");
        mbox2.send(user3, "group", "body");
        mbox1.send(user3, "group2", "body2");
        mbox3.send(user2, "subject3", "body");
    }

    @Test
    public void ReversingCiphering() {
        try {
            int i= 0;
            List<String> expected = new ArrayList<>();
            expected.add("user2#name2#1999/Jan/01;user1#name1#2000/Jan/01;subject;9v8ZO/cqSiVH5xCBYsrzqQ==;");
            expected.add("user1#name1#2000/Jan/01;user2#name2#1999/Jan/01;heey;iKJkk64vr3TpYirdizJwZQsfKgOAcAbysfDNEHE4EZY=;");
            expected.add("user1#name1#2000/Jan/01;user3#name3#2001/Jan/01;subject2;6uv2DIbSEr4ebeRwpOgpMw==;");
            expected.add("user3#name3#2001/Jan/01;user2#name2#1999/Jan/01;group;6uv2DIbSEr4ebeRwpOgpMw==;");
            expected.add("user3#name3#2001/Jan/01;user1#name1#2000/Jan/01;group2;jPMCmmw7vZyBLV5fh/3VjA==;");
            expected.add("user2#name2#1999/Jan/01;user3#name3#2001/Jan/01;subject3;6uv2DIbSEr4ebeRwpOgpMw==;");
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
    }
}
