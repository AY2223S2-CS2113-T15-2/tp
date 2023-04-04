package seedu.duke.secrets;
import java.util.regex.Pattern;

/**
 * Represents the basic "Password-like" class, which is intended to be the parent class of all future password classes.
 * This class provides the basic attributes and methods required to store and retrieve password-like information.
 */
public class Secret {

    /**
     * A regex pattern for matching illegal characters in password names.
     */
    private static final Pattern ILLEGAL_CHARS_PATTERN =
            Pattern.compile("^.*[~!@#$%^&*()_+=\\-`{}<>()\\[\\]|\"\\_].*$");

    /**
     * The folder name of the password. By default, it is set to "unnamed".
     */
    protected String folderName = "unnamed";

    /**
     * The unique identifier of the password.
     */
    private String uid = "";

    /**
     * The name of the password.
     */
    private String name = "";

    /**
     * Constructs a new password with the given name.
     *
     * @param name The name of the password.
     */
    public Secret(String name) {
        // Assumes that name is not illegal before creation
        this.name = name;
        uid = name; // current just a duplicated, can be changed later
    }

    /**
     *  Constructs a new password with the given name and folder name.
     *
     *  @param name The name of the password.
     *  @param folderName The folder name of the password.
     */
    public Secret(String name, String folderName) {
        // Assumes that name is not illegal before creation
        this.name = name;
        uid = name; // current just a duplicated, can be changed later
        this.folderName = folderName;
    }

    /**
     * Checks if the given name contains any illegal characters.
     *
     * @param name The name to be checked.
     * @return if the name contains any illegal characters, {@code false} otherwise.
     */
    public static boolean isIllegalName(String name) {
        return ILLEGAL_CHARS_PATTERN.matcher(name).matches();
    }

    /**
     * Returns the unique identifier of the password.
     *
     * @return The unique identifier of the password.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the name of the password.
     *
     * @param name The new name of the password.
     */
    public void setName(String name) {
        this.name = name;
        this.uid = name; // since they are linked now
    }

    /**
     * Returns the name of the password.
     *
     * @return The name of the password.
     */
    public String getName() {
        return name;
    }


    /**
     * Edits the name of the password to the given new name and updates the unique identifier accordingly.
     *
     * @param newName The new name of the password.
     */
    public void editName(String newName) {
        uid = newName;
        name = newName;
    }

    /**
     * Returns the folder name of the password.
     *
     * @return The folder name of the password.
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * Sets the folder name of the password to the given folder name.
     *
     * @param folderName The new folder name of the password.
     */
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    /**
     * Returns a string representation of the password, formatted for storage in the database.
     *
     * @return A string representation of the password, formatted for storage in the database.
     */
    public String toStringForDatabase() {
        return this.uid + "," + this.name + "," +
            this.folderName;
    }

    /**
     * Returns an empty string as there is no information to reveal in this Secret.
     *
     * @return An empty string
     */
    public String getRevealStr() {
        return "";
    }
}
