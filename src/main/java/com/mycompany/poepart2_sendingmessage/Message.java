/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart2_sendingmessage;

/**
 *
 * @author deepl
 */

public class Message {
    private String id;
    private String recipient;
    private String text;

    //constructor: allows you to create a MESSAGE with id, recipient, and text
    public Message(String id, String recipient, String text) {
        this.id = id;
        this.recipient = recipient;
        this.text = text;
    }

    // getters
    public String getId() { return id; }
    public String getRecipient() { return recipient; }
    public String getText() { return text; }

    @Override
    public String toString() {
        return "Message ID: " + id +
               " Recipient: " + recipient +
               " Text: " + text;
    }
}
