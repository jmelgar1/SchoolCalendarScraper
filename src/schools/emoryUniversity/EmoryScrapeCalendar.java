package schools.emoryUniversity;

import schools.KennesawState.KennesawGUICore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmoryScrapeCalendar {

    KennesawGUICore egc1 = new KennesawGUICore();

    public EmoryScrapeCalendar(WebDriver driver) throws InterruptedException, IOException {
        driver.manage().window().maximize();

        driver.get("https://canvas.emory.edu/");

        //creates list for calendar events
        List<WebElement> allElements = driver.findElements(By.xpath("/html//a[@id='global_nav_calendar_link']"));


        //Check if list is empty
        if(allElements.size() == 0){
            egc1.setLabel("There are no calendar events available.");
            egc1.getProcess().paintImmediately(egc1.getProcess().getVisibleRect());
        }

        //finds all calendar events
        for (WebElement element : allElements) {

            //writes all calendar events to file
            try
            {
                BufferedWriter bw = new BufferedWriter(
                        new FileWriter("src/Files/ScrapeOutput"));
                bw.write(element.getText() + "\n");
                bw.close();
            } catch(Exception e) {
                return;
            }
        }
    }
}