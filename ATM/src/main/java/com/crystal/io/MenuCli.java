package com.crystal.io;

import com.crystal.model.Person;
import com.crystal.repository.DbManagmantAccount;
import com.crystal.repository.DbManagmantPerson;
import com.crystal.repository.ListManagmant;
import com.crystal.service.AtmAccountIdentification;
import com.crystal.service.BankService;
import org.joda.time.DateTime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class MenuCli implements Menu {

    private int choiceForMainMenu;
    private int choiceForBankMenu;

    InputManagerCli inputManagerCli = new InputManagerCli();

    public void mainMenu() throws SQLException {


        System.out.println(
                """
                        Press 1 to connect bank service
                        Press 2 to connect atm service
                        Press 0 to exit""");
        choiceForMainMenu = inputManagerCli.getInt();
        if (choiceForMainMenu == 1) {
            bankMenu();
        } else if (choiceForMainMenu == 2) {
            AtmAccountIdentification atmAccountIdentification = new AtmAccountIdentification();
            atmAccountIdentification.checkIban();
        } else if (choiceForMainMenu == 0) {
            System.out.println("Thank you");
            System.exit(0);
        } else
            System.out.println("Please enter correct number");
        mainMenu();
    }

    public void bankMenu() throws SQLException {

        do {
            System.out.print(

                    """
                            Press 1 to add a new person to db
                            Press 2 to delete a persons to db
                            Press 3 to view all persons from db
                            Press 4 to add new account with id input who already existed in our db
                            Press 5 to show a specific person by id
                            Press 6 to show a specific account by id
                            Press 7 to withdraw money
                            Press 8 to deposit money
                            Press 9 for main menu
                            Press 0 to exit
                             """

            );
            choiceForBankMenu = inputManagerCli.getInt();
            handleAnswer(choiceForBankMenu);

        }
        while (choiceForBankMenu == 1 || choiceForBankMenu == 2 || choiceForBankMenu == 3 || choiceForBankMenu == 4 || choiceForBankMenu == 5
                || choiceForBankMenu == 6 || choiceForBankMenu == 7 || choiceForBankMenu == 8 || choiceForBankMenu == 9);
    }


    public void handleAnswer(int choiceForBankMenu) throws SQLException {
        DbManagmantPerson dbConnectionToPerson = new DbManagmantPerson();
        DbManagmantAccount dbConnectionToAccount = new DbManagmantAccount();
        ListManagmant listManagmant = new ListManagmant();
        switch (choiceForBankMenu) {
            case 1:
                dbConnectionToPerson.addPerson();
                break;
            case 2:
                System.out.println("Please enter the id you want to delete :\n");
                int id = inputManagerCli.getInt();
                dbConnectionToPerson.deletePerson(id);
                break;
            case 3:
                dbConnectionToPerson.showPersons();
                break;
            case 4:
                System.out.println("Please enter the id person you want to add\n");
                dbConnectionToAccount.addNewAccountToExistedPersonInDb(inputManagerCli.getInt());
                break;
            case 5:
                System.out.println("please enter the id of the person you want to display\n");
                listManagmant.getPersonByIdFromDb(inputManagerCli.getInt());
                break;
            case 6:
                System.out.println("please enter the id of the account you want to display\n");
                listManagmant.getAccountByIdFromDb(inputManagerCli.getInt());
                break;
            case 7:
                BankService bankService = new BankService();
                bankService.withdraw();
                break;
            case 8:
                BankService bankService1 = new BankService();
                bankService1.deposit();
                break;
            case 9:

                new MenuCli().mainMenu();
                break;
            case 0:
                System.out.println("Thank you");
                System.exit(0);
                break;
            default:
                System.out.println("please enter the correct number\n");
                new MenuCli().bankMenu();
                break;
        }

    }

    public void menuToAddNewPersonWithHisNewAccount() throws SQLException {
        Connection c;
        Statement stmt;
        c = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres", "12345600");
        c.setAutoCommit(false);

        stmt = c.createStatement();

        System.out.println("Enter name");
        String name = inputManagerCli.getOnlyWordLetter();
        System.out.println("Enter surname");
        String surname = inputManagerCli.getOnlyWordLetter();
        System.out.println("Enter age");
        int age = inputManagerCli.getInt();
        System.out.println("Enter gender in this char format Ex: m/f");
        String gender = inputManagerCli.getOneLetterForGender();
        char genderChar = gender.charAt(0);
        System.out.println("Enter phone number in this format 123-456-7890");
        String phoneNumber = inputManagerCli.getPhoneNumber();
        System.out.println("Enter your birthday in this format yyyy-mm-dd");
        String date = inputManagerCli.getDate();
        DateTime date1 = new DateTime(date);
        System.out.println("Enter birthplace");
        String birthplace = inputManagerCli.getOnlyWordLetter();
        System.out.println("Enter email");
        String email = inputManagerCli.getEmail();
        Person person = new Person(name, surname, age,
                genderChar, phoneNumber, date1, birthplace, email);
        System.out.println(getSqlInsertQuery(person));
        stmt.executeUpdate(getSqlInsertQuery(person));

        stmt.close();
        c.commit();
        c.close();
        System.out.println(name + " " + surname + " added successfully to our db\n");
        DbManagmantAccount dbConnectionToAccount = new DbManagmantAccount();
        dbConnectionToAccount.addNewAccount(DbManagmantPerson.getIdPeopleeTable());
        bankMenu();


    }

    public String getSqlInsertQuery(Person person) {
        StringBuilder str = new StringBuilder("INSERT INTO peoplee ( name, surname,age,gender,phonenumber,birthday,birthplace,email)" + " VALUES (" + "'" +
                person.getName() + "','" + person.getSurname() + "'," + person.getAge() + ",'" + person.getGender() +
                "','" + person.getPhoneNumber() + "','" + person.getBirthday() + "','" + person.getBirthplace() + "','" + person.getEmail() + "'); ");
        System.out.println(str);
        return String.valueOf(str);
    }

//    public static void main(String[] args) {
//        MenuCli menuCli=new MenuCli();
//        menuCli.menuToAddNewPerson();
//    }

}
