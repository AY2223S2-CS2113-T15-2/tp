package seedu.duke;

import seedu.duke.ui.Parser;
import seedu.duke.ui.Ui;
import seedu.duke.command.Command;
import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.InsufficientParamsException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidFieldException;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.messages.ErrorMessages;
import seedu.duke.storage.SecretMaster;

import java.util.logging.Logger;
import java.util.logging.Level;

public class SecureNUS {
    private static final Logger LOGGER = DukeLogger.LOGGER;
    private static final String DUKE_LOG_EXECUTECOMMAND_IDENTIFIER = "Duke - executeCommand";
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
    public SecureNUS() throws FolderExistsException, IllegalFolderNameException {
        secureNUSData = Backend.initialisation();
        DukeLogger.setUpLogger();
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

        SecureNUS secureNUS = new SecureNUS();
        secureNUS.run();

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
            try {
                isExit = executeCommand(c);
            } catch (ExceptionMain e) {
                Ui.inform("Unknown issue");
                return;
            }
            
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
        String input = Ui.readCommand();
        Command command = null;
        Ui.printLine(); //top most line
        try {
            command = Parser.parse(input, secureNUSData.getSecretNames());
        } catch(InvalidCommandException e) {
            Ui.printError(ErrorMessages.INVALID_COMMAND);
            return null;
        } catch (InsufficientParamsException e) {
            Ui.printError(ErrorMessages.INSUFFICIENT_PARAMS);
        } catch (IllegalSecretNameException e) {
            Ui.printError(ErrorMessages.ILLEGAL_SECRET_NAME);
        } catch (IllegalFolderNameException e) {
            Ui.printError(ErrorMessages.ILLEGAL_FOLDER_NAME);
        } catch (OperationCancelException e) {
            Ui.informOperationCancel();
        } catch (RepeatedIdException e) {
            Ui.printError(ErrorMessages.REPEATED_ID);
        } catch (InvalidFieldException e) {
            Ui.printError(ErrorMessages.INVALID_FIELD);
        }
        return command;
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
            SecretNotFoundException, ExceptionMain {
        if (command != null) {
            try {
                command.execute(secureNUSData);
                return command.isExit();
            } catch (ExceptionMain e) {
                Ui.printError(e.getMessage()); //do they want UI to handle it or?
                LOGGER.log(Level.SEVERE, DUKE_LOG_EXECUTECOMMAND_IDENTIFIER, e);
                DukeLogger.close();
            }
        }
        return false;
    }
}
