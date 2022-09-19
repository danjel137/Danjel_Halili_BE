package com.crystal.be.selector;

import com.crystal.be.selector.db.DbPersonSelector;
import com.crystal.be.selector.file.FilePersonSelector;
import com.crystal.be.selector.memory.MemoryPersonSelector;

public class Main {

    public static void main(String[] args) {
        // show a menu
        // let the user pick an option
        // pick a random person

        System.out.println("Welcome to the Random Person Selector Program. Please choose an option from below:");
        PersonSelector personSelector = new MemoryPersonSelector();
//        PersonSelector personSelector = new FilePersonSelector();
//        PersonSelector personSelector = new DbPersonSelector();
        personSelector.showMenu();

    }
}
