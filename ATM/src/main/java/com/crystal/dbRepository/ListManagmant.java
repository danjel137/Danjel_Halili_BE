package com.crystal.dbRepository;

import com.crystal.dao.dataAccessList;
import com.crystal.io.Menu;
import com.crystal.io.OutputManagerToDb;
import com.crystal.io.Scanner_input_Class;
import com.crystal.model.Account;
import com.crystal.model.Person;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ListManagmant implements dataAccessList {
    static Connection c = null;
    static Statement stmt = null;
    public static Set<Integer> idOfAllPersons = new HashSet<>();
    public static Set<Person> personList = new HashSet<>();
    public static Set<Account> accountList = new HashSet<>();
    public static Set<Integer> idOfAllAccount = new HashSet<>();
    @Override
    public Set<Integer> getAllPersons() {
        return  idOfAllPersons;
    }
    @Override
    public Set<Integer> getAllAccounts() {return  idOfAllAccount;
    }
    @Override
    public Set<Person> getPersons() {
        return personList;
    }

    @Override
    public Set<Account> getAccounts() {
        return accountList;
    }



    public static void getPersonByIdFromDb(int id) throws SQLException {

        addingValuesFromPeopleTableDbToList();
        boolean contains;
        contains=idOfAllPersons.stream().anyMatch(i->i.equals(id));
        if(contains){
            personList.forEach(i->{
                if(i.getId()==id){
                    System.out.println(i);
                }
            });

            Menu.bankMenu();
        }

        System.out.println("This id: "+id+" is not found in database ,please enter the correct id \n");
        ListManagmant.getPersonByIdFromDb(Scanner_input_Class.input.nextInt());


    }
    public static void getAccountByIdFromDb(int id) throws SQLException {
        addingValuesFromAccountTableDbToList();
        boolean contains;
        contains=idOfAllAccount.stream().anyMatch(i->i.equals(id));
        if(contains){
            accountList.forEach(i->{
                if(i.getId()==id){
                    System.out.println(i);
                }
            });
            Menu.bankMenu();
        }else

            System.out.println("This id: "+id+" is not found in database ,please enter the correct id \n");
        ListManagmant.getAccountByIdFromDb(Scanner_input_Class.input.nextInt());
    }


    public static void addingValuesFromPeopleTableDbToList() {
        {
            {

                try {

                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                    "postgres", "12345600");
                    c.setAutoCommit(false);


                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM peoplee ;");

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String surname = rs.getString("surname");
                        int age = rs.getInt("age");
                        String gender = rs.getString("gender");
                        char c1 = gender.charAt(0);
                        String phoneNumber = rs.getString("phonenumber");
                        String birthday = rs.getString("birthday");
                        DateTime date1 = new DateTime(birthday);
                        String birthplace = rs.getString("birthplace");
                        String email = rs.getString("email");
                        ListManagmant.idOfAllPersons.add(id);
                        Person person = new Person(id, name, surname, age, c1, phoneNumber, date1, birthplace, email);
                        ListManagmant.personList.add(person);

                    }


                    rs.close();
                    stmt.close();
                    c.close();

                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
            }
        }
    }

    public static void addingValuesFromAccountTableDbToList() {
        {
            {

                try {

                    c = DriverManager
                            .getConnection("jdbc:postgresql://localhost:5432/postgres",
                                    "postgres", "12345600");
                    c.setAutoCommit(false);


                    stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM account ;");

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String iban = rs.getString("iban");
                        int money = rs.getInt("money");
                        String code = rs.getString("code");
                        int fkKey= rs.getInt("fk_people_id");
                        ListManagmant.idOfAllAccount.add(id);
                        Account account=new Account(id,iban,money,code,fkKey);
                        ListManagmant.accountList.add(account);

                    }


                    rs.close();
                    stmt.close();
                    c.close();

                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
            }
        }
    }

}
