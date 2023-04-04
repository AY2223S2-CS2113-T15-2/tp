package seedu.duke.secrets;

import seedu.duke.Backend;

/**
 * Class to represent a Wi-Fi password and its associated information.
 */
public class WifiPassword extends Secret{
    private String username;
    private String password;

    /**
     * Constructs a new Wi-Fi password object with the specified name, username (optional), and password.
     *
     * @param name the name of the Wi-Fi network
     * @param username the username (optional) used to connect to the Wi-Fi network
     * @param password the password used to connect to the Wi-Fi network
     */
    public WifiPassword(String name, String username,
                        String password) {
        super(name);
        // username can be null
        this.password = password;
        this.username = username;
    }

    /**
     * Constructs a new Wi-Fi password object with the specified name, folder name, username (optional), and password.
     *
     * @param name the name of the Wi-Fi network
     * @param folderName the name of the folder containing the Wi-Fi network
     * @param username the username used to connect to the Wi-Fi network
     * @param password the password used to connect to the Wi-Fi network
     */
    public WifiPassword(String name, String folderName, String username,
                         String password) {
        super(name, folderName);
        this.password = password;
        this.username = username;
    }

    /**
     * Returns the username used to connect to the Wi-Fi network.
     *
     * @return the username used to connect to the Wi-Fi network
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username used to connect to the Wi-Fi network.
     *
     * @param username the username used to connect to the Wi-Fi network
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Removes the username used to connect to the Wi-Fi network.
     */
    public void removeUsername() {
        username = null;
    }

    /**
     * Returns the password used to connect to the Wi-Fi network.
     *
     * @return the password used to connect to the Wi-Fi network
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password used to connect to the Wi-Fi network.
     *
     * @param password the password used to connect to the Wi-Fi network
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the Wi-Fi password object, including the password.
     *
     * @return a string representation of the Wi-Fi password object, including the password
     */
    @Override
    public String getRevealStr() {
        return String.format("Password: %s", password);
    }

    @Override
    public String toStringForDatabase() {
        String formattedString =  "wifiPassword," + super.toStringForDatabase() +
                "," + Backend.encode(this.username) + "," + Backend.encode(this.password);
        return formattedString;
    }
}
