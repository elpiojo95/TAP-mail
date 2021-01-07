package patterns;

import oop.*;

import java.util.List;

/**
 * Class to filter the messages with spam as a subject with the observer pattern
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class SpamUserFilter extends Observer {
    /**
     * Update method filtered
     * @param mailbox EnhancedMailbox instance
     */
    @Override
    public void update(EnhancedMailbox mailbox) {
        List<Message> spam = mailbox.filter(MessageUtils.filterSenderName("spam"));
        mailbox.copySpamList(spam);

    }
}
