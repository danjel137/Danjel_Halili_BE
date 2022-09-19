package com.crystal.be.selector;

import java.util.Scanner;

public abstract class PersonSelector {

    public void showMenu() {
        System.out.println();
        System.out.println("1. List all persons");
        System.out.println("2. Select random person");
        System.out.println("3. Exit");

        Scanner input = new Scanner(System.in);
        int answer;
        do {
            answer = input.nextInt();
        } while (answer != 1 && answer != 2 && answer != 3);

        handleAnswer(answer);
    }

    private void handleAnswer(int answer) {
        switch (answer) {
            case 1:
                listAllPersons();
                showMenu();
                break;
            case 2:
                selectRandomPerson();
                showMenu();
                break;
            case 3:
                System.out.println("Bye!");
                System.exit(0);
        }
    }

    protected abstract void listAllPersons();

    private void selectRandomPerson() {
        System.out.println("Selecting random person");
        // TODO
    }
}
