package com.iamscratches.bootingweb.models;

public class Staff {
    private String guid;
    private String firstName;
    private String lastName;
    private String position;

    public Staff() {
    }

    public Staff(String guid, String firstName, String lastName, String position) {
        this.guid = guid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "guid='" + guid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position=" + position +
                '}';
    }
}

