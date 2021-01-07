package redis;


import oop.MailStore;
import oop.MemoryMailStore;

/**
 * MemoryMailStoreFactory pattern
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class MemoryMailStoreFactory implements AbstractMailStoreFactory {
    /**
     * method to return the instance of MemoryMailStore
     * @return MemoryMailStore instance
     */
    @Override
    public MailStore createMailStore() {
        return new MemoryMailStore();
    }
}
