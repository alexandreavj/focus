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
     * Remaining seconds for the timer.
     */
    private int secondsRemaining;


    /**
     * Create a new PomodoroTimer instance.
     * @param secondsBeginning Duration in seconds for the timer.
     * @param controller Controller instance.
     */
    public PomodoroTimer(int secondsBeginning, Controller controller) {
        this.secondsRemaining = secondsBeginning;
        this.controller = controller;
    }

    /**
     * Set the remaining seconds for the timer.
     * @param secondsRemaining Seconds remaining in timer.
     */
    public void setSecondsRemaining(int secondsRemaining) {
        if (secondsRemaining <= 0)
            throw new IllegalArgumentException("Seconds remaining for timer mut be grater than zero!");

        this.secondsRemaining = secondsRemaining;
    }

    /**
     * Get the Timeline instance.
     * @return Remaining seconds for the timer.
     */
    public Timeline getTimeline() {
        return this.timeline;
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

        if (this.controller.getState() == Controller.State.FOCUS) {
            this.secondsRemaining = this.controller.getConfiguration().getFocusDuration();
        } else {
            this.secondsRemaining = this.controller.getConfiguration().getBreakDuration();
        }

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
