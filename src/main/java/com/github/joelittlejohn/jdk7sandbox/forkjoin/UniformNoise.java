package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;
import static java.lang.Math.*;

public class UniformNoise implements PixelTransform {

    @Override
    public int apply(int row, int col, PgmImage image) {

        int horizontalNoise = (int)(abs(sin(col/17.0d)) * PgmImage.MAX_VALUE);

        int verticalNoise = (int)(abs(cos(row/29.0d)) * PgmImage.MAX_VALUE);

        double diagonal = sqrt(pow(row, 2) + pow(col, 2));
        int radialNoise = (int)(abs(cos(diagonal/7.0d)) * PgmImage.MAX_VALUE);

        return applyToPixel(image.getPixels()[row][col], horizontalNoise, verticalNoise, radialNoise);
    }

    private int applyToPixel(int original, int horizontalNoise, int verticalNoise, int radialNoise) {
        return (original*7 + horizontalNoise + verticalNoise + radialNoise) / 10;
    }

}
