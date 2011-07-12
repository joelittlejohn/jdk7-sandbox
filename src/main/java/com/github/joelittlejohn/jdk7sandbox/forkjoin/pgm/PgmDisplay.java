/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.Average;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.ParallelFilter;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.RandomNoise;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.SequentialFilter;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.UniformNoise;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PgmDisplay extends JPanel implements Observer {

    private final PgmImage image;

    public PgmDisplay(PgmImage image) {
        this.image = image;
        image.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        //g.clearRect(0, 0, image.getWidth(), image.getHeight());
        g.drawImage(getGrayscale(), 0, 0, null);
    }

    private BufferedImage getGrayscale() {
        byte[] data = new byte[image.getHeight()*image.getWidth()];

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                data[(i * image.getWidth()) + j] = (byte) image.getPixels()[i][j];
            }
        }

        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorModel cm = new ComponentColorModel(cs, new int[] {8}, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        SampleModel sm = cm.createCompatibleSampleModel(image.getWidth(), image.getHeight());
        DataBufferByte db = new DataBufferByte(data, image.getWidth() * image.getHeight());
        WritableRaster raster = Raster.createWritableRaster(sm, db, null);
        BufferedImage result = new BufferedImage(cm, raster, true, null);

        return result;
    }

    public static void main(String[] args) throws IOException {
        PgmImage image = new PgmReader().read("src/main/resources/hdrweir.pgm");

        PgmDisplay p = new PgmDisplay(image);

        JFrame f = new JFrame("The Frame");
        f.setContentPane(p);
        f.setVisible(true);
        p.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        f.pack();

        //new SequentialFilter(new UniformNoise()).apply(image);
        new ParallelFilter(new UniformNoise()).apply(image);

    }

}
