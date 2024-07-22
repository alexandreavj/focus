package com.focus.focus;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class PomodoroTimer {
    private final Label timerLabel;

    private Timeline timeline;

    private int secondsBeginning;

    private int secondsRemaining;


    public PomodoroTimer(int secondsBeginning, Label timerLabel) {
        this.secondsBeginning = this.secondsRemaining = secondsBeginning;
        this.timerLabel = timerLabel;
    }


    public int getSecondsBeginning() {
        return secondsBeginning;
    }

    public void setSecondsBeginning(int secondsBeginning) {
        if (secondsBeginning <= 0) {
            throw new IllegalArgumentException("The duration for the timer must be greater than 0.");
        } else {
            this.secondsBeginning = secondsBeginning;
        }
    }

    public int getSecondsRemaining() {
        return secondsRemaining;
    }


    public void start() {
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            this.secondsRemaining -= 1;
            this.updateTimerLabel();

            if (this.secondsRemaining <= 0) {
                this.secondsRemaining = this.secondsBeginning;
                this.updateTimerLabel();
                this.timeline.stop();
            }
        }));

        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    public void pause() {
        if (this.timeline != null)
            this.timeline.stop();
    }

    public void reset() {
        if (this.timeline != null)
            this.timeline.stop();

        this.secondsRemaining = this.secondsBeginning;
        this.updateTimerLabel();
    }

    private void updateTimerLabel() {
        this.timerLabel.setText(String.format("%02d:%02d", this.secondsRemaining / 60, this.secondsRemaining % 60));
    }
}
