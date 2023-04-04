package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.InvalidURLException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.storage.SecretMaster;


/**
 * Represents a class to give a command to add a basic password to the secret storage.
 * Inherits from Command.
 * Upon execution, creates a new BasicPassword object with the provided name, folderName, username, password, and url.
 * Adds the BasicPassword object to the secret storage.
 * Prompts the user to input the username, password, and url if they are not provided in the input string.
 * If the BasicPassword object cannot be created or added to the storage, throws a RuntimeException.
 * Prints a success message upon completion of execution.
 */
public class AddBasicPasswordCommand extends Command{

    private String name;
    private String folderName;
    private String username;
    private String password;
    private String url;

    /**
     * Constructor for AddBasicPasswordCommand.
     * Extracts the name, folderName, username, password, and url from the provided input string.
     *
     * @param input The input string to extract the relevant information from.
     */
    public AddBasicPasswordCommand(String input) {
        this.name = extractName(input);
        this.folderName = extractFolderName(input);
        this.url = inquireURL(input);
        this.username = inquireUsername(input);
        this.password = inquirePassword();
    }
    public AddBasicPasswordCommand(BasicPassword basicPassword) {
        this.name = basicPassword.getName();
        this.folderName = basicPassword.getFolderName();
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
     * @throws ExceptionMain If there is an error creating or adding the BasicPassword object to the storage.
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        BasicPassword basicPasswordData;
        try {
            basicPasswordData = new BasicPassword(name,folderName,username,password,url);
        } catch (InvalidURLException e) {
            throw new ExceptionMain("Invalid URL!");
        }

        try {
            secureNUSData.addSecret(basicPasswordData);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new RuntimeException(e);
        }
        String starsPassword = "********";
        System.out.println("I have added a new basic password:\n");
        System.out.println("name     = " + name + "\n" +
                           "folder   = " + folderName + "\n" +
                           "url      = " + url + "\n" +
                           "username = " + username + "\n" +
                           "password = " + starsPassword);
    }

    /**
     * Extracts the name from the provided input string.
     *
     * @param input The input string to extract the name from.
     * @return The name extracted from the input string.
     */
    public String extractName(String input) {
        String extractedName = input.split("new ")[1];
        extractedName = extractedName.split(" /f")[0];
        return extractedName;
    }

    /**
     *  Extracts the name of the folder from the provided input string.
     *
     *  @param input The input string to extract the folderName from.
     *  @return The folderName extracted from the input string.
     */
    public String extractFolderName(String input) {
        String extractedFolderName = "unnamed";
        if (input.split("/f ").length > 1) {
            extractedFolderName = input.split("/f ")[1];
        }
        return extractedFolderName;
    }

    /**
     * Prompts the user to input the username.
     *
     * @param input the user input
     * @return the username entered by the user
     */
    public String inquireUsername(String input) {
        System.out.println("Please enter your username: ");
        String username = Ui.readCommand();
        return username;
    }

    /**
     * Prompts the user to input a password and returns the input as a String.
     *
     * @return a String representing the password input by the user.
     */
    public String inquirePassword() {
        System.out.println("Please enter your password: ");
        String password = Ui.readCommand();
        return password;
    }


    /**
     * Prompts the user to input the URL and returns it as a String.
     *
     * @param input the input String used for any necessary prompts
     * @return the URL inputted by the user
     */
    public String inquireURL(String input) {
        System.out.println("Please enter the url: ");
        String url = Ui.readCommand();
        return url;
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
