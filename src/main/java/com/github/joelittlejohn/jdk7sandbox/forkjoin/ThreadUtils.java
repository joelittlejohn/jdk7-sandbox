package com.github.joelittlejohn.jdk7sandbox.forkjoin;

public class ThreadUtils {

    /**
     * Makes the current thread sleep for the given number of milliseconds,
     * throwing a runtime exception on interrupt.
     *
     * @param milliseconds the sleep duration
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
