package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.secrets.NUSNet;
import seedu.duke.storage.SecretMaster;

/**
 * Represents a Class to giva a command to add a NUSNet account.
 * Extends the Command class.
 */
public class AddNUSNetCommand extends Command{
    private String name;
    private String folderName;
    private String nusNetId;
    private String password;

    /**
     * Constructs an AddNUSNetCommand object.
     *
     * @param input The input string containing the details of the account to add.
     */
    public AddNUSNetCommand(String input) {
        this.name = extractName(input);
        this.folderName = extractFolderName(input);
        this.nusNetId = inquirenusNetId();
        if (this.name == null) {
            this.name = nusNetId;
        }
        this.password = inquirePassword();
    }
    public AddNUSNetCommand(NUSNet nusNet) {
        this.name = nusNet.getName();
        this.folderName = nusNet.getFolderName();
        this.nusNetId = nusNet.getnusNetId();
        this.password = nusNet.getPassword();
    }

    /**
     * Executes the command to add the NUSNet account to the SecretMaster.
     *
     * @param secureNUSData The SecretMaster that stores the NUSNet account.
     * @throws RuntimeException if there is a RepeatedIdException
     *                          or FolderExistsException
     *                          or IllegalSecretNameException
     *                          or IllegalFolderNameException.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        NUSNet nusNetIdData = new NUSNet(name,folderName,nusNetId,password);
        try {
            secureNUSData.addSecret(nusNetIdData);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new RuntimeException(e);
        }
        String starsPassword = "********";
        System.out.println("I have added a new NUS Net ID password:\n");
        System.out.println(
                "name       = " + name + "\n" +
                "folder     = " + folderName + "\n" +
                "NUS Net ID = " + nusNetId + "\n" +
                "password   = " + starsPassword);
    }

    /**
     * Extracts the name of the account from the input string.
     *
     * @param input The input string containing the details of the account to add.
     * @return The name of the account.
     */
    public String extractName(String input) {
        String[] extractedNames = input.split("o/NUSNet ");
        String extractedName;
        if (extractedNames.length == 2) {
            if (extractedNames[1].split(" /f").length > 1) {
                extractedName = extractedNames[1].split(" /f")[0];
            } else {
                extractedName = null;
            }
        } else {
            extractedName = null; //Default Name
        }
        return extractedName;
    }

    /**
     * Extracts the folder name from the user's input.
     *
     * @param input the user's input string
     * @return the folder name string, or "unnamed" if not found
     */
    public String extractFolderName(String input) {
        String extractedFolderName = "unnamed";
        if (input.split("/f ").length > 1) {
            extractedFolderName = input.split("/f ")[1];
        }
        return extractedFolderName;
    }

    /**
     * Prompts the user to enter their NUS Net ID.
     *
     * @return the user's NUS Net ID string input
     */
    public String inquirenusNetId() {
        System.out.println("Please enter your NUS Net ID: ");
        String nusNetId = Ui.readCommand();
        return nusNetId;
    }

    /**
     * Prompts the user to enter their NUS Net ID.
     *
     * @return the user's NUS Net ID string input
     */
    public String inquirePassword() {
        System.out.println("Please enter your NUS Net password: ");
        String password = Ui.readCommand();
        return password;
    }

    /**
     * Extracts the URL from the user's input.
     *
     * @param input the user's input string
     * @return an empty string
     */
    public String extractURL(String input) {
        return "";
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
