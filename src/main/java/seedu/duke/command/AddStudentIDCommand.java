package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.secrets.StudentID;
import seedu.duke.storage.SecretMaster;

/**
 * Represents the class to give a command to add a new Student ID to the SecureNUS system.
 */
public class AddStudentIDCommand extends Command {
    private String name;
    private String folderName;
    private String studentId;

    /**
     * Constructs an AddStudentIDCommand object with the user input as the command parameter.
     *
     * @param input The command input entered by the user.
     */
    public AddStudentIDCommand(String input) {
        this.name = extractName(input);
        this.folderName = extractFolderName(input);
        this.studentId = inquireStudentID();
        if (this.name == null) {
            this.name = studentId;
        }
    }
    public AddStudentIDCommand(StudentID studentID) {
        this.name = studentID.getName();
        this.folderName = studentID.getFolderName();
        this.studentId = studentID.getStudentID();
    }

    /**
     * Executes the AddStudentIDCommand to add a new Student ID to the SecureNUS system.
     *
     * @param secureNUSData
     */
    @Override
    public void execute(SecretMaster secureNUSData) {
        StudentID studentIdData = new StudentID(name,folderName,studentId);
        try {
            secureNUSData.addSecret(studentIdData);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException | IllegalSecretNameException | IllegalFolderNameException e) {
            throw new RuntimeException(e);
        }
        System.out.println("I have added a new Student ID:\n");
        System.out.println(
                "name       = " + name + "\n" +
                "Student ID = " + studentId);
    }

    /**
     * Extracts the name of the Student ID from the user input.
     *
     * @param input The command input entered by the user.
     * @return The name of the Student ID.
     */

    public String extractName(String input) {
        String[] extractedNames = input.split("o/StudentID ");
        String extractedName;
        if (extractedNames.length == 2) {
            if (extractedNames[1].split(" /f").length > 1) {
                extractedName = extractedNames[1].split(" /f")[0];
            } else {
                extractedName = null;
            }
        } else {
            extractedName = null; //Default Name
        }
        return extractedName;
    }

    /**
     * Extracts the folder name of the Student ID from the user input.
     *
     * @param input The command input entered by the user.
     * @return The folder name of the Student ID.
     */
    public String extractFolderName(String input) {
        String extractedFolderName = "unnamed";
        if (input.split("/f ").length > 1) {
            extractedFolderName = input.split("/f ")[1];
        }
        return extractedFolderName;
    }

    /**
     * Prompts the user to enter the Student ID.
     *
     * @return The Student ID entered by the user.
     */
    public String inquireStudentID() {
        System.out.println("Please enter your Student ID: ");
        String studentID = Ui.readCommand();
        return studentID;
    }

    /**
    * Extracts the URL of the Student ID from the user input.
    *
    * @param input The command input entered by the user.
    */
    public String extractURL(String input) {
        return "";
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, as it is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
