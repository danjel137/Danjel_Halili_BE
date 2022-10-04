package com.crystal.repository;

import com.crystal.io.MenuCli;

import java.sql.*;

public class DbManagmantPerson implements DataAccessList {
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
                new MenuCli().menuToAddNewPersonWithHisNewAccount();
            }


        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
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
        ListManagmant listManagmant = new ListManagmant();
        listManagmant.addingValuesFromPeopleTableDbToList();
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
                MenuCli menuCli = new MenuCli();
                menuCli.bankMenu();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

        } else {
            System.out.println(" this id not exist\n");
            MenuCli menuCli = new MenuCli();
            menuCli.bankMenu();
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
