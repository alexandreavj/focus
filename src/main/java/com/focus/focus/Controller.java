package com.focus.focus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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
     * Pomodoro timer instance - handles timer operations.
     */
    private PomodoroTimer pomodoroTimer;

    /**
     * Get timer label object - useful for PomodoroTimer class.
     * @return Timer label object.
     */
    public Label getTimerLabel() {
        return timerLabel;
    }


    /**
     * Starts pomodoro timer and updates the GUI buttons accordingly.
     */
    public void startTimerHandler() {
        this.startTimer.setDisable(true);
        this.startTimer.setVisible(false);
        this.pauseTimer.setDisable(false);
        this.pauseTimer.setVisible(true);

        this.pomodoroTimer.start();
    }

    /**
     * Pauses pomodoro timer and updates the GUI buttons accordingly.
     */
    public void pauseTimerHandler() {
        this.startTimer.setDisable(false);
        this.startTimer.setVisible(true);
        this.pauseTimer.setDisable(true);
        this.pauseTimer.setVisible(false);

        this.pomodoroTimer.pause();
    }

    /**
     * Resets pomodoro timer and updates the GUI buttons accordingly.
     */
    public void resetTimerHandler() {
        this.startTimer.setDisable(false);
        this.startTimer.setVisible(true);
        this.pauseTimer.setDisable(true);
        this.pauseTimer.setVisible(false);

        this.pomodoroTimer.reset();
    }

    /**
     * Initialize the controller.
     * @param location URL location.
     * @param resources Resource bundle.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.pomodoroTimer = new PomodoroTimer(50 * 60, this);
        this.resetTimerHandler();
    }
}
