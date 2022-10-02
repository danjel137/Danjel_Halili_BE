package com.crystal.io;

import com.crystal.dbRepository.BankService;
import com.crystal.dbRepository.DbConnectionToAccount;
import com.crystal.dbRepository.DbConnectionToPerson;
import com.crystal.dbRepository.ListManagmant;

import java.sql.SQLException;

public class OutputManagerToDb {

    public void toDoForBankServices() throws SQLException {
            DbConnectionToPerson dbConnection=new DbConnectionToPerson();
        switch (Menu.choiceForBankMenu) {
            case 1:
                dbConnection.addPerson();
                break;
            case 2:
                System.out.println("Please enter the id you want to delete :\n");
                int id = Scanner_input_Class.input.nextInt();
                dbConnection.deletePerson(id);break;
            case 3:
                dbConnection.showPersons();break;
            case 4:
                System.out.println("Please enter the id person you want to add\n");
                DbConnectionToAccount.addNewAccountToExistedPersonInDb(Scanner_input_Class.input.nextInt());break;
            case 5:
                System.out.println("please enter the id of the person you want to display\n");
                ListManagmant.getPersonByIdFromDb(Scanner_input_Class.input.nextInt());break;
            case 6:
                System.out.println("please enter the id of the account you want to display\n");
                ListManagmant.getAccountByIdFromDb(Scanner_input_Class.input.nextInt());break;
            case 7:
                BankService bankService=new BankService();
                bankService.withdraw();break;
            case 8:
                BankService bankService1=new BankService();
                bankService1.deposit();break;
            case 9:
                Menu.mainMenu();break;
            case 0:
                System.out.println("Thank you");
                System.exit(0);break;
            default:
                System.out.println("please enter the correct number\n");
                Menu.bankMenu();break;
        }

    }
}
