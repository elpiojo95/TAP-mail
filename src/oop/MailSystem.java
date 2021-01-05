package oop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class MailSystem {
    private MailStore mStore;
    private List<User> userList;

    public MailSystem(MailStore mailStore){
        mStore = mailStore;
        userList = mStore.getAllUsers();
    }

    public Mailbox createUser(String username, String name, Calendar birthDate){
        User user = new User(username, name, birthDate);
        userList.add(user);
        return new Mailbox(user, mStore);
    }

    public Mailbox createUser(User user){
        userList.add(user);
        return new Mailbox(user, mStore);
    }

    public List<Message> getMessageList(){
        return mStore.getAllMessages();
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Message> filter(java.util.function.Predicate<Message> predicate){
        ArrayList<Message> list = new ArrayList<>();
        mStore.getAllMessages().stream().filter(predicate).forEach(list::add);
        return list;
    }

    public int numberMessages(){
        return mStore.getAllMessages().size();
    }

    public double average(){
        return ((double)(mStore.getAllMessages().size()) / (userList.size()));
    }

    public List <Message> filterPerSubject(String subject){
        List<Message> subjectSorted = new ArrayList<>();
        mStore.getAllMessages().stream().filter((Message m) -> m.getSubject().toLowerCase().contains(subject.toLowerCase())).forEach(subjectSorted::add);
        return subjectSorted;
    }

    public List <Message> filterPerWord(String word){
        List<Message> wordFilter = new ArrayList<>();
        mStore.getAllMessages().stream().filter((Message m) ->( m.getSubject().toLowerCase().contains(word.toLowerCase()))|| m.getBody().toLowerCase().contains(word.toLowerCase()) ).forEach(wordFilter::add);
        return wordFilter;
    }

    public List <Message> filterPerNumWords(int n){
        List<Message> lessNWords = new ArrayList<>();
        for (Message message : mStore.getAllMessages()) {
            if (n >= message.getBody().split("[\\w]+").length){
                lessNWords.add(message);
            }
        }
        return lessNWords;
    }
    
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

    public List<Message> bornBefore(int year){
        Calendar yearDate = new GregorianCalendar(year, Calendar.JANUARY,1 );
        List<Message> messageBeforeList = new ArrayList<>();
        mStore.getAllMessages().stream().filter(message -> message.getReceiver().getBirthDate().before(yearDate)).forEach(messageBeforeList::add);
        return messageBeforeList;
    }

    public MailStore getmStore() {
        return mStore;
    }


    public int swap() {
        if (mStore instanceof FileMailStore) {
            mStore = new MemoryMailStore((FileMailStore) mStore);
        }else {
            mStore = new FileMailStore((MemoryMailStore) mStore);
        }
        return 0;
    }
}
