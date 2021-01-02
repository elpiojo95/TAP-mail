package patterns;

import oop.*;

import java.util.List;

public class TooLongFilter extends Observer {
    @Override
    public void update(EnhancedMailbox mailbox) {
        List<Message> spam = mailbox.filter(MessageUtils.filterNumChars(20));
        mailbox.copySpamList(spam);
    }
}
