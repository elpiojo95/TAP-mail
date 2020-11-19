package oop;

public class Main {
    public static void main(String[] args) {
        int exitCode;
        System.out.println("Start Main");
        System.out.println("Start CLI");
        Cli program = new Cli();
        exitCode = program.run();
        System.out.println("Finish CLI, exit Code: " + exitCode);
    }
}
