package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    By registerbtn= By.xpath("//button[contains(text(),'Register')]");
    By loginBtn = By.xpath("//button[contains(text(),'Login')]");
    public  HomePage(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void clickregister(){
        wait.until(ExpectedConditions.elementToBeClickable(registerbtn));
        driver.findElement(registerbtn).click();
    }
    public  void clicklogin(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        driver.findElement(loginBtn).click();
    }
}
