package patterns;

import oop.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EnhancedMailbox extends Mailbox {
    private List<Observer> spamFilters = new ArrayList<>();
    private List<Message> spamList = new ArrayList<>();
    private Mailbox instance = new Mailbox(this.getUser(),this.mailStore);


    /**
     * Class constructor
     *
     * @param username  username
     * @param mailStore type of storage
     */
    public EnhancedMailbox(User username, MailStore mailStore) {
        super(username, mailStore);
        spamFilters.add(new SpamUserFilter());
        spamFilters.add(new TooLongFilter());
    }

    @Override
    public void update(){
        this.messageList = mailStore.getMessages(this.user);
        for (Observer observer : spamFilters) {
            observer.update(this);
        }

    }

    public List<Message> spamList() {
        return this.spamList;
    }


    public void copySpamList(List<Message> list){
        for (Message msg : list) {
            this.spamList.add(msg);
            this.messageList.remove(msg);
        }
    }


}
