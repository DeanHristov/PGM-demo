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

    private int R1;
    private int R2;
    private int S1;
    private int S2;

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
//        myBtn.setBackground(Color.decode("#4caf50"));
//        myBtn.setForeground(Color.WHITE);
        myBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addImageView();
                addButtonPanel();
            }
        });

        return myBtn;
    }

    private void addImageView () {
        try {
            FileChooser myFile = new FileChooser();
            PGMIO myPGM = new PGMIO();
            int image[][] = myPGM.read(myFile.getSelectedFile());
            JFrame frame = new JFrame();
            JFrame frame1 = new JFrame();
            PGMImage pgmFile = new PGMImage(myPGM.getImageWidth(), myPGM.getImageHeight(), image);

            frame.setLayout(new GridLayout(0,1));
            frame.add(pgmFile);
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            frame.setSize(myPGM.getImageWidth(), myPGM.getImageHeight());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void addButtonPanel () {
        JFrame frame1 = new JFrame();
        final JTextField r1 = new JTextField();
        final JTextField r2 = new JTextField();
        final JTextField s1 = new JTextField();
        final JTextField s2 = new JTextField();
        JButton apply = new JButton("Apply");
        JButton save = new JButton("Save");

        apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                R1 = Integer.parseInt(r1.getText());
                R2 = Integer.parseInt(r2.getText());
                S1 = Integer.parseInt(s1.getText());
                S2 = Integer.parseInt(s2.getText());

                System.out.println("R1: " + R1 + ", R2: " + R2 + ", S1: " + S1 + ", S2: " + S2);
                addImageView();
            }
        });
        frame1.setLayout(new GridLayout(5,1));
        frame1.add(new JLabel("R1: "));
        frame1.add(r1);
        frame1.add(new JLabel("R2: "));
        frame1.add(r2);
        frame1.add(new JLabel("S1: "));
        frame1.add(s1);
        frame1.add(new JLabel("S2: "));
        frame1.add(s2);
        frame1.add(apply);
        frame1.add(save);
        frame1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        frame1.setSize(300, 200);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}
