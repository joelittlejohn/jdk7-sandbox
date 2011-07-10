package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

public class PgmReader {

    public static final Charset CHARSET = Charset.forName("iso8859-1");

    public PgmImage read(String filename) throws IOException {

        PgmImage image = new PgmImage();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), CHARSET))) {

            image.setMagicNumber(reader.readLine().trim());
            image.setComment(reader.readLine().trim());

            StringTokenizer widthAndHeight = new StringTokenizer(reader.readLine().trim());
            int width = Integer.parseInt(widthAndHeight.nextToken());
            int height = Integer.parseInt(widthAndHeight.nextToken());

            //skip over maxvalue
            reader.readLine().trim();

            image.setPixels(readPixels(reader, width, height));

        }

        return image;

    }

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
