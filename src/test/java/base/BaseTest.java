package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import utils.DriverFactory;
import utils.ConfigReader;

public class BaseTest {
    public WebDriver driver;
    @BeforeClass
    public void setup(){
        driver=DriverFactory.getDriver(ConfigReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("frontendurl"));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
