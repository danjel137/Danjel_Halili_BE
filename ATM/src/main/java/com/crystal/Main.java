package com.crystal;
import com.crystal.io.Menu;
import com.crystal.model.Bank;
import java.sql.SQLException;
public class Main extends Menu {
    public static void main(String[] args) throws SQLException {
        Bank bank=new Bank("BKT","Durres","WESTGBAV");
        Menu.mainMenu();

    }
}
