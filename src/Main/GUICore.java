package Main;

import schools.KennesawState.KennesawGUICore;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICore extends JFrame {
    private JPanel rootPanel;
    private JSlider jSlider;
    private JSplitPane jSplitPane;
    private JPanel MainPanel;
    private JScrollPane jScrollPane;
    private JButton kennesawStateButton;
    private JButton emoryButton;
    private JButton georgiaStateButton;
    private JTable table1;
    private JTextArea step1ChooseATextArea;
    private JButton browseButton;
    private JTextArea textArea1;
    private JFrame frame;
    ImageIcon imageKSU = new ImageIcon("images/ksu-logo.png");
    ImageIcon imageEmory = new ImageIcon("images/emory-logo.png");
    ImageIcon imageGeorgiaState = new ImageIcon("images/gsu-logo.png");

    DirectoryChooser dc1 = new DirectoryChooser();

    String version = "1.0.1-a.1";

    public GUICore() {

        setContentPane(MainPanel);

        setTitle("School Calendar Scraper " + version);
        setSize(800, 600);

        createTable();

        //Kennesaw State Button
        kennesawStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dc1.getPath() == null){
                    JOptionPane.showMessageDialog(frame, "Please select a directory!");
                } else {
                    KennesawGUICore kgc1 = new KennesawGUICore();
                    kgc1.KennesawStateGUIcore();
                }
            }
        });

        //Browse directories button
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dc1.createWindow();
            }
        });
    }

    public void createTable(){
        Object[][] data = {
                {"Kennesaw State", "Online"},
                {"Emory University", "Offline"},
                {"Georgia State", "Offline"},
                {"University of Georgia", "Coming soon"},
                {"Georgia Tech", "Coming soon"},
                {"Georgia Southern", "Coming soon"},
                {"Georgia College", "Coming soon"},
                {"University of North Georgia", "Coming soon"},
                {"Berry College", "Coming soon"}
        };
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"School", "Status"}
        ));
    }

}