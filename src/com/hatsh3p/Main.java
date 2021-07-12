package com.hatsh3p;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // Requirement 1: Configuration file implementation
        Configuration configuration = new Configuration();
        CommandLineInterface commandLineInterface = new CommandLineInterface();
        commandLineInterface.printWelcome();
        try {
            configuration.setProperties("SampleConfigurationFile.txt");
        } catch (Exception e) {
            System.out.println("Error: Issues processing configuration file");
            commandLineInterface.terminate();
        }

        // Requirement 2: Store data from SSA files, Requirement 3: Accept 1+ queries
        // Two cases: File and Directory
        // CASE 1: File
        if(!configuration.isDirectory()) {
            List<Record> records = readRecords(configuration);
            while(!commandLineInterface.isExit()) {
                commandLineInterface.getUserInput();
                commandLineInterface.findQuery(records);
            }
            // CASE 2: Directory
        } else {
            List<List<Record>> multipleRecords;
            if (configuration.getListType().equals("ArrayList")) {
                multipleRecords = new ArrayList<>();
            }  else {
                multipleRecords = new LinkedList<>();
            }
            for (File file: configuration.getListOfFiles()) {
                List<Record> record = readRecords(configuration, file);
                multipleRecords.add(record);
            }
            while(!commandLineInterface.isExit()) {
                commandLineInterface.getUserInput();

                // Get record for the appropriate state
                String stateQueried = commandLineInterface.getQuery().getState();
                List<Record> stateRecord = null;
                Iterator<List<Record>> iterator = multipleRecords.iterator();
                while (iterator.hasNext() && multipleRecords.size() != 0) {
                    List<Record> list = iterator.next();
                    if (list.get(0).getState().equals(stateQueried)){
                        stateRecord = list;
                    }
                }
                commandLineInterface.findQuery(stateRecord);
            }
        }
    }

    public static final String delimiter = ",";

    //TODO: Repeated code below (figure out a way to consolidate)
    private static List<Record> readRecords(Configuration configuration, File stateFile) {
        List<Record> list;
        if (configuration.getListType().equals("ArrayList")) {
            list = new ArrayList<>();
        } else {
            list = new LinkedList<>();
        }

        try {
            File file = new File(String.valueOf(stateFile));
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String[] tempArr;

            while((line = bufferedReader.readLine()) != null) {
                // CSV --> variable
                tempArr = line.split(delimiter);
                String state = tempArr[0];
                String gender = tempArr[1];
                int year = Integer.parseInt(tempArr[2]);
                String name = tempArr[3];
                int frequency = Integer.parseInt(tempArr[4]);

                boolean matchFound = false;
                Iterator<Record> iterator = list.iterator();

                while (iterator.hasNext() && list.size() != 0) {
                    Record record = iterator.next();
                    if (record.match(state, gender, name)) {
                        matchFound = true;
                        if (record.getFrequency() < frequency) {
                            record.setFrequency(frequency);
                            record.setYear(year);
                        }
                    }
                }
                if (!matchFound) {
                    Record newRecord = new Record(state, gender, year, name, frequency);
                    list.add(newRecord);
                }
            }
            bufferedReader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }

    private static List<Record> readRecords(Configuration configuration) {
        List<Record> list;
        if (configuration.getListType().equals("ArrayList")) {
            list = new ArrayList<>();
        } else {
            list = new LinkedList<>();
        }

        try {
            File file = new File(configuration.getDirectoryProperty());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String[] tempArr;

            while((line = bufferedReader.readLine()) != null) {
                // CSV --> variable
                tempArr = line.split(delimiter);
                String state = tempArr[0];
                String gender = tempArr[1];
                int year = Integer.parseInt(tempArr[2]);
                String name = tempArr[3];
                int frequency = Integer.parseInt(tempArr[4]);

                boolean matchFound = false;
                Iterator<Record> iterator = list.iterator();

                while (iterator.hasNext() && list.size() != 0) {
                    Record record = iterator.next();
                    if (record.match(state, gender, name)) {
                        matchFound = true;
                        if (record.getFrequency() < frequency) {
                            record.setFrequency(frequency);
                            record.setYear(year);
                        }
                    }
                }
                if (!matchFound) {
                    Record newRecord = new Record(state, gender, year, name, frequency);
                    list.add(newRecord);
                }
            }
            bufferedReader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }
}
