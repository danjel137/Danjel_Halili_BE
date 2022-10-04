package com.crystal.model;

import lombok.Data;

@Data
public class Account {
    private int id;
    private String IBAN;
    private int money;
    private String code;
    private int personId;

    public Account(int id, String IBAN, int money, String code, int personId) {
        this.id = id;
        this.IBAN = IBAN;
        this.money = money;
        this.code = code;
        this.personId = personId;
    }
}
