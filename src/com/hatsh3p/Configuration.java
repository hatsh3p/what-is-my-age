package com.hatsh3p;

import java.io.*;
import java.util.Properties;

public class Configuration {
    String listType = null;
    String directory = null;

    // use default constructor

    public String getListType() {
        return listType;
    }

    public String getDirectory() {
        return directory;
    }

    public void setProperties(String fileName) throws IOException {
        FileReader reader = null;
        Properties properties = null;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        properties = new Properties(System.getProperties());
        properties.load(reader);
        listType = properties.getProperty("ListType");
        directory = properties.getProperty("Directory");

        if (listType == null || directory == null)
            throw new NullPointerException();

        System.out.println("listType=" + listType + "Directory=" + directory);
    }

    public void getFiles() {
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();

        for (File file: listOfFiles) {
            if(file.isFile())
                System.out.println(file.getName());
        }
    }
}
