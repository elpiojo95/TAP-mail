package oop;


import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cli {
    MailStore mailStore;

    static List<String> validCommand = List.of("help", "createuser", "filter", "logas", "exit");
    static List<String> validMailboxCommands = List.of("help", "send", "update", "list", "sort", "filter", "exit");
    static MailSystem mailSystem;

    Mailbox userMailbox = null;
    // TO-DO repasar las exceptions
    public Cli() {
        mailSystem = new MailSystem(new FileMailStore(new File ("mailStore.txt")));
    }

    public Cli(String path) {
        mailSystem = new MailSystem(new FileMailStore(new File(path)));
    }

    public int run() {
        System.out.println("\tWelcome to MyMailApp");

        int exitCode = 0;
        try {
            exitCode = readCommand();
        while (exitCode == 0)
            exitCode = readCommand();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return exitCode;
    }

    public int runMailbox(Mailbox mailbox) {
        System.out.println("\tWelcome to MyMailApp " + mailbox.getUser().getName());
        userMailbox = mailbox;
        int exitCode = 0;
        while (exitCode == 0) exitCode = readMailboxCommand();

        return exitCode;
    }

    private void printHelp() {
        System.out.println("createuser <username> <Name> <DD/MM/YYYY>");
        System.out.println("filter <predicate>");
        System.out.println("\tpredicates:");
        System.out.println("\t\tcontains <word>");
        System.out.println("\t\tlessthan <n>");
        System.out.println("logas <username>");
        System.out.println("exit");
    }

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

    private int readCommand() throws ParseException {
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
            default: return 2;
        }
        return 0;
    }

    private int readMailboxCommand() {
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        Pattern p = Pattern.compile("(\\w*)\\s*(\\w*)\\s*(.*)");
        String s = scanner.nextLine();
        Matcher m = p.matcher(s);
        m.find();

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

    private int createUser(String[] command) throws ParseException {
        String p = "\\d{2}/\\d{2}/\\d{4}";
        String name = command[2];
        String[] newCommand;
        newCommand = Arrays.copyOfRange(command,3,command.length);
        for (String s : newCommand) {
            if (s.matches(p)){
                Calendar cal = Calendar.getInstance();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                cal.setTime(dateFormat.parse(s));

                mailSystem.createUser(command[1],name, cal);
                return 0;
            }else {
                name = name.concat(" "+s);
            }
        }
        return 2;
    }

    private int filter(String[] command){
        if (command[1].equals("contains")){
            System.out.println(mailSystem.filterPerWord(command[2]));
        }else if (command[1].equals("lessthan")){
            System.out.println(mailSystem.filterPerNumWords(Integer.parseInt(command[2])));
        }else return 2;
        return 0;
    }

    private boolean isValid(String[] command) {
        return validCommand.contains(command[0]);
    }

    private boolean isValidMailbox(String command) {
        return validMailboxCommands.contains(command);
    }

    private void selectMailstore() {
        System.out.println("1. Memory system\n" +
                "2. File system");
        Scanner scanner = new Scanner(System.in);
        boolean validInteger = false;
        int option = 0;
        while (!validInteger) {
            try {
                option= scanner.nextInt();
                if (option==1 || option==2) validInteger = true;
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
        switch (option) {
            case 1 -> mailStore = new MemoryMailStore();
            case 2 -> mailStore = new FileMailStore();
            default -> mailStore = new FileMailStore();
        }
    }

    private int logIn(String[] command) {
        if (command.length != 2) return 2;
        String username = command[1];
        int exitcode;
        User user = mailSystem.getUserList()
                .stream()
                .filter(u -> username.equals(u.getUsername()))
                .findFirst()
                .orElse(null);
        if (user != null) {
            exitcode = runMailbox(mailSystem.getMailbox(user));
            if (exitcode == 1) exitcode--;
            return exitcode;
        }
        return 2;
    }

    private int send(String command1, String command2) {
        String destination;
        if (mailSystem.getUserList().stream().anyMatch(user -> user.getUsername().equals(command1))){
            destination = command1;
        }else return 2;
        Pattern p = Pattern.compile("-s (.*) -b (.*)");
        Matcher m = p.matcher(command2);
        m.find();
        String subject = m.group(1);
        String body= m.group(2);
        userMailbox.send(destination,subject, body);
        System.out.println("Message sent");
        return 0;
    }

    private int update(){
        System.out.println("Mailbox up of date");
        userMailbox.update();
        return 0;

    }
    private int list(){
        System.out.println(userMailbox.messageList());
        return 0;
    }

    private int sort(String command){
        if (command.equals("sender")){
            System.out.println(userMailbox.sorted(Comparator.comparing(Message::getSender)));
        }else if (command.equals("time")){
            System.out.println(userMailbox.sorted(Comparator.comparing(Message::getCreationTime)));
        } else return 2;
        return 0;
    }
    private int filterUserMailbox(String command1,String command2){
        if (command1.equals("subject")){
            System.out.println(userMailbox.filter(MessageUtils.filterSubject(command2)));
        }else if (command1.equals("sender")){
            System.out.println(userMailbox.filter(MessageUtils.filterSender(command2)));
        } else return 2;
        return 0;
    }

}
