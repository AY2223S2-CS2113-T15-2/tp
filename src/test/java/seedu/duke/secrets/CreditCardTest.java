package seedu.duke.secrets;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit tests for the CreditCard class.
 */

class CreditCardTest {

    /**
     * Test without creation of folder
     * @throws InvalidExpiryDateException if the expiry date is of incorrect format
     * @throws InvalidCreditCardNumberException when an invalid credit card number is detected during validation
     */
    @Test
    void noFolderCreationTest () throws InvalidExpiryDateException, InvalidCreditCardNumberException {
        CreditCard creditCard = new CreditCard("creditCard1",
                "John Doe Lim Guang", "1234567890123456", 123,
                "12/23");
        assertEquals("unnamed", creditCard.getFolderName().toString());
        assertEquals("John Doe Lim Guang", creditCard.getFullName());
        assertEquals("1234567890123456", creditCard.getCreditCardNumber());
        assertEquals("creditCard1", creditCard.getUid());
        assertEquals("creditCard1", creditCard.getName());
        assertEquals("12/23", creditCard.getExpiryDate());
    }

    /**
     * Test with creation of folder.
     */
    void withFolderCreationTest () throws InvalidExpiryDateException {
        CreditCard creditCard = new CreditCard("creditCard1", "folder1",
                "John Doe Lim Guang", "12341567890123456", 123,
                "12/23");
        assertEquals("folder1", creditCard.getFolderName());
        assertEquals("John Doe Lim Guang", creditCard.getFullName());
        assertEquals("12341567890123456", creditCard.getCreditCardNumber());
        assertEquals("creditCard1", creditCard.getUid());
        assertEquals("creditCard1", creditCard.getName());
        assertEquals("12/23", creditCard.getExpiryDate());
    }

    /**
     * Test legal dates.
     */
    void legalDatesTest() throws InvalidExpiryDateException {
        assertThrows(InvalidExpiryDateException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "1234567890123456", 123,
                    "22/23");
            }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "1234567890123456", 123,
                    "12/43");
            }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "1234567890123456", 123,
                    "123/23");
            }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "1234567890123456", 123,
                    "12/53");
            }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "1234567890123456", 123,
                    "12/g");
            }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "1234567890123456", 123,
                    "kfc");
            }
        );
        assertThrows(InvalidExpiryDateException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "1234567890123456", 123,
                    "@#/$%");
            }
        );

        assertThrows(InvalidCreditCardNumberException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "1234r567890123456", 123,
                    "@#/$%");
            }
        );
        assertThrows(InvalidCreditCardNumberException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "12341567890123456", 123,
                    "@#/$%");
            }
        );

        assertThrows(InvalidCreditCardNumberException.class, () -> {
            new CreditCard("creditCard1", "folder1",
                    "John Doe Lim Guang", "rtyuiohgfghjkjhgffghuytres", 123,
                    "@#/$%");
            }
        );
    }


    /**
     * Test the getRevealStr method of the CreditCard class
     * @throws InvalidExpiryDateException if the expiry date is invalid
     * @throws InvalidCreditCardNumberException if the credit card number is invalid
     */
    @Test
    void testRevealOutput() throws InvalidExpiryDateException, InvalidCreditCardNumberException {
        CreditCard creditCard = new CreditCard("creditCard1", "folder1",
                "John Doe Lim Guang", "1234567890123456", 123,
                "12/23");
        assertEquals("Credit Card Number: 1234567890123456\n" +
                "CVC Number: 123\n" +
                "Expiry Date: 12/23", creditCard.getRevealStr());
        CreditCard creditCard2 = new CreditCard("creditCard1",
                "John Doe Lim Guang", "1234567890123456", 123,
                "12/23");
        assertEquals("Credit Card Number: 1234567890123456\n" +
                "CVC Number: 123\n" +
                "Expiry Date: 12/23", creditCard2.getRevealStr());
    }
}
