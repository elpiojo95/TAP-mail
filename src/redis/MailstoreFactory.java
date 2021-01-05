package redis;

import oop.FileMailStore;
import oop.MailStore;
import oop.MemoryMailStore;
import patterns.MailStoreDecorator;
import patterns.MailstoreChiperDecorator;
import patterns.MailstoreReverseDecorator;

public class MailstoreFactory {
    public MailStore getMailstore (String mailStoreType){
        if (mailStoreType == null){
            return null;
        }
        if (mailStoreType.toLowerCase().equals("memory")){
            return new MemoryMailStore();
        }
        if(mailStoreType.toLowerCase().equals("redis")){
            return null;
        }
        if (mailStoreType.toLowerCase().equals("file")){
            FileMailStore file = new FileMailStore();
            MailStoreDecorator cipher = new MailstoreChiperDecorator(file);
            MailStoreDecorator reverse = new MailstoreReverseDecorator(cipher);

        }
    }
}
