package views;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class Filechooser {

    private JFileChooser fChooser;

    /**
     * @see: https://www.mkyong.com/swing/java-swing-jfilechooser-example/
     */
    public Filechooser() {
        this.fChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("PGM files", "pgm");

        fChooser.setDialogTitle("Open a PGM file");
        fChooser.setFileSelectionMode(fChooser.FILES_ONLY);
        fChooser.setMultiSelectionEnabled(false);
        fChooser.setAcceptAllFileFilterUsed(false);
        fChooser.addChoosableFileFilter(extensionFilter);

        if (fChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fChooser.getSelectedFile();
            System.out.println(((File) selectedFile).getAbsolutePath());
        }
    }
}
