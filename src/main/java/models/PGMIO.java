package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent workflow for manage PGM /.pgm/ files
 *
 * @see http://netpbm.sourceforge.net/doc/pgm.html#description
 * @see https://www.cs.cmu.edu/~eugene/teach/algs00a/progs/forms.html
 */
public class PGMIO {
    /**
     * Representing the binary PGM file type.
     */
    private static final String MAGIC_TYPE = "P2";

    /**
     * Character indicating a comment.
     */
    private static final char COMMENT = '#';

    /**
     * The maximum gray value.
     */
    private static final int IMAGE_GRAY_LEVEL = 255;

    private static int IMAGE_WIDTH;
    private static int IMAGE_HEIGHT;

    public PGMIO() {}

    /**
     * Reads a GrayScale image from a file in PGM format.
     *
     * @param file the PGM file read from
     * @return two-dimensional byte array representation of the image
     * @throws IOException
     */
    public int[][] read(final File file) throws IOException {
        final BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
        // final DataInputStream dis = new DataInputStream(stream);
        try {
            if (!next(stream).equals(MAGIC_TYPE)) {
                throw new IOException("The file " + file + " is not a binary PGM image.");
            }

            IMAGE_HEIGHT = Integer.parseInt(next(stream));
            IMAGE_WIDTH = Integer.parseInt(next(stream));
            final int max = Integer.parseInt(next(stream));

            if (max < 0 || max > IMAGE_GRAY_LEVEL)
                throw new IOException("The image's maximum gray value must be in range [0, " + IMAGE_GRAY_LEVEL + "].");

            final int[][] image = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
            for (int i = 0; i < IMAGE_WIDTH; ++i) {
                for (int j = 0; j < IMAGE_HEIGHT; ++j) {
                    final int p = stream.read();
                    if (p == -1) {
                        throw new IOException("Reached end-of-file prematurely.");
                    } else if (p > max) {
                        throw new IOException("Pixel value " + p + " outside of range [0, " + max + "].");
                    }

                    image[i][j] = p;
                }
            }
            return image;
        } finally {
            stream.close();
        }
    }

    /**
     * Finds the next whitespace-delimited string in a stream, ignoring any comments.
     *
     * @param stream the stream read from
     * @return the next whitespace-delimited string
     * @throws IOException
     */
    private static String next(final InputStream stream) throws IOException {
        final List<Byte> bytes = new ArrayList<Byte>();
        while (true) {
            final int b = stream.read();

            if (b != -1) {

                final char c = (char) b;
                if (c == COMMENT) {
                    int d;
                    do {
                        d = stream.read();
                    } while (d != -1 && d != '\n' && d != '\r');
                } else if (!Character.isWhitespace(c)) {
                    bytes.add((byte) b);
                } else if (bytes.size() > 0) {
                    break;
                }

            } else {
                break;
            }

        }
        final byte[] bytesArray = new byte[bytes.size()];
        for (int i = 0; i < bytesArray.length; ++i)
            bytesArray[i] = bytes.get(i);
        return new String(bytesArray);
    }

    /**
     * Writes a grayscale image to a file in PGM format.
     *
     * @param image a two-dimensional byte array representation of the image
     * @param file  the file to write to
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static void write(final int[][] image, final File file) throws IOException {
        write(image, file, IMAGE_GRAY_LEVEL);
    }

    /**
     * Writes a grayscale image to a file in PGM format.
     *
     * @param image  a two-dimensional byte array representation of the image
     * @param file   the file to write to
     * @param maxval the maximum gray value
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static void write(final int[][] image, final File file, final int maxval) throws IOException {
        if (maxval > IMAGE_GRAY_LEVEL)
            throw new IllegalArgumentException("The maximum gray value cannot exceed " + IMAGE_GRAY_LEVEL + ".");
        final BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
        try {
            stream.write(MAGIC_TYPE.getBytes());
            stream.write("\n".getBytes());
            stream.write(Integer.toString(image[0].length).getBytes());
            stream.write(" ".getBytes());
            stream.write(Integer.toString(image.length).getBytes());
            stream.write("\n".getBytes());
            stream.write(Integer.toString(maxval).getBytes());
            stream.write("\n".getBytes());
            for (int i = 0; i < image.length; ++i) {
                for (int j = 0; j < image[0].length; ++j) {
                    final int p = image[i][j];
                    if (p < 0 || p > maxval)
                        throw new IOException("Pixel value " + p + " outside of range [0, " + maxval + "].");
                    stream.write(image[i][j]);
                }
            }
        } finally {
            stream.close();
        }
    }

    public int getImageWidth () {
        return IMAGE_WIDTH;
    }

    public int getImageHeight () {
        return IMAGE_HEIGHT;
    }
}
