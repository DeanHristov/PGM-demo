package views;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.File;

public class FileChooser {

    private JFileChooser fChooser;
    private final File entryPath = FileSystemView.getFileSystemView().getHomeDirectory();
    private File selectedFile;

    /**
     * @see: https://www.mkyong.com/swing/java-swing-jfilechooser-example/
     */
    public FileChooser() {
        this.fChooser = new JFileChooser(this.entryPath);
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("PGM files", "pgm");

        fChooser.setDialogTitle("Open a PGM file");
        fChooser.setFileSelectionMode(fChooser.FILES_ONLY);
        fChooser.setMultiSelectionEnabled(false);
        fChooser.setAcceptAllFileFilterUsed(false);
        fChooser.addChoosableFileFilter(extensionFilter);

        if (fChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fChooser.getSelectedFile();
            this.selectedFile = fChooser.getSelectedFile(); // ((File) selectedFile).getAbsolutePath();
        }
    }

    public File getSelectedFile() {
        return selectedFile;
    }
}
