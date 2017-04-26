package com.example.matt.allears;

public class StopWatch {
    private static final long NanoToMilli = 1000000L;
    private static final int UNSTARTED = 0, RUNNING = 1, STOPPED = 2;
    private int runningState = UNSTARTED;
    private long startTime, stopTime;
    public StopWatch() {
        super();
    }
    //Starts the StopWatch
    public void start() {
        if (this.runningState == STOPPED) {
            throw new IllegalStateException("Stopwatch must be reset before being restarted.");
        }
        if (this.runningState != UNSTARTED) {
            throw new IllegalStateException("Stopwatch already started.");
        }
        this.startTime = System.nanoTime();//Records the start time
        this.runningState = RUNNING;//Updates the runningState
    }
    //Stops the StopWatch
    public void stop() {
        if (this.runningState != RUNNING) {
            throw new IllegalStateException("Stopwatch not running.");
        }else{
            this.stopTime = System.nanoTime();//Records the stop time
        }
        this.runningState = STOPPED;//Updates the runningState
    }
    //Resets the StopWatch
    public void reset() {
        this.runningState = UNSTARTED;
    }
    //Returns the time passed in Milliseconds
    public long getTime() {
        return getNanoTime() / NanoToMilli;
    }
    //Returns the time passed in Nanoseconds
    public long getNanoTime() {
        if (this.runningState == STOPPED) {
            return this.stopTime - this.startTime;
        } else if (this.runningState == UNSTARTED) {
            return 0;
        } else if (this.runningState == RUNNING) {
            return System.nanoTime() - this.startTime;
        }
        throw new RuntimeException("Illegal state");
    }
}