package views;

import models.PGMModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MyWindow {
    private JFrame PGMViewer;
    private JFrame ImageView;
    private JFrame controlsView;
    private PGMImage imageData;
    private PGMModel myPGM;
    private FileChooser fileChooserView;
    private int PGMFileData[][];

    private final int WINDOW_WIDTH  = 500;
    private final int WINDOW_HEIGHT = 500;

    public MyWindow () {
        this.PGMViewer    = new JFrame();
        this.ImageView    = new JFrame();
        this.controlsView = new JFrame();
        this.myPGM        = new PGMModel();
        this.fileChooserView = new FileChooser();

    }

    public void showPGMViewer () {
        JButton btnLoadFile = new JButton();

        final int btnLoadFileX = ((WINDOW_WIDTH - 120) / 2);
        final int btnLoadFileY = ((WINDOW_HEIGHT - 40) / 2);
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (int) ((dimension.getWidth() - WINDOW_WIDTH) / 2);
        final int y = (int) ((dimension.getHeight() - WINDOW_HEIGHT) / 2);


        btnLoadFile.setText("Open a file");
        btnLoadFile.setBounds(btnLoadFileX, btnLoadFileY, 120, 40);

        btnLoadFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addImageView(255, 255, 255, 255);
                showControlsPanel();
            }
        });

        PGMViewer.add(btnLoadFile);
        PGMViewer.setTitle("PGM Viewer");
        PGMViewer.setBounds(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
        PGMViewer.setLayout(null);
        PGMViewer.setVisible(true);
        PGMViewer.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    private PGMImage getImage (int image[][], int r1, int r2, int s1, int s2) {
        return new PGMImage(
                myPGM.getImageWidth(),
                myPGM.getImageHeight(),
                image,
                r1,
                r2,
                s1,
                s2
        );
    }

    private void addImageView (int r1, int r2, int s1, int s2) {
        try {
            File openedFile = fileChooserView.showOpenFileChrooser();
            PGMFileData     = myPGM.read(openedFile);
            imageData       = getImage(PGMFileData, r1, r2, s1, s2);

            ImageView.setTitle("PGM Viewer");
            ImageView.setLayout(new GridLayout(0,1));
            ImageView.add(imageData);
            ImageView.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            ImageView.setSize(myPGM.getImageWidth() + 30, myPGM.getImageHeight() + 30);
            ImageView.setLocationRelativeTo(null);
            ImageView.setVisible(true);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void showControlsPanel() {
        final JTextField r1 = new JTextField();
        final JTextField r2 = new JTextField();
        final JTextField s1 = new JTextField();
        final JTextField s2 = new JTextField();

        JButton apply = new JButton("Apply");
        JButton save = new JButton("Save");

        apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onApply(
                    Integer.parseInt(r1.getText()),
                    Integer.parseInt(r2.getText()),
                    Integer.parseInt(s1.getText()),
                    Integer.parseInt(s2.getText())
                );
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onSave();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        controlsView.setTitle("Controls View");
        controlsView.setLayout(new GridLayout(5,1));
        controlsView.add(new JLabel("R1: "));
        controlsView.add(r1);
        controlsView.add(new JLabel("R2: "));
        controlsView.add(r2);
        controlsView.add(new JLabel("S1: "));
        controlsView.add(s1);
        controlsView.add(new JLabel("S2: "));
        controlsView.add(s2);
        controlsView.add(apply);
        controlsView.add(save);
        controlsView.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        controlsView.setSize(300, 200);
        controlsView.setLocationRelativeTo(null);
        controlsView.setVisible(true);
    }


    private void onSave() throws IOException {
        File newFile = fileChooserView.showSaveFileChooser();
        PGMModel.saveToFile(imageData.OUTPUT_IMAGE, newFile);
    }

    private void onApply (int r1, int r2, int s1, int s2) {
        if (r1 <= 255 && r2 <= 255 && s1 <= 255 && s2 <= 255) {
            ImageView.getContentPane().remove(imageData);
            ImageView.repaint();

            imageData   = getImage(PGMFileData, r1, r2, s1, s2);
            PGMFileData = imageData.IMAGE;

            ImageView.add(imageData);
            ImageView.setVisible(true);
            ImageView.repaint();
        }
    }
}
