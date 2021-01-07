package redis;

import oop.MailStore;
import oop.Message;
import oop.User;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * adapt the mailStore to Redis with adapter pattern
 */
public class MailStoreToRedisAdapter implements MailStore {

    RedisMailStore redisMailStore;
    Pattern subjectPattern = Pattern.compile("subject='([\\w ]*)'");
    Pattern bodyPattern = Pattern.compile("body='([\\w ]*)'");
    Pattern senderPattern = Pattern.compile("sender='User\\{([\\w=', :]*)}',");
    Pattern receiverPattern = Pattern.compile("receiver='User\\{([\\w=', :]*)}',");
    Pattern creationTimePattern = Pattern.compile("creationTime=([\\w-: .]*)");
    Pattern usernamePattern = Pattern.compile("username='([\\w]*)'.");
    Pattern namePattern = Pattern.compile(" name='([\\w ]*)'.");
    Pattern birthPattern = Pattern.compile(" birthDate=([\\w-: ]*)");
    public static Map<String, Integer> month;
    static {
        month = new HashMap<>();
        month.put("Jan", Calendar.JANUARY);
        month.put("Feb", Calendar.FEBRUARY);
        month.put("Mar", Calendar.MARCH);
        month.put("Apr", Calendar.APRIL);
        month.put("May", Calendar.MAY);
        month.put("Jun", Calendar.JUNE);
        month.put("Jul", Calendar.JULY);
        month.put("Aug", Calendar.AUGUST);
        month.put("Sep", Calendar.SEPTEMBER);
        month.put("Oct", Calendar.OCTOBER);
        month.put("Nov", Calendar.NOVEMBER);
        month.put("Dec", Calendar.DECEMBER);
    }

    /**
     * Class constructor
     * @param redisMailStore redisMailStore instance
     */
    public MailStoreToRedisAdapter(RedisMailStore redisMailStore) {
        this.redisMailStore = redisMailStore;
    }

    /**
     * send message to the redis server
     * @param msg message to send
     */
    @Override
    public void send(Message msg) {
        redisMailStore.send(msg);
    }

    /**
     * get messages for a certain user to de redis server
     * @param user user receiver
     * @return list of messages
     */
    @Override
    public List<Message> getMessages(User user) {
        List<Message> list = new ArrayList<>();
        for (String m : redisMailStore.getMessages(user.getUsername())) {
            list.add(this.toMessage(m));
        }
        return list;
    }

    /**
     * get all messages from the redis server
     * @return list of messages
     */
    @Override
    public List<Message> getAllMessages() {
        List<Message> list = new ArrayList<>();
        for (String m : redisMailStore.getAllMessages()) {
            list.add(this.toMessage(m));
        }
        return list;
    }

    /**
     * get all users from the redis server
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        for (String username : redisMailStore.getAllUsers()) {
            list.add(this.toMessage(redisMailStore
                    .getMessages(username)
                    .get(0)).getReceiver()
            );
        }
        return list;
    }

    /**
     * method to convert a string to Message instance
     * @param messageString string with message parameters
     * @return Message
     */
    public Message toMessage(String messageString) {
        Matcher m = subjectPattern.matcher(messageString);
        m.find();
        String subject = m.group(1);

        m = bodyPattern.matcher(messageString);
        m.find();
        String body = m.group(1);

        m = senderPattern.matcher(messageString);
        m.find();
        User sender = this.toUser(m.group(1));

        m = receiverPattern.matcher(messageString);
        m.find();
        User receiver = this.toUser(m.group(1));

        m = creationTimePattern.matcher(messageString);
        m.find();
        //Per probar
        Timestamp creationTime = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(m.group(1));
            creationTime = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Timestamp creationTime = m.group(1);

        return new Message(subject,body,sender,receiver,creationTime);
    }

    /**
     * method to convert a string to User instance
     * @param userString string with user parameters
     * @return User
     */
    public User toUser(String userString) {
        Matcher m = usernamePattern.matcher(userString);
        m.find();
        String username = m.group(1);

        m = namePattern.matcher(userString);
        m.find();
        String name = m.group(1);

        m = birthPattern.matcher(userString);
        m.find();
        //Per provar
        String[] date = m.group(1).split(" ");
        Calendar birthdate = new GregorianCalendar(Integer.parseInt(date[5]),month.get(date[1]), Integer.parseInt(date[2]));

        return new User(username, name, birthdate);
    }
}
