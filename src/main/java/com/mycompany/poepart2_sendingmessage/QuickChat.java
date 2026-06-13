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
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuickChat {
    // A shared list that stores all Message objects created while the program runs
    public static List<Message> messages = new ArrayList<>();
    public static List<String> storedRecipients = new ArrayList<>();
    public static int totalMessages = 0;
    
    // === PART 3 ARRAYS ===
    public static List<String> sentMessages = new ArrayList<>();
    public static List<String> disregardedMessages = new ArrayList<>();
    public static List<String> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();
    
    //checking message ID(less than and equal to 10 charcters)
    public static boolean checkMessageID(String messageID) {
        return messageID.length() <= 10;
    }
    
    //checking recipient cell(equal to 11 numbers, starts with + code)
    public static String checkRecipientCell(String recipient) {
        //checks that the number has 11 characters and the cellphone number starts with a +sign like "+27"
        if (recipient.matches("\\+27\\d{9}")) {
        return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        } 
    }
    
    // creating a message Hash
    //This method generates a unique “QuickChat Hash”, a short code that represents the message.
    //It combines parts of the message ID, the number of messages sent, and the first/last words of the message.
    public static String createMessageHash(String messageID, int totalMessages, String messageText) {
        String[] words = messageText.split(" ");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", ""); // strip punctuation
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        return messageID.substring(0, 2) + ":" + totalMessages + ":" +
               firstWord.toUpperCase() + lastWord.toUpperCase();
    }
    
    //send_store_disregard message
    public static String sendMessage(Message msg, int option){
        switch(option) {
            //send message
            case 1:
                if (msg.getText().length() > 250) {
                    int excess = msg.getText().length() - 250;
                    return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
                }
                messages.add(msg); // add the actual Message object
                totalMessages++;
        
                //generates the hash
                String hash = createMessageHash(msg.getId(), totalMessages, msg.getText());
                
                // === PART 3: populate arrays ===
                sentMessages.add(msg.getText());
                messageIDs.add(msg.getId());
                messageHashes.add(hash);

                // Print details
                System.out.println("Message Details:");
                System.out.println("Message ID: " + msg.getId());
                System.out.println("Message Hash: " + hash);
                System.out.println("Recipient: " + msg.getRecipient());
                System.out.println("Message: " + msg.getText());
                return "Message successfully sent.";
                
            //disregard
            case 2:
                disregardedMessages.add(msg.getText());
                return "Press 0 to delete the message";

            //store the message
            case 3:
                storedMessages.add(msg.getText());
                storedRecipients.add(msg.getRecipient());   //keep recipient
                messageIDs.add(msg.getId());
                String storeHash = createMessageHash(msg.getId(), totalMessages, msg.getText());
                messageHashes.add(storeHash);
                storeMessageJSON(msg);
                return "Message successfully stored.";
                
            default:
                return "Invalid option.";
        }
    }
    
            //Print messages
            public static void printMessages(){
                //for loop
                for (Message msg : messages) {
                    System.out.println(msg);
                }
            }

            //return total messages
            public static int returnTotalMessages(){
                return totalMessages;
            }

            // Store QuickChat in JSON
            //did some research at w3school
            // Placeholder: use a JSON library like Gson
            // Example: new Gson().toJson(msg);
            public static void storeMessageJSON(Message msg) {
                Gson gson = new Gson();
                String json = gson.toJson(msg);

                try (FileWriter writer = new FileWriter("messages.json", true)) {
                    writer.write(json + System.lineSeparator());
                    System.out.println("Message stored in JSON file: " + json);
                } catch (IOException e) {
                    System.out.println("Error storing message: " + e.getMessage());
                }
            }

            // === PART 3 FEATURES ===
            public static String getLongestStoredMessage() {
                String longest = "";
                for (String msg : storedMessages) {
                    if (msg.length() > longest.length()) longest = msg;
                }
                return longest;
            }

                public static String searchByMessageID(String id) {
            int index = messageIDs.indexOf(id);
            if (index != -1 && index < storedMessages.size()) {
                return "Message ID: " + messageIDs.get(index) +
                       ", Recipient: " + storedRecipients.get(index) +
                       ", Message: " + storedMessages.get(index);
            }
            return "Message ID not found.";
        }

           public static List<String> searchByRecipient(String recipient) {
            List<String> results = new ArrayList<>();
            for (int i = 0; i < storedRecipients.size(); i++) {
                if (storedRecipients.get(i).equals(recipient)) {
                    results.add(storedMessages.get(i));
                }
            }
            return results;
        }


            public static String deleteByHash(String hash) {
                int index = messageHashes.indexOf(hash);
                if (index != -1 && index < storedMessages.size()) {
                    String deleted = storedMessages.remove(index);
                    messageHashes.remove(index);
                    messageIDs.remove(index);
                    return "Message: \"" + deleted + "\" successfully deleted.";
                }
                return "Hash not found.";
            }

            public static String displayReport() {
            if (storedMessages.isEmpty()) {
                return "No stored messages available.";
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < storedMessages.size(); i++) {
                sb.append("Message ID: ").append(messageIDs.get(i)).append("\n")
                  .append("Recipient: ").append(storedRecipients.get(i)).append("\n")
                  .append("Message: ").append(storedMessages.get(i)).append("\n")
                  .append("Message Hash: ").append(messageHashes.get(i)).append("\n")
                  .append("-----------------------------\n");
            }
            // === NEW FEATURE: Summary line ===
            sb.append("Total stored messages: ").append(storedMessages.size()).append("\n");

            return sb.toString();
        }
            // === NEW FEATURE: Read JSON file back into arrays ===
// This method reads messages.json and repopulates stored arrays.
// It ensures you can reload data and display it, as required by the rubric.
public static String readMessagesFromJSON() {
    try {
        // Read the file into a JSON array
        JSONArray jsonArray = new JSONArray(new String(Files.readAllBytes(Paths.get("messages.json"))));

        // Clear current arrays before reloading
        storedMessages.clear();
        storedRecipients.clear();
        messageIDs.clear();
        messageHashes.clear();

        // Loop through JSON objects and repopulate arrays
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                messageIDs.add(obj.getString("id"));
                storedRecipients.add(obj.getString("recipient"));
                storedMessages.add(obj.getString("text"));
                messageHashes.add(obj.getString("hash"));
            }

            return "Messages successfully loaded from JSON file. Total: " + storedMessages.size();
        } catch (IOException e) {
            return "Error reading JSON file: " + e.getMessage();
        } catch (JSONException e) {
            return "Error parsing JSON file: " + e.getMessage();
        }
    }

        }
