package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {


    @Test
    public void  loginsuccess(){
        driver.get("https://todo-react-frontend-one.vercel.app/login");
        LoginPage loginpage=new LoginPage(driver);
        loginpage.login("user1@gmail.com","user1@1234");
        System.out.println(driver.getCurrentUrl());
        System.out.println("<-----Login Test completed----->");
    }
}
