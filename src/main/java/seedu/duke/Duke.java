package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.storage.SecretMaster;

public class Duke {
    /**
     * Duke class handles the main entry-point for the application, parsing of user commands and execution of commands.
     * Duke class also initializes a SecretMaster object to store and manage the secrets for the application.
     */
    private SecretMaster secureNUSData;

    /**
     * Duke constructor initializes a SecretMaster object to store and manage the secrets for the application.
     *
     * @throws FolderExistsException         If the database folder already exists.
     * @throws IllegalFolderNameException    If the name of a folder is not valid.
     */
    public Duke() throws FolderExistsException, IllegalFolderNameException {
        secureNUSData = Backend.initialisation();
        // secureNUSData = new SecretMaster();
        // System.out.println("Remember to initialise");
    }
    /**
     * Main entry-point for the Duke application.
     * Initializes a Duke object and runs the application.
     *
     * @param args An array of command-line arguments for the application.
     * @throws FolderExistsException         If the database folder already exists.
     * @throws IllegalFolderNameException    If the name of a folder is not valid.
     * @throws IllegalSecretNameException    If the name of a secret is not valid.
     * @throws SecretNotFoundException       If the specified secret cannot be found.
     */
    public static void main(String[] args) throws FolderExistsException, IllegalFolderNameException,
            IllegalSecretNameException, SecretNotFoundException {

        Duke duke = new Duke();
        duke.run();

    }

    /**
     * Starts the main loop of the Duke application.
     * Parses user input commands and executes them until the "exit" command is given.
     *
     * @throws IllegalFolderNameException    If the name of a folder is not valid.
     * @throws IllegalSecretNameException    If the name of a secret is not valid.
     * @throws SecretNotFoundException       If the specified secret cannot be found.
     */
    public void run() throws IllegalFolderNameException, IllegalSecretNameException, SecretNotFoundException {
        Ui.greetUser();

        boolean isExit = false;

        while (!isExit) {
            Command c = parseCommand();
            if (c == null) {
                continue;
            }
            Ui.printLine(); //middle line
            isExit = executeCommand(c);
            
            Ui.printLine(); //end line
        }
        Ui.close();
        Backend.updateStorage(this.secureNUSData.listSecrets());
    }


    /**
     * Reads user input command and returns a Command object.
     *
     * @return A Command object that represents the user input command.
     */
    public Command parseCommand() {
        String command = Ui.readCommand();
        Ui.printLine(); //top most line
        try {
            return Parser.parse(command);
        } catch(InvalidCommandException e) {
            Ui.printError("Invalid Command");
            return null;
        }
    }

    /**
     * Executes the given Command object and returns a boolean indicating whether the application should exit.
     *
     * @param command The Command object to execute.
     * @return A boolean indicating whether the application should exit.
     * @throws IllegalFolderNameException    If the name of a folder is not valid.
     * @throws IllegalSecretNameException    If the name of a secret is not valid.
     * @throws SecretNotFoundException       If the specified secret cannot be found.
     */
    public boolean executeCommand(Command command) throws IllegalFolderNameException, IllegalSecretNameException,
            SecretNotFoundException {
        if (command != null) {
            try {
                command.execute(secureNUSData);
                return command.isExit();
            } catch (ExceptionMain e) {
                Ui.printError(e.getMessage());
            }
        }
        return false;
    }
}
