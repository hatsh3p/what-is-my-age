package com.hatsh3p;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File configuration = new File("SampleConfigurationFile.txt");
        AgePredictor agePredictor = new AgePredictor(configuration);
        agePredictor.predict();
    }
}
