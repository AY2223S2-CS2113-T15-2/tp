package seedu.duke.command;

import seedu.duke.exceptions.secrets.NonExistentFolderException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import java.util.ArrayList;

/**
 * Represents a class to give a command to search for a secret by name in the SecretMaster.
 * Inherits from the Command class.
 */
public class SearchCommand extends Command{
    private final String name;
    private final String folderName;

    /**
     * Constructor for the SearchCommand.
     *
     * @param input The input String containing the name and optionally the folder name to search for.
     */
    public SearchCommand(String input) {
        this.name = extractName(input);
        this.folderName = extractFolderName(input);
    }

    /**
     * Extracts the name from the input String.
     * @param input The input String containing the name and optionally the folder name to search for.
     * @return A String representing the name to search for.
     */
    public String extractName(String input) {
        if (input.contains("-f")) {
            return input.substring(input.indexOf("n/") + 2, input.indexOf("-f")).trim();
        }
        return input.substring(input.indexOf("n/") + 2).trim();
    }

    /**
     * Extracts the folder name from the input String.
     * @param input The input String containing the name and optionally the folder name to search for.
     * @return A String representing the folder name to search in, or null if not provided.
     */
    public String extractFolderName(String input) {
        if (input.contains("-f")) {
            return input.substring(input.indexOf("f/") + 2).trim();
        }
        return null;
    }

    /**
     * Searches for secrets in the SecretMaster with names that contain the specified name and optionally, in the
     * specified folder.
     * Prints out the search results in a formatted table.
     *
     * @param secureNUSData The SecretMaster containing the secrets to search in.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        ArrayList<Secret> secrets;
        if (this.folderName != null) {
            // search a specific folder
            try {
                secrets = secureNUSData.listSecrets(this.folderName);
            } catch (NonExistentFolderException e) {
                throw new RuntimeException(e);
            }
        } else {
            // search all passwords
            secrets = secureNUSData.listSecrets();
        }
        int count = 0;
        StringBuilder output = new StringBuilder();
        for (Secret secret : secrets) {
            if (secret.getName().contains(this.name)) { // case-sensitive search
                ++count;
                output.append("ID:").append("\t|\t").append(count)
                        .append("\t|\t").append(secret.getName()).append("\t");
            }
        }
        System.out.println("Found " + count + " matches!");
        System.out.println(output.toString());
    }

    /**
     * Returns a boolean indicating if this Command is an exit Command.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
