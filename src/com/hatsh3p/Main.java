package com.hatsh3p;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // Requirement 1: Configuration file implementation
        Configuration configuration = null;
        try {
            configuration = new Configuration();
            configuration.setProperties("SampleConfigurationFile.txt");
            configuration.getFiles();
        } catch (FileNotFoundException e) {
            System.out.println("No file found: Exiting program...");
        } catch (NullPointerException e) {
            System.out.println("Properties not found: Exiting program...");
        } catch (IOException e) {
            System.out.println("Problems reading file: Exiting program...");
        }

        // Requirement 2: Store data in appropriate data structure
        List<Record> records = readRecords(configuration);

        // Requirement 3: Process and return multiple queries
        CommandLineInterface commandLineInterface = new CommandLineInterface(records);
        commandLineInterface.getUserInput();
    }

    public static final String delimiter = ",";

    private static List<Record> readRecords(Configuration configuration) {
        List<Record> list;
        if (configuration.getListType().equals("ArrayList")) {
            list = new ArrayList<>();
        } else {
            list = new LinkedList<>();
        }

        try {
            File file = new File(configuration.getDirectory());
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
                    if (record.match(state, gender, name)) { // TODO: should encompass gender and state as well
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
