package com.crystal.be.selector;

import java.util.Objects;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(int id, String email) {
        this.id = id;
        this.email = email;
        this.firstName = email.substring(0, email.indexOf("."));
        this.lastName = getLastNameFromEmail(email);
    }

    private String getLastNameFromEmail(String email) {
        // TODO: handle persons with middle names
        return email.substring(email.indexOf(".") + 1, email.indexOf("@"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", FIRST NAME: '" + firstName + '\'' +
                ", LAST NAME: '" + lastName + '\'' +
                ", EMAIL: '" + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }
}
