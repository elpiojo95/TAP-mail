package patterns;

import oop.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * improvement in the mailbox to split spam messages
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class EnhancedMailbox extends Mailbox {
    private List<Observer> spamFilters = new ArrayList<>();
    private List<Message> spamList = new ArrayList<>();


    /**
     * Class constructor
     * @param username  username
     * @param mailStore type of storage
     */
    public EnhancedMailbox(User username, MailStore mailStore) {
        super(username, mailStore);
        spamFilters.add(new SpamUserFilter());
        spamFilters.add(new TooLongFilter());
    }

    /**
     * update method improved
     */
    @Override
    public void update(){
        this.messageList = mailStore.getMessages(this.user);
        for (Observer observer : spamFilters) {
            observer.update(this);
        }
    }

    /**
     * getter of spamList
     * @return list of messages
     */
    public List<Message> spamList() {
        return this.spamList;
    }

    /**
     * Method to copy a list into spamlist and remove it from messageList
     * @param list list of spam
     */
    public void copySpamList(List<Message> list){
        for (Message msg : list) {
            this.spamList.add(msg);
            this.messageList.remove(msg);
        }
    }
}
