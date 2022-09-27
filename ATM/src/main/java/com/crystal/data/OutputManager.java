package com.crystal.data;

import java.util.Scanner;

public class OutputManager {
    static int choice;
    Scanner input = new Scanner(System.in);

    public void menu() {


        do {
            DbConnectionToPerson.AddingValuesFromDbToList();
            System.out.print(

                    """
                            Press 1 to add a new person to db
                            Press 2 to delete a persons to db
                            Press 3 to view all persons from db
                            Press 0 to exit"""

            );
            choice = input.nextInt();
            toDo();

        }
        while (choice == 1 || choice == 2 || choice == 3 || choice == 4);
        System.out.println("Thank you!");
    }


    public void toDo() {
            DbConnectionToPerson dbConnection=new DbConnectionToPerson();
        switch (choice) {
            case 1:
                dbConnection.addPerson();
                break;
            case 2:
                System.out.println("Please enter the id you want to delete :");
                int id = input.nextInt();
                dbConnection.deletePerson(id);break;
            case 3:
                dbConnection.showPerson();break;
            case 0:
                System.exit(0);break;


        }
    }
}
