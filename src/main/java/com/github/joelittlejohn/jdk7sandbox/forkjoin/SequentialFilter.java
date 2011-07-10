/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.joelittlejohn.jdk7sandbox.forkjoin;

public class SequentialFilter implements Filter {

    private final PixelTransform transform;

    public SequentialFilter(PixelTransform transform) {
        this.transform = transform;
    }

    @Override
    public PgmImage apply(PgmImage original) {

        final PgmImage filtered = original.clone();

        for (int row=0; row<original.getHeight(); row++) {
            for (int col=0; col<original.getWidth(); col++) {
                filtered.getPixels()[row][col] = this.transform.apply(row, col, original);
            }
        }

        return filtered;
    }

}
