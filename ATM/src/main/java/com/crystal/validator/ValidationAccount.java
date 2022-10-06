package com.crystal.validator;

import java.sql.SQLException;

public interface ValidationAccount {
    void checkIban() throws SQLException;

    void checkPin() throws SQLException;
}
