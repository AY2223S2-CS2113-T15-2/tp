package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.InvalidCreditCardNumberException;
import seedu.duke.exceptions.secrets.InvalidExpiryDateException;
import seedu.duke.exceptions.secrets.InvalidURLException;
import seedu.duke.secrets.BasicPassword;
import seedu.duke.secrets.CreditCard;
import seedu.duke.secrets.CryptoWallet;
import seedu.duke.secrets.StudentID;
import seedu.duke.secrets.WifiPassword;
import seedu.duke.secrets.NUSNet;

/**
 * JUnit Test for the SecretSearcher() Class
 */
class SecretSearcherTest {
    @Test
    void addAllPasswords() throws FolderExistsException, InvalidExpiryDateException, InvalidCreditCardNumberException,
            InvalidURLException {
        SecretSearcher secretSearcher = new SecretSearcher();
        secretSearcher.add(new BasicPassword("basic1", "username1", "Password1",
                "http.com"));
        secretSearcher.add(new CreditCard("credit1", "HJ HJ UI", "1234567890123456", "123",
                "12/23"));
        secretSearcher.add(new CryptoWallet("crypto1", "hjhbj", "fdertyuiytyui876ytfgyuit5rt",
                "hb jnjkm kjijh ijhui hjhb iujh uhbgv gfcd"));
        secretSearcher.add(new NUSNet("nusnet1", "folder1", "e0987654", "oitfghjmjh"));
        secretSearcher.add(new StudentID("hi", "T0987490A"));
        secretSearcher.add(new WifiPassword("wifi1", "username1", "password1"));
    }
}
