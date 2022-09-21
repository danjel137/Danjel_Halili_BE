package org.crystal.group.selector.person_from_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PrepareInsertStatement {
    static Scanner input = new Scanner(System.in);

    public static void addPersonsToDb(List<Person> personList) throws SQLException {
        Connection myCon = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                "12345600");
        PreparedStatement myStmt = null;
        for (Person per : personList) {
            myStmt = myCon.prepareStatement("INSERT INTO \"Person\" VALUES (?,?,?,?)");
            myStmt.setInt(1, per.getId());
            myStmt.setString(2, per.getName());
            myStmt.setString(3, per.getSurname());
            myStmt.setString(4, per.getEmail());
            myStmt.execute();
        }
        if (myStmt != null)
            myStmt.close();
        myCon.close();
    }

    public static void addNewPerson() throws SQLException {


        System.out.println("Please enter id");
        Integer addId = input.nextInt();
        System.out.println("Please enter name");
        input.nextLine();
        String addName = input.nextLine();
        System.out.println("Please enter surname");
        String addSurname = input.nextLine();
        System.out.println("Please email");
        String addEmail = input.nextLine();

        Person person = new Person(addId, addName, addSurname, addEmail);
        Db.personListFromDb.add(person);
        addPersonsToDb(List.of(person));
    }
}
