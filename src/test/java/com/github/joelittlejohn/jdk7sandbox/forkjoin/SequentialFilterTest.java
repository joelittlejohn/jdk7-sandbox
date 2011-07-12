package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmWriter;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmReader;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;

public class SequentialFilterTest {

    private static PgmImage original;

    @BeforeClass
    public static void readImage() throws IOException {
        original = new PgmReader().read("src/main/resources/hdrweir.pgm");
    }

    @Test
    public void filterProducesBlurredImage() throws IOException {

        long start = System.currentTimeMillis();

        final PgmImage blurred = new SequentialFilter(new Average()).apply(original);

        long end = System.currentTimeMillis();

        System.out.println("Sequential (Blur): " + (end-start)/1000d + " secs");

        new PgmWriter().write(blurred, "src/main/resources/hdrweir_blurred.pgm");

    }

    @Test
    public void filterProducesUniformNoiseImage() throws IOException {

        long start = System.currentTimeMillis();

        final PgmImage uniformNoise = new SequentialFilter(new UniformNoise()).apply(original);

        long end = System.currentTimeMillis();

        System.out.println("Sequential (Uniform Noise): " + (end-start)/1000d + " secs");

        new PgmWriter().write(uniformNoise, "src/main/resources/hdrweir_uniform_noise.pgm");

    }

    @Test
    public void filterProducesRandomNoiseImage() throws IOException {

        long start = System.currentTimeMillis();

        final PgmImage randomNoise = new SequentialFilter(new RandomNoise()).apply(original);

        long end = System.currentTimeMillis();

        System.out.println("Sequential (Random Noise): " + (end-start)/1000d + " secs");

        new PgmWriter().write(randomNoise, "src/main/resources/hdrweir_random_noise.pgm");

    }

}
