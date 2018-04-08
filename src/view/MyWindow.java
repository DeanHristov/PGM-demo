package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindow {

    private String title;
    private JFrame myWin;

    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;

    public MyWindow (final String title) {
        this.title = title;
        this.myWin = new JFrame();

        myWin.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent ev) {
                System.out.println("Closing...");
                System.exit(0);
            }
        });
    }

    public void show () {
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (int) ((dimension.getWidth() - WINDOW_WIDTH) / 2);
        final int y = (int) ((dimension.getHeight() - WINDOW_HEIGHT) / 2);

        myWin.setTitle(title);
        myWin.setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
        myWin.setLayout(null);

        myWin.setVisible(true);
    }
}
