package redis;

import oop.FileMailStore;
import oop.MailStore;
import oop.MemoryMailStore;
import patterns.MailStoreDecorator;
import patterns.MailstoreChiperDecorator;
import patterns.MailstoreReverseDecorator;

public class MailStoreFactory {
    public MailStore getMailStore (String mailStoreType) {
        return switch (mailStoreType) {
            case "memory" -> new MemoryMailStore();
            case "redis" -> new MailstoreToRedisAdapter(new RedisMailStore());
            case "file" -> new FileMailStore();
            default -> null;
        };
    }

    public MailStore getMailStore (String mailStoreType, FileMailStore fms) {
        if (mailStoreType.equals("memory")) return new MemoryMailStore(fms);
        return null;
    }

    public MailStore getMailStore (String mailStoreType, MemoryMailStore mms) {
        if (mailStoreType.equals("file")) return new FileMailStore(mms);
        return null;
    }

    public MailStore getMailStore (String mailStoreType, String path) {
        if (mailStoreType.equals("file")) return new FileMailStore(path);
        return null;
    }
}
