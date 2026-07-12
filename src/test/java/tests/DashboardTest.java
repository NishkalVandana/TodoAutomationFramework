package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest {
    public  String Task="Drink 2 litres of Water";
    @Test(priority = 1)
    public void addTask(){
        driver.get("https://todo-react-frontend-one.vercel.app/login");
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login("user2@gmail.com","user2@1234");
        DashboardPage dashboardPage=new DashboardPage(driver);
        String addedTaskText = dashboardPage.setAddedTask(Task, "High");
        Assert.assertEquals(addedTaskText, Task);
    }
    @Test(priority = 2)
    public void MarkDone(){
        DashboardPage dashboardPage=new DashboardPage(driver);
        String status=dashboardPage.markTaskDone(Task);
        Assert.assertEquals(status, "Completed");
    }
    @Test(priority = 3)
    public void delete(){
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.deleteTask(Task);
    }
    @Test(priority = 4)
    public void logout(){
        DashboardPage dashboardPage=new DashboardPage(driver);
        String url=driver.getCurrentUrl();
        String afterurl=dashboardPage.setLogout();
        Assert.assertNotEquals(afterurl,url);
    }
}
