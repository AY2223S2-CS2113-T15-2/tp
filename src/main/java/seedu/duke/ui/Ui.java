package seedu.duke.ui;

import seedu.duke.messages.OperationMessages;

import java.util.Scanner;

/**
 * Ui class to handle all user interface printing.
 */
public class Ui {
    public static final String DUPLICATE_WHITESPACE_FMT = "\\s+";

    public static Scanner in = new Scanner(System.in);

    /**
     * Greets the user upon start up of the application.
     */
    public static void greetUser() {
        System.out.println(OperationMessages.INITIALISE);
    }

    /**
     * Prints a horizontal line to separate UI elements.
     */
    public static void printLine() {
        System.out.print("_____________________________________________________\n");
    }

    public static void inform(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Reads the user's command from the console.
     *
     * @return String containing user's command.
     */
    public static String readCommand() {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            return removeExtraWhiteSpaces(line);
        }
        return "";
    }

    public static String readLine() {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            return line;
        }
        return "";
    }

    public static String removeExtraWhiteSpaces(String line) {
        // remove duplicate whitespaces
        line = line.replaceAll(DUPLICATE_WHITESPACE_FMT, " ");
        return line.trim(); // remove leading and trailing whitespaces
    }

    // TODO can this be removed with logging?
    /**
     * Prints error message to console.
     *
     * @param message Error message to be printed.
     */
    public static void printError(String message) {
        System.out.println("Oops! Error encountered "+ message);
    }
    public static void informOperationCancel () {
        System.out.println(OperationMessages.CANCEL_OPERATION);
    }

    public static void close() {
        in.close();
    }
}
