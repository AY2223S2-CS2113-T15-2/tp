package seedu.duke;

import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

public class DukeLogger {
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String USER_DIRECTORY_IDENTIFIER = "user.dir";
    private static final String LOGGER_FOLDER = "assets";
    private static final String LOGGER_FILE = "logFiles.txt";

    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        LOGGER.addHandler(consoleHandler);

        try {
            String currentPath = System.getProperty(DukeLogger.USER_DIRECTORY_IDENTIFIER);
            String logFilesPath = Paths.get(currentPath,
                    DukeLogger.LOGGER_FOLDER, DukeLogger.LOGGER_FILE).toString();
            File logFiles = new File(logFilesPath);
            if (!logFiles.createNewFile()) {
                logFiles.createNewFile();
            }
            FileHandler fileHandler = new FileHandler(logFilesPath, true);
            fileHandler.setLevel(Level.WARNING);
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "DUKE logger is not working!");
        }

    }

    public static void close() {
        for (Handler handler : LOGGER.getHandlers()) {
            handler.close();
        }
    }
}
