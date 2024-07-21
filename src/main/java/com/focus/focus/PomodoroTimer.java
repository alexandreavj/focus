package com.focus.focus;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimer {
    private Timer timer;

    private int secondsBeginning;

    private int secondsRemaining;


    public PomodoroTimer(int secondsBeginning) {
        this.timer = new Timer();
        this.secondsBeginning = this.secondsRemaining = secondsBeginning;
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
        this.timer = new Timer();

        this.timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                secondsRemaining -= 1;
                if (secondsRemaining == 0) {
                    secondsRemaining = secondsBeginning;
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    public void pause() {
        timer.cancel();
    }

    public void reset() {
        this.timer.cancel();
        this.secondsRemaining = this.secondsBeginning;
    }
}
