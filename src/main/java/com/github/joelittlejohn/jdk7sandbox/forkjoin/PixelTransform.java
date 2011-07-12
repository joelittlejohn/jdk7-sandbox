package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;

public interface PixelTransform {

    int apply(int row, int col, PgmImage image);

}
