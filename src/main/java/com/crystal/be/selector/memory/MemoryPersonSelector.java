package com.crystal.be.selector.memory;

import com.crystal.be.selector.DataSource;
import com.crystal.be.selector.PersonSelector;

public class MemoryPersonSelector extends PersonSelector {

    private DataSource dataSource = new MemoryDataSource();

    @Override
    protected void listAllPersons() {
        System.out.println("Listing all persons from Memory");
        dataSource.getData().forEach(System.out::println);
    }
}
