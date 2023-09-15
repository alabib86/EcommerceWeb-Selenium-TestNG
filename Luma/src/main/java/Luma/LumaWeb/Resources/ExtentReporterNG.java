package Luma.LumaWeb.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports getReportObject(){

        String path = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().enableOfflineMode(true);
        reporter.config().setReportName("LumaWeb Automation Result");
        reporter.config().setDocumentTitle("Test Result");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Ahmed Labib");

        return extentReports;

    }
}
