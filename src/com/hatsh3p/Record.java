package com.hatsh3p;

public class Record {
    String state;
    String gender;
    int year;
    String name;
    int frequency;

    public Record() {
    }

    public Record(String state, String gender, int year, String name, int frequency) {
        this.state = state;
        this.gender = gender;
        this.year = year;
        this.name = name;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Record{" +
                "state='" + state + '\'' +
                ", gender=" + gender +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", frequency=" + frequency +
                '}';
    }

    public boolean match(String state, String gender, String name) {
        return state.equals(this.state) && gender.equals(this.gender) && name.equals(this.name);
    }
}
