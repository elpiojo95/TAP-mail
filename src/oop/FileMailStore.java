package oop;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileMailStore implements MailStore{
    private File file;

    public FileMailStore(){
        this.file = new File("mailStore.txt");
    }

    public FileMailStore(File file){
        this.file = file;
    }

    public void send(Message msg) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            writer.write(msg.getReceiver()+";"+msg.getSender()+";"+msg.getSubject()+";"+msg.getBody()+";"+msg.getCreationTime()+";\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Message> get(String username){
        List<Message> list =new ArrayList<>();
        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String line= reader.readLine();
            while (line!=null){
               String[] param = line.split(";");
                if (param[0].equals(username)){
                   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                   Date parsedDate = dateFormat.parse(param[4]);
                   Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                   Message msg = new Message(param[2], param[3], param[1], param[0], timestamp);
                   list.add(msg);
               }
               line= reader.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return list;
    }


}
