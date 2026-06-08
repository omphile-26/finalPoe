/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.mycompany.poepart2_sendingmessage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class QuickChatPart3Test {

    @Test
    public void testSentMessagesArray() {
        Message msg1 = new Message("ID01", "+27834557896", "Did you get the cake?");
        QuickChat.sendMessage(msg1, 1);
        Message msg2 = new Message("ID04", "08388884567", "It is dinner time!");
        QuickChat.sendMessage(msg2, 1);

        assertEquals(Arrays.asList("Did you get the cake?", "It is dinner time!"), QuickChat.sentMessages);
    }

    @Test
    public void testLongestStoredMessage() {
        Message msg = new Message("ID02", "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        QuickChat.sendMessage(msg, 3);
        assertEquals("Where are you? You are late! I have asked you to be on time.", QuickChat.getLongestStoredMessage());
    }

    @Test
    public void testSearchByMessageID() {
        Message msg = new Message("ID04", "08388884567", "It is dinner time!");
        QuickChat.sendMessage(msg, 1);
        String result = QuickChat.searchByMessageID("ID04");
        assertTrue(result.contains("It is dinner time!"));
    }

    @Test
    public void testSearchByRecipient() {
        Message msg1 = new Message("ID02", "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        QuickChat.sendMessage(msg1, 3);
        Message msg2 = new Message("ID05", "+27838884567", "Ok, I am leaving without you.");
        QuickChat.sendMessage(msg2, 3);

        List<String> results = QuickChat.searchByRecipient("+27838884567");
        assertEquals(Arrays.asList(
            "Where are you? You are late! I have asked you to be on time.",
            "Ok, I am leaving without you."
        ), results);
    }

    @Test
    public void testDeleteByHash() {
        Message msg = new Message("ID02", "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        QuickChat.sendMessage(msg, 3);
        String hash = QuickChat.messageHashes.get(QuickChat.messageHashes.size() - 1);
        String result = QuickChat.deleteByHash(hash);
        assertTrue(result.contains("successfully deleted"));
    }

    @Test
    public void testDisplayReport() {
        Message msg = new Message("ID05", "+27838884567", "Ok, I am leaving without you.");
        QuickChat.sendMessage(msg, 3);
        String report = QuickChat.displayReport();
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message Hash"));
    }
}
