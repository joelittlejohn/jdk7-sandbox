/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelFilter implements Filter {

    private final PixelTransform transform;
    private final ForkJoinPool threadPool = new ForkJoinPool();

    public ParallelFilter(PixelTransform transform) {
        this.transform = transform;
    }

    @Override
    public PgmImage apply(PgmImage original) {
        final PgmImage filtered = original.clone();

        final ApplyTransform parallelizableAction = new ApplyTransform(transform, original, filtered, 0, original.getHeight());
        threadPool.execute(parallelizableAction);
        parallelizableAction.join();

        return filtered;
    }

    private class ApplyTransform extends RecursiveAction {

        private final PixelTransform transform;
        private final PgmImage original;
        private final PgmImage filtered;
        private final int startRow;
        private final int rowsToProcess;

        public ApplyTransform(PixelTransform transform, PgmImage original, PgmImage filtered, int startRow, int rowsToProcess) {
            this.transform = transform;
            this.original = original;
            this.filtered = filtered;
            this.startRow = startRow;
            this.rowsToProcess = rowsToProcess;
        }

        @Override
        protected void compute() {
            if (rowsToProcess > 50) {

                invokeAll(new ApplyTransform(transform, original, filtered, startRow, rowsToProcess/2),
                        new ApplyTransform(transform, original, filtered, startRow + (rowsToProcess/2), rowsToProcess - (rowsToProcess/2)));

            } else {

                for (int row = startRow; row < startRow + rowsToProcess; row++) {
                    for (int col = 0; col < original.getWidth(); col++) {
                        filtered.getPixels()[row][col] = transform.apply(row, col, original);
                    }
                }

            }
        }
    }
}
