package seedu.duke.command;

import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;
import seedu.duke.messages.Inquiries;
import seedu.duke.secrets.CreditCard;
import seedu.duke.storage.SecretMaster;

/**
 * A class to add a new credit card to the user's secureNUSData.
 * Inherits from AddSecretCommand, which contains the common attributes
 * and methods needed to add a secret to the user's secureNUSData.
 */
public class AddCreditCardCommand extends AddSecretCommand {
    private String name;
    private String folderName = "unnamed";
    private String fullName;
    private String creditCardNumber;
    private int cvcNumber;
    private String expiryDate;

    /**
     * Constructor for AddCreditCardCommand class.
     * Extracts the necessary information from the input string using the super constructor
     * and prompts the user for additional information.
     *
     * @param input User input command string.
     */
    public AddCreditCardCommand(String input) {
        super(input);
        fullName = inquire(Inquiries.FULLNAME);
        creditCardNumber = inquire(Inquiries.CREDIT_CARD_NUMBER);
        cvcNumber = Integer.parseInt(inquire(Inquiries.CVC_NUMBER));
        expiryDate = inquire(Inquiries.EXPIRY_DATE);
    }

    /**
     * Adds a new credit card to the user's secureNUSData.
     *
     * @param secureNUSData The user's secureNUSData object.
     * @throws RuntimeException if there is a repeated id or if there is an issue with the name or folder name.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {

        CreditCard creditCard = null;
        try {
            creditCard = new CreditCard(name,folderName,fullName, creditCardNumber,cvcNumber, expiryDate);
        } catch (InvalidExpiryDateException e) {
            throw new RuntimeException(e);
        }
        try {
            secureNUSData.addSecret(creditCard);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new RuntimeException(e);
        }
        String starsPassword = "********";
        System.out.println("I have added a new Credit Card:\n");
        System.out.println("Name     = " + name + "\n" +
                "Folder   = " + folderName + "\n" +
                "Full Name      = " + fullName + "\n");
    }

}
