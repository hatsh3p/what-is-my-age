package com.hatsh3p;

import java.util.Scanner;

public class CommandLineInterface {

    private final List<Record> list;
    boolean exit;
    final String EXIT_MESSAGE = "EXIT";
    final int CURRENT_YEAR = 2021;

    public CommandLineInterface(List<Record> list) {
        this.list = list;
        this.exit = false;
    }

    public void getUserInput() {
        System.out.println("Welcome to What's My Age?");
        int age = -1;

        while(!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Name of the person (or EXIT to quit): ");
            String name = scanner.next();
            if (name.equals(EXIT_MESSAGE)) {
                exit = true;
                break;
            }

            System.out.print("Gender (M/F): ");
            String gender = scanner.next();

            System.out.print("State of birth (two-letter state code): ");
            String state = scanner.next();

            // TODO: Report result
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(name)) {
                    age = CURRENT_YEAR - list.get(i).getYear();
                }
            }
            System.out.print(name + ", " + gender + ", born in " + state + ", is most likely around " + age + " years old.\n");
        }
    }

}
