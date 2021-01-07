package redis;

import oop.MailStore;
import oop.MailSystem;
import patterns.EnhancedMailSystem;

/**
 * Main to test factory pattern
 */
public class MainFactory {
    public static void main(String[] args) {
        AbstractMailStoreFactory factory;
        MailStore mailStore;
        MailSystem mailSystem;

        factory = new MemoryMailStoreFactory();
        mailSystem = new EnhancedMailSystem(factory.createMailStore());
        System.out.println("Memory numberMessagesmail:\t" + mailSystem.numberMessages());

        factory = new FileMailStoreFactory();
        mailSystem = new EnhancedMailSystem(factory.createMailStore());
        System.out.println("File numberMessagesmail:\t" + mailSystem.numberMessages());

        factory = new RedisMailStoreFactory();
        mailSystem = new EnhancedMailSystem(factory.createMailStore());
        System.out.println("Redis numberMessages:\t\t" + mailSystem.numberMessages());
    }
}
