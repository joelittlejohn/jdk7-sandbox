package com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

/**
 * Reader for PGM image files. Supports Ascii format PGMs, with 8-bit gray values.
 */
public class PgmReader {

    public static final Charset CHARSET = Charset.forName("iso8859-1");

    /**
     * Read a given path as a PGM file.
     *
     * @param filename path to the PGM file.
     * @return a PGM image object created based on the data found at the given path.
     * @throws IOException if the file cannot be found or read
     */
    public PgmImage read(String filename) throws IOException {

        PgmImage image = new PgmImage();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), CHARSET))) {

            image.setMagicNumber(reader.readLine().trim());
            image.setComment(reader.readLine().trim());

            StringTokenizer widthAndHeight = new StringTokenizer(reader.readLine().trim());
            int width = Integer.parseInt(widthAndHeight.nextToken());
            int height = Integer.parseInt(widthAndHeight.nextToken());

            // skip over maxvalue
            reader.readLine().trim();

            image.setPixels(readPixels(reader, width, height));

        }

        return image;

    }

    /**
     * Read the PGM pixel array (gray values) from the given reader.
     *
     * @param reader a reader currently pointed
     * @param width the width of the image
     * @param height the height of the image
     * @return an 2D array containing the gray values for a PGM image
     * @throws IOException if there is a problem reading data from the given reader
     */
    private int[][] readPixels(BufferedReader reader, int width, int height) throws IOException {

        int[][] pixels = new int[height][width];

        for (int i=0; i<(width*height); ) {
            StringTokenizer values = new StringTokenizer(reader.readLine().trim());

            while (values.hasMoreTokens()) {
                pixels[i/width][i%width] = Integer.parseInt(values.nextToken());
                i++;
            }
        }

        return pixels;
    }

}
