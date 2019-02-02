package views;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.File;
import java.io.IOException;

public class FileChooser {

    private JFileChooser fChooser;
    private FileNameExtensionFilter extensionFilter;
    private final File entryPath = FileSystemView.getFileSystemView().getHomeDirectory();
    private File selectedFile;

    /**
     * @see: https://www.mkyong.com/swing/java-swing-jfilechooser-example/
     */
    public FileChooser() {
        this.fChooser = new JFileChooser(this.entryPath);
        this.extensionFilter = new FileNameExtensionFilter("PGM files", "pgm");

        fChooser.setDialogTitle("Open a PGM file");
        fChooser.setFileSelectionMode(fChooser.FILES_ONLY);
        fChooser.setMultiSelectionEnabled(false);
        fChooser.setAcceptAllFileFilterUsed(false);
        fChooser.addChoosableFileFilter(extensionFilter);
    }


    public File showOpenFileChrooser () {
        fChooser.setDialogTitle("Open a PGM file");

        if (fChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            selectedFile = fChooser.getSelectedFile();

        return selectedFile;
    }

    public File showSaveFileChooser() {
        fChooser.setDialogTitle("Save PGM file");

        if (fChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            selectedFile = fChooser.getSelectedFile();

        return selectedFile;
    }
}
