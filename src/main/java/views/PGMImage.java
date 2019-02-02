package views;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class PGMImage extends JPanel {
    public int IMAGE[][];
    public int OUTPUT_IMAGE[][];
    private int IMAGE_WIDTH;
    private int IMAGE_HEIGHT;
    private int R1;
    private int R2;
    private int S1;
    private int S2;

    public PGMImage(int w, int h, int pgm[][], int r1, int r2, int s1, int s2) {
        this.IMAGE_WIDTH  = w;
        this.IMAGE_HEIGHT = h;
        this.IMAGE        = pgm;
        this.OUTPUT_IMAGE = new int[w][h];
        this.R1           = r1;
        this.R2           = r2;
        this.S1           = s1;
        this.S2           = s2;
    }

    public void paint(Graphics g) {
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < IMAGE_WIDTH; x++)
            for (int y = 0; y < IMAGE_HEIGHT; y++) {
                int PIXEL_COLOR = contrastStretching(IMAGE[x][y], R1, R2, S1, S2);

                Color CURRENT_COLOR = new Color(PIXEL_COLOR, PIXEL_COLOR, PIXEL_COLOR);
                CURRENT_COLOR.brighter();
                image.setRGB(x, y, CURRENT_COLOR.getRGB());
                OUTPUT_IMAGE[x][y] = PIXEL_COLOR;
            }
        g.drawImage(image, 0, 0, null);
    }

    private int contrastStretching(int x, float r1, float r2, float s1, float s2) {
        if (0 <= x && x <= r1)
            return (int) (s1/r1 * x);

        if (r1 < x && x <= r2)
            return (int)  (((s2 - s1)/(r2 - r1)) * (x - r1) + s1);

        if (r2 < x && x <= 255)
            return (int)  (((255 - s2)/(255 - r2)) * (x - r2) + s2);

        return 1; // TODO check it!
    }
}
