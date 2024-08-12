package com.example.tourstothefuture.requests;

import java.util.List;

public class UserRequest {
    private String name;
    private String surname;
    private String century;
    private List<String> transports;
    private List<String> robots;

    public UserRequest(String name, String surname, String century, List<String> transports, List<String> robots) {
        this.name = name;
        this.surname = surname;
        this.century = century;
        this.transports = transports;
        this.robots = robots;
    }

    public UserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCentury() {
        return century;
    }

    public void setCentury(String century) {
        this.century = century;
    }

    public List<String> getTransports() {
        return transports;
    }

    public void setTransports(List<String> transports) {
        this.transports = transports;
    }

    public List<String> getRobots() {
        return robots;
    }

    public void setRobots(List<String> robots) {
        this.robots = robots;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", century='" + century + '\'' +
                ", transports=" + transports +
                ", robots=" + robots +
                '}';
    }
}
