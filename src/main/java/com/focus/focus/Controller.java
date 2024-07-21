package com.focus.focus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final PomodoroTimer pomodoroTimer = new PomodoroTimer(50 * 60);

    @FXML
    private Label timerLabel;

    @FXML
    private Label focusTimeLabel;

    @FXML
    private Slider focusTimeSlider;

    @FXML
    private Button focusPomodoro;

    @FXML
    private Label pauseTimeLabel;

    @FXML
    private Slider pauseTimeSlider;

    @FXML
    private Button pauseTimerPomodoro;

    @FXML
    private Button resetTimer;

    @FXML
    private Button openSettingsTimer;

    @FXML
    private Button saveSettingsTimer;

    @FXML
    private Button startTimer;

    @FXML
    private Button pauseTimer;


    public void startTimerHandler() {
        startTimer.setDisable(true);
        startTimer.setVisible(false);
        pauseTimer.setDisable(false);
        pauseTimer.setVisible(true);
        pomodoroTimer.start();
    }

    public void pauseTimerHandler() {
        startTimer.setDisable(false);
        startTimer.setVisible(true);
        pauseTimer.setDisable(true);
        pauseTimer.setVisible(false);
        pomodoroTimer.pause();
    }

    public void resetTimerHandler() {
        startTimer.setDisable(false);
        startTimer.setVisible(true);
        pauseTimer.setDisable(true);
        pauseTimer.setVisible(false);
        pomodoroTimer.reset();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resetTimerHandler();
    }
}
