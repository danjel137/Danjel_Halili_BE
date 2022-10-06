package com.crystal.service;

import com.crystal.model.Account;
import com.crystal.model.Person;

import java.util.Set;

public interface PersonService {
    Set<Integer> getIdAllPersons();

    Set<Integer> getIdAllAccounts();

    Set<Person> getPersons();

    Set<Account> getAccounts();
}
