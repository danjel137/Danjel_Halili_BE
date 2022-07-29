import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Probability {
    public static void main(String[] args) throws IOException {

        List<String> allPersons = new ArrayList<>();
        Scanner totalList = new Scanner(new FileReader("C:\\Users\\HP\\IdeaProjects\\Danjel_Halili_BE\\src\\main\\java\\lista.txt"));
        while (totalList.hasNext()) {
            allPersons.add(totalList.nextLine());
        }
        if (allPersons.isEmpty()) {
            System.out.println("List is empty !");
        } else {
            List<String> chosenPersons = new ArrayList<>();
            Scanner chosenList = new Scanner(new FileReader("C:\\Users\\HP\\IdeaProjects\\Danjel_Halili_BE\\src\\main\\java\\chosenPersonList.txt"));
            while (totalList.hasNext()) {
                chosenPersons.add(chosenList.nextLine());
            }
            Random r = new Random();
            int randomIndex = r.nextInt(allPersons.size());
            System.out.println("first person is " + allPersons.get(randomIndex));
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
                        if (allPersons.isEmpty()) {
                            System.out.println("list is empty");
                        }

                    }
                    answer = scanner.nextInt();
                }
            }
            FileWriter fileWriter = new FileWriter("C:\\Users\\HP\\IdeaProjects\\Danjel_Halili_BE\\src\\main\\java\\lista.txt");
            for (String str : allPersons) {
                fileWriter.write(str + "\n");
            }
            fileWriter.close();
            FileWriter fileWriter2 = new FileWriter("C:\\Users\\HP\\IdeaProjects\\Danjel_Halili_BE\\src\\main\\java\\chosenPersonList.txt");
            for (String str : chosenPersons) {
                fileWriter2.write(str + "\n");
            }
            fileWriter2.close();

        }


    }
}


