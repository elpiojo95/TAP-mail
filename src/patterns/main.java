package patterns;

import oop.FileMailStore;
import oop.MailSystem;
import oop.Mailbox;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class main {
    public static void main(String[] args) {
        FileMailStore file = new FileMailStore();
        MailStoreDecorator cipher = new MailstoreChiperDecorator(file);
        MailStoreDecorator reverse = new MailstoreReverseDecorator(cipher);
        MailSystem mailSystem = new MailSystem(reverse);
        Calendar leoBirth = new GregorianCalendar(1995, Calendar.SEPTEMBER, 7);
        Calendar mimiBirth = new GregorianCalendar(1999, Calendar.JULY,22);
        Mailbox mbleo = mailSystem.createUser("Leo","Leandro",leoBirth);
        Mailbox mbmimi = mailSystem.createUser("Mimi","Miriam",mimiBirth);

        mbmimi.send(mbleo.getUser(), "subject", "Student marks");
        mbleo.send(mbmimi.getUser(),"heey","hola que tal bro");

        mbleo.update();
        mbmimi.update();
        System.out.println("leo messages: "+mbleo.messageList());
        System.out.println("mimi messages: "+mbmimi.messageList());

    }
}
