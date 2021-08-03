package Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.File;

import javax.swing.*;

public class DirectoryChooser {

    private static String path;

    public static void createWindow() {
        JFrame frame = new JFrame("Choose a folder");
        createUI(frame, path);
        frame.setSize(560, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
    }

    private static <path> void createUI(final JFrame frame, path String){
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        final JLabel label = new JLabel();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(frame);
        if(option == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            DirectoryChooser.path = file.getPath();
            JOptionPane.showMessageDialog(frame, "Directory Selected: " + DirectoryChooser.path);
        }else{
            JOptionPane.showMessageDialog(frame, "No directory selected!");
            }

        panel.add(label);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    public String getPath(){
        return path;
    }
}