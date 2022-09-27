package com.crystal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Account {
    private String IBAN;
    private int money;
    private String code;


}
