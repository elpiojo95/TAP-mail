package redis;

import oop.MailStore;

public class RedisMailStoreFactory implements AbstractMailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return new MailstoreToRedisAdapter(new RedisMailStore());
    }
}
