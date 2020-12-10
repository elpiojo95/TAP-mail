package oop;

import java.util.ArrayList;
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

    public Mailbox createUser(String username, String name, Date birthDate){
        userList.add(new User(username, name, birthDate));
        Mailbox mbox = new Mailbox(username, mStore);
        mailboxesList.add(mbox);
        return mbox;
    }

    public List<Message> getMessageList(){
        userList.forEach(user -> messageList.addAll(mStore.get(user.getUsername())));
        return messageList;
    }

    public List<User> getUserList() {
        return userList;
    }

    /*FILTER MESSAGES GLOBALY NOT IMPLEMENTED*/

    public int NumberMessages(){
        return messageList.size();
    }

    /*AVERAGE NUMBER OF MESSAGES*/

    public List <Message> sortPerSubject (String subject){
        List<Message> subjectSorted = new ArrayList<>();
        messageList.stream().filter((Message m) -> m.getSubject().toLowerCase().contains(subject.toLowerCase())).forEach(subjectSorted::add);
        return subjectSorted;
    }

    /*COUNT THE WORDS OF ALL MESSAGES OF USERS WITH X NAME*/

    /*MESSAGES FROM USERS BORN BEFORE X YEAR*/

}
