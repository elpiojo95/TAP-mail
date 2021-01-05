package redis;

import oop.MailStore;
import oop.Message;
import oop.User;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailstoreToRedisAdapter implements MailStore {

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

    public MailstoreToRedisAdapter(RedisMailStore redisMailStore) {
        this.redisMailStore = redisMailStore;
    }

    @Override
    public void send(Message msg) throws IOException {
        redisMailStore.send(msg);
    }

    @Override
    public List<Message> getMessages(User user) {
        List<Message> list = new ArrayList<>();
        for (String m : redisMailStore.getMessages(user.getUsername())) {
            list.add(this.toMessage(m));
        }
        return list;
    }

    @Override
    public List<Message> getAllMessages() {
        List<Message> list = new ArrayList<>();
        for (String m : redisMailStore.getAllMessages()) {
            list.add(this.toMessage(m));
        }
        return list;
    }

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
        //Per provar
        Timestamp creationTime = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(m.group(1));
            creationTime = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Timestamp creationTime = m.group(1);

        Message msg = new Message(subject,body,sender,receiver,creationTime);
        return msg;
    }


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

        User user = new User(username, name, birthdate);
        return user;
    }
}
