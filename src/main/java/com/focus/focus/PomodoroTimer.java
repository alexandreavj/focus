package com.focus.focus;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class PomodoroTimer {
    private final Controller controller;

    private Timeline timeline;

    private int secondsBeginning;

    private int secondsRemaining;


    public PomodoroTimer(int secondsBeginning, Controller controller) {
        this.secondsBeginning = this.secondsRemaining = secondsBeginning;
        this.controller = controller;
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
                this.controller.resetTimerHandler();
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
        this.controller.getTimerLabel().setText(String.format(
                "%02d:%02d", this.secondsRemaining / 60, this.secondsRemaining % 60));
    }
}
