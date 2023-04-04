package seedu.duke.secrets;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test for NUSNet class.
 */
public class NUSNetTest {

    /**
     * Test NUSNet creation without folder.
     */
    @Test
    public void nusNetNoFolder() {
        NUSNet nusNet = new NUSNet("NUSNet Name", "e088888@u.nus.edu","aSecurePassword2");
        assertEquals("NUSNet Name", nusNet.getName());
        assertEquals("unnamed", nusNet.getFolderName());
        assertEquals("e088888@u.nus.edu", nusNet.getnusNetId());
        assertEquals("aSecurePassword2", nusNet.getPassword());
    }

    /**
     * Test NUSNet creation with folder.
     */
    @Test
    public void nusNetFolder() {
        NUSNet nusNet = new NUSNet("NUSNet Name 2", "FolderName", "e081888@u.nus.edu", "Lorem Ipsum 12");
        assertEquals("NUSNet Name 2", nusNet.getName());
        assertEquals("FolderName", nusNet.getFolderName());
        assertEquals("e081888@u.nus.edu", nusNet.getnusNetId());
        assertEquals("Lorem Ipsum 12", nusNet.getPassword());
    }

}
