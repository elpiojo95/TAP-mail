package oop;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

public class Tests {
    public static void main(String[] args) {
        System.out.println("----Start----");
        Mailbox mb = new Mailbox("Leo");
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


        mb.Update(messagelist);
        System.out.println(mb.Sorted(Comparator.comparing(Message::getSender)));
        System.out.println("---\nFilter\n---");
        System.out.println(mb.Filter(MessageUtils.filterSubject("prueba")));
    }
}
