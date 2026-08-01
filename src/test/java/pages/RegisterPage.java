package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    WebDriver driver;
    WebDriverWait wait;
    By username=By.id("username");
    By email=By.id("email");
    By password=By.id("password");
    By btn=By.cssSelector("button[type='submit']");
    By navigatelogin=By.xpath("//a[contains(text(),'Login')]");
    public  RegisterPage(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void  register(String user,String mail,String pass){
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        driver.findElement(username).sendKeys(user);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(btn).click();
        System.out.println("Registered Successfully");
    }
    public void duplicateRegister(String user,String mail,String pass){
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        driver.findElement(username).sendKeys(user);
        driver.findElement(email).sendKeys(mail);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(btn).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert=driver.switchTo().alert();
        String text=alert.getText();
        alert.accept();
        System.out.println(text);
    }
    public void setNavigatelogin(){
        wait.until(ExpectedConditions.elementToBeClickable(navigatelogin));
        driver.findElement(navigatelogin).click();
    }
}
