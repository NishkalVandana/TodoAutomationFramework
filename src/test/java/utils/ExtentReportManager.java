package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/TestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }
}