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
     * Configuration file path.
     */
    private final String configurationFilePath;

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
            configurationFilePath = System.getenv("APPDATA") + "\\focus\\config.properties";
        // macOS
        } else if (operatingSystem.contains("MAC") || operatingSystem.contains("DARWIN")) {
            configurationFilePath = System.getProperty("user.home") + "/Library/Application Support/focus/config.properties";
        // unix/linux
        } else if (operatingSystem.contains("NIX") || operatingSystem.contains("NUX") || operatingSystem.contains("AIX")) {
            configurationFilePath = System.getProperty("user.dir") + "/.config/focus/config.properties";
        // other
        } else {
            configurationFilePath = System.getProperty("user.home") + "/.focus/config.properties";
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
    public String getConfigurationFilePath() {
        return configurationFilePath;
    }

    /**
     * Creates the configuration file if it does not exist.
     *
     * @throws RuntimeException if the configuration file could not be created.
     */
    private void createConfigurationFile() throws RuntimeException {
        File configurationFile = new File(configurationFilePath);
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
            configuration.load(new FileInputStream(this.configurationFilePath));

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
            configuration.store(new FileOutputStream(this.configurationFilePath), null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save configuration file.");
        }
    }
}
