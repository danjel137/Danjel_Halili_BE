package com.crystal.model;

import lombok.Data;

@Data
public class Atm {
    private int atmId;
    private String atmLocation;
    private long totalAmountWithin;
}
