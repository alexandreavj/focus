package com.focus.focus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class - handles GUI events.
 * @author Alexandre Jacob
 * @version 1.0
 */
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
     * Switches pomodoro timer to focus mode.
     */
    public void focusTimerHandler() {
        this.focusPomodoro.setStyle(ButtonStyle.SELECTED.style);
        this.breakPomodoro.setStyle(ButtonStyle.UNSELECTED.style);

        this.pomodoroTimer.setSecondsBeginning(this.configuration.getFocusDuration());
        this.resetTimerHandler();
    }

    /**
     * Switches pomodoro timer to break mode.
     */
    public void breakTimerHandler() {
        this.breakPomodoro.setStyle(ButtonStyle.SELECTED.style);
        this.focusPomodoro.setStyle(ButtonStyle.UNSELECTED.style);

        this.pomodoroTimer.setSecondsBeginning(this.configuration.getBreakDuration());
        this.resetTimerHandler();
    }

    /**
     * Initialize the controller.
     * @param location URL location.
     * @param resources Resource bundle.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.pomodoroTimer = new PomodoroTimer(this.configuration.getFocusDuration(), this);
        this.focusTimerHandler();
    }
}
