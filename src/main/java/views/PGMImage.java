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
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < IMAGE_HEIGHT; y++) {
            for (int x = 0; x < IMAGE_WIDTH; x++) {

                // https://www.dyclassroom.com/image-processing-project/how-to-create-a-random-pixel-image-in-java
                int rr = (int) (Math.random() * 256); // Debug - Random Red color
                int gg = (int) (Math.random() * 256); // Debug - Random Green color
                int bb = (int) (Math.random() * 256); // Debug - Random Blue color

                Color newColor = new Color(rr, gg, bb); // Debug - Generate random color
                image.setRGB(y, x, newColor.getRGB());

//                System.out.println(IMAGE[y][x] + " X: " +x+ "Y: " +y);
//                Color newColor = new Color(IMAGE[y][x], IMAGE[y][x], IMAGE[y][x]);
//                image.setRGB(y, x, newColor.getRGB());
            }
        }
        g.drawImage(image, 0, 0, null);
    }

    int toRGB(float value) {
        int part = Math.round(value * 255);

        return part * 0x10101;
    }
}