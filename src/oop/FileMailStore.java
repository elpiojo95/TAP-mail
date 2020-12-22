package oop;

import javax.swing.*;
import java.io.*;
import java.lang.invoke.SwitchPoint;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileMailStore implements MailStore{
    private File file;

    public FileMailStore(){
        this.file = new File("mailStore.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileMailStore(String path){
        this.file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public FileMailStore(MemoryMailStore memoryMailStore) {
        this.file = new File("mailStore.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        memoryMailStore.getAll().forEach(this::send);
    }

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

    @Override
    public List<Message> get(User username){
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
                birthRec = switch (date[1]) {
                    case "Jan" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.JANUARY, Integer.parseInt(date[2]));
                    case "Feb" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.FEBRUARY, Integer.parseInt(date[2]));
                    case "Mar" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.MARCH, Integer.parseInt(date[2]));
                    case "Apr" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.APRIL, Integer.parseInt(date[2]));
                    case "May" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.MAY, Integer.parseInt(date[2]));
                    case "Jun" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.JUNE, Integer.parseInt(date[2]));
                    case "Jul" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.JULY, Integer.parseInt(date[2]));
                    case "Aug" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.AUGUST, Integer.parseInt(date[2]));
                    case "Sep" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.SEPTEMBER, Integer.parseInt(date[2]));
                    case "Oct" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.OCTOBER, Integer.parseInt(date[2]));
                    case "Nov" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.NOVEMBER, Integer.parseInt(date[2]));
                    case "Dec" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.DECEMBER, Integer.parseInt(date[2]));
                    default -> throw new IllegalStateException("Unexpected value: " + date[1]);
                };
                User receiver = new User(part[0], part[1], birthRec);
                if (receiver.getUsername().equals(username.getUsername())){
                    //sender
                    String[] part2 = param[1].split("#");
                    String[] date2 = part2[2].split("/");
                    birthSend = switch (date2[1]) {
                        case "Jan" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.JANUARY, Integer.parseInt(date2[2]));
                        case "Feb" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.FEBRUARY, Integer.parseInt(date2[2]));
                        case "Mar" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.MARCH, Integer.parseInt(date2[2]));
                        case "Apr" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.APRIL, Integer.parseInt(date2[2]));
                        case "May" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.MAY, Integer.parseInt(date2[2]));
                        case "Jun" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.JUNE, Integer.parseInt(date2[2]));
                        case "Jul" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.JULY, Integer.parseInt(date2[2]));
                        case "Aug" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.AUGUST, Integer.parseInt(date2[2]));
                        case "Sep" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.SEPTEMBER, Integer.parseInt(date2[2]));
                        case "Oct" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.OCTOBER, Integer.parseInt(date2[2]));
                        case "Nov" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.NOVEMBER, Integer.parseInt(date2[2]));
                        case "Dec" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.DECEMBER, Integer.parseInt(date2[2]));
                        default -> throw new IllegalStateException("Unexpected value: " + date2[1]);
                    };
                    User sender = new User(part2[0], part2[1], birthSend);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                    Date parsedDate = dateFormat.parse(param[4]);
                    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    Message msg = new Message(param[2], param[3], sender, receiver, timestamp);
                    list.add(msg);
                }
                line= reader.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Message> getAll() {
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
                birthRec = switch (date[1]) {
                    case "Jan" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.JANUARY, Integer.parseInt(date[2]));
                    case "Feb" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.FEBRUARY, Integer.parseInt(date[2]));
                    case "Mar" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.MARCH, Integer.parseInt(date[2]));
                    case "Apr" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.APRIL, Integer.parseInt(date[2]));
                    case "May" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.MAY, Integer.parseInt(date[2]));
                    case "Jun" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.JUNE, Integer.parseInt(date[2]));
                    case "Jul" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.JULY, Integer.parseInt(date[2]));
                    case "Aug" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.AUGUST, Integer.parseInt(date[2]));
                    case "Sep" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.SEPTEMBER, Integer.parseInt(date[2]));
                    case "Oct" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.OCTOBER, Integer.parseInt(date[2]));
                    case "Nov" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.NOVEMBER, Integer.parseInt(date[2]));
                    case "Dec" -> new GregorianCalendar(Integer.parseInt(date[0]), Calendar.DECEMBER, Integer.parseInt(date[2]));
                    default -> throw new IllegalStateException("Unexpected value: " + date[1]);
                };
                User receiver = new User(part[0], part[1], birthRec);
                //sender
                String[] part2 = param[1].split("#");
                String[] date2 = part2[2].split("/");
                birthSend = switch (date2[1]) {
                    case "Jan" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.JANUARY, Integer.parseInt(date2[2]));
                    case "Feb" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.FEBRUARY, Integer.parseInt(date2[2]));
                    case "Mar" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.MARCH, Integer.parseInt(date2[2]));
                    case "Apr" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.APRIL, Integer.parseInt(date2[2]));
                    case "May" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.MAY, Integer.parseInt(date2[2]));
                    case "Jun" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.JUNE, Integer.parseInt(date2[2]));
                    case "Jul" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.JULY, Integer.parseInt(date2[2]));
                    case "Aug" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.AUGUST, Integer.parseInt(date2[2]));
                    case "Sep" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.SEPTEMBER, Integer.parseInt(date2[2]));
                    case "Oct" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.OCTOBER, Integer.parseInt(date2[2]));
                    case "Nov" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.NOVEMBER, Integer.parseInt(date2[2]));
                    case "Dec" -> new GregorianCalendar(Integer.parseInt(date2[0]), Calendar.DECEMBER, Integer.parseInt(date2[2]));
                    default -> throw new IllegalStateException("Unexpected value: " + date2[1]);
                };
                User sender = new User(part2[0], part2[1], birthSend);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(param[4]);
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                Message msg = new Message(param[2], param[3], sender, receiver, timestamp);
                list.add(msg);
                line= reader.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

}
