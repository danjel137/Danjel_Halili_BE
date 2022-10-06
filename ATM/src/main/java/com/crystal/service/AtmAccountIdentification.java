package com.crystal.service;

import com.crystal.io.InputManagerCli;
import com.crystal.model.Account;
import com.crystal.model.Person;
import com.crystal.repository.ListManagmant;
import com.crystal.validator.ValidationAccount;

import java.sql.SQLException;
import java.util.Optional;

public class AtmAccountIdentification extends ListManagmant implements ValidationAccount {
    public String TempIBAN;
    public String TempCode;
    int primaryKeyPeople;


    public void checkIban() throws SQLException {
        InputManagerCli inputManagerCli = new InputManagerCli();
        ListManagmant listManagmant = new ListManagmant();
        listManagmant.addingValuesFromAccountTableDbToList();
        listManagmant.addingValuesFromPeopleTableDbToList();
        System.out.println("Please enter your IBAN\n");
        TempIBAN = inputManagerCli.getOnlyWordLetter();
        Optional<Account> account1 = ListManagmant.accountList.stream().filter(account -> account.getIBAN().equals(TempIBAN)).findFirst();

        if (account1.isPresent()) {
            primaryKeyPeople = account1.get().getPersonId();
            checkPin();
        } else {
            System.out.println("Please enter the correct Iban\n");
            checkIban();
        }
    }

    public void checkPin() throws SQLException {
        InputManagerCli inputManagerCli = new InputManagerCli();
        ListManagmant listManagmant = new ListManagmant();
        listManagmant.addingValuesFromPeopleTableDbToList();
        Optional<Person> person = ListManagmant.personList.stream().filter(accountList -> accountList.getId() == primaryKeyPeople).findFirst();
        if (person.isPresent()) {
            String personName = person.get().getName();
            String personSurname = person.get().getSurname();
            System.out.println("Welcome " + personName + " " + personSurname + " please enter your pin\n");
            TempCode = inputManagerCli.getOnlyWordLetter();
            Optional<Account> pinAccount = ListManagmant.accountList.stream().filter(account -> account.getCode().equals(TempCode)).findFirst();
            if (pinAccount.isPresent()) {
                String money = String.valueOf(pinAccount.get().getMoney());
                System.out.println("Your balance is: " + money);
                withdrawOrDeposit();
            } else {
                System.out.println("Please enter correct pin\n");
                checkPin();
            }
        }
    }

    public void withdrawOrDeposit() throws SQLException {
        BankService bankService = new BankService();
        InputManagerCli inputManagerCli = new InputManagerCli();
        System.out.println("Press 1 to withdraw \n" +
                "Press 2 to deposit\n" +
                "Press 0 to exit");
        int choseWithdrawOrDeposit = inputManagerCli.getInt();
        if (choseWithdrawOrDeposit == 1) {
            new AtmService().withdraw();

            System.out.println(bankService.amount);
            System.out.println("Thank you!");
            System.exit(0);
        } else if (choseWithdrawOrDeposit == 2) {
            new AtmService().deposit();
            System.out.println(bankService.amount);
            System.out.println("Thank you!");
            System.exit(0);
        } else if (choseWithdrawOrDeposit == 0) {
            System.exit(0);
        } else
            System.out.println("Please enter correct number!\n");
        withdrawOrDeposit();
    }


}
