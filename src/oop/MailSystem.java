package oop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MailSystem {
    private MailStore mStore;

    public MailSystem(MailStore mailStore){
        mStore = mailStore;
    }

    public Mailbox createUser(String username, String name, Calendar birthDate){
        User user = new User(username, name, birthDate);
        mStore.getAllUsers().add(user);
        return new Mailbox(user, mStore);
    }

    public List<Message> getMessageList(){
        return mStore.getAllMessages();
    }

    public List<User> getUserList() {
        return mStore.getAllUsers();
    }

    public List<Message> filter(java.util.function.Predicate<Message> predicate){
        ArrayList<Message> list = new ArrayList<>();
        mStore.getAllMessages().stream().filter(predicate).forEach(list::add);
        return list;
    }

    public int NumberMessages(){
        return mStore.getAllMessages().size();
    }

    public int average(){
        return ((mStore.getAllMessages().size()) / (mStore.getAllUsers().size()));
    }

    public List <Message> filterPerSubject(String subject){
        List<Message> subjectSorted = new ArrayList<>();
        mStore.getAllMessages().stream().filter((Message m) -> m.getSubject().toLowerCase().contains(subject.toLowerCase())).forEach(subjectSorted::add);
        return subjectSorted;
    }

    public List <Message> filterPerWord(String word){
        List<Message> wordFilter = new ArrayList<>();
        mStore.getAllMessages().stream().filter((Message m) -> m.getSubject().toLowerCase().contains(word.toLowerCase())).forEach(wordFilter::add);
        return wordFilter;
    }

    public List <Message> filterPerNumWords(int n){
        List<Message> lessNWords = new ArrayList<>();
        for (Message message : mStore.getAllMessages()) {
            if (n > message.getBody().split("[\\w]+").length){
                lessNWords.add(message);
            }
        }
        return lessNWords;
    }
    
    public int wordCounterByName(String name){
        int result = 0;
        List<User> usersNamed = new ArrayList<>();
        List<Message> messageUsersNameList = new ArrayList<>();
        mStore.getAllUsers().stream()
                .filter((User u) ->
                        u.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase())
                ).forEach(usersNamed::add);

        usersNamed.forEach(user -> mStore.getAllMessages().stream()
                .filter((Message m) -> m.getSender()
                        .getUsername()
                        .toLowerCase()
                        .contains(user.getUsername().toLowerCase())
                ).forEach(messageUsersNameList::add)
        );

        for (Message message : messageUsersNameList) {
            result = result + message.getBody().split("[\\w]+").length;
        }
        return result;
    }

    public List<Message> bornBefore(int year){
        //Date yearDate = new Date(year, Calendar.DECEMBER,31);
        Calendar yearDate = Calendar.getInstance();
        yearDate.set(year, Calendar.DECEMBER,31);
        List<User> bornsortedlist = new ArrayList<>();
        List<Message> messageBeforeList = new ArrayList<>();
        mStore.getAllUsers().stream()
                .filter((User u) ->
                        u.getBirthDate()
                        .before(yearDate)
                ).forEach(bornsortedlist::add);
        bornsortedlist.forEach(user -> mStore.getAllMessages().stream()
                .filter((Message m) -> m.getReceiver()
                        .getUsername()
                        .toLowerCase()
                        .contains(user.getUsername().toLowerCase())
                ).forEach(messageBeforeList::add)
        );
        return messageBeforeList;
    }

    public MailStore getmStore() {
        return mStore;
    }
}
