package com.crystal.repository;

import com.crystal.io.InputManagerCli;
import com.crystal.io.MenuCli;
import com.crystal.model.Person;
import com.crystal.service.BankService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Optional;


public class DbManagmantAccount {


    public void createAccountTable() {
        Connection c;
        Statement stmt;
        {
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

    public void addNewAccount(int idd) {
        Connection c;
        Statement stmt;
        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "12345600");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            BankService bankService = new BankService();

            int pin = bankService.randomPin();

            String iban = bankService.generateIban();
            String sql = "INSERT INTO account(iban,money,code,fk_people_id)VALUES ('" + iban + "'," + 0 + ",'" + pin + "'," + idd + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
            System.out.println("also the account of person with this id: " + idd + " added successfully " +
                    "\n Iban number is: " + iban +
                    "\n Pin number is: " + pin +
                    "\n The account balance is: 0\n");
            MenuCli menuCli = new MenuCli();
            menuCli.bankMenu();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void addNewAccountToExistedPersonInDb(int id) {
        Connection c;
        Statement stmt;
        ListManagmant listManagmant = new ListManagmant();
        listManagmant.addingValuesFromPeopleTableDbToList();
        Optional<Person> personCompareId = ListManagmant.personList.stream().filter(i -> i.getId() == id).findFirst();
        if (personCompareId.isPresent()) {
            try {
                String name = personCompareId.get().getName();
                String surname = personCompareId.get().getSurname();
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                "postgres", "12345600");
                c.setAutoCommit(false);
                BankService bankService = new BankService();
                stmt = c.createStatement();
                int pin = bankService.randomPin();
                String iban = bankService.generateIban();
                String sql = "INSERT INTO account(iban,money,code,fk_people_id)VALUES ('" + iban + "'," + 0 + ",'" + pin + "'," + id + ");";
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
                c.close();
                System.out.println("The account of person : " +
                        name + " " + surname +
                        " with this id: " + id + " added successfully " +
                        "\n Iban number is: " + iban +
                        "\n Pin number is: " + pin +
                        "\n The account balance is: 0\n");
                MenuCli menuCli = new MenuCli();
                menuCli.bankMenu();
                // todo pass the result to menu and show menu there
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        } else System.out.println("This id doesn't exist please enter correct id!\n");
        InputManagerCli inputManagerCli = new InputManagerCli();
        addNewAccountToExistedPersonInDb(inputManagerCli.getInt());
    }


}
