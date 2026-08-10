package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class DashboardPage {
    WebDriverWait wait;
    WebDriver driver;
    By taskInput = By.cssSelector("input[placeholder='Enter task...']");
    By priorityDropdown = By.cssSelector("select.border.rounded-xl.px-4.py-2");
    By addBtn=By.xpath("//button[contains(text(),'Add Task')]");
    By logout=By.xpath("//button[contains(text(),'Logout')]");

    public DashboardPage(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public  By Task(String task){
        return By.xpath("//p[@class='flex-1 text-center' and contains(text(),'" + task + "')]");
    }
    public By deleteButtonFor(String taskName) {
        return By.xpath("//p[text()='" + taskName + "']/following-sibling::button[text()='Delete']");
    }

    public By markDoneButtonFor(String taskName) {
        return By.xpath("//p[text()='" + taskName + "']/following-sibling::button[text()='Mark Done']");
    }
    public By changedStatus(String taskName) {
        return By.xpath("//p[text()='" + taskName + "']/following-sibling::button[contains(text(),'Completed')]");
    }
    public String setAddedTask(String task,String pr){
        driver.get(ConfigReader.getProperty("dashboardurl"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(taskInput));
        driver.findElement(taskInput).sendKeys(task);
        Select priority=new Select(driver.findElement(priorityDropdown));
        priority.selectByVisibleText(pr);
        driver.findElement(addBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Task(task)));
        return driver.findElement(Task(task)).getText();

    }
    public void deleteTask(String taskName) {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtonFor(taskName)));
        driver.findElement(deleteButtonFor(taskName)).click();
    }

    public String markTaskDone(String taskName) {
        wait.until(ExpectedConditions.elementToBeClickable(markDoneButtonFor(taskName)));
        driver.findElement(markDoneButtonFor(taskName)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(changedStatus(taskName)));
        return driver.findElement(changedStatus(taskName)).getText();

    }
    public String setLogout(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logout));
        driver.findElement(logout).click();
        wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("loginurl")));
        return  driver.getCurrentUrl();
    }

}
