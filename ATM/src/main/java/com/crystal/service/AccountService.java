package com.crystal.service;

import java.sql.SQLException;

public interface AccountService {
    double withdraw() throws SQLException;

    double deposit() throws SQLException;

}
