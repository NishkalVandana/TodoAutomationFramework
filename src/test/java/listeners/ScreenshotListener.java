package listeners;

import base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.setupReport();
    }

    @Override
    public void onTestStart(ITestResult result){
        String test=result.getMethod().getMethodName();
        ExtentReportManager.test=ExtentReportManager.extent.createTest(test);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.test.pass("Success");
    }
    @Override
    public void  onTestFailure(ITestResult result){
        ExtentReportManager.test.fail(result.getThrowable());
        Object testlistener=result.getInstance();
        BaseTest baseTest=(BaseTest) testlistener;
        WebDriver driver=baseTest.driver;
        TakesScreenshot ts=(TakesScreenshot) driver;
        File sourcefile=ts.getScreenshotAs(OutputType.FILE);
        String testname=result.getMethod().getMethodName();
        String dest="screenshots/"+testname+".png";
        try {
            Files.copy(sourcefile.toPath(), Paths.get(dest));
            ExtentReportManager.test.addScreenCaptureFromPath("../screenshots/" + testname + ".png");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.extent.flush();
    }



}
