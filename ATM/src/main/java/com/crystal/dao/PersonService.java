package com.crystal.dao;

import com.crystal.model.Account;

import java.util.List;

public interface PersonService {
    public void addPerson();
    public void deletePerson(int id);
    public void showPerson();
    List<Account> getAccountsByPersonId(String personId);
}
