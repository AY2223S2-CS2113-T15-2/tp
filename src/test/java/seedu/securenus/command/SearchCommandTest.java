package seedu.securenus.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import seedu.securenus.exceptions.RepeatedIdException;
import seedu.securenus.exceptions.secrets.FolderNotFoundException;
import seedu.securenus.exceptions.secrets.IllegalFolderNameException;
import seedu.securenus.exceptions.secrets.IllegalSecretNameException;
import seedu.securenus.exceptions.secrets.FolderExistsException;
import seedu.securenus.exceptions.secrets.NonExistentFolderException;
import seedu.securenus.secrets.BasicPassword;
import seedu.securenus.secrets.Secret;
import seedu.securenus.storage.SecretMaster;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
// import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This class represents the unit tests for the SearchCommand class.
 * It checks the following functionalities:
 * Extraction of name from input
 * Extraction of folder name from input
 * execute method
 * isExit method
 */
class SearchCommandTest {
    private final Secret mockBasicPassword1;
    {
        mockBasicPassword1 = new BasicPassword("Facebook", "Tom123", "password123", "facebook.com");
    }
    private final Secret mockBasicPassword2;
    {
        mockBasicPassword2 = new BasicPassword("Instagram", "Tom123", "password123", "instagram.com");
    }
    private final Secret mockBasicPasswordWithFolder1;
    {
        mockBasicPasswordWithFolder1 = new BasicPassword("Facebook", "Socials", "Tom123",
                "password123", "fb.com");
    }
    private final Secret mockBasicPasswordWithFolder2;
    {
        mockBasicPasswordWithFolder2 = new BasicPassword("Instagram", "Socials", "Tom123",
                "password123", "ig.com");
    }
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    /**
     * Tests the extraction of name from input.
     * The test case checks for extraction of name only.
     */
    @Test
    void extractName_nameOnly() throws IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException,
            FolderExistsException, FolderNotFoundException {
        SecretMaster mockSecureNUSData = new SecretMaster();
        mockSecureNUSData.addSecret(mockBasicPassword1);
        SearchCommand searchCommandNameOnly = new SearchCommand("search Facebook", mockSecureNUSData.getFolders());
        Assertions.assertEquals(searchCommandNameOnly.extractName("search Facebook", "search"), "Facebook");
    }

    /**
     * Tests the extraction of name from input.
     * The test case checks for extraction of name and folder.
     */
    @Test
    void extractName_nameAndFolder() throws IllegalFolderNameException, RepeatedIdException, IllegalSecretNameException,
            FolderExistsException, FolderNotFoundException {
        SecretMaster mockSecureNUSData = new SecretMaster();
        mockSecureNUSData.addSecret(mockBasicPasswordWithFolder1);
        SearchCommand searchCommand = new SearchCommand("search Face f/Socials",
                mockSecureNUSData.getFolders());
        assertEquals(searchCommand.extractName("search Face f/Socials", "search"), "Face");
    }


    // /**
    //  * Tests the extraction of folder name from input.
    //  * The test case checks for extraction of folder name only.
    //  */
    // @Test
    // void extractFolderName_nameOnly() {
    //     SearchCommand searchCommand = new SearchCommand("search Name123!");
    //     assertNull(searchCommand.extractFolderName("search Name123!"));
    // }
    //
    // /**
    //  * Tests the extraction of folder name from input.
    //  * The test case checks for extraction of name and folder.
    //  */
    // @Test
    // void extractFolderName_nameAndFolder() {
    //     SearchCommand searchCommand = new SearchCommand("search Name123! -f f/Folder123!");
    //     assertEquals(searchCommand.extractFolderName("search Name123! -f f/Folder123!"), "Folder123!");
    // }

    // @Test
    // public void execute_nameOnly() throws IllegalFolderNameException,
    //         RepeatedIdException, IllegalSecretNameException, FolderExistsException, NonExistentFolderException {
    //     SecretMaster mockSecureNUSData = new SecretMaster();
    //     mockSecureNUSData.addSecret(mockBasicPassword1);
    //     mockSecureNUSData.addSecret(mockBasicPassword2);
    //
    //     SearchCommand command = new SearchCommand("search Facebook");
    //     command.execute(mockSecureNUSData);
    //
    //     assertEquals("Found 1 matches!\nID: 1\t|\tFacebook\t|\n",
    //             output.toString().replace("\r",""));
    // }

    @Test
    public void execute_nameAndFolder() throws IllegalFolderNameException,
            RepeatedIdException, IllegalSecretNameException, FolderExistsException, NonExistentFolderException,
            FolderNotFoundException {
        SecretMaster mockSecureNUSData = new SecretMaster();
        mockSecureNUSData.addSecret(mockBasicPasswordWithFolder1);
        mockSecureNUSData.addSecret(mockBasicPasswordWithFolder2);

        SearchCommand command = new SearchCommand("search Facebook f/Socials", mockSecureNUSData.getFolders());
        command.execute(mockSecureNUSData);

        assertEquals("_____________________________________________________\n" +
                        "_____________________________________________________\n" +
                        "Found 1 matches!\n" +
                        "| NO. |          NAME           |      FOLDER       |\n" +
                        "|  1  |        Facebook         |      Socials      |\n" +
                        "_____________________________________________________\n",
                output.toString().replace("\r",""));
    }
}
