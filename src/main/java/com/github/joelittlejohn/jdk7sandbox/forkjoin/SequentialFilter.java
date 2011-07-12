package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;

public class SequentialFilter implements Filter {

    private final PixelTransform transform;

    public SequentialFilter(PixelTransform transform) {
        this.transform = transform;
    }

    @Override
    public PgmImage apply(PgmImage original) {

        //final PgmImage filtered = original.clone();

        for (int row=0; row<original.getHeight(); row++) {
            for (int col=0; col<original.getWidth(); col++) {
                original.getPixels()[row][col] = this.transform.apply(row, col, original);
            }
            original.notifyObservers();
        }

        return original;
    }

}
