package seedu.duke.ui;

import seedu.duke.command.AddBasicPasswordCommand;
import seedu.duke.command.AddCreditCardCommand;
import seedu.duke.command.AddCryptoWalletCommand;
import seedu.duke.command.AddNUSNetCommand;
import seedu.duke.command.AddStudentIDCommand;
import seedu.duke.command.AddWifiPasswordCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.MenuCommand;
import seedu.duke.command.SearchCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.exceptions.InsufficientParamsException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidFieldException;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;

import java.util.HashSet;

/**
 * Parses user commands and returns the corresponding command object.
 */
public class Parser {
    /**
     * Parses user input and returns the corresponding command object.
     *
     * @param command       user input command string
     * @return Command object corresponding to the user input
     */
    public static Command parse(String command, HashSet<String> usedNames) throws InvalidCommandException,
            InsufficientParamsException, IllegalFolderNameException, IllegalSecretNameException,
            OperationCancelException, RepeatedIdException, InvalidFieldException {
        if (command.startsWith("new")) {
            return parseAdd(command, usedNames);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        } else if (command.startsWith("list")) {
            return new ListCommand(command);
        } else if (command.startsWith("search")) {
            return new SearchCommand(command);
        } else if (command.startsWith("view")) {
            return new ViewCommand(command);
        } else if (command.startsWith("edit")) {
            return new EditCommand(command);
        } else if (command.startsWith("menu")) {
            return new MenuCommand();
        } else if (command.startsWith("exit")) {
            return new ExitCommand();
        } else {
            // represents accidental wrong input
            throw new InvalidCommandException();
        }
    }

    public static Command parseAdd(String command, HashSet<String> usedNames) throws InsufficientParamsException,
            IllegalFolderNameException, IllegalSecretNameException, OperationCancelException, RepeatedIdException,
            InvalidFieldException {
        if (command.startsWith("new o/")) {
            return parseAddSpecial(command, usedNames);
        } else if (command.startsWith("new ") && command.split(" ").length > 1) {
            return new AddBasicPasswordCommand(command, usedNames);
        } else {
            throw new InsufficientParamsException();
        }
    }

    public static Command parseAddSpecial(String command, HashSet<String> usedNames) throws OperationCancelException,
            IllegalFolderNameException, IllegalSecretNameException, RepeatedIdException, InvalidFieldException,
            InsufficientParamsException {
        if (command.split(" ").length < 3) {
            throw new InsufficientParamsException();
        }
        if (command.startsWith("new " + AddCreditCardCommand.KEYWORD)) {
            return new AddCreditCardCommand(command, usedNames);
        } else if (command.startsWith("new " + AddCryptoWalletCommand.KEYWORD)) {
            return new AddCryptoWalletCommand(command, usedNames); // Have to change to AddCryptoWalletCommand
        } else if (command.startsWith("new " + AddNUSNetCommand.KEYWORD)) {
            return new AddNUSNetCommand(command, usedNames);
        } else if (command.startsWith("new " + AddStudentIDCommand.KEYWORD)) {
            return new AddStudentIDCommand(command, usedNames);
        } else if (command.startsWith("new " + AddWifiPasswordCommand.KEYWORD)) {
            return new AddWifiPasswordCommand(command, usedNames); // Have to change to AddWifiPasswordCommand
        } else if (command.startsWith("new " + AddCreditCardCommand.KEYWORD)) {
            return new AddCreditCardCommand(command, usedNames);
        } else {
            throw new InvalidFieldException();
        }
    }
}
