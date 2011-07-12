package com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.ParallelFilter;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.SequentialFilter;
import com.github.joelittlejohn.jdk7sandbox.forkjoin.UniformNoise;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * UI frame used to render a given image and watch for updates to the image
 * pixels.
 */
public class PgmFrame extends JFrame {

    /**
     * Create a new frame with the given image.
     *
     * @param image the image to render inside this frame.
     */
    public PgmFrame(PgmImage image) {
        this.setContentPane(new PgmPanel(image));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 0, 0);
        this.pack();
    }

    /**
     * The content panel containing the image for this UI frame.
     */
    private class PgmPanel extends JPanel implements Observer {

        private final PgmImage image;

        /**
         * Create a new panel containing the given image.
         *
         * @param image the image to render in this panel.
         */
        public PgmPanel(PgmImage image) {
            this.image = image;
            this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

            image.addObserver(this);
        }

        /**
         * Invoked when the image which this panel is observing changes.
         *
         * @param o the image being observed
         * @param arg the additional argument which was passed by the observable.
         */
        @Override
        public void update(Observable o, Object arg) {
            this.repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(image.getBufferedImage(), 0, 0, null);
        }

    }

    public static void main(String[] args) throws IOException {

        PgmImage image = new PgmReader().read("src/main/resources/hdrweir.pgm");

        new PgmFrame(image).setVisible(true);

        //new SequentialFilter(new UniformNoise()).apply(image);
        new ParallelFilter(new UniformNoise()).apply(image);

    }

}
