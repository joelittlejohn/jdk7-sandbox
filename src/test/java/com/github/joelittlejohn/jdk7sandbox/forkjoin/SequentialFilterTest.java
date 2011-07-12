package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmWriter;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmReader;
import java.io.IOException;
import org.junit.Test;

public class SequentialFilterTest {

    @Test
    public void filterProducesBlurredImage() throws IOException {

        final PgmImage image = new PgmReader().read("src/main/resources/hdrweir.pgm");

        long start = System.currentTimeMillis();

        new SequentialFilter(new Average()).apply(image);

        long end = System.currentTimeMillis();

        System.out.println("Sequential (Blur): " + (end-start)/1000d + " secs");

        new PgmWriter().write(image, "target/hdrweir_blurred.pgm");

    }

    @Test
    public void filterProducesUniformNoiseImage() throws IOException {

        final PgmImage image = new PgmReader().read("src/main/resources/hdrweir.pgm");

        long start = System.currentTimeMillis();

        new SequentialFilter(new UniformNoise()).apply(image);

        long end = System.currentTimeMillis();

        System.out.println("Sequential (Uniform Noise): " + (end-start)/1000d + " secs");

        new PgmWriter().write(image, "target/hdrweir_uniform_noise.pgm");

    }

    @Test
    public void filterProducesRandomNoiseImage() throws IOException {

        final PgmImage image = new PgmReader().read("src/main/resources/hdrweir.pgm");

        long start = System.currentTimeMillis();

        new SequentialFilter(new RandomNoise()).apply(image);

        long end = System.currentTimeMillis();

        System.out.println("Sequential (Random Noise): " + (end-start)/1000d + " secs");

        new PgmWriter().write(image, "target/hdrweir_random_noise.pgm");

    }

}
