package com.crystal.service;

import com.crystal.io.InputManagerCli;
import com.crystal.model.CurrencyType;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BankService implements AccountService {

    public int amount;

    @Override
    public double withdraw() {
        InputManagerCli inputManagerCli = new InputManagerCli();
        BankService bankService = new BankService();
        System.out.println("Please enter id for the person you want to withdraw money\n");
        int id = inputManagerCli.getInt();
        bankService.fetchMoneyAmountFromDb(id);
        System.out.println("Please enter value you want to withdraw\n");
        int moneyYouWantToDeposit = inputManagerCli.getInt();
        if (amount >= moneyYouWantToDeposit) {
            bankService.updateWithdrawMoneyAmountToDb(id, moneyYouWantToDeposit);
            System.out.println("The withdraw was successfully completed, the balance status with id:" + id + "  is: " + amount + " " + CurrencyType.EURO);
            return amount;
        } else {
            System.out.println("your balance  is less than the amount you want to withdraw, your balance account is:" + amount);
        }
        withdraw();
        return 0;
    }


    @Override
    public double deposit() {
        BankService bankService = new BankService();
        InputManagerCli inputManagerCli = new InputManagerCli();
        System.out.println("Please enter id for the person you want to deposit money\n");
        int id = inputManagerCli.getInt();
        bankService.fetchMoneyAmountFromDb(id);
        System.out.println("Please enter value you want to deposit");
        int moneyYouWantToDeposit = inputManagerCli.getInt();
        bankService.updateDepositMoneyAmountToDb(id, moneyYouWantToDeposit);
        System.out.println("The deposit was successfully completed, the balance status with id:" + id + "  is: " + amount + " " + CurrencyType.EURO + "\n");
        return amount;
    }

    public void fetchMoneyAmountFromDb(int id) {
        Connection c;
        Statement stmt;
        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "12345600");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select money from account WHERE id=" + id + ";");
            int money;
            while (rs.next()) {
                money = rs.getInt("money");
                amount = money;
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public void updateDepositMoneyAmountToDb(int id, long moneyYouWantToDeposit) {
        Connection c;
        Statement stmt;
        try {

            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "12345600");
            amount += moneyYouWantToDeposit;
            String SQL = "update account set money=" + amount + " where id=" + id + ";";
            stmt = c.createStatement();
            int rs = stmt.executeUpdate(SQL);
            System.out.println(rs);
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public void updateWithdrawMoneyAmountToDb(int id, long moneyYouWantToWithdraw) {
        Connection c;
        Statement stmt;
        try {
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "12345600");
            amount -= moneyYouWantToWithdraw;
            String SQL = "update account set money=" + amount + " where id=" + id + ";";
            stmt = c.createStatement();

            int rs = stmt.executeUpdate(SQL);
            System.out.println(rs);
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public String generateIban() {
        Iban iban = Iban.random(CountryCode.AL);
        String Iban = String.valueOf(iban);
        return Iban;
    }

    public int randomPin() {
        return (int) (Math.floor(Math.random() * 9_000) + 1_000L);
    }

}
