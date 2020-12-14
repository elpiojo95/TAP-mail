package oop;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        int exitCode;
        System.out.println("Start Main");
        Cli program = new Cli();
        System.out.println("Start CLI");
        exitCode = program.run();
        System.out.println("Finish CLI, exit Code: " + exitCode);
    }
}
