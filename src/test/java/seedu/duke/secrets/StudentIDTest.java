package seedu.duke.secrets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for StudentID class in seedu.duke.secrets package.
 */
public class StudentIDTest {

    /**
     * Test for creating a StudentID object with no folder name.
     */
    @Test
    public void studentIDTestNoFolder() {
        StudentID studentID = new StudentID("StudentID Name","A021313J");
        assertEquals("StudentID Name", studentID.getName());
        assertEquals("unnamed", studentID.getFolderName());
        assertEquals("A021313J", studentID.getStudentID());
    }

    /**
     * Test for creating a StudentID object with a folder name.
     */
    @Test
    public void studentIDTestFolder() {
        StudentID studentID = new StudentID("StudentID2 Name", "StudentsOfNUS", "A021313G");
        assertEquals("StudentID2 Name", studentID.getName());
        assertEquals("StudentsOfNUS", studentID.getFolderName());
        assertEquals("A021313G", studentID.getStudentID());
    }

}
