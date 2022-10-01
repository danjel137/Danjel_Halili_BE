package com.crystal.model;

import lombok.*;
import org.joda.time.DateTime;

@Data


public class Person {
    private int id;
    private String name;
    private String surname;
    private int age;
    private char gender;
    private String phoneNumber;
    private DateTime birthday;
    private String birthplace;
    private String email;

    public Person(String name, String surname, int age, char gender, String phoneNumber, DateTime birthday, String birthplace, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.email = email;
    }

    public Person(int id, String name, String surname, int age, char gender, String phoneNumber, DateTime birthday, String birthplace, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.email = email;
    }

}
