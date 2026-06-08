/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart2_sendingmessage;

/**
 *
 * @author deepl
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //PART 1: Registration & Login ===
        User_registration_and_login authSystem = new User_registration_and_login();

        System.out.println("=== User Registration ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter cell phone number: ");
        String cellNumber = scanner.nextLine();

        String registrationMessage = authSystem.registerUser(username, password, cellNumber);
        System.out.println(registrationMessage);

        if (!registrationMessage.contains("Welcome user")) {
            System.out.println("Registration failed. Exiting program.");
            return;
        }

        System.out.println("\n=== User Login ===");
        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        boolean loginSuccess = authSystem.loginUser(loginUsername, loginPassword);
        System.out.println(authSystem.returnLoginStatus(loginSuccess));

        if (!loginSuccess) {
            System.out.println("Login failed. Exiting program.");
            return;
        }

        // PART 2: QuickChat Messaging
        System.out.println("\nWelcome to QuickChat!");
        System.out.print("Enter number of messages: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        //for loop, goes with createMessageHash
        for (int i = 0; i < num; i++) {
            System.out.print("Enter Message ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Recipient Number: ");
            String recipient = scanner.nextLine();

            System.out.print("Enter Message: ");
            String text = scanner.nextLine();

            Message msg = new Message(id, recipient, text);
            
            //ask the user what to do with message
            System.out.println("Choose option: 1=Send, 2=Disregard/delete, 3=Store, 4=Stored Messages Menu");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 4) {
                // === PART 3: Stored Messages Menu ===
                boolean exitStoredMenu = false;
                while (!exitStoredMenu) {
                    System.out.println("\n=== Stored Messages Menu ===");
                    System.out.println("a. Display sender and recipient of all stored messages");
                    System.out.println("b. Display longest stored message");
                    System.out.println("c. Search by Message ID");
                    System.out.println("d. Search by recipient");
                    System.out.println("e. Delete by message hash");
                    System.out.println("f. Display full report");
                    System.out.println("g. Exit Stored Menu");
                    System.out.print("Choose option: ");
                    String choice = scanner.nextLine();

                    switch (choice.toLowerCase()) {
                        case "a":
                            QuickChat.printMessages();
                            break;
                        case "b":
                            System.out.println("Longest stored message: " + QuickChat.getLongestStoredMessage());
                            break;
                        case "c":
                            System.out.print("Enter Message ID to search: ");
                            String searchId = scanner.nextLine();
                            System.out.println(QuickChat.searchByMessageID(searchId));
                            break;
                        case "d":
                            System.out.print("Enter recipient number: ");
                            String searchRecipient = scanner.nextLine();
                            System.out.println("Messages: " + QuickChat.searchByRecipient(searchRecipient));
                            break;
                        case "e":
                            System.out.print("Enter message hash to delete: ");
                            String hash = scanner.nextLine();
                            System.out.println(QuickChat.deleteByHash(hash));
                            break;
                        case "f":
                            System.out.println("=== Stored Messages Report ===");
                            System.out.println(QuickChat.displayReport());
                            break;
                        case "g":
                            exitStoredMenu = true;
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                }
            } else {
                //Call sendMessage and show result
                String result = QuickChat.sendMessage(msg, option);
                System.out.println(result);
            }
        }
        
        //shows the number of messages that have been sent
        System.out.println("Total messages sent: " + QuickChat.returnTotalMessages());
        QuickChat.printMessages();

        scanner.close();
    }
}
