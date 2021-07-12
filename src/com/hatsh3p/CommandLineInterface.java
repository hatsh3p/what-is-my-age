package com.hatsh3p;

import java.util.Scanner;

public class CommandLineInterface {

    private boolean exit;
    final String EXIT_MESSAGE = "EXIT";
    final int CURRENT_YEAR = 2021;
    private Query query;

    public CommandLineInterface() {
        this.exit = false;
    }

    public void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name of the person (or EXIT to quit): ");
        String name = scanner.next();
        if (name.equals(EXIT_MESSAGE)) {
            exit = true;
            return;
        }

        System.out.print("Gender (M/F): ");
        String gender = scanner.next();

        System.out.print("State of birth (two-letter state code): ");
        String state = scanner.next();

        query = new Query(name, state, gender);
    }

    public void findQuery(List<Record> list) {
        if (isExit())
            return;
        
        int age = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(query.getName())) {
                age = CURRENT_YEAR - list.get(i).getYear();
            }
        }
        if (age == -1) {
            System.out.println("Query not found");
        } else {
            System.out.print(query.getName() + ", " + query.getGender() + ", born in " + query.getState() + ", is most likely around " + age + " years old.\n");
        }
    }

    public void terminate() {
        System.out.println("Program terminated...");
        this.exit = true;
    }

    public void printWelcome() {
        System.out.println("Welcome to What's My Age?");
    }

    public boolean isExit() {
        return exit;
    }

    public Query getQuery() {
        return query;
    }
}
