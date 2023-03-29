package seedu.duke.command;

import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import java.util.Scanner;

/**
 * Represents a class to give a command to view a specific secret.
 */
public class ViewCommand extends Command {
    private final String passwordName;

    /**
     * Constructs a ViewCommand object.
     *
     * @param input the user input to extract the password name
     */
    public ViewCommand(String input) {
        this.passwordName = extractName(input);
    }

    /**
     * Extracts the password name from the user input.
     *
     * @param input the user input containing the password name
     * @return the password name
     */
    public String extractName(String input) {
        return input.split("view")[1].trim();
    }

    /**
     * Prompts the user to enter the secret password to reveal the password.
     *
     * @return the user input of the secret password
     */
    public String inquirePassword(Scanner in) {
        System.out.println("Enter secret password to reveal \"" + this.passwordName + "\":");
        return in.nextLine();
    }

    /**
     * Executes the view command to reveal the password of a specific secret.
     *
     * @param secureNUSData the SecretMaster object containing the secret to view
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        Secret passwordSecret;
        try {
            passwordSecret = secureNUSData.getByName(this.passwordName);
        } catch (SecretNotFoundException e) {
            throw new RuntimeException(e);
        }
        // TODO: How to show password?
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return false as view command is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
