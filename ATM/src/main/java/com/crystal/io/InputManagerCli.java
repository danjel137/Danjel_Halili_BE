package com.crystal.io;
import com.crystal.validator.InputValidator;
import java.util.Scanner;


public class InputManagerCli implements InputManager {
    Scanner scanner = new Scanner(System.in);
    InputValidator validator = new InputValidator();

    @Override
    public int getInt() {
        String ans;
        do {
            ans = scanner.nextLine();
            if (!validator.isValidInt(ans))
                System.out.println("Not valid number");
        } while (!validator.isValidInt(ans));
        return Integer.parseInt(ans);
    }

    @Override
    public String getOnlyWordLetter() {
        String ans;
        do {
            ans = scanner.nextLine();
            if (!validator.isValidString(ans))
                System.out.println("The input you just added cannot contain numbers,please enter only wordLetter");
        } while (!validator.isValidString(ans));
        return ans;
    }

    @Override
    public String getOneLetterForGender() {
        String ans;
        do {
            ans = scanner.nextLine();
            if (!validator.isValidCharForGenderChose(ans))
                System.out.println("Input is required in this format:M or F");
        } while (!validator.isValidCharForGenderChose(ans));
        return ans;
    }

    @Override
    public String getDate() {
        String ans;
        do {
            ans = scanner.nextLine();
            if (!validator.isValidDate(ans))
                System.out.println("Input is required in this format: yyyy-mm-dd");
        } while (!validator.isValidDate(ans));
        return ans;
    }

    @Override
    public String getEmail() {
        String ans;
        do {
            ans = scanner.nextLine();
            if (!validator.isValidEmail(ans))
                System.out.println("This email is not valid");
        } while (!validator.isValidEmail(ans));
        return ans;
    }

    @Override
    public String getPhoneNumber() {
        String ans;
        do {
            ans = scanner.nextLine();
            if (!validator.isValidPhoneNumber(ans))
                System.out.println("Please enter phone number in this format: 123-456-7890");
        } while (!validator.isValidPhoneNumber(ans));
        return ans;
    }

}
