package patterns;

import oop.MailStore;

/**
 * Decorator pattern to mailStore
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public abstract class MailStoreDecorator implements MailStore {
    protected MailStore mailStore;
    protected BodyEncryptionStrategy bodyStrategy;

    /**
     * Class constructor
     * @param ms MailStore
     */
    public MailStoreDecorator(MailStore ms){
        this.mailStore = ms;
    }
}
