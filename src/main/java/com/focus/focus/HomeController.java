package com.focus.focus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class HomeController {
    private enum State {
        FOCUS,
        PAUSE
    }

    private State state;

    private final Preferences preferences;

    private PomodoroTimer timer;

    @FXML
    private Button buttonFocusTimer;

    @FXML
    private Button buttonPauseTimer;

    @FXML
    private Button buttonResetTimer;

    @FXML
    private Button buttonSettingsTimer;

    @FXML
    private Button buttonStartPauseTimer;

    @FXML
    private Label labelFocusSlider;

    @FXML
    private Label labelMinutesTimer;

    @FXML
    private Label labelPauseSlider;

    @FXML
    private Label labelSecondsTimer;

    @FXML
    private Pane paneSettingsTimer;

    @FXML
    private Slider sliderFocusTime;

    @FXML
    private Slider sliderPauseTime;

    @FXML
    private ImageView iconPauseTimer;

    @FXML
    private ImageView iconSaveSettingsTimer;

    @FXML
    private ImageView iconSettingsTimer;

    @FXML
    private ImageView iconStartTimer;

    public HomeController() throws IOException {
        this.preferences = new Preferences();
        this.state = State.FOCUS;
    }

    @FXML
    private void buttonClick(ActionEvent event) {
        if (event.getSource() == this.buttonSettingsTimer) {
            if (iconSaveSettingsTimer.isVisible()) {
                this.preferences.setStudyTime((int) this.sliderFocusTime.getValue());
                this.preferences.setPauseTime((int) this.sliderPauseTime.getValue());
                try {
                    this.preferences.updatePreferencesFile();
                } catch(IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ups...");
                    alert.setHeaderText("Could not update app preferences.");
                    alert.setContentText("The updates will not be saved when you close the app!");
                    alert.showAndWait();
                }
                if (this.state == State.FOCUS) {
                    this.timer.setDefaultDuration(this.preferences.getStudyTime());
                } else {
                    this.timer.setDefaultDuration(this.preferences.getPauseTime());
                }
                if (!this.timer.isRunning()) {
                    this.timer.updateUI();
                }
            }
            this.iconSettingsTimer.setVisible(this.paneSettingsTimer.isVisible());
            this.iconSaveSettingsTimer.setVisible(!this.paneSettingsTimer.isVisible());
            this.paneSettingsTimer.setVisible(!this.paneSettingsTimer.isVisible());
        } else if (event.getSource() == this.buttonStartPauseTimer) {
            if (this.iconStartTimer.isVisible()) {
                this.timer.startTimer();
            } else {
                this.timer.pauseTimer();
            }
            this.iconStartTimer.setVisible(!this.iconStartTimer.isVisible());
            this.iconPauseTimer.setVisible(!this.iconPauseTimer.isVisible());
        } else if (event.getSource() == this.buttonResetTimer) {
            this.timer.resetTimer();
            this.iconStartTimer.setVisible(true);
            this.iconPauseTimer.setVisible(false);
        } else if (event.getSource() == this.buttonFocusTimer && this.state != State.FOCUS) {
            this.buttonFocusTimer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-border-color: BLACK; -fx-border-width: 0.5; -fx-border-radius: 5;");
            this.buttonPauseTimer.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-border-color: BLACK; -fx-border-width: 0.5; -fx-border-radius: 5;");
            this.state = State.FOCUS;
            this.timer.setDefaultDuration(this.preferences.getStudyTime());
            this.timer.resetTimer();
            this.timer.updateUI();
            this.iconPauseTimer.setVisible(false);
            this.iconStartTimer.setVisible(true);
        } else if (event.getSource() == this.buttonPauseTimer && this.state != State.PAUSE) {
            this.buttonFocusTimer.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-border-color: BLACK; -fx-border-width: 0.5; -fx-border-radius: 5;");
            this.buttonPauseTimer.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1); -fx-border-color: BLACK; -fx-border-width: 0.5; -fx-border-radius: 5;");
            this.state = State.PAUSE;
            this.timer.setDefaultDuration(this.preferences.getPauseTime());
            this.timer.resetTimer();
            this.timer.updateUI();
            this.iconPauseTimer.setVisible(false);
            this.iconStartTimer.setVisible(true);
        }
    }

    public void initialize() {
        this.timer = new PomodoroTimer(this.preferences.getStudyTime(), this.labelMinutesTimer, this.labelSecondsTimer);
        this.sliderFocusTime.setValue(this.preferences.getStudyTime());
        this.sliderPauseTime.setValue(this.preferences.getPauseTime());
        this.labelFocusSlider.setText(String.valueOf(this.preferences.getStudyTime()));
        this.labelPauseSlider.setText(String.valueOf(this.preferences.getPauseTime()));
        this.labelMinutesTimer.setText(String.format("%02d", this.preferences.getStudyTime()));
        this.sliderFocusTime.valueProperty().addListener((observableValue, number, t1) -> {
            int newValue = (int) sliderFocusTime.getValue();
            if (newValue % 5 == 0) {
                labelFocusSlider.setText(String.valueOf(newValue));
            }
        });
        this.sliderPauseTime.valueProperty().addListener((observableValue, number, t1) -> {
            int newValue = (int) sliderPauseTime.getValue();
            if (newValue % 5 == 0) {
                labelPauseSlider.setText(String.valueOf(newValue));
            }
        });
    }
}
