package reflection;

import oop.MailSystem;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        MailSystem mailSystem;


        mailSystem = new ReflectingMailSystem();
        System.out.println("Memory numberMessages:\t" + mailSystem.numberMessages());

    }
}
