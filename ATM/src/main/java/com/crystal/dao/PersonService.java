package com.crystal.dao;

import com.crystal.model.Account;

import java.sql.SQLException;
import java.util.List;

public interface PersonService {
    public void addPerson();
    public void deletePerson(int id) throws SQLException;
    public void showPersons();

}
