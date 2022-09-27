package com.crystal;



import com.crystal.data.OutputManager;
import com.crystal.model.Bank;
import com.crystal.model.Person;
import org.joda.time.DateTime;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {

       Person person=new Person(1,"Danjel","Halili",25,'m',"+355676225794", new DateTime("1997-04-04"),  "Puka","danjelhalili2@gmail.com");

         Bank bank=new Bank("BKT","Durres","AWDAWD");


        OutputManager outputManager=new OutputManager();
        outputManager.menu();


    }
}
