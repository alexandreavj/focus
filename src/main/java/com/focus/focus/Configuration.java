package com.focus.focus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Configuration class - handles configuration file operations.
 * @author Alexandre Jacob
 * @version 1.0
 */
public class Configuration {
    /**
     * Configuration file name.
     */
    private static final String CONFIG_FILE_NAME = "config.properties";

    /**
     * Configuration file path.
     */
    private final String appDataDirectory;

    /**
     * Focus duration in seconds.
     */
    private int focusDuration;

    /**
     * Break duration in seconds.
     */
    private int breakDuration;


    /**
     * Constructor for Configuration class.
     * Gets the path for the configuration file.
     * Creates it if it does not exist or loads it if it does.
     * @throws RuntimeException if the configuration file could not be created.
     */
    public Configuration() throws RuntimeException {
        // find configuration file path
        String operatingSystem = System.getProperty("os.name").toUpperCase();
        // windows
        if (operatingSystem.contains("WIN")) {
            appDataDirectory = System.getenv("APPDATA") + "\\focus\\";
        // macOS
        } else if (operatingSystem.contains("MAC") || operatingSystem.contains("DARWIN")) {
            appDataDirectory = System.getProperty("user.home") + "/Library/Application Support/focus/";
        // unix/linux
        } else if (operatingSystem.contains("NIX") || operatingSystem.contains("NUX") || operatingSystem.contains("AIX")) {
            appDataDirectory = System.getProperty("user.dir") + "/.config/focus/";
        // other
        } else {
            appDataDirectory = System.getProperty("user.home") + "/.focus/";
        }

        // creates the configuration file if it does not exist
        // if it does exist, it will load the configuration
        createConfigurationFile();
        loadConfigurationFile();
    }


    /**
     * Get the focus duration.
     * @return Focus duration in seconds.
     */
    public int getFocusDuration() {
        return focusDuration;
    }

    /**
     * Set the focus duration.
     * @param focusDuration Focus duration in seconds.
     */
    public void setFocusDuration(int focusDuration) {
        this.focusDuration = focusDuration;
    }

    /**
     * Get the break duration.
     * @return Break duration in seconds.
     */
    public int getBreakDuration() {
        return breakDuration;
    }

    /**
     * Set the break duration.
     * @param breakDuration Break duration in seconds.
     */
    public void setBreakDuration(int breakDuration) {
        this.breakDuration = breakDuration;
    }

    /**
     * Get the configuration file path.
     * @return Configuration file path.
     */
    public String getAppDataDirectory() {
        return appDataDirectory;
    }

    /**
     * Creates the configuration file if it does not exist.
     *
     * @throws RuntimeException if the configuration file could not be created.
     */
    private void createConfigurationFile() throws RuntimeException {
        File configurationFile = new File(this.appDataDirectory + CONFIG_FILE_NAME);
        File parentDirectory = configurationFile.getParentFile();

        if (!parentDirectory.exists()) {
            if (!parentDirectory.mkdirs())
                throw new RuntimeException("Failed to create configuration file parent directory.");
        }

        if (!configurationFile.exists()) {
            try {
                if (!configurationFile.createNewFile())
                    throw new RuntimeException("Failed to create configuration file.");
            } catch (Exception e) {
                throw new RuntimeException("Failed to create configuration file.");
            }
        }
    }

    /**
     * Load the configuration file.
     * @throws RuntimeException if the configuration file could not be loaded.
     */
    private void loadConfigurationFile() throws RuntimeException {
        Properties configuration = new Properties();
        try {
            configuration.load(new FileInputStream(this.appDataDirectory + CONFIG_FILE_NAME));

            // set default values in configuration if they do not exist or load them otherwise
            this.focusDuration = Integer.parseInt(configuration.getProperty("focusDuration", "50")) * 60;
            this.breakDuration = Integer.parseInt(configuration.getProperty("breakDuration", "20")) * 60;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration file.");
        }
    }

    /**
     * Save the configuration file.
     * @throws RuntimeException if the configuration file could not be saved.
     */
    public void saveConfigurationFile() {
        Properties configuration = new Properties();
        try {
            configuration.setProperty("focusDuration", Integer.toString(this.focusDuration / 60));
            configuration.setProperty("breakDuration", Integer.toString(this.breakDuration / 60));
            configuration.store(new FileOutputStream(this.appDataDirectory + CONFIG_FILE_NAME), null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save configuration file.");
        }
    }
}
