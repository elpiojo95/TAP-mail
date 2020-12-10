package oop;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

public class Tests {
    public static void main(String[] args) {
        System.out.println("----Start----");
        MemoryMailStore memory = new MemoryMailStore();
        Mailbox mb = new Mailbox("Leo", memory);
        Mailbox mb2 = new Mailbox("Mimi", memory);
        mb2.send("Leo", "Prueba2", "Cute red panda");
        mb.send("Leo", "to me", "recordatorio no ser tonto");
        mb2.send("Leo", "TAP", "Es mejor que IPO");
        mb2.send("Leo", "A Dormir que es TARDE!!", "No ves la hora?\nMañana hay que madrugar!");
        mb.send("Mimi","Prueba1","hola que tal bro");
        mb.send("Mimi","Prueba3","holi");
        mb.send("Mimi","Prueba4","heey");
        mb2.send("Mimi","to myself","soy tonta y me gusta serlo");

        /*ArrayList<Message> messagelist = new ArrayList<>();
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
            */
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
    }
}
