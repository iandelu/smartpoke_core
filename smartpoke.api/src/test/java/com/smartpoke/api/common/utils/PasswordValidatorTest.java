package com.smartpoke.api.common.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PasswordValidatorTest {

        @Test
        public void testIsValid() {
            assertTrue(PasswordValidator.isValid("Password1#"));
            assertFalse(PasswordValidator.isValid("password1#"));
            assertFalse(PasswordValidator.isValid("PASSWORD1#"));
            assertFalse(PasswordValidator.isValid("Password#"));
            assertFalse(PasswordValidator.isValid("Password1"));
            assertFalse(PasswordValidator.isValid("password#"));
            assertFalse(PasswordValidator.isValid("PASSWORD#"));
            assertFalse(PasswordValidator.isValid("password1"));
            assertFalse(PasswordValidator.isValid("PASSWORD1"));
        }
}
