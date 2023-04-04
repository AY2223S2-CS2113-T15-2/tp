package seedu.duke.command;

import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.UnknownException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.messages.InquiryMessages;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.storage.SecretMaster;

import java.util.HashSet;


/**
 * Represents a class to give a command to add a basic password to the secret storage.
 * Inherits from Command.
 * Upon execution, creates a new BasicPassword object with the provided name, folderName, username, password, and url.
 * Adds the BasicPassword object to the secret storage.
 * Prompts the user to input the username, password, and url if they are not provided in the input string.
 * If the BasicPassword object cannot be created or added to the storage, throws a RuntimeException.
 * Prints a success message upon completion of execution.
 */
public class AddBasicPasswordCommand extends AddSecretCommand {

    public static final String KEYWORD = "new";
    private String username;
    private String password;
    private String url;


    /**
     * Constructor for AddBasicPasswordCommand.
     * Extracts the name, folderName, username, password, and url from the provided input string.
     *
     * @param input The input string to extract the relevant information from.
     */
    public AddBasicPasswordCommand(String input, HashSet<String> usedNames) throws IllegalSecretNameException,
            IllegalFolderNameException, OperationCancelException, RepeatedIdException {
        super(input, usedNames, KEYWORD);
        this.url = inquire(InquiryMessages.URL, "URL");
        this.username = inquire(InquiryMessages.USERNAME, "Username");
        this.password = inquire(InquiryMessages.PASSWORD, "Password");
    }
    public AddBasicPasswordCommand(BasicPassword basicPassword) {
        super(basicPassword);
        this.url = basicPassword.getUrl();
        this.username = basicPassword.getUsername();
        this.password = basicPassword.getPassword();
    }

    /**
     * Executes the AddBasicPasswordCommand.
     * Creates a new BasicPassword object with the provided name, folderName, username, password, and url.
     * Adds the BasicPassword object to the secret storage.
     * Prompts the user to input the username, password, and url if they are not provided in the input string.
     * If the BasicPassword object cannot be created or added to the storage, throws a RuntimeException.
     * Prints a success message upon completion of execution.
     *
     * @param secureNUSData The secret storage to add the BasicPassword object to.
     * @throws UnknownException If there is an error creating or adding the BasicPassword object to the storage.
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        BasicPassword basicPasswordData = new BasicPassword(name,folderName,username,password,url);

        try {
            secureNUSData.addSecret(basicPasswordData);
        } catch (RepeatedIdException e) {
            throw new ExceptionMain("Repeated ID: Basic Password Command");
        } catch (FolderExistsException e) {
            throw new ExceptionMain("Unknown Error: Basic Password Command");
        } catch (IllegalSecretNameException e) {
            throw new ExceptionMain("Unknown Error: Basic Password Command");
        } catch (IllegalFolderNameException e) {
            throw new ExceptionMain("Unknown Error: Basic Password Command");
        }
        System.out.println("I have added a new basic password:\n");
        System.out.println("name     = " + name + "\n" +
                           "folder   = " + folderName + "\n" +
                           "url      = " + url + "\n" +
                           "username = " + username + "\n" +
                           "password = " + HIDDEN_FIELD);
    }

    /**
     * Returns false, indicating that this command does not result in program exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }


}
