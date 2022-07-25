import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Probability {
    public static void main(String[] args) {
        List<String> li = List.of(
                "isuf.muca@crystal-system.eu",
                "danjel.halili@crystal-system.eu",
                "flavio.lorenci@crystal-system.eu",
                "elia.omeri@crystal-system.eu",
                "ardit.elezi@crystal-system.eu",
                "luka.buziu@crystal-system.eu",
                "megi.lala@crystal-system.eu",
                "irena.shahini@crystal-system.eu",
                "indrit.vaka@crystal-system.eu",
                "griselda.muci@crystal-system.eu",
                "theodor.gheorghe@crystal-system.eu",
                "ioan.cocianu@crystal-system.eu",
                "teofil.mitrea@crystal-system.eu",
                "eduard.tiutiu@crystal-system.eu",
                "george.dobrota@crystal-system.eu",
                "stefanita.plesa@crystal-system.eu",
                "adrian-nicolae.tigau@crystal-system.eu",
                "emanuel.grabovschi@crystal-system.eu",
                "george.sirbu@crystal-system.eu",
                "andrei.state@crystal-system.eu",
                "florin-adrian.dumitru@crystal-system.eu",
                "victor.hincu@crystal-system.eu"
        );
        List<String> allPersons = new ArrayList<>();
        List<String> chosenPersons = new ArrayList<>();
        allPersons.addAll(li);
        Random r = new Random();
        int randomIndex = r.nextInt(allPersons.size());
        System.out.println("first person is " + li.get(randomIndex));
        chosenPersons.add(allPersons.get(randomIndex));
        allPersons.remove(allPersons.get(randomIndex));
        Scanner scanner = new Scanner(System.in);

        System.out.println("if you want to continue press 1 else press 0");
        int answer = scanner.nextInt();

        while (answer != 0) {

            if (answer == 1) {
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
                    if (allPersons.size() == 0) {
                        System.out.println("list is empty");
                    }

                }
                answer = scanner.nextInt();
            }
        }

    }

}


