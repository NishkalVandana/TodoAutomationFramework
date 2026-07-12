package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {
    @Test(priority = 1)
    public void testregister(){
        driver.get("https://todo-react-frontend-one.vercel.app/register");
        RegisterPage registerPage=new RegisterPage(driver);
        String beforeurl= driver.getCurrentUrl();
        registerPage.register("user8","user8@gmail.com","user8@1234");
        String afterurl="https://todo-react-frontend-one.vercel.app/login";
        Assert.assertNotEquals(beforeurl,afterurl);
    }
    @Test(priority = 2)
    public void testExistRegister(){
        driver.get("https://todo-react-frontend-one.vercel.app/register");
        RegisterPage registerPage=new RegisterPage(driver);
        String beforeurl= driver.getCurrentUrl();
        registerPage.duplicateRegister("user1","user1@gmail.com","user1@1234");
        String afterurl=driver.getCurrentUrl();
        Assert.assertEquals(beforeurl,afterurl);
    }
    @Test(priority = 3)
    public void navigate(){
        driver.get("https://todo-react-frontend-one.vercel.app/register");
        String beforeurl= driver.getCurrentUrl();
        RegisterPage registerPage=new RegisterPage(driver);
        registerPage.setNavigatelogin();
        String afterurl=driver.getCurrentUrl();
        Assert.assertNotEquals(beforeurl,afterurl);

    }
}
