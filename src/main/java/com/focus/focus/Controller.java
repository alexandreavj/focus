package com.focus.focus;

import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Controller class - handles GUI events.
 * @author Alexandre Jacob
 * @version 1.0
 */
public class Controller implements Initializable {
    /**
     * App state enumeration - used to set the state of the timer between focus or break.
     */
    public enum State {
        FOCUS,
        BREAK
    }

    /**
     * Button style enumeration - used to style the buttons.
     */
    private enum ButtonStyle {
        SELECTED("-fx-background-color: rgba(0, 0, 0, 0.1);"
                + "-fx-border-color: BLACK;"
                + "-fx-border-width: 1px;"
                + "-fx-border-radius: 3px;"
        ),
        UNSELECTED("-fx-background-color: rgba(0, 0, 0, 0.0);"
                + "-fx-border-color: BLACK;"
                + "-fx-border-width: 1px;"
                + "-fx-border-radius: 3px;"
        );

        private final String style;

        ButtonStyle(String style) {
            this.style = style;
        }
    }

    /**
     * Configuration instance - handles configuration file.
     */
    private final Configuration configuration = new Configuration();

    /**
     * Logger instance - used to log messages.
     */
    private final Logger logger = Logger.getLogger("focus");

    /**
     * Output file for the logger.
     */
    private final String log_file = String.valueOf(Paths.get(configuration.getAppDataDirectory() + "focus.log"));

    /**
     * GUI pomodoro timer label.
     */
    @FXML
    private Label timerLabel;

    /**
     * GUI button to start pomodoro timer.
     */
    @FXML
    private Button startTimer;

    /**
     * GUI button to pause pomodoro timer.
     */
    @FXML
    private Button pauseTimer;

    /**
     * GUI button to switch pomodoro timer to focus mode.
     */
    @FXML
    private Button focusPomodoro;

    /**
     * GUI button to switch pomodoro timer to break mode.
     */
    @FXML
    private Button breakPomodoro;

    /**
     * GUI button to open pomodoro settings pane.
     */
    @FXML
    private Button openSettingsTimer;

    /**
     * GUI button to save pomodoro settings.
     */
    @FXML
    private Button saveSettingsTimer;

    /**
     * GUI pane for pomodoro settings.
     */
    @FXML
    private Pane settingsTimerPane;

    /**
     * GUI slider for focus duration.
     */
    @FXML
    private Slider focusDurationSlider;

    /**
     * GUI slider for break duration.
     */
    @FXML
    private Slider breakDurationSlider;

    /**
     * GUI label for focus duration.
     */
    @FXML
    private Label focusDurationLabel;

    /**
     * GUI label for break duration.
     */
    @FXML
    private Label breakDurationLabel;

    /**
     * GUI media view for background video.
     */
    @FXML
    private MediaView backgroundMediaView;

    /**
     * GUI parent anchor pane.
     */
    @FXML
    private AnchorPane parent;

    /**
     * GUI button to load background.
     */
    @FXML
    private Button loadBackgroundButton;

    /**
     * GUI button to mute background.
     */
    @FXML
    private Button volumeButton;

    /**
     * GUI button to mute background.
     */
    @FXML
    private Button muteButton;

    /**
     * Pomodoro timer instance - handles timer operations.
     */
    private PomodoroTimer pomodoroTimer;

    /**
     * State of the timer (focus or break).
     */
    private State state = State.FOCUS;


    /**
     * Get timer label object - useful for PomodoroTimer class.
     * @return Timer label object.
     */
    public Label getTimerLabel() {
        return timerLabel;
    }

    /**
     * Get app's timer state.
     * @return App's timer state (focus or break).
     */
    public State getState() {
        return this.state;
    }

    /**
     * Get Configuration instance - handles configuration file and stores timer focus and break duration.
     * @return App configuration instance.
     */
    public Configuration getConfiguration() {
        return this.configuration;
    }

    /**
     * Starts pomodoro timer and updates the GUI buttons accordingly.
     */
    public void startTimerButtonHandler() {
        this.startTimer.setDisable(true);
        this.startTimer.setVisible(false);
        this.pauseTimer.setDisable(false);
        this.pauseTimer.setVisible(true);

        this.pomodoroTimer.start();
    }

    /**
     * Pauses pomodoro timer and updates the GUI buttons accordingly.
     */
    public void pauseTimerButtonHandler() {
        this.startTimer.setDisable(false);
        this.startTimer.setVisible(true);
        this.pauseTimer.setDisable(true);
        this.pauseTimer.setVisible(false);

        this.pomodoroTimer.pause();
    }

    /**
     * Resets pomodoro timer and updates the GUI buttons accordingly.
     */
    public void resetTimerButtonHandler() {
        this.startTimer.setDisable(false);
        this.startTimer.setVisible(true);
        this.pauseTimer.setDisable(true);
        this.pauseTimer.setVisible(false);

        this.pomodoroTimer.reset();
    }

