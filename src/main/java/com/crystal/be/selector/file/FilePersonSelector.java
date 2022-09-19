package com.crystal.be.selector.file;

import com.crystal.be.selector.DataSource;
import com.crystal.be.selector.PersonSelector;

public class FilePersonSelector extends PersonSelector {

    private DataSource dataSource = new FileDataSource();

    @Override
    protected void listAllPersons() {
        System.out.println("Listing all persons from File");
        dataSource.getData().forEach(System.out::println);
    }

}
