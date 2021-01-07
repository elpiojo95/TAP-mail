package redis;

import oop.FileMailStore;
import oop.MailStore;

/**
 * FileMailStoreFactory pattern
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class FileMailStoreFactory implements AbstractMailStoreFactory {
    /**
     * method to return the instance of FileMailStore
     * @return FileMailStore instance
     */
    @Override
    public MailStore createMailStore() {
        return new FileMailStore();
    }
}
