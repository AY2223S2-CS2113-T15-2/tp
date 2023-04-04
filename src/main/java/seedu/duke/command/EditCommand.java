package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Represents a class to give a command to edit a secret in the secureNUS application.
 * The EditCommand class extends the Command class.
 * The EditCommand class takes in a String input that specifies which secret to edit
 * and the new values of the fields to be updated.
 * The EditCommand class extracts the new values of the fields from the input string using regular expressions.
 * The class then uses these values to edit the secret in the SecretMaster object.
 */
public class EditCommand extends Command {
    private final String name;
    private final String newFolderName;
    private final String newDescription;
    private final String newName;

    /**
     * Constructs an EditCommand object with the specified input string.
     * Extracts the name of the secret to edit and the new values of the fields to be updated from the input string.
     *
     * @param input the input string that specifies which secret to edit and the new values of the fields to be updated.
     */
    public EditCommand(String input) {
        String[] extractedFields = extract(input);
        this.name = extractedFields[0];
        this.newFolderName = extractedFields[1];
        this.newDescription = extractedFields[2];
        this.newName = extractedFields[3];
    }

    /**
     * Extracts the name of the secret to edit and the new values of the fields to be updated from the input string
     * using regular expressions.
     *
     * @param input the input string that specifies which secret to edit and the new values of the fields to be updated.
     * @return an array of Strings containing the extracted values of the fields to be updated.
     */
    public String[] extract(String input) {
        String[] extractedFields = new String[4];

        // Define regular expression patterns
        Pattern namePattern = Pattern.compile("p/([\\w\\s]+)");
        Pattern newFolderPattern = Pattern.compile("-f nf/([\\w\\s]+)");
        Pattern newDescriptionPattern = Pattern.compile("-d nd/([\\w\\s]+)");
        Pattern newNamePattern = Pattern.compile("-N np/([\\w\\s]+)");

        // Extract values using regular expressions
        Matcher nameMatcher = namePattern.matcher(input);
        Matcher newFolderMatcher = newFolderPattern.matcher(input);
        Matcher newDescriptionMatcher = newDescriptionPattern.matcher(input);
        Matcher newNameMatcher = newNamePattern.matcher(input);

        // Check if there is a match and extract the value
        extractedFields[0] = nameMatcher.find() ? nameMatcher.group(1).trim() : null;
        extractedFields[1] = newFolderMatcher.find() ? newFolderMatcher.group(1).trim() : "unnamed";
        extractedFields[2] = newDescriptionMatcher.find() ? newDescriptionMatcher.group(1).trim() : "";
        extractedFields[3] = newNameMatcher.find() ? newNameMatcher.group(1).trim() : "";

        if (extractedFields[0] == null) {
            Ui.printError("(Invalid input)");
        }

        return extractedFields;
    }

    /**
     * Edits the specified secret in the SecretMaster object with the new values of the fields.
     *
     * @param secureNUSData the SecretMaster object containing the list of secrets.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        Secret passwordSecret;
        try {
            passwordSecret = secureNUSData.getByName(name);
            secureNUSData.editSecret(passwordSecret, newName, newFolderName);
        } catch (SecretNotFoundException e) {
            Ui.printError("(The password is not found).");
        } catch (FolderExistsException e) {
            Ui.printError("(The folder being created already exists).");
        }
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, as it is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
