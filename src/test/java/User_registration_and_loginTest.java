/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart2_sendingmessage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class User_registration_and_loginTest {

    private User_registration_and_login authSystem;

    @BeforeEach
    public void setUp() {
        authSystem = new User_registration_and_login();
    }

    // Test valid username
    @Test
    public void testValidUsername() {
        assertTrue(authSystem.checkUserName("user_"));
    }

    // Test invalid username (no underscore)
    @Test
    public void testInvalidUsername() {
        assertFalse(authSystem.checkUserName("username"));
    }

    // Test valid password
    @Test
    public void testValidPassword() {
        assertTrue(authSystem.checkPasswordComplexity("Pass@123"));
    }

    // Test invalid password (too short)
    @Test
    public void testInvalidPassword() {
        assertFalse(authSystem.checkPasswordComplexity("Pass@1"));
    }

    // Test valid cell number
    @Test
    public void testValidCellNumber() {
        assertTrue(authSystem.checkCellPhoneNumber("+27123456789"));
    }

    // Test invalid cell number
    @Test
    public void testInvalidCellNumber() {
        assertFalse(authSystem.checkCellPhoneNumber("123"));
    }

    // Test successful registration
    @Test
    public void testSuccessfulRegistration() {
        String result = authSystem.registerUser("user_", "Pass@123", "+27123456789");
        assertTrue(result.contains("Welcome user"));
    }

    // Test successful login
    @Test
    public void testSuccessfulLogin() {
        authSystem.registerUser("user_", "Pass@123", "+27123456789");
        assertTrue(authSystem.loginUser("user_", "Pass@123"));
    }

    // Test failed login
    @Test
    public void testFailedLogin() {
        authSystem.registerUser("user_", "Pass@123", "+27123456789");
        assertFalse(authSystem.loginUser("user_", "WrongPassword"));
    }
}
