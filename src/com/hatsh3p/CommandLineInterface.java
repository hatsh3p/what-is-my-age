package com.hatsh3p;

import java.util.Scanner;

public class CommandLineInterface {

    private final String[] stateCodes;
    private int numberOfStateCodes = 0;
    private boolean exit;
    private Query query;

    public CommandLineInterface() {
        // 50 States + DC
        int NUMBER_OF_STATES = 51;
        this.stateCodes = new String[NUMBER_OF_STATES];
        this.exit = false;
    }

    public void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String name = null;
        String state;
        String gender;
        boolean validName = false;
        boolean validGender = false;
        boolean validState = false;

        do {
            System.out.print("Name of the person (or EXIT to quit): ");
            if (scanner.hasNext("[A-Za-z]+")) {
                name = scanner.next();
                String EXIT_MESSAGE = "EXIT";
                if (name.equals(EXIT_MESSAGE)) {
                    terminate();
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
                for (String code : stateCodes) {
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
        int highestFrequency = -1;
        int year = -1;
        int CURRENT_YEAR = 2021;
        Iterator<Record> iterator = list.iterator();
        boolean matchFound = false;

        while (iterator.hasNext()) {
            Record record = iterator.next();
            if (record.match(query.getState(), query.getGender(), query.getName())) {
                matchFound = true;
                if (record.getFrequency() > highestFrequency) {
                    highestFrequency = record.getFrequency();
                    year = record.getYear();
                }
            }
        }

        if (matchFound) {
            age = CURRENT_YEAR - year;
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

    public void addStateCodes(String state) {
        stateCodes[numberOfStateCodes++] = state;
    }

    public void printWelcome() {
        System.out.println("Welcome to What's My Age?");
    }

    public void terminate() {
        System.out.println("Program terminated...");
        exit = true;
    }

    public boolean isExit() {
        return exit;
    }

    public Query getQuery() {
        return query;
    }
}
