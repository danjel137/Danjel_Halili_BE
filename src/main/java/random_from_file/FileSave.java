package random_from_file;


import com.fasterxml.jackson.databind.ObjectMapper;
import person_from_db.Person;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class FileSave {

    public static void ListAllPersonSaveInJsonFile(List<Person> list) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/resources/JsonfileFromDb.json"), list);
    }
    public static Person[] ListAllPersonWriteFromJsonFileArray(){
        ObjectMapper mapper = new ObjectMapper();
        try{
           Person[] persons =mapper.readValue(new FileReader("src/main/resources/JsonfileFromDb.json"),Person[].class);


            for (int i=0;i<persons.length;i++) {
               // System.out.println(persons[i].getId()+persons[i].getEmail()+persons[i].getName()+persons[i].getSurname());
            }return persons;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void FileSaveAllNamePersonPersonFromDb(List <String>list) throws IOException {
        FileWriter fileWriter2 = new FileWriter("src/main/resources/FileAllPersons");
        for (String st : list) {
            fileWriter2.write(st + "\n");
        }
        fileWriter2.close();

    }

    public static void FileAllPersonFromDb(List<Person> list) throws IOException {
        FileWriter fileWriter2 = new FileWriter("src/main/resources/FileAllPersonFromDb");
        for (Person person : list) {
            fileWriter2.write(person + "\n");
        }
        fileWriter2.close();

    }
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
