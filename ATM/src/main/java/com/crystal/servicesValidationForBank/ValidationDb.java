package com.crystal.servicesValidationForBank;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

public abstract class ValidationDb {
    public static String generateIban(){
        Iban iban = Iban.random(CountryCode.AL);
        String Iban= String.valueOf(iban);
        return Iban;
    }

    public static int randomPin(){
        return (int) (Math.floor(Math.random() * 9_000) + 1_000L);
    }


}
