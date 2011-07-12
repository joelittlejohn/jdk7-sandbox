package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;

/**
 * Transforms image pixels. See implementations for algorithm details.
 */
public interface PixelTransform {

    /**
     * Apply this transform to a pixel in the given image.
     *
     * @param row the row of the target pixel
     * @param col the column of the target pixel
     * @param image the image containing the pixel to be transformed.
     * @return a new pixel gray value, calculated by applying this transform to the source pixel.
     */
    int apply(int row, int col, PgmImage image);

}
