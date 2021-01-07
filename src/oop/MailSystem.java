package oop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Represents a System for the admin ans implements general operations
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class MailSystem {
    protected MailStore mStore;
    protected List<User> userList;

    /**
     *  Class constructor
     *
     */
    public MailSystem(){
    }

    /**
     *  Class constructor
     * @param mailStore MailStore
     */
    public MailSystem(MailStore mailStore){
        mStore = mailStore;
        userList = mStore.getAllUsers();
    }

    /**
     * Creates a new user in the system, by passing the information for parameter
     * @param username username of the new user
     * @param name name of the new user
     * @param birthDate birthdate of the new user
     * @return the mailbox of the new user
     */
    public Mailbox createUser(String username, String name, Calendar birthDate){
        User user = new User(username, name, birthDate);
        userList.add(user);
        return new Mailbox(user, mStore);
    }

    /**
     * Creates a new user in the system, by passing the user for parameter
     * @param user new user
     * @return the mailbox of the new user
     */
    public Mailbox createUser(User user){
        userList.add(user);
        return new Mailbox(user, mStore);
    }

    /**
     * returns a list of all messages in the system
     * @return list of all messages
     */
    public List<Message> getMessageList(){
        return mStore.getAllMessages();
    }

    /**
     * returns a list of all users in the system
     * @return list of users
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * filters the messages by a predicate
     * @param predicate predicate to filter
     * @return list of messages filtered
     */
    public List<Message> filter(java.util.function.Predicate<Message> predicate){
        ArrayList<Message> list = new ArrayList<>();
        mStore.getAllMessages().stream().filter(predicate).forEach(list::add);
        return list;
    }

    /**
     * return the number of messages in the system
     * @return int number of messages
     */
    public int numberMessages(){
        return mStore.getAllMessages().size();
    }

    /**
     * makes the average of messages per user
     * @return double number of messages per user
     */
    public double average(){
        return ((double)(mStore.getAllMessages().size()) / (userList.size()));
    }

    /**
     * filters messages in the system that contains the string in parameter on subject
     * @param subject subject to filter
     * @return list of messages
     */
    public List <Message> filterPerSubject(String subject){
        List<Message> subjectSorted = new ArrayList<>();
        mStore.getAllMessages().stream().filter((Message m) -> m.getSubject().toLowerCase().contains(subject.toLowerCase())).forEach(subjectSorted::add);
        return subjectSorted;
    }

    /**
     * filters messages in the system that contains the word
     * @param word word to filter
     * @return list of messages
     */
    public List <Message> filterPerWord(String word){
        List<Message> wordFilter = new ArrayList<>();
        mStore.getAllMessages().stream().filter((Message m) ->( m.getSubject().toLowerCase().contains(word.toLowerCase()))|| m.getBody().toLowerCase().contains(word.toLowerCase()) ).forEach(wordFilter::add);
        return wordFilter;
    }

    /**
     *
     * filters messages in the system that the body contains less than n words
     * @param n maximum of words
     * @return list of messages
     */
    public List <Message> filterPerNumWords(int n){
        List<Message> lessNWords = new ArrayList<>();
        for (Message message : mStore.getAllMessages()) {
            if (n >= message.getBody().split("[\\w]+").length){
                lessNWords.add(message);
            }
        }
        return lessNWords;
    }

    /**
     * counts de number of words of all messages of the users with a name specified by parameter
     * @param name name of the sender
     * @return number of words
     */
    public int wordCounterByName(String name){
        int result = 0;
        List<Message> messageUsersNameList = new ArrayList<>();
        mStore.getAllMessages().stream()
                .filter(message -> message.getSender()
                        .getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .forEach(messageUsersNameList::add);
        for (Message message : messageUsersNameList) {
            result = result + (message.getBody().split(" ")).length;
        }
        return result;
    }

    /**
     * list the messages of the receivers born before a year specified by parameter
     * @param year year
     * @return list of messages
     */
    public List<Message> bornBefore(int year){
        Calendar yearDate = new GregorianCalendar(year, Calendar.JANUARY,1 );
        List<Message> messageBeforeList = new ArrayList<>();
        mStore.getAllMessages().stream().filter(message -> message.getReceiver().getBirthDate().before(yearDate)).forEach(messageBeforeList::add);
        return messageBeforeList;
    }

    /**
     * getter of mailStore
     *
     */
    public MailStore getmStore() {
        return mStore;
    }


    /**
     * Swaps the mailStore Type, File Type to Memory or vice versa
     * @return 1 - error, 0 - correct
     */
    public int swap() {
        if (mStore instanceof FileMailStore) {
            mStore = new MemoryMailStore((FileMailStore) mStore);
        }else if (mStore instanceof MemoryMailStore) {
            mStore = new FileMailStore((MemoryMailStore) mStore);
        }
        else return 1;
        return 0;
    }
}
