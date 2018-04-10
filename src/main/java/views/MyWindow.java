package views;

import models.PGMIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyWindow {

    private String title;
    private JFrame myWin;
    private JButton myBtn;
    private PGMIO myPGM;

    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;

    public MyWindow (final String title) {
        this.title = title;
        this.myWin = new JFrame();
        this.myBtn = new JButton();

        myWin.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void show () {
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (int) ((dimension.getWidth() - WINDOW_WIDTH) / 2);
        final int y = (int) ((dimension.getHeight() - WINDOW_HEIGHT) / 2);

        myWin.add(button("Open a file"));
        myWin.setTitle(title);
        myWin.setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
        myWin.setLayout(null)   ;

        myWin.setVisible(true);
    }

    private JButton button (final String btnTitle) {
        final int x = ((WINDOW_WIDTH - 120) / 2);
        final int y = ((WINDOW_HEIGHT - 40) / 2);

        myBtn.setText(btnTitle);
        myBtn.setBounds(x, y, 120, 40);
        myBtn.setBackground(Color.decode("#4caf50"));
        myBtn.setForeground(Color.WHITE);
        myBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    final FileChooser myFile = new FileChooser();
                    final PGMIO myPGM = new PGMIO();
                    final int image[][] = myPGM.read(myFile.getSelectedFile());
                    final JFrame frame = new JFrame();
                    final PGMImage pgmFile = new PGMImage(myPGM.getImageWidth(), myPGM.getImageHeight(), image);

                    frame.setLayout(new GridLayout(0,2));
                    frame.add(pgmFile);
                    frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    frame.setSize(800,640);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        return myBtn;
    }
}
