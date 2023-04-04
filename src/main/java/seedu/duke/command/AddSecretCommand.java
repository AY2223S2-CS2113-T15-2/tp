package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

/**
 * Represents the Class to give a command to add a secret.
 */
public class AddSecretCommand extends Command {
    private String name;
    private String folderName;

    /**
     * Constructor for AddSecretCommand class.
     *
     * @param input the input command string
     */
    public AddSecretCommand(String input) {
        name = extractName(input);
        folderName = extractFolderName(input);
    }

    /**
     * Extracts the name of the secret from the input command.
     * 
     * @param input the input command string
     * @return the name of the secret
     */
    public String extractName(String input) {
        String extractedName = input.split("new ")[1];
        extractedName = extractedName.split(" /f")[0];
        return extractedName;
    }

    /**
     * Extracts the folder name of the secret from the input command.
     *
     * @param input the input command string
     * @return the folder name of the secret
     */
    public String extractFolderName(String input) {
        String extractedFolderName = "unfiled";
        if (input.split("/f ").length > 1) {
            extractedFolderName = input.split("/f ")[1];
        }
        return extractedFolderName;
    }

    /**
     * Prompts the user for input and returns the user's response.
     * 
     * @param question the question to ask the user
     * @return return the user's response
     */
    public String inquire(String question) {
        System.out.println(question);
        String result = Ui.readCommand();
        return result;
    }

    /**
     * Executes the AddSecretCommand.
     * Adds a new secret to the SecureNUS application.
     *
     * @param secureNUSData secureNUSData the secret master object
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        Secret secret = new Secret(name,folderName);
        try {
            secureNUSData.addSecret(secret);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new RuntimeException(e);
        }
        String starsPassword = "********";
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
