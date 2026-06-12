/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */



import com.mycompany.poepart2_sendingmessage.Message;
import com.mycompany.poepart2_sendingmessage.QuickChat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

public class QuickChatPart3Test {

      @BeforeEach
    void setUp() {
        // Clear arrays before each test
        QuickChat.storedMessages.clear();
        QuickChat.storedRecipients.clear();
        QuickChat.messageIDs.clear();
        QuickChat.messageHashes.clear();
        QuickChat.sentMessages.clear();
        QuickChat.disregardedMessages.clear();
        QuickChat.totalMessages = 0;
    }

    @Test
    void testGetLongestStoredMessage() {
        QuickChat.storedMessages.add("Short");
        QuickChat.storedMessages.add("This is a much longer message");
        assertEquals("This is a much longer message", QuickChat.getLongestStoredMessage());
    }

    @Test
    void testSearchByMessageIDFound() {
        QuickChat.messageIDs.add("MSG1");
        QuickChat.storedRecipients.add("+27123456789");
        QuickChat.storedMessages.add("Hello World");
        String result = QuickChat.searchByMessageID("MSG1");
        assertTrue(result.contains("Hello World"));
    }

    @Test
    void testSearchByMessageIDNotFound() {
        String result = QuickChat.searchByMessageID("NOPE");
        assertEquals("Message ID not found.", result);
    }

    @Test
    void testSearchByRecipient() {
        QuickChat.messageIDs.add("MSG2");
        QuickChat.storedRecipients.add("+27123456789");
        QuickChat.storedMessages.add("Recipient test message");
        List<String> results = QuickChat.searchByRecipient("+27123456789");
        assertEquals(1, results.size());
        assertTrue(results.get(0).contains("Recipient test message"));
    }

    @Test
    void testDeleteByHash() {
        QuickChat.storedMessages.add("Delete me");
        QuickChat.messageIDs.add("MSG3");
        QuickChat.storedRecipients.add("+27123456789");
        QuickChat.messageHashes.add("AB:1:DELETEME");

        String result = QuickChat.deleteByHash("AB:1:DELETEME");
        assertTrue(result.contains("successfully deleted"));
        assertTrue(QuickChat.storedMessages.isEmpty());
    }

    @Test
    void testDisplayReport() {
        QuickChat.storedMessages.add("Report message");
        QuickChat.messageIDs.add("MSG4");
        QuickChat.storedRecipients.add("+27123456789");
        QuickChat.messageHashes.add("AB:1:REPORTMESSAGE");

        String report = QuickChat.displayReport();
        assertTrue(report.contains("MSG4"));
        assertTrue(report.contains("Total stored messages: 1"));
    }

    // Optional: test JSON reload if file exists
    // This requires messages.json to be present with valid content
    @Test
    void testReadMessagesFromJSON_NoFile() {
        String result = QuickChat.readMessagesFromJSON();
        assertTrue(result.startsWith("Error")); // Expect error if file missing
    }
}
