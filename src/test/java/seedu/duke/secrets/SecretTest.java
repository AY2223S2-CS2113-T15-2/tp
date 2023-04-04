package seedu.duke.secrets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for the Secret class.
 */
class SecretTest {

    /**
     * Test for the isIllegalName() method.
     * This method checks if the method returns true for all weird characters.
     */
    @Test
    void isIllegalName_checkWeirdChars() {
        String[] names = {"$", "#", "!", "@", "%", "^",
            "&", "*", "(", ")", "~", "`", "-", "+", "="};
        for (String name: names) {
            assertEquals(true, Secret.isIllegalName(name));
        }
        assertEquals(false, Secret.isIllegalName("Garena Login"));
    }

    /**
     * Test for the getUid() and getName() methods.
     * This method checks if both methods return the same value.
     */
    @Test
    void getUidAndName() {
        Secret secret = new Secret("name", "folder");
        assertEquals("name", secret.getUid());
        assertEquals("name", secret.getName());
    }

    /**
     * Test for the getFolderName() method.
     * This method checks if the correct folder name is returned.
     */
    @Test
    void getFolder() {
        Secret secret = new Secret("name", "folder");
        assertEquals("folder", secret.getFolderName());
    }

    /**
     * Test for the editName() method.
     * This method checks if the name and uid are changed correctly.
     */
    @Test
    void editName() {
        Secret secret = new Secret("name", "folder");
        secret.editName("newName");
        assertEquals("newName", secret.getUid());
        assertEquals("newName", secret.getName());
        assertEquals("folder", secret.getFolderName());
    }

    /**
     * Test for the setFolderName() method.
     * This method checks if the folder name is changed correctly.
     */
    @Test
    void setFolderName() {
        Secret secret = new Secret("name", "folder");
        secret.setFolderName("newFolder");
        assertEquals("name", secret.getUid());
        assertEquals("name", secret.getName());
        assertEquals("newFolder", secret.getFolderName());
    }

    /**
     * Test for the getRevealStr() method.
     * This method checks if the reveal string is empty for a newly created secret.
     */
    @Test
    void testRevealOutput() {
        Secret secret = new Secret("name", "folder");
        assertEquals("", secret.getRevealStr());
        Secret secret2 = new Secret("name");
        assertEquals("", secret2.getRevealStr());
    }
}
