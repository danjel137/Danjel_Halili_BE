package com.crystal.be.selector.db;

import com.crystal.be.selector.PersonSelector;

public class DbPersonSelector extends PersonSelector {

    @Override
    protected void listAllPersons() {
        System.out.println("Listing all persons from the Database");
    }

}
