package schools.KennesawState;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class KennesawScrapeCalendar {

    KennesawGUICore kgc1 = new KennesawGUICore();

    public KennesawScrapeCalendar(WebDriver driver) throws InterruptedException, IOException {
        driver.manage().window().maximize();

        //Give user time to authenticate with DUO
        FluentWait<WebDriver> webDriverWait = new WebDriverWait(driver, 30).pollingEvery(Duration.ofSeconds(2));
        webDriverWait.until(ExpectedConditions.urlContains("https://kennesaw.view.usg.edu/d2l/home"));

        driver.get("https://kennesaw.view.usg.edu/d2l/le/calendar/6629");

        //creates list for calendar events
        List<WebElement> allElements = driver.findElements(By.xpath("//*[@id='eventIds']/ul"));


        //Check if list is empty
        if(allElements.size() == 0){
            kgc1.setLabel("There are no calendar events available.");
            kgc1.getProcess().paintImmediately(kgc1.getProcess().getVisibleRect());
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