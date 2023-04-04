package seedu.duke.command;

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
        Pattern passwordPattern = Pattern.compile("p/([\\w\\s]+)");
        Pattern folderPattern = Pattern.compile("-f nf/([\\w\\s]+)");
        Pattern descriptionPattern = Pattern.compile("-d nd/([\\w\\s]+)");
        Pattern newPasswordPattern = Pattern.compile("-N np/([\\w\\s]+)");

        // Extract values using regular expressions
        Matcher passwordMatcher = passwordPattern.matcher(input);
        Matcher folderMatcher = folderPattern.matcher(input);
        Matcher descriptionMatcher = descriptionPattern.matcher(input);
        Matcher newPasswordMatcher = newPasswordPattern.matcher(input);

        // Check if there is a match and extract the value
        extractedFields[0] = passwordMatcher.find() ? passwordMatcher.group(1).trim() : null;
        extractedFields[1] = folderMatcher.find() ? folderMatcher.group(1).trim() : null;
        extractedFields[2] = descriptionMatcher.find() ? descriptionMatcher.group(1).trim() : null;
        extractedFields[3] = newPasswordMatcher.find() ? newPasswordMatcher.group(1).trim() : null;

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
            passwordSecret = secureNUSData.getByName(this.name);
        } catch (SecretNotFoundException e) {
            throw new RuntimeException(e);
        }
        passwordSecret.editName(newName);
        // TODO: editFolderName()
        // TODO: editDescription()
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
