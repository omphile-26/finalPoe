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

public class PoePart2_sendingMessage {
    // A shared list that stores all Message objects created while the program runs
    public static List<Message> messages =new ArrayList<>();
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
    //This method generates a unique “Message Hash”, a short code that represents the message.
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
    public static String sendMessage(Message msg, int option){
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
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
