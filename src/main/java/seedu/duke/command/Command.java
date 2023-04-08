package seedu.duke.command;

import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.NullFolderException;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.FolderNotFoundException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.NonExistentFolderException;
import seedu.duke.exceptions.secrets.NullSecretException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.messages.InquiryMessages;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;
import seedu.duke.ui.Ui;

import java.util.HashSet;
import java.util.Scanner;

/**
 * The abstract class Command serves as a blueprint for all other command classes to inherit from. It contains two
 * abstract methods: isExit and execute, which must be implemented by any child classes.
 */
public abstract class Command {
    protected static final String HIDDEN_FIELD = "*******";
    protected static final String CANCEL_COMMAND = "c/";
    private static final String EMPTY_STRING_REGEX = "[ ]*";
    public abstract boolean isExit();
    public abstract void execute (SecretMaster secureNUSData) throws SecretNotFoundException, ExceptionMain,
            NonExistentFolderException, OperationCancelException, FolderExistsException;

    /**
     * Prompts the user for input and returns the user's response.
     *
     * @param question the question to ask the user
     * @return return the user's response
     */
    public String inquire(String question, String fieldName) throws OperationCancelException {
        assert question != null;
        assert fieldName != null;
        String result = query(question);
        while (isEmptyEntry(result)) {
            System.out.println(String.format(InquiryMessages.TEMPLATE_EMPTY, fieldName));
            result = query(question);
        }
        return result;
    }

    /**
     * Overloaded method for unit testing purposes.
     */
    public String inquire(String question, String fieldName, Scanner scanner) throws OperationCancelException {
        assert question != null;
        assert fieldName != null;
        String result = query(question, scanner);
        while (isEmptyEntry(result)) {
            System.out.println(String.format(InquiryMessages.TEMPLATE_EMPTY, fieldName));
            result = query(question, scanner);
        }
        return result;
    }

    /**
     * Extracts the name of the secret from the input command.
     *
     * @param input the input command string
     * @return the name of the secret
     */
    public String extractName(String input, String keyword) {
        assert input != null;
        String extractedName = input.split(keyword + " ")[1];
        extractedName = extractedName.split(" f/")[0];
        return extractedName;
    }

    public void nameCheck(String name) throws NullSecretException, IllegalSecretNameException {
        if (name == "" || name == null) {
            throw new NullSecretException();
        }
        if (Secret.isIllegalName(name)) {
            throw new IllegalSecretNameException();
        }
    }


    public void nameCheckWithExistence(String name, HashSet<String> usedNames) throws NullSecretException,
            IllegalSecretNameException, SecretNotFoundException {
        if (name == "" || name == null) {
            throw new NullSecretException();
        }
        if (Secret.isIllegalName(name)) {
            throw new IllegalSecretNameException();
        }
        if (!usedNames.contains(name)) {
            throw new SecretNotFoundException();
        }
    }

    public void folderCheckWithExistence(String folderName, HashSet<String> folders) throws NullFolderException,
            FolderNotFoundException, IllegalFolderNameException {
        if (folderName == "" || folderName == null) {
            throw new NullFolderException();
        }
        if (!SecretMaster.isLegalFolderName(folderName)) {
            throw new IllegalFolderNameException();
        }
        if (!folders.contains(folderName)) {

            throw new FolderNotFoundException();
        }
    }

    /**
     * Extracts the folder name of the secret from the input command.
     *
     * @param input the input command string
     * @return the folder name of the secret
     */
    public String extractFolderName(String input) {
        assert input != null;
        String extractedFolderName = "unnamed";
        if (input.split(" f/").length > 1) {
            extractedFolderName = input.split(" f/")[1];
            extractedFolderName = extractedFolderName.split(" ")[0];
        }
        return extractedFolderName;
    }

    public String query(String question) throws OperationCancelException {
        assert question != null;
        System.out.println(question);
        String line = Ui.readLine();
        if (line.equals(CANCEL_COMMAND)) {
            throw new OperationCancelException();
        }
        return line;
    }

    /**
     * Overloaded method for unit testing purposes.
     */
    public String query(String question, Scanner scanner) throws OperationCancelException {
        assert question != null;
        System.out.println(question);
        String line = Ui.readLine(scanner);
        if (line.equals(CANCEL_COMMAND)) {
            throw new OperationCancelException();
        }
        return line;
    }


    public boolean isEmptyEntry(String input) {
        return input.equals("") ||
                input.matches(EMPTY_STRING_REGEX) ||
                input == null;
    }

    public void printCurrentState(SecretMaster secureNUSData) {
        // used for debugging
        String printout = "Current Folders [";
        for (String foldern: secureNUSData.getFolders()) {
            printout += "\n" + foldern + ',';
        }
        printout += "]";
        System.out.println(printout);
    }
}
