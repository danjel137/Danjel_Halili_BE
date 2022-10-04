package com.crystal;

import com.crystal.io.MenuCli;
import com.crystal.model.Bank;

import java.sql.SQLException;

public class Main extends MenuCli {
    public Main() {
    }

    public static void main(String[] args) throws SQLException {
        Bank bank = new Bank("BKT", "Durres", "WESTGBAV");
        MenuCli menuCli = new MenuCli();
        menuCli.mainMenu();

    }
}
