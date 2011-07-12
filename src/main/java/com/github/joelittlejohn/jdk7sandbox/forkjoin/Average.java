/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;

public class Average implements PixelTransform {

    @Override
    public int apply(int row, int col, PgmImage original) {

        int noOfPixelsToAverage = 1;
        long accumulator = original.getPixels()[row][col];

        if (col > 0 && row > 0) {
            accumulator = accumulator + original.getPixels()[row-1][col-1];
            noOfPixelsToAverage++;
        }
        if (col > 0) {
            accumulator = accumulator + original.getPixels()[row][col-1];
            noOfPixelsToAverage++;
        }
        if (row > 0) {
            accumulator = accumulator + original.getPixels()[row-1][col];
            noOfPixelsToAverage++;
        }
        if (col < (original.getWidth()-1) && row < (original.getHeight()-1)) {
            accumulator = accumulator + original.getPixels()[row+1][col+1];
            noOfPixelsToAverage++;
        }
        if (col < (original.getWidth()-1)) {
            accumulator = accumulator + original.getPixels()[row][col+1];
            noOfPixelsToAverage++;
        }
        if (row < (original.getHeight()-1)) {
            accumulator = accumulator + original.getPixels()[row+1][col];
            noOfPixelsToAverage++;
        }
        if (col > 0 && row < (original.getHeight()-1)) {
            accumulator = accumulator + original.getPixels()[row+1][col-1];
            noOfPixelsToAverage++;
        }
        if (col < (original.getWidth()-1) && row > 0) {
            accumulator = accumulator + original.getPixels()[row-1][col+1];
            noOfPixelsToAverage++;
        }

        return (int)(accumulator / noOfPixelsToAverage);
    }

}
