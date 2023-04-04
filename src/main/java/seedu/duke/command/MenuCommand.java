package seedu.duke.command;

import seedu.duke.storage.SecretMaster;

/**
 * Class that displays the list of all available commands in a menu format.
 */
public class MenuCommand extends Command {

    /**
     * Displays the list of all available commands in a menu format.
     */
    public void displayMenu() {
        System.out.println("SecureNUS Command Menu:\n" +
                "1. Add a general password: new [PASSWORD_NAME] /f [FOLDER_NAME]\n" +
                "2. Add password for websites needing Credit Card details: " +
                "new o/CreditCard [NAME] /f [FOLDER_NAME]\n" +
                "3. Add password for websites needing Crypto Wallet details: " +
                "new o/CryptoWallet [NAME] /f [FOLDER_NAME]\n" +
                "4. Add password for websites needing NUSNet ID: " +
                "new o/NUSNet [NAME] /f [FOLDER_NAME]\n" +
                "5. Add passwords for websites needing NUS StudentID (starting with A): " +
                "new o/StudentID [NAME] /f [FOLDER_NAME]\n" +
                "6. Add passwords for websites needing Wifi Passwords: " +
                "new o/WifiPassword [NAME] /f [FOLDER_NAME]\n" +
                "7. Delete the unwanted password: delete\n" +
                "8. List all the passwords: list [f/FOLDER_NAME]\n" +
                "9. View password: view p/PASSWORD_NAME \n" +
                "10. Search for password: search n/NAME_LIKE [-f f/FOLDER_LIKE]\n" +
                "11. Save the password in .txt file: save\n" +
                "12. Load the password from the .txt file: load [-a, –append] [f/FILE_PATH]\n" +
                "(BUGGY, NOT COMPLETED) 13. Edit the passwords: edit p/PASSWORD_NAME [-f nf/NEW_FOLDER_NAME]\n " +
                "[-d nd/NEW_DESCRIPTION] [-N np/NEW_PASSWORD_NAME]\n" +
                "14. Menu list to refer for commands: menu\n" +
                "15. Exit from the password manager: exit\n"
        );
    }

    /**
     * Executes the menu command, which displays the list of all available commands.
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        displayMenu();
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
