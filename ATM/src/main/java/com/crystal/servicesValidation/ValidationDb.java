package com.crystal.servicesValidation;

public abstract class ValidationDb {
    public static long randomIbnValidation(){
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return number;
    }

    public static int randomPinValidation(){
        int number = (int) (Math.floor(Math.random() * 9_000) + 1_000L);
        return number;
    }
}
