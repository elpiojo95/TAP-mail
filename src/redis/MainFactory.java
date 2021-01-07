package redis;

import oop.MailSystem;
import patterns.EnhancedMailSystem;

/**
 * Main to test factory pattern
 */
public class MainFactory {
    public static void main(String[] args) {
        AbstractMailStoreFactory factory;
        MailSystem mailSystem;

        factory = new MemoryMailStoreFactory();
        mailSystem = new EnhancedMailSystem(factory.createMailStore());
        System.out.println("Memory numberMessages:\t" + mailSystem.numberMessages());

        factory = new FileMailStoreFactory();
        mailSystem = new EnhancedMailSystem(factory.createMailStore());
        System.out.println("File numberMessages:\t" + mailSystem.numberMessages());

        factory = new RedisMailStoreFactory();
        mailSystem = new EnhancedMailSystem(factory.createMailStore());
        System.out.println("Redis numberMessages:\t\t" + mailSystem.numberMessages());
    }
}
