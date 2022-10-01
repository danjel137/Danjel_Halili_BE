package com.crystal.dbRepository;

import com.crystal.dao.PersonService;
import com.crystal.io.Menu;
import com.crystal.io.OutputManagerToDb;
import com.crystal.io.Scanner_input_Class;
import com.crystal.model.Person;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DbConnectionToPerson implements PersonService {
    static Connection c = null;
    static Statement stmt = null;

    public void createPeopleTable() {

        {
            c = null;
            stmt = null;
            try {

                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                "postgres", "12345600");

                stmt = c.createStatement();

                String sql = "CREATE TABLE PEOPLEE" +
                        "(ID SERIAL PRIMARY KEY," +
                        "NAME           TEXT    NOT NULL," +
                        "SURNAME           TEXT    NOT NULL," +
                        "AGE            INT     NOT NULL," +
                        "GENDER        CHAR(1)," +
                        "PHONENUMBER           TEXT    NOT NULL," +
                        "BIRTHDAY  TEXT NOT NULL," +
                        "BIRTHPLACE  TEXT NOT NULL," +
                        "EMAIL  TEXT NOT NULL)";

                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    public void addPerson() {

        try {

            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "12345600");
            c.setAutoCommit(false);

            stmt = c.createStatement();

            while (true) {
                menuToAddNewPerson();

            }


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static String convertPersonObjectToString(Person person) {
        StringBuilder str = new StringBuilder("INSERT INTO peoplee ( name, surname,age,gender,phonenumber,birthday,birthplace,email)" + " VALUES (" + "'" +
                person.getName() + "','" + person.getSurname() + "'," + person.getAge() + ",'" + person.getGender() +
                "','" + person.getPhoneNumber() + "','" + person.getBirthday() + "','" + person.getBirthplace() + "','" + person.getEmail() + "'); ");
        System.out.println(str);
        return String.valueOf(str);

    }

    public static void menuToAddNewPerson() {
        String message = "";

        try {
            Scanner_input_Class.input = new Scanner(System.in);
            System.out.println("Enter name");
            String name = Scanner_input_Class.input.nextLine();
            System.out.println("Enter surname");
            String surname = Scanner_input_Class.input.nextLine();
            System.out.println("Enter age");
            message = "Please enter a type of int for age";
            int age = Scanner_input_Class.input.nextInt();

            message = "Please enter a type of char for gender Ex: m/f";
            System.out.println("Enter gender in this char format Ex: m/f");
            Character gender = Scanner_input_Class.input.next().charAt(0);
            Pattern regex = Pattern.compile("[M | m | F | f]");
            if (!regex.matcher(gender + "").matches()) throw new IllegalArgumentException("");
            Scanner_input_Class.input.nextLine();
            System.out.println("Enter your phone number");
            String phoneNumber = Scanner_input_Class.input.nextLine();

            System.out.println("Enter your birthday in this format yyyy-mm-dd");
            String date = Scanner_input_Class.input.nextLine();
            message = "Please enter a correct format of date Ex: `yyyy-mm-dd`";
            DateTime date1 = new DateTime(date);

            System.out.println("Enter your birthplace");
            String birthplace = Scanner_input_Class.input.nextLine();

            System.out.println("Enter your email");
            String email = Scanner_input_Class.input.nextLine();
            Person person = new Person(name, surname, age,
                    gender, phoneNumber, date1, birthplace, email);
            stmt.executeUpdate(convertPersonObjectToString(person));

            stmt.close();
            c.commit();
            c.close();
            System.out.println(name +" "+surname+ " added successfully to our db\n");
            DbConnectionToAccount.addNewAccount(DbConnectionToPerson.getIdPeopleeTable());
            Menu.bankMenu();
        } catch (IllegalArgumentException | InputMismatchException | SQLException sql) {

            System.out.printf("%s", message + "\n");
        }

    }
    public static int getIdPeopleeTable() throws SQLException {
        c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres", "12345600");
        c.setAutoCommit(false);

        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id FROM peoplee ;");
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
       return id;
    }
    public void deletePerson(int id) throws SQLException {
        ListManagmant.addingValuesFromPeopleTableDbToList();
        if (ListManagmant.idOfAllPersons.contains(id)) {

            try {

                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                "postgres", "12345600");
                c.setAutoCommit(false);

                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("select name ,surname from peoplee where id =" + id);
                while (rs.next()) {
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    System.out.println(name + " " + surname + " deleted successfully\n");
                }
                String sql = "DELETE from peoplee where ID =" + id;
                stmt.executeUpdate(sql);
                c.commit();
                stmt = c.createStatement();
                rs.close();
                stmt.close();
                c.close();
                Menu.bankMenu();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

        }
            else {
            System.out.println(" this id not exist\n");
            Menu.bankMenu();
        }
    }

    public void showPersons() {
        {

            try {

                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                "postgres", "12345600");
                c.setAutoCommit(false);

                stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM peoplee ;");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    int age = rs.getInt("age");
                    String gender = rs.getString("gender");
                    String phoneNumber = rs.getString("phonenumber");
                    String birthday = rs.getString("birthday");
                    String birthplace = rs.getString("birthplace");
                    String email = rs.getString("email");
                    System.out.println("ID = " + id);
                    System.out.println("NAME = " + name);
                    System.out.println("SURNAME = " + surname);
                    System.out.println("AGE = " + age);
                    System.out.println("GENDER = " + gender);
                    System.out.println("PHONE_NUMBER = " + phoneNumber);
                    System.out.println("BIRTHDAY = " + birthday);
                    System.out.println("BIRTHPLACE = " + birthplace);
                    System.out.println("EMAIL = " + email);
                    System.out.println();
                }
                rs.close();
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }



}
