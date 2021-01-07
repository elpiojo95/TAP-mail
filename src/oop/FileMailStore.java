package oop;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Back-End storage based in file implementation
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class FileMailStore implements MailStore{
    private File file;
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
     */
    public FileMailStore() {
        this.file = new File("mailStore.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class constructor
     * @param path file path
     */
    public FileMailStore(String path) {
        this.file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor based in MemoryMailStore
     * @param memoryMailStore MemoryMailStore instance
     */
    public FileMailStore(MemoryMailStore memoryMailStore) {
        this.file = new File("mailStore.txt");
        try {
            if (!file.createNewFile()){
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        memoryMailStore.getAllMessages().forEach(this::send);
    }

    /**
     * Interface MailStore
     * Send a mail to store it at file
     * @param msg message to storage
     */
    @Override
    public void send(Message msg) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            String[] Date = msg.getReceiver().getBirthDate().getTime().toString().split("\s");
            String receiverDate = Date[5] +"/" +Date[1] +"/" +Date[2];
            String[] Date2 = msg.getSender().getBirthDate().getTime().toString().split("\s");
            String senderDate = Date2[5] +"/" +Date2[1] +"/" +Date2[2];

            writer.write(msg.getReceiver().getUsername()+"#"+msg.getReceiver().getName()+"#"+receiverDate+";"+msg.getSender().getUsername()+"#"+msg.getSender().getName()+"#"+senderDate+";"+msg.getSubject()+";"+msg.getBody()+";"+msg.getCreationTime()+";\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Interface MailStore
     * Get the messages of certain receiver of the file
     * @param username username of the receiver
     * @return list of messages
     */
    @Override
    public List<Message> getMessages(User username) {
        List<Message> list =new ArrayList<>();
        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line= reader.readLine();
            while (line!=null){
                //receiver
                String[] param = line.split(";");
                String[] part = param[0].split("#");
                String[] date = part[2].split("/");
                Calendar birthRec, birthSend;
                birthRec = new GregorianCalendar(Integer.parseInt(date[0]), month.get(date[1]),
                        Integer.parseInt(date[2]));
                User receiver = new User(part[0], part[1], birthRec);
                if (receiver.getUsername().equals(username.getUsername())){
                    //sender
                    String[] part2 = param[1].split("#");
                    String[] date2 = part2[2].split("/");
                    birthSend = new GregorianCalendar(Integer.parseInt(date2[0]), month.get(date2[1]),
                            Integer.parseInt(date2[2]));
                    User sender = new User(part2[0], part2[1], birthSend);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    Date parsedDate = dateFormat.parse(param[4]);
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    Message msg = new Message(param[2], param[3], sender, receiver, timestamp);
                    list.add(msg);
                }
                line= reader.readLine();
            }
            reader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Interface MailStore
     * Get all the messages of the system of the file
     * @return list of messages
     */
    @Override
    public List<Message> getAllMessages() {
     List<Message> list =new ArrayList<>();
        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line= reader.readLine();
            while (line!=null){
                //receiver
                String[] param = line.split(";");
                String[] part = param[0].split("#");
                String[] date = part[2].split("/");
                Calendar birthRec, birthSend;
                birthRec = new GregorianCalendar(Integer.parseInt(date[0]), month.get(date[1]),
                        Integer.parseInt(date[2]));
                User receiver = new User(part[0], part[1], birthRec);
                //sender
                String[] part2 = param[1].split("#");
                String[] date2 = part2[2].split("/");
                birthSend = new GregorianCalendar(Integer.parseInt(date2[0]), month.get(date2[1]),
                        Integer.parseInt(date2[2]));
                User sender = new User(part2[0], part2[1], birthSend);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(param[4]);
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                Message msg = new Message(param[2], param[3], sender, receiver, timestamp);
                list.add(msg);
                line= reader.readLine();
            }
            reader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Interface MailStore
     * get all the users of the system of the file
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                //receiver
                String[] param = line.split(";");
                String[] part = param[0].split("#");
                String[] date = part[2].split("/");
                Calendar birthRec, birthSend;
                birthRec = new GregorianCalendar(Integer.parseInt(date[0]), month.get(date[1]),
                        Integer.parseInt(date[2]));
                User receiver = new User(part[0], part[1], birthRec);

                if (userList.stream().filter(user ->
                        user.getUsername().equals(receiver.getUsername())).findFirst().isEmpty()) userList.add(receiver);
                //sender
                String[] part2 = param[1].split("#");
                String[] date2 = part2[2].split("/");
                birthSend = new GregorianCalendar(Integer.parseInt(date2[0]), month.get(date2[1]),
                        Integer.parseInt(date2[2]));
                User sender = new User(part2[0], part2[1], birthSend);
                if (userList.stream().filter(user ->
                        user.getUsername().equals(sender.getUsername())).findFirst().isEmpty()) userList.add(sender);
                line = reader.readLine();
            }
            reader.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return userList;
    }
}
