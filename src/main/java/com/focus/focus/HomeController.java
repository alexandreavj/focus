package com.focus.focus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Calendar;

public class HomeController {
    private enum State {
        FOCUS,
        PAUSE
    }

    private static final String[] QUOTES = {
            "Believe you can and you're halfway there.",
            "The only way to do great work is to love what you do.",
            "Push yourself, because no one else is going to do it for you.",
            "Failure will never overtake me if my determination to succeed is strong enough.",
            "The harder you work for something, the greater you'll feel when you achieve it.",
            "It does not matter how slowly you go, as long as you do not stop.",
            "Dream big and dare to fail.",
            "The only limit to our realization of tomorrow will be our doubts of today.",
            "Don't watch the clock; do what it does. Keep going.",
            "Success is not final, failure is not fatal: It is the courage to continue that counts.",
            "You are never too old to set another goal or to dream a new dream.",
            "It always seems impossible until it is done.",
            "Your limitation—it's only your imagination.",
            "Success doesn’t just find you. You have to go out and get it.",
            "The key to success is to focus on goals, not obstacles.",
            "The only way to achieve the impossible is to believe it is possible.",
            "Your limitation—it’s only your imagination.",
            "Push yourself, because no one else is going to do it for you.",
            "Great things never come from comfort zones.",
            "Dream it. Wish it. Do it.",
            "Success doesn’t just find you. You have to go out and get it.",
            "The harder you work for something, the greater you’ll feel when you achieve it.",
            "Dream bigger. Do bigger.",
            "Don’t stop when you’re tired. Stop when you’re done.",
            "Wake up with determination. Go to bed with satisfaction.",
            "Do something today that your future self will thank you for.",
            "Little things make big days.",
            "It’s going to be hard, but hard does not mean impossible.",
            "Don’t wait for opportunity. Create it.",
            "Sometimes we’re tested not to show our weaknesses, but to discover our strengths.",
            "The key to success is to focus on goals, not obstacles.",
            "Dream it. Wish it. Do it.",
            "Success doesn’t just find you. You have to go out and get it.",
            "The harder you work for something, the greater you’ll feel when you achieve it.",
            "Dream bigger. Do bigger.",
            "Don’t stop when you’re tired. Stop when you’re done.",
            "Wake up with determination. Go to bed with satisfaction.",
            "Do something today that your future self will thank you for.",
            "Little things make big days.",
            "It’s going to be hard, but hard does not mean impossible.",
            "Don’t wait for opportunity. Create it.",
            "Sometimes we’re tested not to show our weaknesses, but to discover our strengths.",
            "The key to success is to focus on goals, not obstacles.",
            "Dream it. Wish it. Do it.",
            "Success doesn’t just find you. You have to go out and get it.",
            "The harder you work for something, the greater you’ll feel when you achieve it.",
            "Dream bigger. Do bigger.",
            "Don’t stop when you’re tired. Stop when you’re done.",
            "Wake up with determination. Go to bed with satisfaction.",
            "Do something today that your future self will thank you for.",
            "Little things make big days.",
            "It’s going to be hard, but hard does not mean impossible.",
            "Don’t wait for opportunity. Create it.",
            "Sometimes we’re tested not to show our weaknesses, but to discover our strengths."
    };

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
    private Label labelFortune;

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

    @FXML
    private ImageView pusheen;

    @FXML
    private ImageView speechBalloonClock;

    @FXML
    private Label labelClock;

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

    @FXML
    private void mouseEnter(MouseEvent event) {
        if (event.getSource() == this.pusheen) {
            this.speechBalloonClock.setVisible(true);
            this.labelClock.setText(String.format("%02d:%02d", LocalTime.now().getHour(), LocalTime.now().getMinute()));
            this.labelClock.setVisible(true);
        }
    }

    @FXML
    private void mouseExit(MouseEvent event) {
        if (event.getSource() == this.pusheen) {
            this.speechBalloonClock.setVisible(false);
            this.labelClock.setVisible(false);
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
        this.labelFortune.setText(QUOTES[(int) Math.round(Math.random() * QUOTES.length)]);
    }
}
