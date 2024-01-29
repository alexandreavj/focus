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
    private Preferences preferences;

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
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
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
            }
            this.iconSettingsTimer.setVisible(this.paneSettingsTimer.isVisible());
            this.iconSaveSettingsTimer.setVisible(!this.paneSettingsTimer.isVisible());
            this.paneSettingsTimer.setVisible(!this.paneSettingsTimer.isVisible());
        }
    }

    public void initialize() {
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
