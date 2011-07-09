package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;
import org.apache.commons.io.IOUtils;

public class PgmImage {

    public static final Charset CHARSET = Charset.forName("iso8859-1");

    private final String path;
    private final int[][] pixels;

    private final String magicNumber;
    private final String comment;
    private final int width;
    private final int height;
    private final int maxValue;

    public PgmImage(String path) throws IOException {
        this.path = path;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), CHARSET))) {

            this.magicNumber = reader.readLine().trim();
            this.comment = reader.readLine().trim();

            StringTokenizer widthAndHeight = new StringTokenizer(reader.readLine().trim());
            this.width = Integer.parseInt(widthAndHeight.nextToken());
            this.height = Integer.parseInt(widthAndHeight.nextToken());

            this.maxValue = Integer.parseInt(reader.readLine().trim());

            this.pixels = getPixels(reader);

        }
    }

    private int[][] getPixels(BufferedReader reader) throws IOException {

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

    public void write(String outputFile) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), CHARSET))) {
            writer.write(magicNumber + "\n");
            writer.write(comment + "\n");
            writer.write(width + " " + height + "\n");
            writer.write(findLargest(pixels) + "\n");

            for (int[] row : this.pixels) {
                for (int pixel : row) {
                    writer.write(String.valueOf(pixel) + " ");
                }
                writer.write("\n");
            }
        }

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

}
