package schools.KennesawState;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KennesawLogin {

    String programStatus;

    KennesawGUICore kgc1 = new KennesawGUICore();

    public KennesawLogin(WebDriver driver){
        driver.get("https://kennesaw.view.usg.edu/");

        /*driver.get("https://ksufed.kennesaw.edu/adfs/ls/?SAMLRequest=jdE9" +
                   "b4MwEAbgvVL%2fA%2fKODQYMWBApapdI6ZK0HbpUxhwJKtjUZ5L%2" +
                   "b%2fJJGUTt2uw%2b90qO7aj37o9nB5wzog81jTVCNg7v270J3uRIi" +
                   "0W2UpgXvlEjytOkKXfCkEUqT4BUc9tbUhNOIBBvEGTYGvTJ%2bGUU" +
                   "8DqMijMVzzGWWySSlcckzkZZvJFgjgvNL9sEanEdwe3CnXsPLbluT" +
                   "o%2fcTSsY%2bwBhAdaanHs50xgOFdmYtH9gwMbXQ2WAPvWEX9PZS0" +
                   "WVHgq9xMFiT2RlpFfYojRoBpddyv37aysUqJ2e91XYgq%2fu7IKh%" +
                   "2b5O4%2fQXVzk9VNWWR5l5RtHqokz8M0UllY8pyHWdOIBJRQqgPqw" +
                   "SxXQdq4%2fnD0OCkNVNvxl16xK2IBVezvW1bf");
         */

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