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

public class PgmFrame extends JFrame {

    public PgmFrame(PgmImage image) {
        this.setContentPane(new PgmPanel(image));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 0, 0);
        this.pack();
    }

    public static void main(String[] args) throws IOException {
        PgmImage image = new PgmReader().read("src/main/resources/hdrweir.pgm");

        PgmFrame frame = new PgmFrame(image);
        frame.setVisible(true);

        //new SequentialFilter(new UniformNoise()).apply(image);
        new ParallelFilter(new UniformNoise()).apply(image);

    }

    private class PgmPanel extends JPanel implements Observer {

        private final PgmImage image;

        public PgmPanel(PgmImage image) {
            this.image = image;
            this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

            image.addObserver(this);
        }

        @Override
        public void update(Observable o, Object arg) {
            this.repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(image.getBufferedImage(), 0, 0, null);
        }

    }

}
