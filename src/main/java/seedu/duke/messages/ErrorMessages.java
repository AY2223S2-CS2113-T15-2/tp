package seedu.duke.messages;

public class ErrorMessages {
    public static final String INVALID_COMMAND = "Invalid Command: Type 'menu' command to" +
            " see the list of usable commands";
    public static final String INVALID_EXPIRY_DATE = "Invalid Expiry Date: Provide something of the format: MM/YY";
    public static final String REPEATED_ID = "RepeatedId: This name has been used before. Use a different name";
    public static final String ILLEGAL_FOLDER_NAME = "Illegal Folder Name: Please use only alphanumeric numbers and _";
    public static final String ILLEGAL_SECRET_NAME = "Illegal Secret Name: Please use only alphanumeric numbers and _";
    public static final String FOLDER_EXISTS = "Folder Exists: This should not happen, please contact administrator " +
            "if you see this";
    public static final String INSUFFICIENT_PARAMS = "Insufficient Parameters: Add the required parameters for this " +
            "command!";
    public static final String INVALID_FIELD = "The input field {o/Field} does not exist! Try one that exists " +
            "from our menu.";
}
