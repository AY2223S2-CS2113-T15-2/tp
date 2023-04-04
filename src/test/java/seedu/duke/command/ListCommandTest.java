package seedu.duke.command;

import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;
import seedu.duke.exceptions.RepeatedIdException;


import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.CreditCard;
import seedu.duke.secrets.CryptoWallet;
import seedu.duke.secrets.NUSNet;
import seedu.duke.secrets.StudentID;
import seedu.duke.secrets.WifiPassword;

import seedu.duke.storage.SecretMaster;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
/**
 * JUnit tests for the ListCommand class.
 */
public class ListCommandTest {

    /**
     * Tests the isExit method when there is no folder name.
     */
    @Test
    void isExit_noFolder() {
        ListCommand listCommand = new ListCommand("list");
        assertFalse(listCommand.isExit());
    }

    /**
     * Tests the isExit method when there is a folder name.
     */
    @Test
    void isExit_withFolder() {
        ListCommand listCommand = new ListCommand("list f/Folder123!");
        assertFalse(listCommand.isExit());
    }

    /**
     * Tests the extractFolderName method when there is no folder name.
     */
    @Test
    void getList_noFolder() {
        ListCommand listCommand = new ListCommand("list");
        assertEquals(listCommand.extractFolderName("list"), "unnamed");
    }

    /**
     * Tests the extractFolderName method when there is a folder name.
     */
    @Test
    void getList_withFolder() {
        ListCommand listCommand = new ListCommand("list f/Folder123!");
        assertEquals(listCommand.extractFolderName("list f/Folder123!"), "f/Folder123!");
    }

    /**
     * Tests the ListCommand method for different secret types.
     */
    @Test
    void testSecretTypes() {
        SecretMaster secretMaster = new SecretMaster();

        try {
            secretMaster.addSecret(new BasicPassword("BasicPassword1", "Username1",
                    "Password1", "Url1.com"));
            secretMaster.addSecret(new CreditCard("CreditCard1", "John Doe",
                    "1234567812345678", "123", "12/25"));
            secretMaster.addSecret(new CryptoWallet("CryptoWallet1", "Folder1",
                    "DeepsD", "PrivateKey1", "SeedPhrase1"));
            secretMaster.addSecret(new NUSNet("NUSNet1", "NUSNetId1",
                    "Password1"));
            secretMaster.addSecret(new StudentID("StudentID1",
                    "StudentID1"));
            secretMaster.addSecret(new WifiPassword("WifiPassword1", "Username1",
                    "Password1"));

            ListCommand listCommand = new ListCommand("list");
            listCommand.execute(secretMaster);

        } catch (FolderExistsException | IllegalFolderNameException | IllegalSecretNameException | RepeatedIdException |
                 InvalidCreditCardNumberException | InvalidExpiryDateException e) {
            e.printStackTrace();
        }
    }
}
