package com.focus.focus;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Pomodoro timer class - handles timer operations.
 * @author Alexandre Jacob
 * @version 1.0
 */
public class PomodoroTimer {
    /**
     * Controller instance - used to update the timer label.
     */
    private final Controller controller;

    /**
     * Timeline instance - used schedule timer updates.
     */
    private Timeline timeline;

    /**
     * Duration in seconds for the timer.
     */
    private int secondsBeginning;

    /**
     * Remaining seconds for the timer.
     */
    private int secondsRemaining;


    /**
     * Create a new PomodoroTimer instance.
     * @param secondsBeginning Duration in seconds for the timer.
     * @param controller Controller instance.
     */
    public PomodoroTimer(int secondsBeginning, Controller controller) {
        this.secondsBeginning = this.secondsRemaining = secondsBeginning;
        this.controller = controller;
    }


    /**
     * Get the duration in seconds for the timer.
     * @return Duration in seconds for the timer.
     */
    public int getSecondsBeginning() {
        return secondsBeginning;
    }

    /**
     * Set the duration in seconds for the timer.
     * @param secondsBeginning Duration in seconds for the timer.
     */
    public void setSecondsBeginning(int secondsBeginning) {
        if (secondsBeginning <= 0) {
            throw new IllegalArgumentException("The duration for the timer must be greater than 0.");
        } else {
            this.secondsBeginning = secondsBeginning;
        }
    }

    /**
     * Get the remaining seconds for the timer.
     * @return Remaining seconds for the timer.
     */
    public int getSecondsRemaining() {
        return secondsRemaining;
    }


    /**
     * Start the timer.
     */
    public void start() {
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            this.secondsRemaining -= 1;
            this.updateTimerLabel();

            if (this.secondsRemaining <= 0) {
                this.controller.resetTimerButtonHandler();
            }
        }));

        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    /**
     * Pause the timer.
     */
    public void pause() {
        if (this.timeline != null)
            this.timeline.stop();
    }

    /**
     * Reset the timer.
     */
    public void reset() {
        if (this.timeline != null)
            this.timeline.stop();

        this.secondsRemaining = this.secondsBeginning;
        this.updateTimerLabel();
    }

    /**
     * Update the timer label.
     */
    private void updateTimerLabel() {
        this.controller.getTimerLabel().setText(String.format(
                "%02d:%02d", this.secondsRemaining / 60, this.secondsRemaining % 60));
    }
}
