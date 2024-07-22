package com.focus.focus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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

    private PomodoroTimer pomodoroTimer;


    public void startTimerHandler() {
        this.startTimer.setDisable(true);
        this.startTimer.setVisible(false);
        this.pauseTimer.setDisable(false);
        this.pauseTimer.setVisible(true);

        this.pomodoroTimer.start();
    }

    public void pauseTimerHandler() {
        this.startTimer.setDisable(false);
        this.startTimer.setVisible(true);
        this.pauseTimer.setDisable(true);
        this.pauseTimer.setVisible(false);

        this.pomodoroTimer.pause();
    }

    public void resetTimerHandler() {
        this.startTimer.setDisable(false);
        this.startTimer.setVisible(true);
        this.pauseTimer.setDisable(true);
        this.pauseTimer.setVisible(false);

        this.pomodoroTimer.reset();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.pomodoroTimer = new PomodoroTimer(30, timerLabel);
    }
}
