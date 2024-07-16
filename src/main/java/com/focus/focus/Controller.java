package com.focus.focus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class Controller {
    private static int secondsLeft;

    @FXML
    private Label focusTimeLabel;

    @FXML
    private Slider focusTimeSlider;

    @FXML
    private Button focusTimer;

    @FXML
    private Label pauseTimeLabel;

    @FXML
    private Slider pauseTimeSlider;

    @FXML
    private Button pauseTimer;

    @FXML
    private Button resetTimer;

    @FXML
    private Button openSettingsTimer;

    @FXML
    private Button saveSettingsTimer;

    @FXML
    private Button startTimer;
}
