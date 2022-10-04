package com.crystal.repository;

import java.sql.SQLException;

public interface DataAccessList {
    public void addPerson();

    public void deletePerson(int id) throws SQLException;

    public void showPersons();


}
