package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindow {

    private String title;
    private JFrame myWin;
    private JButton myBtn;

    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;

    public MyWindow (final String title) {
        this.title = title;
        this.myWin = new JFrame();
        this.myBtn = new JButton();

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
                new Filechooser();
            }
        });

        return myBtn;
    }
}
