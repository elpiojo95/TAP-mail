package redis;

import oop.FileMailStore;
import oop.MailStore;

public class FileMailStoreFactory implements AbstractMailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return new FileMailStore();
    }
}
