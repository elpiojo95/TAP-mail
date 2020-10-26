package oop;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Tests {
    public static void main(String[] args) {
        System.out.println("Hello");
        Mailbox mb = new Mailbox("Leo");
        ArrayList<Message> list = new ArrayList<>();
        list.add(new Message("haha saludos",
                "Hola que tal bro",
                "Leo",
                "B",
                new Timestamp(System.currentTimeMillis())));
        list.add(new Message("haha saludos",
                "Hola que tal bro",
                "Leo",
                "Z",
                new Timestamp(System.currentTimeMillis())));
        list.add(new Message("haha saludos",
                "Hola que tal bro",
                "Leo",
                "A",
                new Timestamp(System.currentTimeMillis())));
        mb.Update(list);
        System.out.println(mb.Sorted());
    }
}
