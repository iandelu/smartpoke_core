package com.smartpoke.api.common.utils;

public class PasswordValidator {

    /*
    * A password is valid if:
    * - It has at least 8 characters
    * - It has at least one uppercase letter
    * - It has at least one lowercase letter
    * - It has at least one digit
    * - It has at least one special character password.matches(".*[#$%^&*_].*");
    */
    public static boolean isValid(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[#$%^&*_].*");

    }
}
