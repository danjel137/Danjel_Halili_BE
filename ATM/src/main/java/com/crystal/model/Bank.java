package com.crystal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString

public class Bank {
    private String name;
    private String place;
    private String BIC;
}
