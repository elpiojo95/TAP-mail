package oop;

import java.util.Calendar;
import java.util.Date;

public class Tests {
    public static void main(String[] args) {
        System.out.println("----Start----");
        MemoryMailStore memory = new MemoryMailStore();
        MailSystem mailSystem = new MailSystem(memory);
        System.out.println("----Start testing MailSystem----");
        Mailbox mbleo = mailSystem.createUser("Leo","Leandro", new Date(1995, Calendar.SEPTEMBER,7));
        Mailbox mbmimi = mailSystem.createUser("Mimi","Miriam", new Date(1999, Calendar.JULY,22));
        Mailbox mbjordi = mailSystem.createUser("JordiPtoAmo", "Leandro", new Date(2010, Calendar.SEPTEMBER, 14));

        mbleo.send("Leo", "to me", "recordatorio no ser tonto");
        mbleo.send("JordiPtoAmo","Prueba3","holi");
        mbleo.send("Mimi","Prueba1","hola que tal bro");

        mbmimi.send("Leo", "Prueba2", "Cute red panda");
        mbmimi.send("JordiPtoAmo","Prueba4","heey");
        mbmimi.send("Mimi","to myself","soy tonta y me gusta serlo");

        mbjordi.send("Leo", "Prueba5", "Leo bon examen!");
        mbjordi.send("JordiPtoAmo","per mi","recorda posar un 10 a la mimi i al leo");
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


        /*Mailbox mb = new Mailbox("Leo", memory);
        Mailbox mb2 = new Mailbox("Mimi", memory);
        mb2.send("Leo", "Prueba2", "Cute red panda");
        mb.send("Leo", "to me", "recordatorio no ser tonto");
        mb2.send("Leo", "TAP", "Es mejor que IPO");
        mb2.send("Leo", "A Dormir que es TARDE!!", "No ves la hora?\nMañana hay que madrugar!");
        mb.send("Mimi","Prueba1","hola que tal bro");
        mb.send("Mimi","Prueba3","holi");
        mb.send("Mimi","Prueba4","heey");
        mb2.send("Mimi","to myself","soy tonta y me gusta serlo");

        ArrayList<Message> messagelist = new ArrayList<>();
        messagelist.add(new Message("Prueba 1",
                "Hola que tal bro",
                "Mimi",
                "Leo",
                new Timestamp(System.currentTimeMillis())));
        messagelist.add(new Message("TAP",
                "Es mejor que IPO",
                "Favio",
                "Leo",
                new Timestamp(System.currentTimeMillis())));
        messagelist.add(new Message("Prueba 2",
                "Cute red panda",
                "Mimi",
                "Leo",
                new Timestamp(System.currentTimeMillis())));
        messagelist.add(new Message("A Dormir que es TARDE!!",
                "No ves la hora?\nMañana hay que madrugar!",
                "AAMama",
                "Leo",
                new Timestamp(System.currentTimeMillis())));

        System.out.println("---LEO'S---");
        mb.update();
        System.out.println("---\nsorted by sender\n---");
        System.out.println(mb.sorted(Comparator.comparing(Message::getSender)));
        System.out.println("---\nMessages\n---");
        System.out.println(mb.messageList());
        System.out.println("---\nFilter\n---");
        System.out.println(mb.filter(MessageUtils.filterSubject("prueba")));
        System.out.println("-----''-----");

        System.out.println("\n---MIMI'S---");
        mb2.update();
        System.out.println("---\nsorted by sender\n---");
        System.out.println(mb2.sorted(Comparator.comparing(Message::getSender)));
        System.out.println("---\nMessages\n---");
        System.out.println(mb2.messageList());
        System.out.println("---\nFilter\n---");
        System.out.println(mb2.filter(MessageUtils.filterSubject("prueba")));
        System.out.println("-----''-----");
         */
    }
}
