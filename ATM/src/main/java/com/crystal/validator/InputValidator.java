package com.crystal.validator;

import java.util.regex.Pattern;

public class InputValidator {
    public boolean isValidInt(String number) {
        return Pattern.compile("^[0-9]*$").matcher(number).find();
    }

    public boolean isValidString(String string) {
        return Pattern.compile("[A-Za-z]").matcher(string).find();
    }

    public boolean isValidCharForGenderChose(String charGender) {
        return Pattern.compile("[M | m | F | f]").matcher(charGender).find();
    }

    public boolean isValidDate(String date) {
        return Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$").matcher(date).find();
    }

    public boolean isValidEmail(String email) {
        return Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).find();
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.compile("^^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$").matcher(phoneNumber).find();
    }
}
