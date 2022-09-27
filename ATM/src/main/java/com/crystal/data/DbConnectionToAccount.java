package com.crystal.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;


import static com.crystal.servicesValidation.ValidationDb.randomIbnValidation;
import static com.crystal.servicesValidation.ValidationDb.randomPinValidation;

public class DbConnectionToAccount  {
    static Connection c = null;
    static Statement stmt = null;
    static Scanner input = new Scanner(System.in);

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
                        //"FOREIGN KEY (ID) REFERENCES PEOPLEE (ID) ON DELETE CASCADE)";

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
                long iban=randomIbnValidation();
                String Iban="AL"+iban;
                int pin=randomPinValidation();

                String sql = "INSERT INTO account(iban,money,code,fk_people_id)VALUES ('" +Iban+ "'," +0+",'"+pin+"',"+idd+");";
                    stmt.executeUpdate(sql);
                    stmt.close();
                    c.commit();
                    c.close();
                    new OutputManager().menu();
                }

            catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }




}
