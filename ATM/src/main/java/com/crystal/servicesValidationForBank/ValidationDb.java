package com.crystal.servicesValidationForBank;

public abstract class ValidationDb {
    public static long randomIbanValidation(){
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }

    public static int randomPinValidation(){
        return (int) (Math.floor(Math.random() * 9_000) + 1_000L);
    }
}
