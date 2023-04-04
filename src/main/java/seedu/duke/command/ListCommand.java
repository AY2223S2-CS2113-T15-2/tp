package seedu.duke.command;

import seedu.duke.exceptions.secrets.NonExistentFolderException;
import seedu.duke.secrets.Secret;
import seedu.duke.storage.SecretMaster;

import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.CreditCard;
import seedu.duke.secrets.CryptoWallet;
import seedu.duke.secrets.NUSNet;
import seedu.duke.secrets.StudentID;
import seedu.duke.secrets.WifiPassword;


import java.util.ArrayList;

/**
 * The ListCommand class represents the command to list all secrets in a specific folder or all secrets if no folder
 * is specified.
 */
public class ListCommand extends Command {

    private final String folderName;

    /**
     * Constructs a new ListCommand object with the given input.
     *
     * @param input the input string containing the folder name, if specified.
     */
    public ListCommand(String input) {
        this.folderName = extractFolderName(input);
    }

    /**
     * Extracts the folder name from the input string.
     *
     * @param input the input string containing the folder name, if specified.
     * @return the folder name.
     */
    public String extractFolderName(String input) {
        String extractedFolderName = "unnamed";
        if (input.split(" ").length > 1) {
            extractedFolderName = input.split(" ")[1];
        }
        return extractedFolderName;
    }

    /**
     * Returns the type and details of the given secret.
     *
     * @param secret the secret to get the type and details of.
     * @return return the type and details of the secret.
     */
    @SuppressWarnings("checkstyle:LocalVariableName")
    private String getSecretTypeInfo(Secret secret) {
        if (secret instanceof BasicPassword) {
            BasicPassword basicPassword = (BasicPassword) secret;
            return
                    "Type of Secret: Basic Password" + "\n" +
                            "Name: " + basicPassword.getName() + "\n" +
                            "Folder: " + basicPassword.getFolderName() + "\n" +
                            "Username: " + basicPassword.getUsername() + "\n" +
                            "URL: " + basicPassword.getUrl() + "\n" +
                            "Password: " + basicPassword.getPassword() + "\n";

        } else if (secret instanceof CreditCard) {
            CreditCard creditCard = (CreditCard) secret;
            return "Type of Secret: Credit Card" + "\n" +
                    "Name: " + creditCard.getName() + "\n" +
                    "Folder: " + creditCard.getFolderName() + "\n" +
                    "Full Name: " + creditCard.getFullName() + "\n" +
                    "Credit Card Number: " + creditCard.getCreditCardNumber() + "\n" +
                    "CVC Number: " + creditCard.getCvcNumber() + "\n" +
                    "Expiry Date: " + creditCard.getExpiryDate()+ "\n";

        } else if (secret instanceof CryptoWallet) {
            CryptoWallet cryptoWallet = (CryptoWallet) secret;
            return "Type of Secret: CryptoCurrency Wallet" + "\n" +
                    "Name: " + cryptoWallet.getName() + "\n" +
                    "Folder: " + cryptoWallet.getFolderName() + "\n" +
                    "Username: " + cryptoWallet.getUsername() + "\n" +
                    "Private Key: " + cryptoWallet.getPrivateKey() + "\n" +
                    "Seed Phrase: " + cryptoWallet.getSeedPhrase()+ "\n";

        } else if (secret instanceof NUSNet) {
            NUSNet nusNet = (NUSNet) secret;
            return "Type of Secret: NUSNet ID" + "\n" +
                    "Name: " + nusNet.getName() + "\n" +
                    "Folder: " + nusNet.getFolderName() + "\n" +
                    "NUSNet ID: " + nusNet.getnusNetId() + "\n" +
                    "Password: " + nusNet.getPassword() + "\n";


        } else if (secret instanceof StudentID) {
            StudentID studentID = (StudentID) secret;
            return "Type of Secret: Student ID" + "\n" +
                    "Name: " + studentID.getName() + "\n" +
                    "Folder: " + studentID.getFolderName() + "\n" +
                    "Student ID: " + studentID.getStudentID()+ "\n";

        } else if (secret instanceof WifiPassword) {
            WifiPassword wifiPassword = (WifiPassword) secret;
            return "Type of Secret: Wifi Password" + "\n" +
                    "Name: " + wifiPassword.getName() + "\n" +
                    "Folder: " + wifiPassword.getFolderName() + "\n" +
                    "Username: " + wifiPassword.getUsername() + "\n" +
                    "Password: " + wifiPassword.getPassword()+ "\n";

        } else {
            return "Secret";
        }
    }

    /**
     * Executes the ListCommand by listing all secrets or secrets in a specific folder.
     *
     * @param secureNUSData the SecretMaster object to execute the command on
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        ArrayList<Secret> secrets;
        try {
            if (folderName.equals("unnamed")) {
                secrets = secureNUSData.listSecrets();
            } else {
                secrets = secureNUSData.listSecrets(folderName);
            }
            if (secrets.isEmpty()) {
                System.out.println("There are no secrets in this folder.");
                return;
            }
            System.out.println("List of secrets:");
            int counter = 1;
            for (Secret secret : secrets) {
                String secretTypeInfo = getSecretTypeInfo(secret);
                System.out.println(counter + ". " + secretTypeInfo);
                counter += 1;
            }
        } catch (NonExistentFolderException e) {
            System.out.println("Folder " + folderName + " does not exist.");
        }
    }

    /**
     * Returns false the ListCommand is an exit command which shows that the code does not exit when list is called
     *
     * @return always false for ListCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
