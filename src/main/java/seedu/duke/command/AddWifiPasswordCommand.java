package seedu.duke.command;

import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.messages.InquiryMessages;
import seedu.duke.secrets.WifiPassword;
import seedu.duke.storage.SecretMaster;

import java.util.HashSet;

/**
 * Represents the class to give a command to add a new Student ID to the SecureNUS system.
 */
public class AddWifiPasswordCommand extends AddSecretCommand {
    public static final String KEYWORD = "o/WifiPassword";
    private String username;
    private String password;

    /**
     * Constructs an AddStudentIDCommand object with the user input as the command parameter.
     *
     * @param input The command input entered by the user.
     */
    public AddWifiPasswordCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {
        super(input, usedNames, KEYWORD);
        username = inquire(InquiryMessages.USERNAME, "Username");
        password = inquire(InquiryMessages.PASSWORD, "Password");
    }
    public AddWifiPasswordCommand(WifiPassword wifiPassword) {
        this.name = wifiPassword.getName();
        this.folderName = wifiPassword.getFolderName();
        this.username = wifiPassword.getUsername();
        this.password = wifiPassword.getPassword();
    }

    /**
     * Executes the AddStudentIDCommand to add a new Student ID to the SecureNUS system.
     *
     * @param secureNUSData
     */

    @Override
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        WifiPassword wifiPassword = new WifiPassword(name, folderName, username, password);
        try {
            secureNUSData.addSecret(wifiPassword);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (IllegalSecretNameException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (IllegalFolderNameException e) {
            throw new ExceptionMain(e.getMessage());
        }
        System.out.println("I have added a new Wifi Password:\n");
        System.out.println(
                "name       = " + name + "\n" +
                "Folder     = " + folderName + "\n" +
                "Username   = " + username + "\n" +
                "Password   = " + password);
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
