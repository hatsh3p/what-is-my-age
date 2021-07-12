package com.hatsh3p;

import java.util.Scanner;

public class CommandLineInterface {
    private final String EXIT_MESSAGE = "EXIT";
    private final int CURRENT_YEAR = 2021;
    private final String[] STATE_CODES = {"AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI",
        "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MP", "MS", "MT", "NC", "ND", "NE",
        "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UM", "UT", "VA", "VI",
        "VT", "WA", "WI", "WV", "WY"};
    private boolean exit;
    private Query query;

    public CommandLineInterface() {
        this.exit = false;
    }

    public void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String name = null;
        String state = null;
        String gender = null;
        boolean validName = false;
        boolean validGender = false;
        boolean validState = false;

        do {
            System.out.print("Name of the person (or EXIT to quit): ");
            if (scanner.hasNext("[A-Za-z]+")) {
                name = scanner.next();
                if (name.equals(EXIT_MESSAGE)) {
                    exit = true;
                    return;
                }
                validName = true;
            }
            if (!validName) {
                System.out.println("Error: Invalid name input. Try again.");
                name = scanner.next();
            }
        } while (!validName);


        do {
            System.out.println("Note: US SSA data does not include non-binary as a gender identity");
            System.out.print("Gender (M/F): ");
            if (scanner.hasNext("[M|F]")) {
                gender = scanner.next();
                validGender = true;
            } else {
                System.out.println("Error: Invalid gender input. Try again.");
                gender = scanner.next();
            }
        } while(!validGender);

        do {
            System.out.print("State of birth (two-letter state code): ");
            if (scanner.hasNext()) {
                state = scanner.next();
                for (String code : STATE_CODES) {
                    if (state.equals(code)) {
                        validState = true;
                        break;
                    }
                }
                if (!validState) {
                    System.out.println("Error: Invalid state code");
                }
            } else {
                System.out.println("Error: Invalid state input");
                state = scanner.next();
            }
        } while (!validState);

            query = new Query(name, state, gender);
    }

    public void findQuery(List<Record> list) {
        if (isExit())
            return;
        int age = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(query.getName()))
                age = CURRENT_YEAR - list.get(i).getYear();
        }
        if (age != -1) {
            System.out.print(query.getName() + ", " +
                    query.getGender() + ", born in " +
                    query.getState() + ", is most likely around " +
                    age + " years old.\n");
        } else {
            System.out.println(query.getName() + ", " +
                    query.getGender() + ", not found in " +
                    query.getState());
        }
    }

    public void printWelcome() {
        System.out.println("Welcome to What's My Age?");
    }

    public void terminate() {
        System.out.println("Program terminated...");
        this.exit = true;
    }

    public boolean isExit() {
        return exit;
    }

    public Query getQuery() {
        return query;
    }
}
