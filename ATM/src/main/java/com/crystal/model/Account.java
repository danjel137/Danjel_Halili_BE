package com.crystal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Account {
    private int id;
    private String IBAN;
    private int money;
    private String code;

    private int fkKeyRelatedToPersonTable;

    public Account(int id, String IBAN, int money, String code, int fkKeyRelatedToPersonTable) {
        this.id = id;
        this.IBAN = IBAN;
        this.money = money;
        this.code = code;
        this.fkKeyRelatedToPersonTable = fkKeyRelatedToPersonTable;
    }
}
