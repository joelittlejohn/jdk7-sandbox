package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;

/**
 * Applies random noise to pixels.
 */
public class RandomNoise implements PixelTransform {

    /**
     * The amount of weight to give the original pixel value, where lower
     * weight means noise has a greater influence on the original pixel value.
     */
     public static final int ORIGINAL_WEIGHT = 5;

    /**
     * Applies a random amount of noise to the target pixel.
     *
     * @param row the row of the target pixel
     * @param col the column of the target pixel
     * @param image the image containing the pixel to be transformed.
     * @return a new pixel value that includes some noise in the form of a low intensity offset
     */
    @Override
    public int apply(int row, int col, PgmImage image) {

        final int randomValue = (int)(Math.random() * PgmImage.MAX_VALUE);

        return applyToPixel(image.getPixels()[row][col], randomValue);

    }

    /**
     * Calculate a new pixel value by applying the noise to the original value.
     *
     * @param original the original source pixel value
     * @param noise the noise to be applied
     * @return a new pixel value, with noise applied
     */
    private int applyToPixel(int original, int noise) {
        return (original*ORIGINAL_WEIGHT + noise) / ORIGINAL_WEIGHT + 1;
    }

}
