package KennesawState;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EmoryGUICore extends JPanel implements ActionListener{

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel process;
    private static String user;
    private static String password;
    private static JPanel panel;
    private static JFrame frame;

    EmoryWriteToCSV wtc1 = new EmoryWriteToCSV();

    public static void KennesawStateGUIcore() {

        //create kennesaw state GUI
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350, 200);
        frame.add(panel);
        frame.setTitle("Kennesaw State");

        panel.setLayout(null);

        userLabel = new JLabel("NetID");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 220, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 220, 25);
        panel.add(passwordText);

        button = new JButton("Submit");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new EmoryGUICore());
        panel.add(button);

        process = new JLabel("");
        process.setBounds(10, 110, 300, 25);
        panel.add(process);

        process.setText("Disclaimer: No login information is collected.");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);

    }

    //is submit button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        setUsername(user);
        setPassword(password);

        process.setText("Getting Login...");
        process.paintImmediately(process.getVisibleRect());

        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");

        process.setText("Logging in...");
        process.paintImmediately(process.getVisibleRect());

        WebDriver driver = new ChromeDriver(options);

        process.setText("Waiting for DUO verification...");
        process.paintImmediately(process.getVisibleRect());

        new EmoryLogin(driver);

        try {
            new EmoryScrapeCalendar(driver);
            process.setText("Getting calendar events...");
            process.paintImmediately(process.getVisibleRect());
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        wtc1.writeCSV();

        process.setText("Finished! Please select a location for the file!");
    }

    public void setUsername(String user){
        this.user = user;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setLabel(String string){
        process.setText(string);
    }
    public String getUsername(){
        return user;
    }
    public String getPassword(){
        return password;
    }
    public String getLabel(){
        return process.getText();
    }
    public JLabel getProcess(){
        return process;
    }
}