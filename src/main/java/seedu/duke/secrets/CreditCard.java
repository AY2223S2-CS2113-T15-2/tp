package seedu.duke.secrets;

import seedu.duke.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;

/**
 * Represents a credit card entry in the user's secrets list.
 * Stores the credit card's name, full name, credit card number, CVC number and expiry date.
 * Credit card numbers are expected to be a string of 16 digits, while expiry dates are expected to be in the format
 * "MM/YY".
 */
public class CreditCard extends Secret {
    private String fullName;
    private String creditCardNumber;
    private int cvcNumber;
    private String expiryDate;
    private final String expiryDateFmt = "[0-1][0-9]/[0-3][0-9]";
    private final String creditCardNumberFmt = "\\d{16}";

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
                      int cvcNumber, String expiryDate) throws
            InvalidExpiryDateException, InvalidCreditCardNumberException {


        super(name);
        this.fullName = fullName;
        this.creditCardNumber = creditCardNumber;
        if (!creditCardNumber.matches(creditCardNumberFmt)) {
            throw new InvalidCreditCardNumberException();
        }
        this.cvcNumber = cvcNumber;
        if (!expiryDate.matches(expiryDateFmt)) {
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
                      int cvcNumber, String expiryDate) throws InvalidExpiryDateException {
        super(name, folderName);
        this.fullName = fullName;
        this.creditCardNumber = creditCardNumber;
        this.cvcNumber = cvcNumber;
        if (!expiryDate.matches(expiryDateFmt)) {
            throw new InvalidExpiryDateException();
        }
        this.expiryDate = expiryDate;
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
    public int getCvcNumber() {
        return cvcNumber;
    }

    /**
     * Sets the CVC number of the credit card.
     *
     * @param cvcNumber the new CVC number
     */
    public void setCvcNumber(int cvcNumber) {
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
                "CVC Number: %d\n" +
                "Expiry Date: %s", creditCardNumber, cvcNumber,
                expiryDate);
    }
}
