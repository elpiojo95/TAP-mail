package patterns;

import oop.*;

import java.util.List;
/**
 * Class to filter the messages with more than 20 characters with the observer pattern
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class TooLongFilter extends Observer {
    /**
     * Update method filtered
     * @param mailbox EnhancedMailbox instance
     */
    @Override
    public void update(EnhancedMailbox mailbox) {
        List<Message> spam = mailbox.filter(MessageUtils.filterNumChars(20));
        mailbox.copySpamList(spam);
    }
}
