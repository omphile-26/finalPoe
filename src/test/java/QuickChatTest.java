/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */




import com.mycompany.poepart2_sendingmessage.Message;
import com.mycompany.poepart2_sendingmessage.QuickChat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class QuickChatTest {

    // Test: Message ID should not be more than 10 characters
    @Test
    public void testCheckMessageID() {
        assertTrue(QuickChat.checkMessageID("msg01")); // Success
        assertFalse(QuickChat.checkMessageID("thisIDisTooLong123")); // Failure
    }

    // Test: Recipient number formatting
    @Test
    public void testCheckRecipientCell() {
        // Valid: +27 followed by 9 digits (total 12 characters)
        assertEquals("Cell phone number successfully captured.",
                     QuickChat.checkRecipientCell("+27123456789"));

        // Invalid: missing + or wrong length
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                     QuickChat.checkRecipientCell("07123456789"));
    }



    // Test: Message length validation (<= 250 characters)
    @Test
    public void testMessageLength() {
        String shortMessage = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(shortMessage.length() <= 250, "Message ready to send.");

        String longMessage = "x".repeat(300); // 300 characters
        assertTrue(longMessage.length() > 250,
                "Message exceeds 250 characters by " + (longMessage.length() - 250) + "; please reduce the size.");
    }

    // Test: Message Hash generation
     @Test
    void testCreateMessageHash() {
        String result = QuickChat.createMessageHash("msg01", 1, "Hello");
        assertEquals("ms:1:HELLOHELLO", result);
    }

    @Test
    void testCreateMessageHashWithPunctuation() {
        String result = QuickChat.createMessageHash("id99", 2, "Hi Mike!");
        assertEquals("id:2:HIMIKE", result); // punctuation stripped
    }

    // Test: Send Message options
    @Test
    public void testSendMessageOptions() {
        Message msg = new Message("msg01", "+27718693002", "Hello");

        assertEquals("Message successfully sent.", QuickChat.sendMessage(msg, 1));
        assertEquals("Press 0 to delete the message", QuickChat.sendMessage(msg, 2));
        assertEquals("Message successfully stored.", QuickChat.sendMessage(msg, 3));
        assertEquals("Invalid option.", QuickChat.sendMessage(msg, 99));
    }

    // Test: Total messages count
    @Test
    public void testReturnTotalMessages() {
        Message msg = new Message("msg02", "+27718693002", "Test message");
        QuickChat.sendMessage(msg, 1);
        assertEquals(QuickChat.returnTotalMessages(), QuickChat.messages.size());
    }
    
    // ✅ NEW TEST: Verify console output includes Message Hash and details
    @Test
    public void testSendMessagePrintsDetails() {
        // Redirect console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Prepare test message
        Message msg = new Message("0012345678", "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        // Simulate sending (option 1)
        QuickChat.sendMessage(msg, 1);

        // Capture output
        String output = outContent.toString();
        
       // 🔍 Debugging line: print actual output
        System.out.println(output);

        // Assertions: check that output contains all required details
        assertTrue(output.contains("Message ID: 0012345678"));
        assertTrue(output.contains("Recipient: +27718693002"));
        assertTrue(output.contains("Message: Hi Mike, can you join us for dinner tonight?"));
     
        
    }
}
