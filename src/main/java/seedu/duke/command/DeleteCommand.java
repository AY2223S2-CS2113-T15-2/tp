package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

/**
 * Represents a class to give a command to delete a secret from the secureNUSData.
 * Inherits from the Command class.
 */
public class DeleteCommand extends Command {

    private String secretName;
    private String folderName;

    /**
     * Class constructor that extracts the name of the secret and its folder from the input string.
     *
     * @param input the input string from the user
     */
    public DeleteCommand(String input) {
        this.secretName = extractName(input);
        this.folderName = extractFolderName(input);
    }

    /**
     * Extracts the name of the secret to be deleted from the input string.
     *
     * @param input the input string from the user
     * @return the name of the secret to be deleted
     */
    public String extractName(String input) {
        String extractedName = input.split("delete ")[1];
        extractedName = extractedName.split(" /f")[0];
        return extractedName;
    }

    /**
     * Extracts the name of the folder in which the secret to be deleted is located from the input string.
     *
     * @param input the input string from the user
     * @return the name of the folder in which the secret to be deleted is located
     */
    public String extractFolderName(String input) {
        String extractedFolderName = "unnamed";
        if (input.split("/f ").length > 1) {
            extractedFolderName = input.split("/f ")[1];
        }
        return extractedFolderName;
    }

    /**
     * Deletes the specified secret from the secureNUSData.
     *
     * @param secureNUSData the SecretMaster object that stores all the secrets.
     * @throws SecretNotFoundException if the secret to be deleted is not found in the secureNUSData.
     */
    @Override
    public void execute(SecretMaster secureNUSData) throws SecretNotFoundException {
        Secret deleteData;
        try {
            deleteData = secureNUSData.getByName(secretName);
            try {
                secureNUSData.removeSecret(deleteData);
            } catch (SecretNotFoundException e) {
                Ui.printError("Data not found!");
            }
        } catch (SecretNotFoundException e) {
            Ui.printError("Data not found!");
        }

        System.out.println("You deleted " + secretName + " in " + folderName + "\n");
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
