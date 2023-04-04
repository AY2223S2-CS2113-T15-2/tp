package seedu.duke.secrets;

/**
 * This class represents a student ID secret.
 * It inherits from the Secret class.
 */
public class StudentID extends Secret {
    private static final String ACCEPTED_ID_REGEX = "A[0-9]{7}[A-Z]";
    private String studentID;

    /**
     * Constructs a new StudentID object with the specified name and student ID.
     *
     * @param name the name of the student ID secret
     * @param studentID the student ID number
     */
    public StudentID(String name, String studentID) {
        super(name);
        this.studentID = studentID;
    }

    /**
     * Constructs a new StudentID object with the specified name, folder name, and student ID.
     *
     * @param name the name of the student ID secret
     * @param folderName the folder name of the student ID secret
     * @param studentID the student ID number
     */
    public StudentID(String name, String folderName, String studentID) {
        super(name, folderName);
        this.studentID = studentID;
    }

    public static boolean isLegalId(String studentId) {
        return studentId.matches(ACCEPTED_ID_REGEX);
    }

    /**
     * Returns the student ID number.
     *
     * @return the student ID number
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Returns a string representation of the StudentID object for storing in the database.
     *
     * @return a string representation of the StudentID object
     */
    @Override
    public String toStringForDatabase() {
        String formattedString =  "studentID," + super.toStringForDatabase() +
            "," + this.studentID;
        return formattedString;
    }

    /**
     * Returns a string representation of the StudentID object.
     *
     * @return a string representation of the StudentID object
     */
    @Override
    public String getRevealStr() {
        return String.format("Name: %s\n" +
                        "StudentID: %s",
                getName(), studentID);
    }
}
