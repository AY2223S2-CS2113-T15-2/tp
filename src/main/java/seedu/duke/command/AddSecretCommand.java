package seedu.duke.command;

import seedu.duke.ui.Ui;
import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.messages.InquiryMessages;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import java.util.HashSet;

/**
 * Represents the Class to give a command to add a secret.
 */
public abstract class AddSecretCommand extends Command {
    protected static final String HIDDEN_FIELD = "*******";
    protected static final String CANCEL_COMMAND = "c/";
    private static final String EMPTY_STRING_REGEX = "[ ]*";
    protected String name;
    protected String folderName;

    public AddSecretCommand() {}

    /**
     * Constructor for AddSecretCommand class.
     *
     * @param input the input command string
     */
    public AddSecretCommand(String input, HashSet<String> usedNames, String keyword) throws IllegalSecretNameException,
            IllegalFolderNameException, RepeatedIdException {
        name = extractName(input, keyword);
        folderName = extractFolderName(input);
        checkNameAndFolderName(name, folderName, usedNames);
    }


    public AddSecretCommand(Secret secret) {
        name = secret.getName();
        folderName = secret.getFolderName();
    }

    public void checkNameAndFolderName(String name, String folderName, HashSet<String> usedNames) throws
            IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException {
        if (!SecretMaster.isLegalFolderName(folderName)) {
            throw new IllegalFolderNameException();
        }
        if (Secret.isIllegalName(name)) {
            throw new IllegalSecretNameException();
        }
        if (usedNames.contains(name)) {
            throw new RepeatedIdException();
        }
    }
    /**
     * Extracts the name of the secret from the input command.
     * 
     * @param input the input command string
     * @return the name of the secret
     */
    public String extractName(String input, String keyword) {
        String extractedName = input.split(keyword + " ")[1];
        extractedName = extractedName.split(" f/")[0];
        return extractedName;
    }

    /**
     * Extracts the folder name of the secret from the input command.
     *
     * @param input the input command string
     * @return the folder name of the secret
     */
    public String extractFolderName(String input) {
        String extractedFolderName = "unnamed";
        if (input.split(" f/").length > 1) {
            extractedFolderName = input.split(" f/")[1];
            extractedFolderName = extractedFolderName.split(" ")[0];
        }
        return extractedFolderName;
    }

    public boolean isEmptyEntry(String input) {
        return input.equals("") ||
                input.matches(EMPTY_STRING_REGEX) ||
                input == null;
    }


    /**
     * Prompts the user for input and returns the user's response.
     * 
     * @param question the question to ask the user
     * @return return the user's response
     */
    public String inquire(String question, String fieldName) throws OperationCancelException {
        String result = query(question);
        while (isEmptyEntry(result)) {
            System.out.println(String.format(InquiryMessages.TEMPLATE_EMPTY, fieldName));
            result = query(question);
        }
        return result;
    }

    public String query(String question) throws OperationCancelException {
        System.out.println(question);
        String line = Ui.readLine();
        if (line.equals(CANCEL_COMMAND)) {
            throw new OperationCancelException();
        }
        return line;
    }

    /**
     * Executes the AddSecretCommand.
     * Adds a new secret to the SecureNUS application.
     *
     * @param secureNUSData secureNUSData the secret master object
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        Secret secret = new Secret(name,folderName);
        try {
            secureNUSData.addSecret(secret);
        } catch (RepeatedIdException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new ExceptionMain(e.getMessage());
        }
        System.out.println("I have added a new Secret:\n");
        System.out.println("name     = " + name + "\n" +
                "folder   = " + folderName + "\n");
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
