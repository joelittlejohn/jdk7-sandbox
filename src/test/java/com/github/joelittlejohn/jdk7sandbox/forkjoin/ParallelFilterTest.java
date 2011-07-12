package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmWriter;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmReader;
import java.io.IOException;
import org.junit.Test;

public class ParallelFilterTest {

    @Test
    public void filterProducesBlurredImage() throws IOException {

        final PgmImage original = new PgmReader().read("src/main/resources/hdrweir.pgm");

        long start = System.currentTimeMillis();

        final PgmImage blurred = new ParallelFilter(new Average()).apply(original);

        long end = System.currentTimeMillis();

        System.out.println("Parallel (Blur): " + (end-start)/1000d + " secs");

        new PgmWriter().write(blurred, "src/main/resources/hdrweir_blurred.pgm");

    }

    @Test
    public void filterProducesRandomNoiseImage() throws IOException {

        final PgmImage original = new PgmReader().read("src/main/resources/hdrweir.pgm");

        long start = System.currentTimeMillis();

        final PgmImage uniformNoise = new ParallelFilter(new UniformNoise()).apply(original);

        long end = System.currentTimeMillis();

        System.out.println("Parallel (Uniform Noise): " + (end-start)/1000d + " secs");

        new PgmWriter().write(uniformNoise, "src/main/resources/hdrweir_uniform_noise.pgm");

    }

    @Test
    public void filterProducesUniformNoiseImage() throws IOException {

        final PgmImage original = new PgmReader().read("src/main/resources/hdrweir.pgm");

        long start = System.currentTimeMillis();

        final PgmImage randomNoise = new ParallelFilter(new RandomNoise()).apply(original);

        long end = System.currentTimeMillis();

        System.out.println("Parallel (Random Noise): " + (end-start)/1000d + " secs");

        new PgmWriter().write(randomNoise, "src/main/resources/hdrweir_random_noise.pgm");

    }

}
