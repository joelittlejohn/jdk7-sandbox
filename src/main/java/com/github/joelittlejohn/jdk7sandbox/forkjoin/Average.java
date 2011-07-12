/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;

/**
 * Averages a pixel with it's surrounding pixels to create a blurring effect.
 */
public class Average implements PixelTransform {

    /**
     * Applies an blurring operation to the target pixel by averaging it with
     * its neighbours.
     *
     * @param row the row of the target pixel
     * @param col the column of the target pixel
     * @param image the image containing the pixel to be transformed.
     * @return the new pixel value,
     */
    @Override
    public int apply(int row, int col, PgmImage image) {

        int noOfPixelsToAverage = 1;
        long accumulator = image.getPixels()[row][col];

        if (col > 0 && row > 0) {
            accumulator = accumulator + image.getPixels()[row-1][col-1];
            noOfPixelsToAverage++;
        }
        if (col > 0) {
            accumulator = accumulator + image.getPixels()[row][col-1];
            noOfPixelsToAverage++;
        }
        if (row > 0) {
            accumulator = accumulator + image.getPixels()[row-1][col];
            noOfPixelsToAverage++;
        }
        if (col < (image.getWidth()-1) && row < (image.getHeight()-1)) {
            accumulator = accumulator + image.getPixels()[row+1][col+1];
            noOfPixelsToAverage++;
        }
        if (col < (image.getWidth()-1)) {
            accumulator = accumulator + image.getPixels()[row][col+1];
            noOfPixelsToAverage++;
        }
        if (row < (image.getHeight()-1)) {
            accumulator = accumulator + image.getPixels()[row+1][col];
            noOfPixelsToAverage++;
        }
        if (col > 0 && row < (image.getHeight()-1)) {
            accumulator = accumulator + image.getPixels()[row+1][col-1];
            noOfPixelsToAverage++;
        }
        if (col < (image.getWidth()-1) && row > 0) {
            accumulator = accumulator + image.getPixels()[row-1][col+1];
            noOfPixelsToAverage++;
        }

        return (int)(accumulator / noOfPixelsToAverage);
    }

}
