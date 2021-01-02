package patterns;

import oop.*;

import java.util.List;

public class SpamUserFilter extends Observer {
    @Override
    public void update(EnhancedMailbox mailbox) {
        List<Message> spam = mailbox.filter(MessageUtils.filterSenderName("spam"));
        mailbox.copySpamList(spam);

    }
}
