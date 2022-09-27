package com.crystal.data;

import com.crystal.dao.dataAccessList;
import com.crystal.model.Account;
import com.crystal.model.Person;

import java.util.HashSet;
import java.util.Set;

public class ListManagmant implements dataAccessList {
    public static Set<Integer> idOfAllPersons = new HashSet<>();
    public static Set<Person> personList = new HashSet<>();
    public static Set<Account> accountList = new HashSet<>();
    @Override
    public Set<Integer> GetAllPersonsId() {
        return  idOfAllPersons;
    }

    @Override
    public Set<Person> getPersons() {
        return personList;
    }

    @Override
    public Set<Account> GetAccounts() {
        return accountList;
    }
}
