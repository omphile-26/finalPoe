/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart2_sendingmessage;

/**
 *
 * @author deepl
 */

public class User_registration_and_login {

    String registeredUsername;
    String registeredPassword;
    String registeredCellNumber;

    // Method: check username
    ////the username must be underscore and the username must have maximum 5 letters
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Method: check password complexity
    ///makes sure that the user enters the correct password
    /////password must have a number, capital letter, sysmbols,
    public boolean checkPasswordComplexity(String password) {
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        return password.length() >= 8 && hasUpperCase && hasDigit && hasSpecialChar;
    }

    // Method: check cell phone number
    //makes sure that the user enters the correct cell phone numbers
    public boolean checkCellPhoneNumber(String cellNumber) {
        return (cellNumber.startsWith("+") || cellNumber.matches("^\\d{2,}.*")) && cellNumber.length() >= 10;
    }

    // Method: register user
    //makes sure that the user enters the correct information
    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that this password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        registeredUsername = username;
        registeredPassword = password;
        registeredCellNumber = cellNumber;

        return "Welcome user " + username + ", username, " + username + " user last name: It is great to see you.";
    }

    // Method: login user
    // for the user to login
    public boolean loginUser(String username, String password) {
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    // Method: return login status
    public String returnLoginStatus(boolean loginSuccess) {
        return loginSuccess ? "Login successful" : "Login failed";
    }

}

