import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {
    Scanner input = new Scanner(System.in);
    List<String> allPersons = new ArrayList<>();
//            List.of(
//            "isuf.muca@crystal-system.eu",
//            "danjel.halili@crystal-system.eu",
//            "flavio.lorenci@crystal-system.eu",
//            "elia.omeri@crystal-system.eu",
//            "ardit.elezi@crystal-system.eu",
//            "luka.buziu@crystal-system.eu",
//            "megi.lala@crystal-system.eu",
//            "irena.shahini@crystal-system.eu",
//            "indrit.vaka@crystal-system.eu"
//
//    ));

    List<String> chosenPersons = new ArrayList<>();

    public void fetchFromFile() throws FileNotFoundException {
        try {
            FileSave.FileWriteAllPersons(this);
        } catch (Exception e) {
            System.out.println("List is empty can't fetch anymore person");
        }
        try {
            FileSave.FileWriteChosenPersons(this);
        } catch (Exception e) {
            System.out.println("List is empty can't fetch anymore person");
        }
    }

    static Random r = new Random();

    public void choseOnlyFirstPersonRandom(){
        int randomIndex = Main.r.nextInt(allPersons.size());
        System.out.println("first person is " +  allPersons.get(randomIndex) + "\n");
         chosenPersons.add( allPersons.get(randomIndex));
         allPersons.remove( allPersons.get(randomIndex));
         text();
    }
    public void probabilityChosen() {

        {
            int b = r.nextInt(20);
            int c = r.nextInt(20);

            if (b == c) {
                int d = r.nextInt(chosenPersons.size());
                System.out.println("other person is " + chosenPersons.get(d));


            } else {

                int randomIndex1 = r.nextInt(allPersons.size());
                chosenPersons.add(allPersons.get(randomIndex1));
                System.out.println("other person is " + allPersons.get(randomIndex1));
                allPersons.remove(randomIndex1);
                if (allPersons.isEmpty()) {
                    System.out.println("list is empty");
                }


            }

        }
    }


    public void printPersons(List<String> list) {
        list.forEach(System.out::println);
    }

    public void numAllPerson() {
        System.out.println(
                "write first show number of all persons when they aren't chosen yet" + "\n" +
                        "write second to show number of person then already chose ");
        String n = input.nextLine();
        if (n.equalsIgnoreCase("first")) {
            System.out.println(allPersons.size() + " persons aren't chosen yet");

        } else if (n.equalsIgnoreCase("second")) {
            System.out.println(chosenPersons.stream()
                    .count() + " persons are chosen.");

        }

    }

    public void showWhatYouWantToDo(int n) throws IOException {


        if (n == 1) {
            probabilityChosen();
        } else if (n == 2) {
            printPersons(allPersons);
        } else if (n == 3) {
            printPersons(chosenPersons);
        } else if (n == 4) {
            numAllPerson();
        } else if (n == 5) {
            listModify();
        } else {
            System.out.println("Please enter the correct number!");
        }
        text();
    }

    public void listModify() {
        System.out.println("write add if you want to add new email person" + "\n" +
                "write delete if you want to remove any email person in list");
        String a = input.nextLine();
        if (a.equalsIgnoreCase("add")) {
            System.out.println("Please add the email of the person you want to add");
            String st = input.nextLine();
            boolean b = allPersons.stream().anyMatch((l) -> l.equals(st));
            boolean c = chosenPersons.stream().anyMatch((l) -> l.equals(st));

            if (!b && !c) {
                allPersons.add(st);
            }

            else {
                System.out.println(st + " this email already exist");
            }

        }
        if (a.equalsIgnoreCase("delete")) {
            System.out.println("Please add the email of the person you want to remove");
            String st = input.nextLine();
            boolean b = allPersons.stream().allMatch((l) -> l.equals(st));
            boolean c = chosenPersons.stream().allMatch((l) -> l.equals(st));
            if (!c) {
                chosenPersons.remove(st);
            }
            if (!b) {
                allPersons.remove(st);
            } else {
                System.out.println(st + " do not exist in this list");
            }

        }
    }


    public void text() {
        System.out.println("Press 1 to chose a random person" + "\n" +
                "Press 2 to show all persons when they aren't chosen yet" + "\n" +
                "Press 3 to show person then already chose " + "\n" +
                "Press 4 to show person number of person in lists" + "\n" +
                "Press 5 for more" + "\n" +
                "Press 6 to left");
    }
}
