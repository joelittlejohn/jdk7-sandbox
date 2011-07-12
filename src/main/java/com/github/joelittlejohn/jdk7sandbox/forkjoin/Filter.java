package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;

/**
 * A that can be applied to a PGM image to transform the pixels.
 */
public interface Filter {

    /**
     * Apply this filter to the given image, changing the data in the pixel array.
     *
     * @param imageToBeFiltered the image to be filtered.
     */
    void apply(PgmImage imageToBeFiltered);

}
