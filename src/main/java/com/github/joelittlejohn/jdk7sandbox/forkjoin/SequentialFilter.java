package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;
import static com.github.joelittlejohn.jdk7sandbox.forkjoin.ThreadUtils.*;

/**
 * An image filter that performs single-threaded, sequential filtering row by row.
 */
public class SequentialFilter implements Filter {

    private final PixelTransform transform;

    /**
     * Create a new filter which will apply the given pixel transform.
     *
     * @param transform the transform to be applied to individual pixes when
     *        this filter is applied.
     */
    public SequentialFilter(PixelTransform transform) {
        this.transform = transform;
    }

    /**
     * Apply this filter to the given image.
     *
     * @param imageToBeFiltered the image have its pixels transformed
     * @return the filtered image.
     */
    @Override
    public void apply(PgmImage imageToBeFiltered) {

        final PgmImage original = imageToBeFiltered.clone();

        for (int row=0; row<imageToBeFiltered.getHeight(); row++) {
            for (int col=0; col<imageToBeFiltered.getWidth(); col++) {
                imageToBeFiltered.getPixels()[row][col] = this.transform.apply(row, col, original);
            }
            imageToBeFiltered.notifyObservers();

            sleep(5);
        }

    }

}
