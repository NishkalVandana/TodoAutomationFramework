package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest {

    @DataProvider(name="TaskData")
    public  Object[][] getTask(){
        return new Object[][]{
                {"Walk 20 min","Medium"},
                {"Eat protein food","High"},
                {"Drink 2 litres of water","High"}
        };
    }
    @Test
    public void login(){
        driver.get("https://todo-react-frontend-one.vercel.app/login");
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login("user1@gmail.com","user1@1234");
    }
    @Test(priority = 1,dataProvider = "TaskData")
    public void addTask(String Task,String priority){
        DashboardPage dashboardPage=new DashboardPage(driver);
        String addedTaskText = dashboardPage.setAddedTask(Task, priority);
        Assert.assertEquals(addedTaskText, Task);
    }

    @Test(priority = 2,dataProvider = "TaskData")
    public void MarkDone(String Task,String priority){
        DashboardPage dashboardPage=new DashboardPage(driver);
        String status=dashboardPage.markTaskDone(Task);
        Assert.assertEquals(status, "Completed");
    }

    @Test(priority = 3,dataProvider = "TaskData")
    public void delete(String Task,String priority) {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.deleteTask(Task);
    }
    @Test(priority = 4)
    public void logout(){
        DashboardPage dashboardPage=new DashboardPage(driver);
        String url=driver.getCurrentUrl();
        String afterurl=dashboardPage.setLogout();
        Assert.assertNotEquals(afterurl,url);
        System.out.println("<-----DashboardPage Test Completed----->");
    }
}
