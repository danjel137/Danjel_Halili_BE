package person_from_db;

import random_from_file.FileSave;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Db {
    public static List<Person> personListFromDb = new ArrayList<>();
    public static List<String> namePersonsFromDb = new ArrayList<>();

    public static void main(String[] args)  {


        Connection c = null;

        Statement stmt = null;

        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "12345600");

            System.out.println("Successfully Connected.");


            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("select * from public.\"Person\" ;");


            while (rs.next()) {

                int personId = rs.getInt("id");

                String name = rs.getString("name");

                String surname = rs.getString("surname");


                String email = rs.getString("email");
                personListFromDb.add(new Person(personId, name, surname, email));


            }
            for (Person person : personListFromDb) {
                namePersonsFromDb.add(person.getName());
            }
            System.out.println(namePersonsFromDb);
            System.out.println(personListFromDb);


            rs.close();

            stmt.close();

            c.close();
            FileSave.FileSaveAllNamePersonPersonFromDb(namePersonsFromDb);
            FileSave.FileAllPersonFromDb(personListFromDb);
            FileSave.ListAllPersonSaveInJsonFile(personListFromDb);
        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            System.exit(0);

        }


    }

}
