package Luma.LumaWeb.Component;

import Luma.LumaWeb.Resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extentReports = ExtentReporterNG.getReportObject();
    ThreadLocal <ExtentTest> extentTestTL = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
        extentTestTL.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestTL.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTestTL.get().log(Status.FAIL, "Test Fail..");
        extentTestTL.get().fail(result.getThrowable());

        try {
            driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
