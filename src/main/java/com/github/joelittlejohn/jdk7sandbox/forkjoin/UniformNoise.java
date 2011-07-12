package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;
import static java.lang.Math.*;

/**
 * Applies uniform horizontal, vertical and radial noise to an image.
 */
public class UniformNoise implements PixelTransform {

    /**
     * The amount of weight to give the original pixel value, where lower
     * weight means noise has a greater influence on the original pixel value.
     */
    public static final int ORIGINAL_WEIGHT = 10;

    public static final double HORIZONTAL_NOISE_PERIOD = 17.0d;
    public static final double VERTICAL_NOISE_PERIOD = 29.0d;
    public static final double RADIAL_NOISE_PERIOD = 7.0d;

    /**
     * Applies noise to the target pixel where the noise value is uniform and
     * calculated based on the pixel's coordinates within the image.
     *
     * @param row the row of the target pixel
     * @param col the column of the target pixel
     * @param image the image containing the pixel to be transformed.
     * @return a new pixel value that includes some noise in the form of a low intensity offset
     */
    @Override
    public int apply(int row, int col, PgmImage image) {

        int horizontalNoise = (int)(abs(sin(col/ HORIZONTAL_NOISE_PERIOD)) * PgmImage.MAX_VALUE);

        int verticalNoise = (int)(abs(cos(row/ VERTICAL_NOISE_PERIOD)) * PgmImage.MAX_VALUE);

        double diagonal = sqrt(pow(row, 2) + pow(col, 2));
        int radialNoise = (int)(abs(cos(diagonal/ RADIAL_NOISE_PERIOD)) * PgmImage.MAX_VALUE);

        return applyToPixel(image.getPixels()[row][col], horizontalNoise, verticalNoise, radialNoise);
    }

    /**
     * Calculate a new pixel value by applying the given noise to the original value.
     *
     * @param original the original source pixel value
     * @param horizontalNoise the horizontal noise to be applied
     * @param verticalNoise the vertical noise to be applied
     * @param radialNoise the radial noise to be applied
     * @return a new pixel value, with noise applied
     */
    private int applyToPixel(int original, int horizontalNoise, int verticalNoise, int radialNoise) {
        return (original*ORIGINAL_WEIGHT + horizontalNoise*2 + verticalNoise*2 + radialNoise) / (ORIGINAL_WEIGHT + 5);
    }

}
