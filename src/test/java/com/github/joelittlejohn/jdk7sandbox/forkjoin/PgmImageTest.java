/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PgmImageTest {

    @Test
    public void readPgmFile() throws IOException {
        PgmImage image = new PgmImage("/home/joe/Desktop/test.pgm");
        image.write("/home/joe/Desktop/test2.pgm");
    }

}
