package seedu.duke;

import java.util.Scanner;

/**
 * Ui class to handle all user interface printing.
 */
public class Ui {
    private static Scanner in = new Scanner(System.in);

    /**
     * Greets the user upon start up of the application.
     */
    public static void greetUser() {
        System.out.println("Welcome to secureNUS v1.0\n" +
                "Current Features\n" +
                "Adding a password      : new [NAME] /f [FOLDER_NAME]\n" +
                "Adding a NUSNet ID     : new o/NUSNet [NAME] /f [FOLDER_NAME]\n" +
                "Adding a Student ID    : new o/StudentID [NAME] /f [FOLDER_NAME] \n");
    }

    /**
     * Prints a horizontal line to separate UI elements.
     */
    public static void printLine() {
        System.out.print("_____________________________________________________\n");
    }

    public static void print(String message) {
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
            return in.nextLine();
        }
        return "";
    }

    public static String readLine() {
        if (in.hasNextLine()) {
            return in.nextLine();
        }
        return "";
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

    public static void close() {
        in.close();
    }
}
