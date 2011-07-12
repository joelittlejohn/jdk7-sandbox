package com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.util.Arrays;
import java.util.Observable;

public class PgmImage extends Observable implements Cloneable {

    public static final int MAX_VALUE = 255;

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

    public BufferedImage getBufferedImage() {

        byte[] data = new byte[this.getHeight()*this.getWidth()];

        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                data[(i * this.getWidth()) + j] = (byte) this.getPixels()[i][j];
            }
        }

        ColorModel cm = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_GRAY), new int[] {8}, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        SampleModel sm = cm.createCompatibleSampleModel(this.getWidth(), this.getHeight());
        DataBufferByte db = new DataBufferByte(data, this.getWidth() * this.getHeight());
        WritableRaster raster = Raster.createWritableRaster(sm, db, null);
        BufferedImage result = new BufferedImage(cm, raster, true, null);

        return result;
    }


    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }

}
