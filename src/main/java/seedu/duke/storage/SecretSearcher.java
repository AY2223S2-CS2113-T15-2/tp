package seedu.duke.storage;

import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.FolderNotEmptyException;
import seedu.duke.exceptions.secrets.FolderNotFoundException;
import seedu.duke.secrets.Secret;

import java.util.Hashtable;
import java.util.HashSet;

/**
 * A class that stores and retrieves Secret objects by their unique identifier (UID) using a hash table.
 * It also provides the ability to group secrets by folder.
 */
public class SecretSearcher {

    /**
     * A hash table that stores all Secret objects by their unique identifier (UID).
     */
    private final Hashtable<String, Secret> storage;

    /**
     * A hash table that stores secrets grouped by folder name.
     * Each folder name maps to another hash table that stores Secret objects by their UID.
     * This allows for efficient retrieval of secrets based on both their UID and folder name.
     */
    private final Hashtable<String, Hashtable<String, Secret>> folders;

    /**
     * Creates a new instance of the SecretSearcher class with an empty storage and folders.
     */
    public SecretSearcher() {
        storage = new Hashtable<String, Secret>();
        folders = new Hashtable<String, Hashtable<String, Secret>>();
    }

    /**
     * Creates a new instance of the SecretSearcher class with the given storage and folders.
     *
     * @param storage A hash table containing Secret objects to be stored in the search index.
     * @param folders A hash table containing folders and their respective Secret objects to be stored in the
     *                search index.
     */
    public SecretSearcher(Hashtable<String, Secret> storage, Hashtable<String, Hashtable<String, Secret>> folders) {
        this.storage = storage;
        this.folders = folders;
    }

    /**
     * Retrieves the Secret object with the given UID from the search index.
     *
     * @param secretId The UID of the Secret object to retrieve.
     * @return The Secret object with the given UID, or null if no Secret object with that UID exists in the
     *         search index.
     */
    public Secret get(String secretId) {
        return storage.get(secretId);
    }

    /**
     * Creates a new folder in the search index with the given folder name.
     *
     * @param folderName The name of the folder to create.
     * @throws FolderExistsException if a folder with the given name already exists in the search index.
     */
    public void createFolder(String folderName) throws FolderExistsException {
        if (folders.containsKey(folderName)) {
            throw new FolderExistsException();
        }
        folders.put(folderName,
                new Hashtable<String, Secret>());
    }

    /**
     * Deletes a folder from the search index with the given folder name.
     *
     * @param folderName The name of the folder to delete.
     * @throws FolderNotFoundException if a folder with the given name does not exist in the search index.
     * @throws FolderNotEmptyException if the folder to be deleted is not empty.
     */
    public void deleteFolder(String folderName) throws FolderNotFoundException, FolderNotEmptyException {
        if (!folders.containsKey(folderName)) {
            throw new FolderNotFoundException();
        }
        if (!folders.get(folderName).isEmpty()) {
            throw new FolderNotEmptyException();
        }
        folders.remove(folderName);
    }

    /**
     * Adds a Secret object to the search index.
     *
     * @param secret The Secret object to add to the search index.
     * @throws FolderExistsException if the folder specified by the Secret object does not exist in the search index.
     */
    public void add(Secret secret) throws FolderExistsException {
        storage.put(secret.getUid(), secret);
        // creates a folder if it doesn't already exist (MIGHT remove in future)
        if (!folders.containsKey(secret.getFolderName())) {
            createFolder(secret.getFolderName());
        }
        folders.get(secret.getFolderName()).put(secret.getUid(), secret);
    }

    /**
     * Deletes the given Secret object from the storage and the folders Hashtable.
     *
     * @param secret the Secret object to delete
     */
    public void delete(Secret secret) {
        storage.remove(secret.getUid());
        folders.get(secret.getFolderName()).remove(secret.getUid());
        // delete folder if it is empty
        if (folders.get(secret.getFolderName()).isEmpty()) {
            folders.remove(secret.getFolderName());
        }
    }

    /**
     * Returns a HashSet containing all the secret names in the storage.
     *
     * @return a HashSet of strings containing all the secret names in the storage
     */
    public HashSet getNames() {
        HashSet<String> nameHashset = new HashSet();
        for (String name : this.storage.keySet()) {
            nameHashset.add(name);
        }
        return nameHashset;
    }
}
