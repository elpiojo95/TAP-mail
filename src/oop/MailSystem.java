package oop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MailSystem {
    private MailStore mStore;
    private List<User> userList;
    private List<Mailbox> mailboxesList;
    private List<Message> messageList;

    public MailSystem(MailStore mailStore){
        mStore = mailStore;
        userList = new ArrayList<>();
        mailboxesList = new ArrayList<>();
        messageList = new ArrayList<>();
    }

    public Mailbox createUser(String username, String name, Calendar birthDate){
        User user = new User(username, name, birthDate);
        userList.add(user);
        Mailbox mbox = new Mailbox(user, mStore);
        mailboxesList.add(mbox);
        return mbox;
    }


    public List<Message> getMessageList(){
        List<Message> newMsg = new ArrayList<>();
        userList.stream().map(user -> mStore.get(user.getUsername())).forEach(newMsg::addAll);
        newMsg.stream().filter(message -> !messageList.contains(message)).forEach(messageList::add);
        return messageList;
    }

    public List<User> getUserList() {
        return userList;
    }

    /*FILTER MESSAGES GLOBALY NOT IMPLEMENTED*/

    public List<Message> filter(java.util.function.Predicate<Message> predicate){
        ArrayList<Message> list = new ArrayList<>();
        messageList.stream().filter(predicate).forEach(list::add);
        return list;
    }

    public int NumberMessages(){
        return messageList.size();
    }

    public int average(){
        return ((messageList.size()) / (userList.size()));
    }

    public List <Message> filterPerSubject(String subject){
        List<Message> subjectSorted = new ArrayList<>();
        messageList.stream().filter((Message m) -> m.getSubject().toLowerCase().contains(subject.toLowerCase())).forEach(subjectSorted::add);
        return subjectSorted;
    }
    
    public int wordCounterByName(String name){
        int result = 0;
        List<User> usersNamed = new ArrayList<>();
        List<Message> messageUsersNameList = new ArrayList<>();
        userList.stream().filter((User u) -> u.getName().toLowerCase().contains(name.toLowerCase())).forEach(usersNamed::add);
        usersNamed.forEach(user -> messageList.stream().filter((Message m) -> m.getSender().toLowerCase().contains(user.getUsername().toLowerCase())).forEach(messageUsersNameList::add));

    /*COUNT THE WORDS OF ALL MESSAGES OF USERS WITH X NAME*/

    /*MESSAGES FROM USERS BORN BEFORE X YEAR*/

}
