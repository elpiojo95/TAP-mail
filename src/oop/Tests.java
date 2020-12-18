package oop;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class Tests {
    public static void main(String[] args) {
        System.out.println("----Start----");
        //File f= new File ("mailStore.txt");
        //MemoryMailStore memory = new MemoryMailStore();
        FileMailStore file = new FileMailStore();
        //MailSystem mailSystem = new MailSystem(memory);
        MailSystem mailSystem = new MailSystem(file);
        System.out.println("----Start testing MailSystem----");
        Calendar leoBirth = Calendar.getInstance();
        leoBirth.set(1995, Calendar.SEPTEMBER,7);
        Calendar mimiBirth = Calendar.getInstance();
        mimiBirth.set(1999, Calendar.JULY,22);
        Calendar jordiBirth = Calendar.getInstance();
        jordiBirth.set(2010, Calendar.SEPTEMBER, 14);
        Mailbox mbleo = mailSystem.createUser("Leo","Leandro",leoBirth);
        Mailbox mbmimi = mailSystem.createUser("Mimi","Miriam",mimiBirth);
        Mailbox mbjordi = mailSystem.createUser("JordiPtoAmo", "Leandro", jordiBirth);

        mbleo.send("Leo", "to me", "recordatorio no ser tonto");
        mbleo.send("JordiPtoAmo","Prueba3","holi");
        mbleo.send("Mimi","Prueba1","hola que tal bro");

        mbmimi.send("Leo", "Prueba2", "Cute red panda");
        mbmimi.send("JordiPtoAmo","Prueba4","heey");
        mbmimi.send("Mimi","to myself","soy tonta y me gusta serlo");

        mbjordi.send("Leo", "Prueba5", "Leo bon examen");
        mbjordi.send("JordiPtoAmo","per mi","prueba de words");
        mbjordi.send("Mimi","alumna exemplar","segueix aixi crack");



        System.out.println("----Messages in the system----");
        System.out.println(mailSystem.getMessageList());
        System.out.println("--------");
        System.out.println("----Users in the System----");
        System.out.println(mailSystem.getUserList());
        System.out.println("--------");
        System.out.println("----filter by sender----");
        System.out.println(mailSystem.filter(MessageUtils.filterSender("JordiPtoAmo")));
        System.out.println("--------");
        System.out.println("----filter word ----");
        System.out.println(mailSystem.filterPerWord("prueba"));
        System.out.println("--------");
        System.out.println("----filter num word ----");
        System.out.println(mailSystem.filterPerNumWords(4));
        System.out.println("--------");
        System.out.println("----number of messages----");
        System.out.println(mailSystem.NumberMessages());
        System.out.println("--------");
        System.out.println("----Average----");
        System.out.println(mailSystem.average());
        System.out.println("--------");
        System.out.println("----group per PRUEBA----");
        System.out.println(mailSystem.filterPerSubject("Prueba"));
        System.out.println("--------");
        System.out.println("----Count words users named Leandro----");
        System.out.println(mailSystem.wordCounterByName("Leandro"));
        System.out.println("--------");
        System.out.println("----messages users borned before 2000----");
        System.out.println(mailSystem.bornBefore(2000));
        System.out.println("--------");

        mbleo.update();
        System.out.println("\n\n----message list leo----");
        System.out.println(mbleo.messageList());
        System.out.println("--------");

    }
}
