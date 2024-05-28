package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class extentreport {

    private static ExtentReports extent;
    private static ExtentTest test;
    public static ExtentReports getReporterObject()
    {
        if(extent == null)
        {
            String path = System.getProperty("user.dir") + "/reports/index.html";
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(new File(path)); // Specify report file path
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
//            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//            reporter.config().setReportName("api test");
//            reporter.config().setDocumentTitle("api");
//
//            extent = new ExtentReports();
//            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester","mandar");
        }
        return extent;
    }

    public static ExtentTest getTest()
    {
        return test;
    }

    public static void setTest(ExtentTest test)
    {
        extentreport.test = test;
    }
}
