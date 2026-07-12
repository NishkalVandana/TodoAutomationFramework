package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import utils.DriverFactory;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    @BeforeClass
    public void setup(){
        driver=DriverFactory.getDriver("Chrome");
        driver.manage().window().maximize();
        driver.get("https://todo-react-frontend-one.vercel.app");
    }

    @AfterClass
    public void tearDown() throws InterruptedException{
        Thread.sleep(3000);
        driver.quit();
    }
}
