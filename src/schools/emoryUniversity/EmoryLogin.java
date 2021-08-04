package schools.emoryUniversity;

import schools.KennesawState.KennesawGUICore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmoryLogin {

    String programStatus;

    KennesawGUICore egc1 = new KennesawGUICore();

    public EmoryLogin(WebDriver driver){
        driver.get("https://login.emory.edu/idp/profile/SAML2/Redirect/SSO?execution=e1s1");

        //getting the username
        WebElement username = driver.findElement(By.id("userName"));

        //getting the password
        WebElement password = driver.findElement(By.id("password"));

        //finding the submit button
        WebElement loginButton = driver.findElement(By.id("loginbutton"));

        //user's user and password goes here
        username.sendKeys(egc1.getUsername());
        password.sendKeys(egc1.getPassword());
        loginButton.click();
    }
}