package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import java.io.IOException;
import org.junit.Test;

public class SequentialFilterTest {

    @Test
    public void filterProducesBlurredImage() throws IOException {

        final PgmImage original = new PgmReader().read("/home/joe/Desktop/hdrweir.pgm");

        long start = System.currentTimeMillis();

        final PgmImage blurred = new SequentialFilter(new Average()).apply(original);

        long end = System.currentTimeMillis();

        System.out.println("Sequential (Blur): " + (end-start)/1000d + " secs");

        new PgmWriter().write(blurred, "/home/joe/Desktop/hdrweir_blurred.pgm");

    }

    @Test
    public void filterProducesRandomNoiseImage() throws IOException {

        final PgmImage original = new PgmReader().read("/home/joe/Desktop/hdrweir.pgm");

        long start = System.currentTimeMillis();

        final PgmImage uniformNoise = new SequentialFilter(new UniformNoise()).apply(original);

        long end = System.currentTimeMillis();

        System.out.println("Sequential (Uniform Noise): " + (end-start)/1000d + " secs");

        new PgmWriter().write(uniformNoise, "/home/joe/Desktop/hdrweir_uniform_noise.pgm");

    }

    @Test
    public void filterProducesUniformNoiseImage() throws IOException {

        final PgmImage original = new PgmReader().read("/home/joe/Desktop/hdrweir.pgm");

        long start = System.currentTimeMillis();

        final PgmImage randomNoise = new SequentialFilter(new RandomNoise()).apply(original);

        long end = System.currentTimeMillis();

        System.out.println("Sequential (Random Noise): " + (end-start)/1000d + " secs");

        new PgmWriter().write(randomNoise, "/home/joe/Desktop/hdrweir_random_noise.pgm");

    }

}
