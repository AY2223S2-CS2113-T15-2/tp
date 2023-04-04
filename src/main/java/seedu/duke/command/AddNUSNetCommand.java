package seedu.duke.command;

import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.messages.InquiryMessages;
import seedu.duke.secrets.NUSNet;
import seedu.duke.storage.SecretMaster;

import java.util.HashSet;

/**
 * Represents a Class to giva a command to add a NUSNet account.
 * Extends the Command class.
 */
public class AddNUSNetCommand extends AddSecretCommand {
    public static final String KEYWORD = "o/NUSNet";
    private String nusNetId;
    private String password;

    /**
     * Constructs an AddNUSNetCommand object.
     *
     * @param input The input string containing the details of the account to add.
     */
    public AddNUSNetCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {

        super(input, usedNames, KEYWORD);
        this.nusNetId = inquireNusNetId();
        this.password = inquire(InquiryMessages.PASSWORD, "Password");;
    }
    public AddNUSNetCommand(NUSNet nusNet) {
        super(nusNet);
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
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        NUSNet nusNetIdData = new NUSNet(name,folderName,nusNetId,password);
        try {
            secureNUSData.addSecret(nusNetIdData);
        } catch (RepeatedIdException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new ExceptionMain(e.getMessage());
        }
        System.out.println("I have added a new NUS Net ID password:\n");
        System.out.println(
                "name       = " + name + "\n" +
                "folder     = " + folderName + "\n" +
                "NUS Net ID = " + nusNetId + "\n" +
                "password   = " + HIDDEN_FIELD);
    }

    /**
     * Prompts the user to enter their NUS Net ID.
     *
     * @return the user's NUS Net ID string input
     */
    public String inquireNusNetId() throws OperationCancelException {
        nusNetId = inquire(InquiryMessages.NUSNET_ID, "NUSNet ID");
        while (!NUSNet.isLegalId(nusNetId)) {
            nusNetId = inquire(InquiryMessages.NUSNET_ID_RETRY, "NUSNet ID");
        }
        return nusNetId;
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