    /**
     * Switches pomodoro timer to focus mode.
     */
    public void focusTimerButtonHandler() {
        this.state = State.FOCUS;

        this.focusPomodoro.setStyle(ButtonStyle.SELECTED.style);
        this.breakPomodoro.setStyle(ButtonStyle.UNSELECTED.style);

        this.pomodoroTimer.setSecondsRemaining(this.configuration.getFocusDuration());
        this.resetTimerButtonHandler();
    }

    /**
     * Switches pomodoro timer to break mode.
     */
    public void breakTimerButtonHandler() {
        this.state = State.BREAK;

        this.breakPomodoro.setStyle(ButtonStyle.SELECTED.style);
        this.focusPomodoro.setStyle(ButtonStyle.UNSELECTED.style);

        this.pomodoroTimer.setSecondsRemaining(this.configuration.getBreakDuration());
        this.resetTimerButtonHandler();
    }

    /**
     * Opens the pomodoro settings pane.
     */
    public void pomodoroSettingsButtonHandler() {
        this.settingsTimerPane.setVisible(true);
        this.saveSettingsTimer.setVisible(true);
        this.openSettingsTimer.setVisible(false);
    }

    /**
     * Saves the pomodoro settings.
     */
    public void savePomodoroSettingsButtonHandler() {
        this.settingsTimerPane.setVisible(false);
        this.saveSettingsTimer.setVisible(false);
        this.openSettingsTimer.setVisible(true);

        this.configuration.setFocusDuration(this.focusDurationSlider.valueProperty().intValue() * 60);
        this.configuration.setBreakDuration(this.breakDurationSlider.valueProperty().intValue() * 60);

        if (this.pomodoroTimer.getTimeline() == null || this.pomodoroTimer.getTimeline().getStatus() == Animation.Status.STOPPED) {
            this.pomodoroTimer.reset();
        }

        this.configuration.saveConfigurationFile();
    }

    /**
     * Loads the background video.
     */
    public void loadBackgroundButtonHandler() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("choose a background");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("videos", "*.mp4"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images", "*.jpeg", "*.jpg"));

        File selectedFile = fileChooser.showOpenDialog(this.parent.getScene().getWindow());
        if (selectedFile != null && selectedFile.exists()) {
            Media media = new Media(selectedFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            this.backgroundMediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
    }

    /**
     * Unmutes the background video.
     */
    public void volumeButtonHandler() {
        this.backgroundMediaView.getMediaPlayer().setMute(false);
        this.volumeButton.setVisible(false);
        this.muteButton.setVisible(true);
    }

    /**
     * Mutes the background video.
     */
    public void muteButtonHandler() {
        this.backgroundMediaView.getMediaPlayer().setMute(true);
        this.muteButton.setVisible(false);
        this.volumeButton.setVisible(true);
    }

    /**
     * Initialize the controller.
     * @param location URL location.
     * @param resources Resource bundle.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set logger output file
        try {
            this.logger.info("Setting logger output file: " + this.log_file);
            FileHandler fileHandler = new FileHandler(this.log_file, true);
            fileHandler.setFormatter(new SimpleFormatter());
            this.logger.addHandler(fileHandler);
        } catch (Exception e) {
            this.logger.warning("Failed to set logger output file.");
        }

        // automatically resize the background video to fit window
        this.backgroundMediaView.setPreserveRatio(false);
        this.backgroundMediaView.fitWidthProperty().bind(this.parent.widthProperty());
        this.backgroundMediaView.fitHeightProperty().bind(this.parent.heightProperty());

        // initialize pomodoro timer
        this.pomodoroTimer = new PomodoroTimer(this.configuration.getFocusDuration(), this);
        this.focusTimerButtonHandler();

        // initialize sliders and their labels
        this.focusDurationSlider.valueProperty().addListener((_, _, newValue) -> {
            this.focusDurationLabel.setText(String.valueOf(newValue.intValue()));
        });
        this.breakDurationSlider.valueProperty().addListener((_, _, newValue) -> {
            this.breakDurationLabel.setText(String.valueOf(newValue.intValue()));
        });

        this.focusDurationSlider.setValue(this.configuration.getFocusDuration() / 60);
        this.breakDurationSlider.setValue(this.configuration.getBreakDuration() / 60);

        // initialize background video
        URL defaultBGJAR = getClass().getResource("retro_mac_animation.mp4");
        this.logger.info("Default BG - initialize controller: " + defaultBGJAR);
        if (defaultBGJAR != null) {
            // copy resource to application data directory, if not already there
            Path defaultBGAppData = Paths.get(this.configuration.getAppDataDirectory(), "retro_mac_animation.mp4");
            this.logger.info("Destination for default BG: " + defaultBGAppData);
            if (!Files.exists(defaultBGAppData)) {
                try {
                    Files.copy(Path.of(defaultBGJAR.toURI()), defaultBGAppData);
                    this.logger.info("Default BG successfully copied to " + defaultBGJAR);
                } catch (IOException | URISyntaxException e) {
                    this.logger.warning("Could not copy default background from JAR file to application data directory: " + e);
                }
            }

            if (Files.exists(defaultBGAppData)) {
                Media media = new Media(defaultBGAppData.toUri().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                this.backgroundMediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
            }
        }
    }
}
