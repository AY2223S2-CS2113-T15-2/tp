package seedu.duke.storage;

import seedu.duke.exceptions.secrets.FolderExistsException;
import seedu.duke.exceptions.secrets.IllegalSecretNameException;
import seedu.duke.exceptions.secrets.IllegalFolderNameException;
import seedu.duke.exceptions.secrets.NonExistentFolderException;
import seedu.duke.exceptions.secrets.SecretNotFoundException;
import seedu.duke.exceptions.RepeatedIdException;
import seedu.duke.secrets.Secret;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Master class that manages the storage and retrieval of secrets and folders.
 */
public class SecretMaster {
    // use for quick finding
    private static final String DEFAULT_FOLDER = "unnamed";
    private static final String ALLOWED_NAMES_REGEX = "^[a-zA-Z0-9_]*$"; // only alphanumeric allowed

    /**
     * Object that manages searching for secrets.
     */
    private SecretSearcher secretSearcher; // Hash Table

    /**
     * Object that manages enumerating secrets or listing secrets in the order it was added in.
     */
    private SecretEnumerator secretEnumerator; // Array view

    /**
     * HashSet that stores the names of folders and ensure folders and passwords are distinct.
     */
    private HashSet<String> folders;

    /**
     * HashSet that stores the names of secrets.
     */
    private HashSet<String> secretNames;

    /**
     * Constructor for the SecretMaster class.
     */
    public SecretMaster() {
        secretSearcher = new SecretSearcher();
        secretEnumerator = new SecretEnumerator();
        folders = new HashSet<String>();
        secretNames = new HashSet<String>();
    }

    /**
     * Constructor for the SecretMaster class that takes in existing SecretSearcher and SecretEnumerator objects.
     *
     * @param secretSearcher SecretSearcher object containing existing secret data.
     * @param secretEnumerator SecretEnumerator object containing existing secret data.
     */
    public SecretMaster(SecretSearcher secretSearcher, SecretEnumerator secretEnumerator) {
        this.secretSearcher = secretSearcher;
        this.secretEnumerator = secretEnumerator;
        this.folders = secretEnumerator.getFolders();
        this.secretNames = secretSearcher.getNames();
    }

    /**
     * Checks if a given name is a legal name conforming to the standards.
     *
     * @param name String name to check.
     * @return boolean indicating whether the name is legal.
     */
    public boolean isLegalName(String name) {
        return name.matches(ALLOWED_NAMES_REGEX);
    }

    /**
     * Retrieves the HashSet of folder names.
     *
     * @return HashSet of folder names.
     */
    public HashSet<String> getFolders() {
        return folders;
    }

    /**
     * Retrieves the SecretSearcher object.
     *
     * @return SecretSearcher object.
     */
    public SecretSearcher getSecretSearcher() {
        return secretSearcher;
    }

    /**
     * Retrieves the SecretEnumerator object.
     *
     *  @return SecretEnumerator object.
     */
    public SecretEnumerator getSecretEnumerator() {
        return secretEnumerator;
    }

    /**
     * Retrieves the HashSet of secret names.
     *
     * @return HashSet of secret names.
     */
    public HashSet<String> getSecretNames() {
        return secretNames;
    }

    /**
     * Creates a new folder with the given name.
     *
     * @param folderName String name of the folder to be created.
     * @throws FolderExistsException if the folder already exists.
     * @throws IllegalFolderNameException if the folder name is not a legal name.
     */
    public void createFolder(String folderName) throws FolderExistsException, IllegalFolderNameException {

        if (!isLegalName(folderName)) {
            throw new IllegalFolderNameException();
        }

        if (!folders.contains(folderName)) {
            folders.add(folderName);
            secretEnumerator.createFolder(folderName);
            secretSearcher.createFolder(folderName);
        }
    }

    /**
     * Retrieves the Secret object at the given index.
     *
     * @param index int index of the Secret object to retrieve.
     * @return Secret object at the given index.
     */
    public Secret getByIndex(int index) {
        return secretEnumerator.get(index);
    }

