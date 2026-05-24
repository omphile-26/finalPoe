/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart2_sendingmessage;

/**
 *
 * @author deepl
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickChat {
    // A shared list that stores all QuickChat objects created while the program runs
    public static List<QuickChat> messages =new ArrayList<>();
    public static int totalMessages = 0;
    
    //checking message ID(less than and equal to 1o charcters
    public static boolean checkMessageID(String messageID) {
        return messageID.length() <= 10;
    }
    
    //checking recipient cell(greater and equal to 10, starts with + code)
    public static String checkRecipientCell(String recipient) {
        //checks that the number has 10 or fewer characters and the cellphone number starts with a +sign like "+27"
        //all conditions must be true, so we use the "&" sign
        if (recipient.length() <= 10 && recipient.startsWith("+")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
         }
        }
    // creating a message Hash
    //This method generates a unique “QuickChat Hash”, a short code that represents the message.
    //It combines parts of the message ID, the number of messages sent, and the first/last words of the message.
    
    public static String createMessageHash(String messageID, int numMessages, String message) {
        // creates space between word
        String[] words = message.split(" ");
        //"[0] represents the first word and "[words.length-1] represents the last words
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        //building a hash
        return messageID.substring(0, 2) + ":" + numMessages + ":" + firstWord + lastWord;
    }
    //send_store_disregard message
    public static String sendMessage(QuickChat msg, int option){
        switch(option) {
            //send message
            case 1:
                messages.add(msg);
                totalMessages++;
                return "Message successfully sent.";
                //disregard
            case 2:
                return "Press 0 to delete the message";
                //store the message
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid option.";
            
        }
    }
    
    //Print messages
    public static void printMessages(){
        //for loop
        for (QuickChat msg : messages) {
            System.out.println(msg);
        }
    }
    
    //return total messages
    public static int returnTotalMessages(){
        return totalMessages;
    }
    // Store QuickChat in JSON
    //did some research at w3school
    
    public static void storeMessageJSON(Message msg) {
        // Placeholder: use a JSON library like Gson
        // Example: new Gson().toJson(msg);
    }
    
    // main program loop
    public static void main(String[] args) {
        //scanner name is chat
        Scanner chat = new Scanner(System.in);
        //message when after logging in thr chatapp
        System.out.println("Welcome to Quickchat");
        
        // tells the user to type how many messages they want to send.
        System.out.println("Enter number of messages: ");
        int num =chat.nextInt();
        chat.nextLine();
        
        //for loop, goes with createMessageHash
        for (int i = 0; i < num; i++){
            System.out.println("Enter Message ID: ");
            String id = chat.nextLine();
            
            System.out.println("Enter Recipient Number: ");
            String recipient = chat.nextLine();
            
            System.out.println("Enter Message: ");
            String text = chat.nextLine();
            
            Message msg = new Message(id, recipient, text);
            
            //ask the user what to do with message
            System.out.println("Choose option: 1=Send, 2=Disregard/delete 3=Store");
            int option = chat.nextInt();
            chat.nextLine(); //clear buffer
            
            //Call sendMessage and show result
            String results = sendMessage(msg, option);
            System.out.println(results);
        }
        
        //show total messages sent at the end
        System.out.println("Total messages sent: " + totalMessages);
        
    }
    
   
}
