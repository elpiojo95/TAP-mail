package redis;

import oop.FileMailStore;
import oop.MailStore;
import oop.MemoryMailStore;

/**
 * Factory of instances of mailStore
 */
public class MailStoreFactory {
    public MailStore getMailStore (String mailStoreType) {
        return switch (mailStoreType) {
            case "memory" -> new MemoryMailStore();
            case "redis" -> new MailstoreToRedisAdapter(new RedisMailStore());
            case "file" -> new FileMailStore();
            default -> null;
        };
    }

    /**
     * Method to change FileMailStore Type to MemoryMailStore Type
     * @param mailStoreType String mailStoreType
     * @param fms instance of FileMailStore
     * @return null
     */
    public MailStore getMailStore (String mailStoreType, FileMailStore fms) {
        if (mailStoreType.equals("memory")) return new MemoryMailStore(fms);
        return null;
    }

    /**
     * Method to change MemoryMailStore Type to FileMailStore Type
     * @param mailStoreType String mailStoreType
     * @param mms instance of MemoryMailStore
     * @return null
     */
    public MailStore getMailStore (String mailStoreType, MemoryMailStore mms) {
        if (mailStoreType.equals("file")) return new FileMailStore(mms);
        return null;
    }

    /**
     * Method to create a FileMailStore Type
     * @param mailStoreType String mailStoreType
     * @param path FileMailStore path file
     * @return null
     */
    public MailStore getMailStore (String mailStoreType, String path) {
        if (mailStoreType.equals("file")) return new FileMailStore(path);
        return null;
    }
}