    /**
     * Retrieves the Secret object at the given index within the given folder.
     *
     * @param index int index of the Secret object to retrieve.
     * @param folder String name of the folder to retrieve the Secret from.
     * @return Secret object at the given index within the given folder.
     */
    public Secret getByIndex(int index, String folder) {
        return secretEnumerator.get(index, folder);
    }

    /**
     * Retrieves an ArrayList of all Secrets.
     *
     * @return ArrayList of all Secrets.
     */
    public ArrayList<Secret> listSecrets() {
        return secretEnumerator.getList();
    }

    /**
     * Retrieves an ArrayList of all Secrets within the given folder.
     *
     * @param folderName String name of the folder to retrieve Secrets from.
     * @return ArrayList of all Secrets within the given folder.
     * @throws NonExistentFolderException if the folder does not exist.
     */
    public ArrayList<Secret> listSecrets(String folderName) throws NonExistentFolderException {
        if (!folders.contains(folderName)) {
            throw new NonExistentFolderException();
        }
        return secretEnumerator.getList(folderName);
    }

    /**
     * Retrieves the Secret object with the given name.
     *
     * @param secretName String name of the Secret object to retrieve.
     * @return Secret object with the given name.
     * @throws SecretNotFoundException if the Secret does not exist.
     */
    public Secret getByName(String secretName) throws SecretNotFoundException {
        if (!secretNames.contains(secretName)) {
            throw new SecretNotFoundException();
        }
        return secretSearcher.get(secretName);
    }

    /**
     * Adds a new Secret to the storage system.
     *
     * @param secret Secret object to be added.
     * @throws FolderExistsException if the folder specified in the Secret already exists.
     * @throws RepeatedIdException if the Secret has a repeated ID.
     * @throws IllegalSecretNameException if the Secret name is not a legal name.
     * @throws IllegalFolderNameException if the folder name in the Secret is not a legal name.
     */
    public void addSecret(Secret secret) throws FolderExistsException, RepeatedIdException,
            IllegalSecretNameException, IllegalFolderNameException {
        if (!isLegalName(secret.getName())) {
            throw new IllegalSecretNameException();
        }
        if (secretNames.contains(secret.getUid())) {
            throw new RepeatedIdException();
        }
        String folderName = secret.getFolderName();
        // Creating a new folder

        if (folderName != null) {
            createFolder(folderName);
        }
        secretNames.add(secret.getName());
        secretEnumerator.add(secret);
        secretSearcher.add(secret);
    }

    /**
     * Removes a Secret from the storage system.
     *
     * @param secret Secret object to be removed.
     * @throws SecretNotFoundException if the Secret does not exist.
     */
    public void removeSecret(Secret secret) throws SecretNotFoundException {
        if (!secretNames.contains(secret.getUid())) {
            throw new SecretNotFoundException();
        }
        String folderName = secret.getFolderName();
        secretNames.remove(secret.getName());
        secretEnumerator.delete(secret);
        secretSearcher.delete(secret);
    }

    /**
     * Retrieves an ArrayList of all Secret names.
     *
     * @return ArrayList of all Secret names.
     */
    public ArrayList<String> listSecretNames() {
        ArrayList<String> secretNamesList = new ArrayList<String>();
        for (Secret secret : secretEnumerator.getList()) {
            secretNamesList.add(secret.getName());
        }
        return secretNamesList;
    }

    /**
     * Retrieves an ArrayList of all Secret names within the given folder.
     *
     * @param folderName String name of the folder to retrieve Secret names from.
     * @return ArrayList of all Secret names within the given folder.
     * @throws NonExistentFolderException if the folder does not exist.
     */
    public ArrayList <String> listSecretNames(String folderName) throws NonExistentFolderException {
        if (!folders.contains(folderName)) {
            throw new NonExistentFolderException();
        }
        ArrayList<String> secretNamesList = new ArrayList<String>();
        for (Secret secret : secretEnumerator.getList(folderName)) {
            secretNamesList.add(secret.getName());
        }
        return secretNamesList;
    }
}
