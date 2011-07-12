package com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class PgmWriter {

    public static final Charset CHARSET = Charset.forName("iso8859-1");

    public void write(PgmImage image, String filename) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), CHARSET))) {
            writer.write(image.getMagicNumber() + "\n");
            writer.write(image.getComment() + "\n");
            writer.write(image.getWidth() + " " + image.getHeight() + "\n");
            writer.write(image.getMaxValue() + "\n");

            for (int[] row : image.getPixels()) {
                for (int pixel : row) {
                    writer.write(String.valueOf(pixel) + " ");
                }
                writer.write("\n");
            }
        }

    }

}
