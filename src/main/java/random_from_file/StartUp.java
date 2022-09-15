package random_from_file;

import java.io.IOException;

import java.util.Scanner;

public class StartUp {
    public static void main(String[] args) throws IOException {


        Scanner input = new Scanner(System.in);
        Main object = new Main();
        object.fetchFromFile();
        object.choseOnlyFirstPersonRandom();
        int answer = input.nextInt();
        do {
            object.showWhatYouWantToDo(answer);
            answer = input.nextInt();
        } while (answer != 6);
        FileSave.FileSaveAllPersons(object);
        FileSave.FileSaveChosenPerson(object);
        System.out.println("List are save successfully" + "\n" + "Thank you!!!");


    }
}


