package com.crystal.dbRepository;

import com.crystal.io.Menu;
import com.crystal.io.Scanner_input_Class;
import com.crystal.model.Person;
import com.crystal.servicesValidationForBank.ValidationDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Optional;
import static com.crystal.servicesValidationForBank.ValidationDb.randomPin;

public class DbConnectionToAccount  {
    static Connection c = null;
    static Statement stmt = null;

    public void createAccountTable() {

        {
            c = null;
            stmt = null;
            try {

                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                "postgres", "12345600");

                stmt = c.createStatement();

                String sql = "CREATE TABLE ACCOUNT" +
                        "(ID SERIAL PRIMARY KEY," +
                        "IBAN           TEXT    NOT NULL," +
                        "MONEY           INT    NOT NULL," +
                        "CODE            TEXT     NOT NULL," +
                        "FK_PEOPLE_ID INT," +
                        "FOREIGN KEY(FK_PEOPLE_ID) \n" +
                        "REFERENCES PEOPLEE(ID) ON DELETE CASCADE)";

                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }
        public static void addNewAccount(int idd) {

            try {
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                "postgres", "12345600");
                c.setAutoCommit(false);

                stmt = c.createStatement();

                int pin= randomPin();

                String iban= ValidationDb.generateIban();
                System.out.println("this");
                System.out.println(iban+pin);
                String sql = "INSERT INTO account(iban,money,code,fk_people_id)VALUES ('" + iban + "'," +0+",'"+pin+"',"+idd+");";
                    stmt.executeUpdate(sql);
                    stmt.close();
                    c.commit();
                    c.close();
                System.out.println("also the account of person with this id: "+idd+" added successfully "+
                        "\n Iban number is: "+iban+
                        "\n Pin number is: "+pin  +
                        "\n The account balance is: 0\n");
                    Menu.bankMenu();
                }

            catch (Exception e) {
                System.out.println("this");
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    public static void addNewAccountToExistedPersonInDb(int id) {
        ListManagmant.addingValuesFromPeopleTableDbToList();
        Optional<Person> personCompareId=ListManagmant.personList.stream().filter(i->i.getId()==id).findFirst();
        if(personCompareId.isPresent()){
        try {
            String name=personCompareId.get().getName();
            String surname=personCompareId.get().getSurname();
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "12345600");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            int pin= randomPin();
            String iban= ValidationDb.generateIban();
            String sql = "INSERT INTO account(iban,money,code,fk_people_id)VALUES ('" + iban + "'," +0+",'"+pin+"',"+id+");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
            System.out.println("The account of person : " +
                    name+" "+surname+
                    " with this id: "+id +" added successfully "+
            "\n Iban number is: "+iban+
            "\n Pin number is: "+pin  +
             "\n The account balance is: 0\n");
            Menu.bankMenu();
        }

        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }else System.out.println("This id doesn't exist please enter correct id!\n");
        addNewAccountToExistedPersonInDb(Scanner_input_Class.input.nextInt());
        }

    public static void main(String[] args) {

        DbConnectionToAccount.addNewAccount(2);
    }


}
