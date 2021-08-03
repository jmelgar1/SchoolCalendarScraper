package KennesawState;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmoryLogin {

    String programStatus;

    EmoryGUICore kgc1 = new EmoryGUICore();

    public EmoryLogin(WebDriver driver){
        driver.get("https://ksufed.kennesaw.edu/adfs/ls/?SAMLRequest=jdE9T8MwEAbgHYn%2fEHmP7dhpPqykUgVLpbK0wMCCnOTaRiR2" +
                   "8DktPx%2bXqoKR7T70So%2fuqtXsj2YLnzOgj9aPNUE9Du7av8uFyLSGDNKGp1nGm0RDm%2b8hLcpEJmVHoldw2FtTE0E5idaIM" +
                   "6wNem18GHGRxLyIuXxOEiWE4hmVQhQyk28kWiGC8yH7YA3OI7gduFPfwst2U5Oj9xMqxj7AGEB9pqceznTGA4VuZp0Y2DAxHehs" +
                   "sIfesAt6c6lo2JHoaxwM1mR2RlmNPSqjR0DlW7VbPW1UsKrJWW9bO5Dl%2fV0UVT9y95%2bgvrnJ8qYsFvlell0ea5nnccr1Ii5" +
                   "FLuJF02QSdDjhHqgHE66CtHH94ehx0i3Q1o6%2f9IpdEQFUsb9vWX4D");

        //getting the username
        WebElement username = driver.findElement(By.id("userNameInput"));

        //getting the password
        WebElement password = driver.findElement(By.id("passwordInput"));

        //finding the submit button
        WebElement loginButton = driver.findElement(By.id("submitButton"));

        //user's user and password goes here
        username.sendKeys(kgc1.getUsername());
        password.sendKeys(kgc1.getPassword());
        loginButton.click();


        //logging in to D2L
        try{
            driver.switchTo().frame("duo_iframe");
        } catch (Exception e) {
            kgc1.setLabel("Incorrect Login");
            kgc1.getProcess().paintImmediately(kgc1.getProcess().getVisibleRect());
        }


        //Finding the submit for verification button
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try{
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='auth_methods']/fieldset/div[1]/button[@type='submit']"))).click();
        } catch (Exception e) {
            System.out.println("Could not find webpage, please try again.");
        }
    }
}