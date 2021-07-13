package com.hatsh3p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AgePredictor {

    AgePredictor(File file) {
    }

    public void predict() {
        // Requirement 1: Validate configuration file
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
        // Two cases: (1) Single File and (2) Directory
        // (1): Single File
        if(!configuration.isDirectory()) {
            File recordFile = new File(configuration.getDirectoryProperty());
            List<Record> records = readRecords(configuration, recordFile);
            String state = records.get(0).getState();
            commandLineInterface.addStateCodes(state);
            while(!commandLineInterface.isExit()) {
                commandLineInterface.getUserInput();
                commandLineInterface.findQuery(records);
            }
            // (2): Directory
        } else {
            List<List<Record>> multipleRecords;
            List<String> states;
            if (configuration.getListType().equals("ArrayList")) {
                multipleRecords = new ArrayList<>();
                states = new ArrayList<>();
            }  else {
                multipleRecords = new LinkedList<>();
                states = new LinkedList<>();
            }
            for (File file: configuration.getListOfFiles()) {
                List<Record> record = readRecords(configuration, file);
                multipleRecords.add(record);
                String state = record.get(0).getState();
                states.add(state);
                commandLineInterface.addStateCodes(state);
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

    private static final String delimiter = ",";

    private static List<Record> readRecords(Configuration configuration, File recordFile) {
        List<Record> list;
        if (configuration.getListType().equals("ArrayList")) {
            list = new ArrayList<>();
        } else {
            list = new LinkedList<>();
        }

        try {
            File file = new File(String.valueOf(recordFile));
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] tempArr;
//            int linesLoaded = 0;

            while((line = bufferedReader.readLine()) != null) {
                // CSV --> variable
                tempArr = line.split(delimiter);
                String state = tempArr[0];
                String gender = tempArr[1];
                int year = Integer.parseInt(tempArr[2]);
                String name = tempArr[3];
                int frequency = Integer.parseInt(tempArr[4]);

                Record record = new Record(state, gender, year, name, frequency);
                list.add(record);

//                linesLoaded++;
//                if (linesLoaded % 10000 == 0) {
//                    System.out.println("Loaded " + linesLoaded + " records so far...");
//                    System.out.println(record);
//                }
            }
            bufferedReader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }
}
