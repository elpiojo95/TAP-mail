package reflection;

import oop.MailStore;
import oop.MailSystem;
import redis.AbstractMailStoreFactory;
import redis.FileMailStoreFactory;
import redis.MemoryMailStoreFactory;
import redis.RedisMailStoreFactory;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        MailSystem mailSystem;


        mailSystem = new ReflectingMailSystem();
        System.out.println("Memory numberMessagesmail:\t" + mailSystem.numberMessages());

    }
}
