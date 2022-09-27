package com.crystal.dao;

import com.crystal.model.Account;
import com.crystal.model.Person;

import java.util.Set;

public interface dataAccessList {
    Set<Integer> GetAllPersonsId();
    Set<Person> getPersons();
    Set<Account> GetAccounts();
}
