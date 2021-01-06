package redis;


import oop.MailStore;
import oop.MemoryMailStore;

public class MemoryMailStoreFactory implements AbstractMailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new MemoryMailStore();
    }
}
