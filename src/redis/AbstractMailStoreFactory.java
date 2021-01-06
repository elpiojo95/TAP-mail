package redis;

import oop.MailStore;

public interface AbstractMailStoreFactory {
    MailStore createMailStore();
}
