package com.focus.focus;

import java.io.*;

public class Preferences implements Serializable {
    private int studyTime;

    private int pauseTime;

    private final transient String path;

    public Preferences() throws IOException {
        File prefFile;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            // RUNNING ON WINDOWS
            prefFile = new File(System.getenv("APPDATA") + File.separator + "focus-alexj" + File.separator + "focus_preferences.dat");
        } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            // RUNNING ON MAC
            prefFile = new File(System.getProperty("user.home") + "/Library/Application Support/" + "focus-alexj/" + "focus_preferences.dat");
        } else {
            // RUNNING OTHER OS
            prefFile = new File(System.getProperty("user.home") + File.separator + "focus-alexj" + File.separator + "focus_preferences.dat");
        }

        this.path = prefFile.getAbsolutePath();
        if (prefFile.exists() && prefFile.isFile()) {
            try {
                this.loadPreferencesFile();
            } catch (IOException | ClassNotFoundException e) {
                // default values
                this.studyTime = 50;
                this.pauseTime = 20;

                // create parent folder if it does not exist
                prefFile.getParentFile().mkdirs();
                this.updatePreferencesFile();
            }
        } else {
            // default values
            this.studyTime = 50;
            this.pauseTime = 20;

            // create parent folder if it does not exist
            prefFile.getParentFile().mkdirs();
            this.updatePreferencesFile();
        }
    }

    public int getStudyTime() {
        return studyTime;
    }

    public int getPauseTime() {
        return pauseTime;
    }

    public String getPath() {
        return path;
    }

    public void setStudyTime(int studyTime) {
        this.studyTime = studyTime;
    }

    public void setPauseTime(int pauseTime) {
        this.pauseTime = pauseTime;
    }

    private void loadPreferencesFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(this.path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Preferences preferences = (Preferences) ois.readObject();
        this.studyTime = preferences.getStudyTime();
        this.pauseTime = preferences.getPauseTime();
    }

    public void updatePreferencesFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(this.path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }
}
