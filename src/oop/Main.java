package oop;

public class Main {
    public static void main(String[] args) {
        int exitCode;
        System.out.println("Start Main");
        //Cli program = new Cli();
        Cli program = new Cli("prueba.txt");
        System.out.println("Start CLI");
        exitCode = program.run();
        System.out.println("Finish CLI, exit Code: " + exitCode);
    }
}
