package views;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class PGMImage extends JPanel {
    private int IMAGE_WIDTH;
    private int IMAGE_HEIGHT;
    private int IMAGE[][];

    public PGMImage(int w, int h, int pgm[][]) {
        this.IMAGE_WIDTH = w;
        this.IMAGE_HEIGHT = h;
        this.IMAGE = pgm;
    }

    public void paint(Graphics g) {
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < IMAGE_WIDTH; x++) {
            for (int y = 0; y < IMAGE_HEIGHT; y++) {
                int PIXEL_COLOR = IMAGE[x][y];
                Color CURRENT_COLOR = new Color(PIXEL_COLOR, PIXEL_COLOR, PIXEL_COLOR);
                image.setRGB(x, y, CURRENT_COLOR.getRGB());
            }
        }
        g.drawImage(image, 0, 0, null);
    }
}