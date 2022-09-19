package com.crystal.be.selector.memory;

import com.crystal.be.selector.DataSource;
import com.crystal.be.selector.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryDataSource implements DataSource {

    @Override
    public List<Person> getData() {
        return new ArrayList<>(Arrays.asList(
                new Person(1, "danjel.halili@crystal.eu"),
                new Person(2, "isuf.muca@crystal-system.eu"),
                new Person(3, "elia.omeri@crystal-system.eu"),
                new Person(4, "megi.lala@crystal-system.eu"),
                new Person(5, "flavio.lorenci@crystal-system.eu"),
                new Person(6, "irena.shahini@crystal-system.eu"),
                new Person(7, "indrit.vaka@crystal-system.eu")
        ));
    }
}
