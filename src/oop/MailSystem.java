package oop;

import java.util.*;


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
        User user = new User(username, name, birthDate);
        userList.add(user);
        Mailbox mbox = new Mailbox(user, mStore);
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

    public Mailbox getMailbox(User user) {
        return mailboxesList.stream()
                .filter(mailbox -> mailbox.getUser().equals(user))
                .findFirst()
                .orElse(null);
    }

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

        for (Message message : messageUsersNameList) {
            result = result + message.getBody().split("[\\w]+").length;
        }
        return result;
    }

    public List<Message> bornBefore(int year){
        Date yearDate = new Date(year, Calendar.DECEMBER,31);
        List<User> bornsortedlist = new ArrayList<>();
        List<Message> messageBeforeList = new ArrayList<>();
        userList.stream().filter((User u) -> u.getBirthDate().before(yearDate)).forEach(bornsortedlist::add);
        bornsortedlist.forEach(user -> messageList.stream().filter((Message m) -> m.getReceiver().toLowerCase().contains(user.getUsername().toLowerCase())).forEach(messageBeforeList::add));
        return messageBeforeList;
    }
}
