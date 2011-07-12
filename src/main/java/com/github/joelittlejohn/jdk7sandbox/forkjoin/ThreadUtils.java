package com.github.joelittlejohn.jdk7sandbox.forkjoin;

public class ThreadUtils {

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
