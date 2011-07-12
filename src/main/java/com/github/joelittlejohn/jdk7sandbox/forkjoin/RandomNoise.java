package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;

public class RandomNoise implements PixelTransform {

    @Override
    public int apply(int row, int col, PgmImage original) {

        final int randomValue = (int)(Math.random() * PgmImage.MAX_VALUE);

        return applyToPixel(original.getPixels()[row][col], randomValue);

    }

    private int applyToPixel(int original, int noise) {
        return (original*5 + noise) / 6;
    }

}
