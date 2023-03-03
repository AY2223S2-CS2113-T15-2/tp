package seedu.duke.storage;

import seedu.duke.exceptions.FolderExistsException;
import seedu.duke.exceptions.FolderNotEmptyException;
import seedu.duke.exceptions.FolderNotFoundException;
import seedu.duke.passwords.Secret;

import java.util.ArrayList;
import java.util.Hashtable;

public class SecretSearcher {
    private final Hashtable<String, Secret> storage =
            new Hashtable<String, Secret>();

    // index using folder (FOR FUTURE FIND LIKE WITH FOLDER)
    private final Hashtable<String, Hashtable<String, Secret>> folders =
            new Hashtable<String, Hashtable<String, Secret>>();

    public Secret get(String secretId) {
        return storage.get(secretId);
    }

    public void createFolder(String folderName) throws FolderExistsException {
        if (folders.containsKey(folderName)) {
            throw new FolderExistsException();
        }
        folders.put(folderName,
                new Hashtable<String, Secret>());
    }
    public void deleteFolder(String folderName) throws FolderNotFoundException, FolderNotEmptyException {
        if (!folders.containsKey(folderName)) {
            throw new FolderNotFoundException();
        }
        if (!folders.get(folderName).isEmpty()) {
            throw new FolderNotEmptyException();
        }
        folders.remove(folderName);
    }
    public void add(Secret secret) throws FolderExistsException {
        storage.put(secret.getUid(), secret);
        // creates a folder if it doesn't already exist (MIGHT remove in future)
        if (!folders.containsKey(secret.getFolderName())) {
            createFolder(secret.getFolderName());
        }
        folders.get(secret.getFolderName()).put(secret.getUid(), secret);
    }

    public void delete(Secret secret) {
        storage.remove(secret.getUid());
        folders.get(secret.getFolderName()).remove(secret.getUid());
        // delete folder if it is empty
        if (folders.get(secret.getFolderName()).isEmpty()) {
            folders.remove(secret.getFolderName());
        }
    }
}
