package seedu.duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * The EditCommandTest class tests the EditCommand class.
 */
class EditCommandTest {
    /**
     * Test the extract method in EditCommand class.
     */
    @Test
    void extract() {
        EditCommand editCommand = new EditCommand("edit p/PASSWORD_NAME -f nf/NEW_FOLDER_NAME " +
                "-d nd/NEW_DESCRIPTION -N np/NEW_PASSWORD_NAME");
        String[] expectedOutputs = {"PASSWORD_NAME", "NEW_FOLDER_NAME", "NEW_DESCRIPTION", "NEW_PASSWORD_NAME"};
        String[] actualOutputs = editCommand.extract("edit p/PASSWORD_NAME -f nf/NEW_FOLDER_NAME " +
                "-d nd/NEW_DESCRIPTION -N np/NEW_PASSWORD_NAME");
        assertArrayEquals(actualOutputs, expectedOutputs);
    }

    // TODO: execute()

    /**
     * Test the isExit method in EditCommand class.
     */
    @Test
    void isExit() {
        EditCommand editCommand = new EditCommand("edit p/Test");
        assertFalse(editCommand.isExit());
    }
}
