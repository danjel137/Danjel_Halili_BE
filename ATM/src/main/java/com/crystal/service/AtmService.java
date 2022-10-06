package com.crystal.service;

import com.crystal.io.InputManagerCli;
import com.crystal.model.CurrencyType;

import java.sql.*;


public class AtmService implements AccountService {
    @Override
    public double withdraw() throws SQLException {
        BankService bankService = new BankService();
        InputManagerCli inputManagerCli = new InputManagerCli();
        bankService.fetchMoneyAmountFromDb(getIdAccountSelectedFromFkRelatedToPeopleTable());
        System.out.println("Please enter value you want to withdraw");
        int moneyYouWantToWithdraw = inputManagerCli.getInt();
        if (bankService.amount >= moneyYouWantToWithdraw) {
            if (moneyYouWantToWithdraw < 501) {
                bankService.updateWithdrawMoneyAmountToDb(getIdAccountSelectedFromFkRelatedToPeopleTable(), moneyYouWantToWithdraw);
                AtmAccountIdentification atmAccountIdentification = new AtmAccountIdentification();
                System.out.println("The withdraw was successfully completed, the balance status with id:"
                        + atmAccountIdentification.primaryKeyPeople + "  is: " + bankService.amount + " " + CurrencyType.EURO);
                return bankService.amount;
            } else {

                System.out.println("You can't withdraw more the 500 " + CurrencyType.EURO +
                        "\n \n" +
                        "please enter the amount of money you want to withdraw less than 501" + CurrencyType.EURO);
                withdraw();

            }
            return 0;
        } else
            System.out.println("your balance is less than the amount you want to withdraw, your balance account is:" + bankService.amount);
        withdraw();
        return 0;
    }

    @Override
    public double deposit() throws SQLException {
        BankService bankService = new BankService();
        InputManagerCli inputManagerCli = new InputManagerCli();
        bankService.fetchMoneyAmountFromDb(getIdAccountSelectedFromFkRelatedToPeopleTable());
        System.out.println("Please enter value you want to deposit");
        int moneyYouWantToDeposit = inputManagerCli.getInt();
        if (moneyYouWantToDeposit < 1001) {
            bankService.updateDepositMoneyAmountToDb(getIdAccountSelectedFromFkRelatedToPeopleTable(), moneyYouWantToDeposit);
            System.out.println("The deposit was successfully completed, the balance status with id:" + getIdAccountSelectedFromFkRelatedToPeopleTable()
                    + "  is: " + bankService.amount + " " + CurrencyType.EURO);
            return bankService.amount;
        } else {
            System.out.println("You can't deposit more the 1000 " + CurrencyType.EURO +
                    "\n \n" +
                    "please enter the amount of money you want to deposit less than 1001 " + CurrencyType.EURO);
            deposit();
        }
        return 0;
    }

    public static int getIdAccountSelectedFromFkRelatedToPeopleTable() throws SQLException {
        Connection c;
        Statement stmt;
        c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres", "12345600");
        c.setAutoCommit(false);

        stmt = c.createStatement();
        AtmAccountIdentification atmAccountIdentification = new AtmAccountIdentification();
        ResultSet rs = stmt.executeQuery("SELECT id FROM account WHERE fk_people_id=" + atmAccountIdentification.primaryKeyPeople + ";");
        int id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        return id;
    }


}
