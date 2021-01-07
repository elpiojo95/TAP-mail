package redis;

import oop.MailStore;

/**
 * RedisMailStoreFactory pattern
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class RedisMailStoreFactory implements AbstractMailStoreFactory{
    /**
     * method to return the instance of RedisMailStore
     * @return RedisMailStore instance
     */
    @Override
    public MailStore createMailStore() {
        return new MailstoreToRedisAdapter(new RedisMailStore());
    }
}
