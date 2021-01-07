package oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that represents a command line interface for interacting with the MailSystem
 * @author Leandro F. Gomez Racca
 * @author Miriam Gertrudix Pedrola
 */
public class Cli {
    static List<String> validCommand = List.of("help", "createuser", "filter", "logas", "exit", "swap");
    static List<String> validMailboxCommands = List.of("help", "send", "update", "list", "sort", "filter", "exit");
    static MailSystem mailSystem;

    Mailbox userMailbox = null;

    /**
     * Class constructor
     */
    public Cli() {
        mailSystem = new MailSystem(new FileMailStore());
    }

    /**
     * Class constructor
     * @param path Path of file which data is loaded
     */
    public Cli(String path) {
        mailSystem = new MailSystem(new FileMailStore(path));
    }

    /**
     * Run the command line interface
     * @return exit code
     * 1 - All fine
     */
    public int run() {
        System.out.println("\tWelcome to MyMailApp");

        int exitCode = 0;
        while (exitCode == 0) exitCode = readCommand();
        return exitCode;
    }

    /**
     * Run a Mailbox, cli runs into a Mailbox interface for user commands
     * @param mailbox User mailbox
     * @return exit code
     *      * 1 - All fine
     */
    private int runMailbox(Mailbox mailbox) {
        System.out.println("\tWelcome to MyMailApp " + mailbox.getUser().getName());
        userMailbox = mailbox;
        int exitCode = 0;
        while (exitCode == 0) exitCode = readMailboxCommand();

        return exitCode;
    }

    /**
     * Print help message for cli
     */
    private void printHelp() {
        System.out.println("createuser <username> <Name> <DD/MM/YYYY>");
        System.out.println("filter <predicate>");
        System.out.println("\tpredicates:");
        System.out.println("\t\tcontains <word>");
        System.out.println("\t\tlessthan <n>");
        System.out.println("logas <username>");
        System.out.println("swap");
        System.out.println("exit");
    }

    /**
     * Print help message for Mailbox cli
     */
    private void printHelpMailbox() {
        System.out.println("send <to> -s \"subject\" -b \"body\"");
        System.out.println("update");
        System.out.println("list");
        System.out.println("sort<Comparator>");
        System.out.println("\tComparators:");
        System.out.println("\t\tsender");
        System.out.println("\t\ttime");
        System.out.println("filter<predicate>");
        System.out.println("\tPredicates:");
        System.out.println("\t\tsubject<word>");
        System.out.println("\t\tsender<username>");
        System.out.println("exit");
        
    }

    /**
     * Read a command from System.in and execute that command
     * @return exitCode 0 - All fine 1 - finished 2 - error
     */
    private int readCommand() {
        System.out.print("$ ");
        Scanner scanner = new Scanner(System.in);
        String[] command = scanner.nextLine().split(" ");
        if (!isValid(command)) return 1;
        switch (validCommand.indexOf(command[0])) {
            // HELP command
            case 0 :
                printHelp();
                break;
            // CREATEUSER command
            case 1 :
                return createUser(command);
            // FILTER command
            case 2 :
                return filter(command);
            // LOGAS command
            case 3 :
                return logIn(command);
            // EXIT command
            case 4 : return 1;
            case 5 : return mailSystem.swap();
            default: return 2;
        }
        return 0;
    }

    /**
     * Read a Mailbox command from System.in and execute that command
     * @return exitCode 0 - All fine 1 - finished 2 - error
     */
    private int readMailboxCommand() {
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        Pattern p = Pattern.compile("(\\w*)\\s*(\\w*)\\s*(.*)");
        String s = scanner.nextLine();
        Matcher m = p.matcher(s);
        if(!m.find()) return 2;

        if (!isValidMailbox(m.group(1))) return 1;
        switch (validMailboxCommands.indexOf(m.group(1))) {
            // HELP command
            case 0 :
                printHelpMailbox();
                break;
            // SEND command
            case 1 :
                return send(m.group(2),m.group(3));
            // update command
            case 2 :
                return update();
            // list command
            case 3 :
                return list();
            // sort command
            case 4 :
                return sort(m.group(2));
            // filter command
            case 5 :
                return filterUserMailbox(m.group(2), m.group(3));
            //EXIT case
            case 6 : return 1;
            default: return 2;
        }
        return 0;
    }

