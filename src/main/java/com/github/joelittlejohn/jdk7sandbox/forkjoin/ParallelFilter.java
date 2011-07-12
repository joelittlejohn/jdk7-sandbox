/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import static com.github.joelittlejohn.jdk7sandbox.forkjoin.ThreadUtils.*;

/**
 * An image filter that parallelizes the filtering operation using the
 * fork/join features of Java 7.
 */
public class ParallelFilter implements Filter {

    private final PixelTransform transform;
    private final ForkJoinPool threadPool = new ForkJoinPool();

    /**
     * Create a new filter which will apply the given pixel transform.
     *
     * @param transform the transform to be applied to individual pixes when
     *        this filter is applied.
     */
    public ParallelFilter(PixelTransform transform) {
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

        final ApplyTransform parallelizableAction = new ApplyTransform(transform, imageToBeFiltered, original, 0, imageToBeFiltered.getHeight());
        threadPool.invoke(parallelizableAction);

    }

    /**
     * A fork join task that applies a transformation to a given image,
     * splitting the transformation operation by row. When a row is filtered,
     * observers are notified.
     */
    private class ApplyTransform extends RecursiveAction {

        private final PixelTransform transform;
        private final PgmImage imageToBeFiltered;
        private final PgmImage original;
        private final int startRow;
        private final int rowsToProcess;

        public ApplyTransform(PixelTransform transform, PgmImage imageToBeFiltered, PgmImage original, int startRow, int rowsToProcess) {
            this.transform = transform;
            this.imageToBeFiltered = imageToBeFiltered;
            this.original = original;
            this.startRow = startRow;
            this.rowsToProcess = rowsToProcess;
        }

        @Override
        protected void compute() {
            if (rowsToProcess > 50) {

            invokeAll(new ApplyTransform(transform, imageToBeFiltered, original, startRow, rowsToProcess/2),
                    new ApplyTransform(transform, imageToBeFiltered, original, startRow + (rowsToProcess/2), rowsToProcess - (rowsToProcess/2)));

//            invokeAll(new ApplyTransform(transform, original, filtered, startRow + (rowsToProcess/2), rowsToProcess - (rowsToProcess/2)),
//                    new ApplyTransform(transform, original, filtered, startRow, rowsToProcess/2));

            } else {

                for (int row = startRow; row < startRow + rowsToProcess; row++) {
                    for (int col = 0; col < original.getWidth(); col++) {
                        imageToBeFiltered.getPixels()[row][col] = transform.apply(row, col, original);
                    }

                    imageToBeFiltered.notifyObservers();

                    sleep(5);
                }

            }
        }

    }
}
