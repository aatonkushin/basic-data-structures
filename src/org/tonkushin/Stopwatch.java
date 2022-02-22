package org.tonkushin;

public class Stopwatch {
    private long start;
    private long elapsedTime = 0;

    public void start() {
        start = System.currentTimeMillis();
    }

    public void stop() {
        elapsedTime = System.currentTimeMillis() - start;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public String getElapsedTimeString() {
        return elapsedTime + " ms";
    }
}
