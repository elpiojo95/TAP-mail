package oop;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Cli {

    public Cli() {
    }

    public int run() {
        switch (selectMailstore()) {
            case 1 :

                break;
            case 2 :
                break;
        }
        return 0;
    }

    public int selectMailstore() {
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
        return option;
    }
}
