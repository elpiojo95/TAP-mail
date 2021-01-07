package redis;

import oop.MailStore;

/**
 * Abstract MailStore Factory pattern interface
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public interface AbstractMailStoreFactory {
    MailStore createMailStore();
}