    /**
     * Method that parse a given command and create a new user
     * @param command to parse
     * @return exitCode 0 - All fine 2 - error
     */
    private int createUser(String[] command){
        String p = "\\d{2}/\\d{2}/\\d{4}";
        String name = command[2];
        String[] newCommand;
        newCommand = Arrays.copyOfRange(command,3,command.length);
        for (String s : newCommand) {
            if (s.matches(p)){
                Calendar cal = Calendar.getInstance();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    cal.setTime(dateFormat.parse(s));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                mailSystem.createUser(command[1],name, cal);
                return 0;
            }else {
                name = name.concat(" "+s);
            }
        }
        return 2;
    }


    /**
     * method to filter the messages in two options
     * contains: if the message contains the word
     * lessthan: if the message have less than n words
     * @param command command to execute
     * @return exitCode 0 - All fine 2 - error
     */
    private int filter(String[] command){
        if (command[1].equals("contains")){
            System.out.println(mailSystem.filterPerWord(command[2]));
        }else if (command[1].equals("lessthan")){
            System.out.println(mailSystem.filterPerNumWords(Integer.parseInt(command[2])));
        }else return 2;
        return 0;
    }

    /**
     * Method to check that the command is valid
     * @param command command
     * @return exitCode 0 - All fine 2 - error
     */
    private boolean isValid(String[] command) {
        return validCommand.contains(command[0]);
    }

    /**
     * Method to check that the command is valid
     * @param command command
     * @return exitCode 0 - All fine 2 - error
     */
    private boolean isValidMailbox(String command) {
        return validMailboxCommands.contains(command);
    }

    /**
     * Method to log as a user in the userList
     * @param command command
     * @return exitCode 0 - All fine 2 - error
     */
    private int logIn(String[] command) {
        if (command.length != 2) return 2;
        String username = command[1];
        int exitCode;
        User user = mailSystem.getUserList()
                .stream()
                .filter(u -> username.equals(u.getUsername()))
                .findFirst()
                .orElse(null);
        if (user != null) {
            exitCode = runMailbox(new Mailbox(user,mailSystem.getStore()));
            if (exitCode == 1) exitCode--;
            return exitCode;
        }
        return 2;
    }

    /**
     * method to send a message to another mailbox
     * @param command1 command
     * @param command2 command
     * @return exitCode 0 - All fine 2 - error
     */
    private int send(String command1, String command2) {
        User destination = mailSystem.getUserList()
                .stream()
                .filter(user -> user.getUsername().equals(command1))
                .findFirst()
                .orElse(null);
        if (destination == null) return 2;
        Pattern p = Pattern.compile("-s (.*) -b (.*)");
        Matcher m = p.matcher(command2);
        if(!m.find()) return 2;
        String subject = m.group(1);
        String body= m.group(2);
        userMailbox.send(destination,subject, body);
        System.out.println("Message sent");
        return 0;
    }

    /**
     * method to update the user mailbox
     * @return exitCode 0 - All fine 2 - error
     */
    private int update(){
        System.out.println("Mailbox up of date");
        userMailbox.update();
        return 0;

    }

    /**
     * method to print the messages of the mailbox
     * @return exitCode 0 - All fine 2 - error
     */
    private int list(){
        System.out.println(userMailbox.messageList());
        return 0;
    }

    /**
     * method to sort the messages in two options
     * sender: sort by sender username
     * time: sort by message creationTime
     * @param command command
     * @return exitCode 0 - All fine 2 - error
     */
    private int sort(String command){
        if (command.equals("sender")){
            System.out.println(userMailbox.sorted(Comparator.comparing(Message::getSender)));
        }else if (command.equals("time")){
            System.out.println(userMailbox.sorted(Comparator.comparing(Message::getCreationTime)));
        } else return 2;
        return 0;
    }

    /**
     * method to sort the messages in two options
     * sender: sort by sender username
     * time: sort by message creationTime
     * @param command1 command
     * @param command2 command
     * @return exitCode 0 - All fine 2 - error
     */
    private int filterUserMailbox(String command1,String command2){
        if (command1.equals("subject")){
            System.out.println(userMailbox.filter(MessageUtils.filterSubject(command2)));
        }else if (command1.equals("sender")){

            System.out.println(userMailbox.filter(MessageUtils.filterSender(mailSystem.getUserList()
                    .stream()
                    .filter(user -> user.getUsername().equals(command2))
                    .findFirst()
                    .orElse(null))));

        } else return 2;
        return 0;
    }
}
