package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;

/**
 * JUnit test class for Duke class.
 */

class SecureNUSTest {
    private static final String DUKETEST_LOG_EXECUTECOMMANDEXITTRUE_IDENTIFIER
        = "DukeTest - executeCommandExitTrue: ";
    private static final String DUKETEST_LOG_EXECUTECOMMANDNOTEXITFALSE_IDENTIFIER
            = "DukeTest - executeCommand: ";

    @Test
    public void executeCommandExitTrue() {
        try {
            SecureNUS secureNUS = new SecureNUS();
            // Assertions.assertTrue(secureNUS.executeCommand(Parser.parse("bye", secureNUSData)));
            // Assertions.assertTrue(secureNUS.executeCommand(Parser.parse("hellooooo", secureNUSData)));
        } catch (Exception e) {
            DukeLogger.setUpLogger();
            DukeLogger.LOGGER.log(Level.WARNING, SecureNUSTest.DUKETEST_LOG_EXECUTECOMMANDEXITTRUE_IDENTIFIER, e);
            DukeLogger.close();
        }
    }

    // @Test
    // public void executeCommandNotExitFalse() {
    //     try {
    //         SecureNUS secureNUS = new SecureNUS();
    //         Assertions.assertFalse(secureNUS.executeCommand(Parser.parse("new o/NUSNet", secureNUSData)));
    //         Assertions.assertFalse(secureNUS.executeCommand(Parser.parse("new o/StudentID", secureNUSData)));
    //         Assertions.assertFalse(secureNUS.executeCommand(Parser.parse("new o/CryptoWallet", secureNUSData)));
    //         Assertions.assertFalse(secureNUS.executeCommand(Parser.parse("delete", secureNUSData)));
    //         Assertions.assertFalse(secureNUS.executeCommand(Parser.parse("list", secureNUSData)));
    //         Assertions.assertFalse(secureNUS.executeCommand(Parser.parse("search", secureNUSData)));
    //         Assertions.assertFalse(secureNUS.executeCommand(Parser.parse("view", secureNUSData)));
    //         Assertions.assertFalse(secureNUS.executeCommand(Parser.parse("edit", secureNUSData)));
    //     } catch (Exception e) {
    //         DukeLogger.setUpLogger();
    //         DukeLogger.LOGGER.log(Level.WARNING,
    //             SecureNUSTest.DUKETEST_LOG_EXECUTECOMMANDNOTEXITFALSE_IDENTIFIER, e);
    //         DukeLogger.close();
    //     }
    // }
}
