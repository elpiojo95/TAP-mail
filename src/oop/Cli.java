package oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cli {
    MailStore mailStore;

    static List<String> validComand = List.of("help", "createuser", "filter", "logas", "exit");
    static List<String> validMailboxComands = List.of("help", "send", "update", "list", "sort", "filter", "exit");
    static MailSystem mailSystem;

    Mailbox userMailbox = null;
    // TO-DO repasar las exceptions
    public Cli() {
        mailSystem = new MailSystem(new MemoryMailStore());
    }

    public int run() {
        System.out.println("\tWelcome to MyMailApp");
        // start test
        String[] command = {"createuser", "elpiojo", "Leo", "07/09/1995"};
        try {
            createUser(command);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // end test
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
        try {
            exitCode = readMailboxComand();
            while (exitCode == 0)
                exitCode = readMailboxComand();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return exitCode;
    }

    private void printHelp() {
        System.out.println("createuser <username> <Name> <DD/MM/YYYY>");
        System.out.println("filter <predicate>\n\tpredicates:\n\t\tcontains <word>\n\t\tlessthan <n>");
        System.out.println("logas <username>");
    }

    private void printHelpMailbox() {
        System.out.println("send <to> \"subject\" \"body\"");
        System.out.println("filter <...>");
        System.out.println("logas <username>");
    }

    private int readCommand() throws ParseException {
        System.out.print("$");
        Scanner scanner = new Scanner(System.in);
        String[] command = scanner.nextLine().split(" ");
        if (!isValid(command)) return 1;
        switch (validComand.indexOf(command[0])) {
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

    private int readMailboxComand() throws ParseException {
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        Pattern p = Pattern.compile("([\\w]*)(\\w*)(.*)");
        String s = scanner.nextLine();
        Matcher m = p.matcher(s);
        m.find();

        if (!isValidMailbox(m.group(1))) return 1;
        switch (validMailboxComands.indexOf(m.group(1))) {
            // HELP command
            case 0 :
                printHelpMailbox();
                break;
            // SEND command
            case 1 :
               // return send(m.group(1));
            // ??? command
            case 2 :

                break;
            // ??? command
            case 3 :

            // ??? command
            case 4 : return 1;
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
                Date date=new SimpleDateFormat("dd/MM/yyyy").parse(s);
                mailSystem.createUser(command[1],name,date);
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
        return validComand.contains(command[0]);
    }

    private boolean isValidMailbox(String command) {
        return validMailboxComands.contains(command);
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
        User user = mailSystem.getUserList()
                .stream()
                .filter(u -> username.equals(u.getUsername()))
                .findFirst()
                .orElse(null);
        if (user != null) {
            return runMailbox(mailSystem.getMailbox(user));
        }
        return 2;
    }

    private int send(String[] command) {
        String destination = command[1];
        System.out.println(Arrays.toString(command));
        String subject;
        String body;
        //userMailbox.send(destination,subject, body);
        return 0;
    }

}
