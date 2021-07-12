package com.hatsh3p;

import java.io.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Configuration {
    private String listProperty;
    private String directoryProperty;
    private boolean isDirectory;
    private File[] listOfFiles;

    public void setProperties(String fileName) throws IOException {
        FileReader reader;
        Properties properties;

        // Read configuration file
        try {
            reader = new FileReader(fileName);
            properties = new Properties(System.getProperties());
            properties.load(reader);
            listProperty = properties.getProperty("ListType");
            directoryProperty = properties.getProperty("Directory");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Validate listProperty
        if (!listProperty.equals("ArrayList") && !listProperty.equals("LinkedList")) {
            throw new InvalidPropertiesFormatException("Unsupported or invalid list type");
        }

        // Validate directoryProperty and determine if directory OR file
        File file = new File(directoryProperty);
        if (file.isDirectory()) {
            isDirectory = true;
            listOfFiles = file.listFiles();
        } else if (file.isFile()) {
            isDirectory = false;
        } else {
            throw new FileNotFoundException();
        }
    }

    public String getListType() {
        return listProperty;
    }

    public String getDirectoryProperty() {
        return directoryProperty;
    }

    public File[] getListOfFiles() {
        return listOfFiles;
    }

    public boolean isDirectory() {
        File file = new File(directoryProperty);
        isDirectory = file.isDirectory();
        return isDirectory;
    }
}
