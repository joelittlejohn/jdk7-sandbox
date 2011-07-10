package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class PgmImageTest {

    @Test
    public void pixelArrayDecidesWidthHeightAndMax() throws IOException {

        PgmImage image = new PgmImage();
        image.setPixels(new int[][] {
            { 1, 2, 3, 4, 5, 6, 7 },
            { 3, 4, 5, 6, 7, 8, 9 }
        });

        assertThat(image.getWidth(), is(7));
        assertThat(image.getHeight(), is(2));
        assertThat(image.getMaxValue(), is(9));

    }

}
