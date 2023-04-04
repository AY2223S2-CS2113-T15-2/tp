package seedu.duke.command;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.InvalidURLException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The EditCommandTest class tests the EditCommand class.
 */
class EditCommandTest {
    /**
     * Test the extract method in EditCommand class.
     * The test case checks for extraction of name, new folder, new description and new password name.
     */
    @Test
    void extract_validInput() {
        EditCommand editCommand = new EditCommand("edit p/PASSWORD_NAME -f nf/NEW_FOLDER_NAME " +
                "-d nd/NEW_DESCRIPTION -N np/NEW_PASSWORD_NAME");
        String[] expectedOutputs = {"PASSWORD_NAME", "NEW_FOLDER_NAME", "NEW_DESCRIPTION", "NEW_PASSWORD_NAME"};
        String[] actualOutputs = editCommand.extract("edit p/PASSWORD_NAME -f nf/NEW_FOLDER_NAME " +
                "-d nd/NEW_DESCRIPTION -N np/NEW_PASSWORD_NAME");
        assertArrayEquals(actualOutputs, expectedOutputs);
    }

    /**
     * Test the extract method in EditCommand class.
     * The test case checks for an invalid input which results in null
     * extracted fields (except for folder which is "unnamed").
     */
    // @Test
    // void extract_invalidInput_extractDefaultValues() {
    //     EditCommand editCommand = new EditCommand("invalid input");
    //
    //     String[] output = editCommand.extract("invalid input");
    //
    //     assertNull(output[0]);
    //     assertEquals("unnamed", output[1]);
    //     assertNull(output[2]);
    //     assertNull(output[3]);
    // }

    @Test
    public void extract_validName_nameUpdated() throws InvalidURLException, IllegalFolderNameException,
            RepeatedIdException, IllegalSecretNameException, FolderExistsException, SecretNotFoundException {
        SecretMaster mockSecureNUSData = new SecretMaster();
        Secret mockBasicPassword = new BasicPassword("name", "folder", "username", "password123", "url.com");
        mockSecureNUSData.addSecret(mockBasicPassword);
        EditCommand editCommand = new EditCommand("edit p/name -N np/newName");

        editCommand.execute(mockSecureNUSData);

        Secret updatedMockBasicPassword = mockSecureNUSData.getByName("newName");
        assertEquals("newName", updatedMockBasicPassword.getName());
    }

    @Test
    void execute_withExistingSecret_editSecret() throws SecretNotFoundException, IllegalFolderNameException,
            RepeatedIdException, IllegalSecretNameException, FolderExistsException {
        SecretMaster secretMaster = new SecretMaster();
        secretMaster.addSecret(new Secret("Password1", "Folder1"));
        secretMaster.addSecret(new Secret("Password2", "Folder2"));

        EditCommand editCommand = new EditCommand("p/Password1 -f nf/Folder3 -N np/Password3");

        editCommand.execute(secretMaster);

        assertEquals("Password3", secretMaster.getByName("Password3").getName());
        assertEquals("Folder3", secretMaster.getByName("Password3").getFolderName());
        assertThrows(SecretNotFoundException.class, () -> secretMaster.getByName("Password1"));
    }

    @Test
    void execute_withNonExistingSecret_printErrorMessage() throws IllegalFolderNameException, RepeatedIdException,
            IllegalSecretNameException, FolderExistsException {
        SecretMaster secretMaster = new SecretMaster();
        secretMaster.addSecret(new Secret("Password1", "Folder1"));
        secretMaster.addSecret(new Secret("Password2", "Folder2"));

        EditCommand editCommand = new EditCommand("p/Password3 -f nf/Folder3 -N np/Password4");

        editCommand.execute(secretMaster);

        assertThrows(SecretNotFoundException.class, () -> secretMaster.getByName("Password4"));
    }

    /**
     * Test the isExit method in EditCommand class.
     */
    @Test
    void isExit() {
        EditCommand editCommand = new EditCommand("edit p/Test");
        assertFalse(editCommand.isExit());
    }
}
