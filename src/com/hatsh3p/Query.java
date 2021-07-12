package com.hatsh3p;

public class Query {
    String name;
    String state;
    String gender;

    public Query(String name, String state, String gender) {
        this.name = name;
        this.state = state;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
