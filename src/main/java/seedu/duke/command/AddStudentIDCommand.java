package seedu.duke.command;

import seedu.duke.exceptions.ExceptionMain;
import seedu.duke.exceptions.OperationCancelException;
import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.messages.InquiryMessages;
import seedu.duke.secrets.StudentID;
import seedu.duke.storage.SecretMaster;

import java.util.HashSet;

/**
 * Represents the class to give a command to add a new Student ID to the SecureNUS system.
 */
public class AddStudentIDCommand extends AddSecretCommand {
    public static final String KEYWORD = "o/StudentID";
    private String studentId;

    /**
     * Constructs an AddStudentIDCommand object with the user input as the command parameter.
     *
     * @param input The command input entered by the user.
     */
    public AddStudentIDCommand(String input, HashSet<String> usedNames) throws IllegalFolderNameException,
            IllegalSecretNameException, RepeatedIdException, OperationCancelException {
        super(input, usedNames, KEYWORD);
        this.studentId = inquireStudentID();
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
    public void execute(SecretMaster secureNUSData) throws ExceptionMain {
        StudentID studentIdData = new StudentID(name,folderName,studentId);
        try {
            secureNUSData.addSecret(studentIdData);
        } catch (RepeatedIdException e) {
            throw new RuntimeException(e);
        } catch (FolderExistsException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (IllegalSecretNameException e) {
            throw new ExceptionMain(e.getMessage());
        } catch (IllegalFolderNameException e) {
            throw new ExceptionMain(e.getMessage());
        }
        System.out.println("I have added a new Student ID:\n");
        System.out.println(
                "name       = " + name + "\n" +
                "Folder     = " + folderName + "\n" +
                "Student ID = " + HIDDEN_FIELD);
    }


    /**
     * Prompts the user to enter the Student ID.
     *
     * @return The Student ID entered by the user.
     */
    public String inquireStudentID() throws OperationCancelException {
        String studentID = inquire(InquiryMessages.STUDENT_ID, "Student ID");
        while (!StudentID.isLegalId(studentID)) {
            studentID = inquire(InquiryMessages.STUDENT_ID_RETRY, "Student ID");
        }
        return studentID;
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
