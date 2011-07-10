package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import java.util.Arrays;

public class PgmImage implements Cloneable {

    public static final int MAX_VALUE = 65536;

    private String magicNumber;
    private String comment;
    private int[][] pixels;

    protected PgmImage() {
    }

    public String getComment() {
        return comment;
    }

    protected void setComment(String comment) {
        this.comment = comment;
    }

    public int getHeight() {
        return pixels.length;
    }

    public String getMagicNumber() {
        return magicNumber;
    }

    protected void setMagicNumber(String magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getMaxValue() {
        return findLargest(pixels);
    }

    public int[][] getPixels() {
        return pixels;
    }

    protected void setPixels(int[][] pixels) {
        this.pixels = pixels;
    }

    public int getWidth() {
        return pixels[0].length;
    }

    private int findLargest(int[][] pixels) {

        int largest = 0;

        for (int[] row : pixels) {
            for (int pixel : row) {
                if (pixel > largest)
                    largest = pixel;
            }
        }

        return largest;
    }

    @Override
    public PgmImage clone() {

        PgmImage clone = new PgmImage();

        clone.magicNumber = this.magicNumber;
        clone.comment = this.comment;

        clone.pixels = new int[this.getHeight()][this.getWidth()];
        for (int row=0; row<this.pixels.length; row++) {
            clone.pixels[row] = Arrays.copyOf(this.pixels[row], this.pixels[row].length);
        }

        return clone;
    }

}
