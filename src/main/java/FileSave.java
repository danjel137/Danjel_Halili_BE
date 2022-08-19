import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class FileSave {


    public static void FileSaveAllPersons(Main o) throws IOException {

        FileWriter fileWriter = new FileWriter("src\\main\\resources\\FileAllPersons");
        List<String> list = o.allPersons;
        String content = list.stream()
                .map(p -> p + "\n")
                .collect(Collectors.joining());
        fileWriter.write(content);
        fileWriter.close();

    }

    public static void FileSaveChosenPerson(Main o) throws IOException {
        FileWriter fileWriter2 = new FileWriter("src\\main\\resources\\FileChosenPerson");
        List<String> list = o.chosenPersons;
        for (String st : o.chosenPersons) {
            fileWriter2.write(st + "\n");
        }
        fileWriter2.close();

    }

    public static void FileWriteAllPersons(Main o) throws FileNotFoundException {
        Scanner totalList = new Scanner(new FileReader("src\\main\\resources\\FileAllPersons"));
        while (totalList.hasNextLine()) {
            o.allPersons.add(totalList.nextLine());
        }
        if (o.allPersons.isEmpty()) {
            System.out.println("List is empty !");
        }
    }

    public static void FileWriteChosenPersons(Main o) throws FileNotFoundException {
        Scanner chosenList = new Scanner(new FileReader("src\\main\\resources\\FileChosenPerson"));
        while (chosenList.hasNext()) {
            o.chosenPersons.add(chosenList.nextLine());
        }
    }
}
