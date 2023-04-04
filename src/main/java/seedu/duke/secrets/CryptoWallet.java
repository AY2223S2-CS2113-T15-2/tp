package seedu.duke.secrets;

import seedu.duke.Backend;

import java.util.ArrayList;

/**
 * Represents a Crypto Wallet secret that stores information
 * about a cryptocurrency wallet.
 * Inherits from the Secret class.
 */
public class CryptoWallet extends Secret {

    /**
     * Username associated with the wallet.
     */
    private String username;
    private String privateKey;
    private String seedPhrase;
    private ArrayList<String> urls;

    /**
     * Constructor for CryptoWallet class.
     *
     * @param name The name of the crypto wallet.
     * @param username The username associated with the wallet.
     * @param privateKey The private key of the wallet.
     * @param seedPhrase The seed phrase of the wallet.
     */
    public CryptoWallet(String name, String username,
                        String privateKey, String seedPhrase) {
        super(name);
        this.urls = new ArrayList<String>();
        this.username = username;
        this.seedPhrase = seedPhrase;
        this.privateKey = privateKey;
    }

    /**
     * Constructs a new CryptoWallet object with specified name, folder name, username, private key, and seed phrase.
     *
     * @param name the name of the crypto wallet
     * @param folderName the folder name of the crypto wallet
     * @param username the username associated with the crypto wallet
     * @param privateKey the private key associated with the crypto wallet
     * @param seedPhrase the seed phrase associated with the crypto wallet
     */
    public CryptoWallet(String name, String folderName, String username,
                        String privateKey, String seedPhrase) {
        super(name, folderName);
        this.urls = new ArrayList<String>();
        this.username = username;
        this.seedPhrase = seedPhrase;
        this.privateKey = privateKey;
    }
    public CryptoWallet(String name, String folderName, String username,
                        String privateKey, String seedPhrase, ArrayList<String> urls) {
        super(name, folderName);
        this.urls = urls;
        this.username = username;
        this.seedPhrase = seedPhrase;
        this.privateKey = privateKey;
    }

    /**
     * Returns the username associated with the CryptoWallet.
     *
     * @return A String representing the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the CryptoWallet.
     *
     * @param username A String representing the new username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the private key associated with the CryptoWallet.
     *
     * @return A String representing the private key.
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * Returns the seed phrase associated with the CryptoWallet.
     *
     * @return A String representing the seed phrase.
     */
    public String getSeedPhrase() {
        return seedPhrase;
    }

    /**
     * Sets the seed phrase associated with the CryptoWallet.
     *
     * @param seedPhrase A String representing the new seed phrase to be set.
     */
    public void setSeedPhrase(String seedPhrase) {
        this.seedPhrase = seedPhrase;
    }

    /**
     * Returns the list of urls associated with the CryptoWallet.
     *
     * @return An ArrayList of String objects representing the urls.
     */
    public ArrayList<String> getUrls() {
        return urls;
    }


    /**
     * Sets the list of urls associated with the CryptoWallet.
     *
     * @param urls An ArrayList of String objects representing the new urls to be set.
     */
    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    /**
     * Adds a new url to the list of urls associated with the CryptoWallet.
     *
     * @param url A String representing the new url to be added.
     */
    public void addUrl(String url) {
        urls.add(url);
    }

    /**
     * Returns a formatted string representation of the secret's seed phrase.
     *
     * @return a formatted string representation of the secret's seed phrase.
     */
    @Override
    public String getRevealStr() {
        return String.format("Seed Phrase: %s", seedPhrase);
    }

    @Override
    public String toStringForDatabase() {
        String formattedString =  "CryptoWallet," + super.toStringForDatabase() +
                "," + Backend.encode(this.username) + "," + Backend.encode(this.privateKey) + "," +
                Backend.encode("" + this.seedPhrase) + ",";
        String formattedURLs = "";
        for (String url : this.urls) {
            formattedURLs += url + ",";
        }
        return formattedString + formattedURLs;
    }
}
