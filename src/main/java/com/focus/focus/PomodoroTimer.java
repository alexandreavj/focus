package com.focus.focus;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimer {
    private int defaultDuration;

    private int secondsRemaining;

    private final Label labelMinutesUI, labelSecondsUI;

    private Timer timer;

    private boolean isRunning;

    public PomodoroTimer(int defaultDurationMinutes, Label labelMinutesUI, Label labelSecondsUI) {
        this.defaultDuration = defaultDurationMinutes * 60;
        this.secondsRemaining = this.defaultDuration;
        this.labelMinutesUI = labelMinutesUI;
        this.labelSecondsUI = labelSecondsUI;
        this.resetTimer();
        this.isRunning = false;
    }

    public void setDefaultDuration(int defaultDurationMinutes) {
        this.defaultDuration = defaultDurationMinutes * 60;
        if (!this.isRunning) {
            this.secondsRemaining = this.defaultDuration;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void startTimer() {
        this.timer = new Timer();
        this.isRunning = true;
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                secondsRemaining -= 1;
                if (secondsRemaining <= 0) {
                    resetTimer();
                } else {
                    updateUI();
                }
            }
        }, 0, 1000);
    }

    public void pauseTimer() {
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.isRunning = false;
    }

    public void resetTimer() {
        if (this.timer != null) {
            this.secondsRemaining = this.defaultDuration;
            this.updateUI();
            this.timer.cancel();
        }
        this.isRunning = false;
    }

    public void updateUI() {
        Platform.runLater(() -> {
            this.labelMinutesUI.setText(String.format("%02d", this.secondsRemaining / 60));
            this.labelSecondsUI.setText(String.format("%02d", this.secondsRemaining % 60));
        });

    }
}
