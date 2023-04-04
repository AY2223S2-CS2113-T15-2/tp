package seedu.duke.secrets;

import seedu.duke.Backend;
import seedu.duke.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;

/**
 * Represents a credit card entry in the user's secrets list.
 * Stores the credit card's name, full name, credit card number, CVC number and expiry date.
 * Credit card numbers are expected to be a string of 16 digits, while expiry dates are expected to be in the format
 * "MM/YY".
 */
public class CreditCard extends Secret {
    private static final String EXPIRY_DATE_FMT = "[0-1][0-9]/[0-3][0-9]";
    private static final String CREDIT_CARD_NUMBER_FMT = "\\d{16}";

    private static final String CVC_NUMBER_FMT = "\\d{3}";
    private String fullName;
    private String creditCardNumber;
    private String cvcNumber;
    private String expiryDate;

    /**
     * Constructor for a CreditCard object.
     *
     * @param name Name of the credit card.
     * @param fullName Full name of the credit card owner.
     * @param creditCardNumber 16-digit credit card number.
     * @param cvcNumber 3-digit CVC number of the credit card.
     * @param expiryDate Expiry date of the credit card in the format "MM/YY".
     * @throws InvalidExpiryDateException If the expiry date is not in the format "MM/YY".
     * @throws InvalidCreditCardNumberException If the credit card number is not a string of 16 digits.
     */
    public CreditCard(String name, String fullName,
                      String creditCardNumber,
                      String cvcNumber, String expiryDate) throws
            InvalidExpiryDateException, InvalidCreditCardNumberException {


        super(name);
        this.fullName = fullName;
        this.creditCardNumber = creditCardNumber;
        if (!creditCardNumber.matches(CREDIT_CARD_NUMBER_FMT)) {
            throw new InvalidCreditCardNumberException();
        }
        this.cvcNumber = cvcNumber;
        if (!expiryDate.matches(EXPIRY_DATE_FMT)) {
            throw new InvalidExpiryDateException();
        }
        this.expiryDate = expiryDate;

    }

    /**
     * Constructor for a CreditCard object in a specific folder.
     *
     * @param name Name of the credit card.
     * @param folderName Name of the folder containing the credit card.
     * @param fullName Full name of the credit card owner.
     * @param creditCardNumber 16-digit credit card number.
     * @param cvcNumber 3-digit CVC number of the credit card.
     * @param expiryDate Expiry date of the credit card in the format "MM/YY".
     * @throws InvalidExpiryDateException If the expiry date is not in the format "MM/YY".
     */
    public CreditCard(String name, String folderName,
                      String fullName, String creditCardNumber,
                      String cvcNumber, String expiryDate) throws InvalidExpiryDateException {
        super(name, folderName);
        this.fullName = fullName;
        this.creditCardNumber = creditCardNumber;
        this.cvcNumber = cvcNumber;
        if (!isLegalExpiryDate(expiryDate)) {
            throw new InvalidExpiryDateException();
        }
        this.expiryDate = expiryDate;
    }

    public static boolean isLegalExpiryDate(String expiryDate) {
        return expiryDate.matches(EXPIRY_DATE_FMT);
    }

    public static boolean isLegalCreditCardNumber(String creditCardNumber) {
        return creditCardNumber.matches(CREDIT_CARD_NUMBER_FMT);
    }

    public static boolean isLegalCvcNumber(String number) {
        return number.matches(CVC_NUMBER_FMT);
    }

    /**
     * Returns the expiry date of the credit card.
     *
     * @return Expiry date of the credit card in the format "MM/YY".
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the credit card.
     *
     * @param expiryDate Expiry date of the credit card in the format "MM/YY".
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the full name associated with the credit card.
     *
     * @return the full name associated with the credit card
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name associated with the credit card.
     *
     * @param fullName the full name to be set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the credit card number.
     *
     * @return A String representing the credit card number.
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * Sets the credit card number.
     *
     * @param creditCardNumber A String representing the credit card number to be set.
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * Returns the CVC number of the credit card.
     *
     * @return the CVC number
     */
    public String getCvcNumber() {
        return cvcNumber;
    }

    /**
     * Sets the CVC number of the credit card.
     *
     * @param cvcNumber the new CVC number
     */
    public void setCvcNumber(String cvcNumber) {
        this.cvcNumber = cvcNumber;
    }

    /**
     * Returns a string representation of the credit card, including its credit card number,
     * CVC number, and expiry date.
     *
     * @return a string representation of the credit card
     */
    @Override
    public String getRevealStr() {
        return String.format("Credit Card Number: %s\n" +
                "CVC Number: %s\n" +
                "Expiry Date: %s", creditCardNumber, cvcNumber,
                expiryDate);
    }

    @Override
    public String toStringForDatabase() {
        String formattedString =  "CreditCard," + super.toStringForDatabase() +
                "," + this.fullName + "," + Backend.encode(this.creditCardNumber) + "," +
                Backend.encode("" + this.cvcNumber) + "," + this.expiryDate;
        return formattedString;
    }
}
