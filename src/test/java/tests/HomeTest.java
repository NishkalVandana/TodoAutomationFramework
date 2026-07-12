package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTest extends BaseTest {
    @Test
    public void clickRegister(){
        driver.get("https://todo-react-frontend-one.vercel.app");
        HomePage home=new HomePage(driver);
        String actual=driver.getCurrentUrl();
        home.clickregister();
        Assert.assertNotEquals(actual,driver.getCurrentUrl());
    }

    @Test
    public void clickLogin(){
        driver.get("https://todo-react-frontend-one.vercel.app");
        HomePage home=new HomePage(driver);
        String actual=driver.getCurrentUrl();
        home.clicklogin();
        Assert.assertNotEquals(actual,driver.getCurrentUrl());
    }

}
