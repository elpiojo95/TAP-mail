package oop;

import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cli {
    MailStore mailStore;

    static List<String> validComand = List.of("createuser", "filter", "logas", "exit");
    static List<String> validMailboxComands = List.of("send", "update", "list", "sort", "filter");
    static MailSystem mailSystem;

    public Cli() {
    }

    public int run() {
        System.out.println(validComand);
        System.out.println("\tWelcome to MyMailApp");
        int exitCode = readCommand();
        while (exitCode == 0)
            exitCode = readCommand();
        return exitCode;
    }

    private void printHelp() {
        System.out.println("createuser <username> <args>");
        System.out.println("filter <...>");
        System.out.println("logas <username>");
        System.out.print("> ");
    }

    private int readCommand() {
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        String[] command = scanner.nextLine().split(" ");
        if (!isValid(command)) return 1;
        switch (validComand.indexOf(command[0])) {
            case 0 : return createuser(command);
            case 1 : System.out.println("filter");
                break;
            case 2 : System.out.println("logas");
                break;
            case 3 : return 1;
            default: return 2;
        }
        return 0;
    }

    private int createuser(String[] command) {
        mailSystem.newuser();
        return 0;
    }

    private boolean isValid(String[] command) {
        return validComand.contains(command[0]);
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

    private void options() {
        System.out.println("1. Create User\n" +
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

    private void logIn() {
        System.out.println("Enter Username: ");
        Scanner scanner = new Scanner(System.in);

    }

}
