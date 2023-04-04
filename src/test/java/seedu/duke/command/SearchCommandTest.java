package seedu.duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This class represents the unit tests for the SearchCommand class.
 * It checks the following functionalities:
 * Extraction of name from input
 * Extraction of folder name from input
 * isExit method
 */
class SearchCommandTest {

    /**
     * Tests the extraction of name from input.
     * The test case checks for extraction of name only.
     */
    @Test
    void extractName_nameOnly() {
        SearchCommand searchCommandNameOnly = new SearchCommand("search n/Name123!");
        assertEquals(searchCommandNameOnly.extractName("search n/Name123!"), "Name123!");
    }

    /**
     * Tests the extraction of name from input.
     * The test case checks for extraction of name and folder.
     */
    @Test
    void extractName_nameAndFolder() {
        SearchCommand searchCommand = new SearchCommand("search n/Name123! -f f/Folder123!");
        assertEquals(searchCommand.extractName("search n/Name123! -f f/Folder123!"), "Name123!");
    }


    /**
     * Tests the extraction of folder name from input.
     * The test case checks for extraction of folder name only.
     */
    @Test
    void extractFolderName_nameOnly() {
        SearchCommand searchCommand = new SearchCommand("search n/Name123!");
        assertNull(searchCommand.extractFolderName("search n/Name123!"));
    }

    /**
     * Tests the extraction of folder name from input.
     * The test case checks for extraction of name and folder.
     */
    @Test
    void extractFolderName_nameAndFolder() {
        SearchCommand searchCommand = new SearchCommand("search n/Name123! -f f/Folder123!");
        assertEquals(searchCommand.extractFolderName("search n/Name123! -f f/Folder123!"), "Folder123!");
    }

    // TODO: execute()

    /**
     * Tests the isExit method.
     */
    @Test
    void isExit() {
        SearchCommand searchCommand = new SearchCommand("search n/Name123!");
        assertFalse(searchCommand.isExit());
    }
}
